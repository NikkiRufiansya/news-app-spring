package com.nikki.webnews.controller.api;

import com.nikki.webnews.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/visitor")
public class RestApiVisitorController {
    @Autowired
    private VisitorService visitorService;

    @Autowired
    public RestApiVisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }



    @GetMapping
    public ResponseEntity<Integer> getVisitorCountByDate() {
        LocalDate today = LocalDate.now();
        int visitorCount = visitorService.getVisitorCountByDate(today);
        return ResponseEntity.ok(visitorCount);
    }

    @PostMapping
    public ResponseEntity<Void> incrementVisitorCount() {
        LocalDate today = LocalDate.now();
        visitorService.incrementVisitorCount(today);
        return ResponseEntity.ok().build();
    }
}

