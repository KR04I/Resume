package ru.nstu.rgr.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nstu.rgr.model.Visitor;
import ru.nstu.rgr.service.VisitorService;


import java.util.List;

@RestController
@RequestMapping("/visitor")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;

    @ApiOperation("Receive all visitor")
    @GetMapping
    public List<Visitor> getAllvisitor() {
        return visitorService.findAll();
    }

    @ApiOperation("Create a visitor")
    @PostMapping
    public Visitor create(@RequestBody Visitor visitor){
        return visitorService.save(visitor);
    }

    @ApiOperation("Edit a visitor")
    @PutMapping("/{id}")
    public Visitor edit(@PathVariable Long id, @RequestBody Visitor visitor){
        return visitorService.edit(id, visitor);
    }

    @ApiOperation("Delete a visitor")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        visitorService.delete(id);
    }
}
