package ru.nstu.rgr.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nstu.rgr.model.Visitor;
import ru.nstu.rgr.model.WorkingHours;
import ru.nstu.rgr.service.WorkingHoursService;

import java.util.List;

@RestController
@RequestMapping("/workinghours")
@RequiredArgsConstructor
public class WorkingHoursController {

    private final WorkingHoursService workinghoursService;

    @ApiOperation("Receive all workinghours")
    @GetMapping
    public List<WorkingHours> getAllworkinghours() {
        return workinghoursService.findAll();
    }
    @ApiOperation("Create a workinghours")
    @PostMapping
    public WorkingHours create(@RequestBody WorkingHours workinghours){
        return workinghoursService.save(workinghours);
    }
    @ApiOperation("Edit a workinghours")
    @PutMapping("/{id}")
    public WorkingHours edit(@PathVariable Long id, @RequestBody WorkingHours workinghours){
        return workinghoursService.edit(id, workinghours);
    }
    @ApiOperation("Delete a workinghours")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        workinghoursService.delete(id);
    }
}
