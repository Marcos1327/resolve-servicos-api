package com.resolveservicos.controllers;

import com.resolveservicos.entities.dto.SchedulingRecord;
import com.resolveservicos.entities.model.Scheduling;
import com.resolveservicos.services.SchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Scheduling> createScheduling(@RequestBody SchedulingRecord schedulingRecord) {
        Scheduling scheduling = schedulingService.create(schedulingRecord);
        return ResponseEntity.ok(scheduling);
    }
}
