package one.dio.cloudparking.controller;


import one.dio.cloudparking.controller.DTO.ParkingDTO;
import one.dio.cloudparking.model.Parking;
import one.dio.cloudparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private final ParkingService parkingService;
    private ParkingMapper parkingMapper;


    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDTO> findAll(){


        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.parkingDTOList(parkingList);
        return result;

    }

}
