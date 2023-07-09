package ru.nstu.rgr.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nstu.rgr.model.Registration;
import ru.nstu.rgr.service.RegistrationService;


import java.util.List;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @ApiOperation("Receive all registration")
    @GetMapping
    public List<Registration> getAllpassport() {
        return registrationService.findAll();
    }

    @ApiOperation("Create a registration")
    @PostMapping
    public Registration create(@RequestBody Registration registration){
        return registrationService.save(registration);
    }

    @ApiOperation("Edit a registration")
    @PutMapping("/{id}")
    public Registration edit(@PathVariable Long id, @RequestBody Registration registration){
        return registrationService.edit(id, registration);
    }

    @ApiOperation("Delete a registration")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        registrationService.delete(id);
    }
}
