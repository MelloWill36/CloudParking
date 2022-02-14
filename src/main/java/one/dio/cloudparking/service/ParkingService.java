package one.dio.cloudparking.service;

import one.dio.cloudparking.model.Parking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        var id1 = getUUID();
        var id2 = getUUID();
        Parking parking = new Parking((String) id, "WNM-666", "PR", "PASSAT", "PRETO");
        Parking parking1 = new Parking((String) id1, "DSB-333", "SC", "FUSCA", "BRANCO");
        Parking parking2 = new Parking((String) id2, "NMB-333", "RO", "CHEVETTE", "VERMELHO");
        parkingMap.put((String) id, parking);
        parkingMap.put((String) id1, parking1);
        parkingMap.put((String) id2, parking2);
    }


    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }
    private static Object getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking findByID(String id) {
        return parkingMap.get(id);
    }

    public Parking create (Parking parkingCreate) {
        String uuid = (String) getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }
}
