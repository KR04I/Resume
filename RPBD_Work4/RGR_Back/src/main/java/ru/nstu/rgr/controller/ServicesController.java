package ru.nstu.rgr.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nstu.rgr.model.Services;
import ru.nstu.rgr.service.ServicesService;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {
    private final ServicesService servicesService;

    @ApiOperation("Receive all services")
    @GetMapping
    public List<Services> getAllservices() {
        return servicesService.findAll();
    }

    @ApiOperation("Create a services")
    @PostMapping
    public Services create(@RequestBody Services services){
        return servicesService.save(services);
    }

    @ApiOperation("Edit a services")
    @PutMapping("/{id}")
    public Services edit(@PathVariable Long id, @RequestBody Services services){
        return servicesService.edit(id, services);
    }

    @ApiOperation("Delete a services")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        servicesService.delete(id);
    }
}
