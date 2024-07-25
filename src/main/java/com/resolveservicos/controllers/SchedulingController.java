package com.resolveservicos.controllers;

import com.resolveservicos.entities.dto.SchedulingRecord;
import com.resolveservicos.entities.model.Scheduling;
import com.resolveservicos.services.SchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/schedulings")
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

    @PatchMapping("/update/{schedulingId}")
    public ResponseEntity<Scheduling> updateScheduling(@PathVariable Long schedulingId, @RequestBody SchedulingRecord schedulingRecord) {
        Scheduling scheduling = schedulingService.update(schedulingId, schedulingRecord);
        return ResponseEntity.ok(scheduling);
    }

    @PutMapping("/cancel/{schedulingId}")
    public ResponseEntity<Void> cancelScheduling(@PathVariable Long schedulingId) {
        schedulingService.cancel(schedulingId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/conclude/{schedulingId}")
    public ResponseEntity<Scheduling> concludeScheduling(@PathVariable Long schedulingId) {
        Scheduling scheduling = schedulingService.conclude(schedulingId);
        return ResponseEntity.ok(scheduling);
    }

    @GetMapping
    public ResponseEntity<List<Scheduling>> getAllSchedulings() {
        List<Scheduling> appointments = schedulingService.getAllSchedulings();
        return ResponseEntity.ok(appointments);
    }


}
