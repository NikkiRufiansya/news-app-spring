package com.nikki.webnews.controller.api;

import com.nikki.webnews.model.Device;
import com.nikki.webnews.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class RestApiDeviceController {
    private final DeviceRepository deviceRepository;

    @Autowired
    public RestApiDeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Optional<Device> existingDevice = deviceRepository.findByNameDevice(device.getNameDevice());
        if (existingDevice.isPresent()) {
            // Jika perangkat dengan nama yang sama sudah ada, kembalikan perangkat yang sudah ada
            return ResponseEntity.status(HttpStatus.OK).body(existingDevice.get());
        } else {
            // Jika perangkat belum ada, simpan perangkat baru
            Device createdDevice = deviceRepository.save(device);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDevice);
        }
    }

}

