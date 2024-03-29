package com.parking.controller;

import com.parking.controller.dto.ParkingCreateDTO;
import com.parking.controller.dto.ParkingDTO;
import com.parking.controller.mapper.ParkingMapper;
import com.parking.model.Parking;
import com.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> createParking(@RequestBody ParkingCreateDTO createDTO){
        var parkingCreate = parkingMapper.toParkingCreate(createDTO);
        var parking = parkingService.createParking(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> updateParking(@PathVariable String id, @RequestBody ParkingCreateDTO createDTO){
        var parkingCreate = parkingMapper.toParkingCreate(createDTO);
        var parking = parkingService.updateParking(id, parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteParking(@PathVariable String id){
        parkingService.deleteParking(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<ParkingDTO> checkout(@PathVariable String id) {
        Parking parking = parkingService.checkout(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }
}
