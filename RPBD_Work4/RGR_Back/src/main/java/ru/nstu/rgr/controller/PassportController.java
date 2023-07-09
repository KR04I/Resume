package ru.nstu.rgr.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nstu.rgr.model.Passport;
import ru.nstu.rgr.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passport")
@RequiredArgsConstructor

public class PassportController {

    private final PassportService passportService;

    @ApiOperation("Receive all passport")
    @GetMapping
    public List<Passport> getAllpassport() {
        return passportService.findAll();
    }

    @ApiOperation("Create a passport")
    @PostMapping
    public Passport create(@RequestBody Passport passport){
        return passportService.save(passport);
    }

    @ApiOperation("Edit a passport")
    @PutMapping("/{id}")
    public Passport edit(@PathVariable Long id, @RequestBody Passport passport){
        return passportService.edit(id, passport);
    }

    @ApiOperation("Delete a passport")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        passportService.delete(id);
    }
}
