package ru.nstu.rgr.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nstu.rgr.model.Staff;
import ru.nstu.rgr.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @ApiOperation("Receive all staff")
    @GetMapping
    public List<Staff> getAllpassport() {
        return staffService.findAll();
    }

    @ApiOperation("Create a staff")
    @PostMapping
    public Staff create(@RequestBody Staff staff){
        return staffService.save(staff);
    }

    @ApiOperation("Edit a staff")
    @PutMapping("/{id}")
    public Staff edit(@PathVariable Long id, @RequestBody Staff staff){
        return staffService.edit(id, staff);
    }

    @ApiOperation("Delete a staff")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        staffService.delete(id);
    }
}
