package one.dio.cloudparking.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.dio.cloudparking.controller.DTO.ParkingCreateDTO;
import one.dio.cloudparking.controller.DTO.ParkingDTO;
import one.dio.cloudparking.controller.mapper.ParkingMapper;
import one.dio.cloudparking.model.Parking;
import one.dio.cloudparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    @Autowired
    private final ParkingService parkingService;
    private ParkingMapper parkingMapper;


    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.parkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find parkings by ID")
    public ResponseEntity<ParkingDTO> findByID(@PathVariable String id) {
        Parking parking = parkingService.findByID(id);
        ParkingDTO result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Create parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}