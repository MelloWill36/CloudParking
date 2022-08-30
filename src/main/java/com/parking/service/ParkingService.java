package com.parking.service;

import com.parking.exception.ParkingNotFoundException;
import com.parking.model.Parking;
import com.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking createParking(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void deleteParking(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }


    @Transactional
    public Parking updateParking(String id, Parking parkingCreate) {
        Parking parkingUpdate = findById(id);
        parkingUpdate.setColor(parkingCreate.getColor());
        parkingUpdate.setState(parkingCreate.getState());
        parkingUpdate.setModel(parkingCreate.getModel());
        parkingUpdate.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parkingUpdate);
        return parkingUpdate;
    }

    @Transactional
    public Parking checkout(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckout.getBill(parking));
        parkingRepository.save(parking);

        return parking;
    }
}
