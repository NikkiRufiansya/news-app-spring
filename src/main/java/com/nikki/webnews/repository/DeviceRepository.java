package com.nikki.webnews.repository;


import com.nikki.webnews.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByNameDevice(String nameDevice);

    @Query("SELECT COUNT(id) FROM Device")
    int getTotalDevice();
}

