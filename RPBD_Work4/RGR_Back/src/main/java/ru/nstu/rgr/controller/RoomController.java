package ru.nstu.rgr.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nstu.rgr.model.Room;
import ru.nstu.rgr.service.RoomService;


import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @ApiOperation("Receive all room")
    @GetMapping
    public List<Room> getAllroom() {
        return roomService.findAll();
    }

    @ApiOperation("Create a room")
    @PostMapping
    public Room create(@RequestBody Room room){
        return roomService.save(room);
    }

    @ApiOperation("Edit a room")
    @PutMapping("/{id}")
    public Room edit(@PathVariable Long id, @RequestBody Room room){
        return roomService.edit(id, room);
    }

    @ApiOperation("Delete a room")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        roomService.delete(id);
    }
}
