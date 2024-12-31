package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.utilities.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class StationService {

    private final FuelStationRepository fuelStationRepository;
    private VerificationCodeGenerator verificationCodeGenerator;

    public StationService(FuelStationRepository fuelStationRepository, VerificationCodeGenerator verificationCodeGenerator) {
        this.fuelStationRepository = fuelStationRepository;
        this.verificationCodeGenerator = verificationCodeGenerator;
    }

    public boolean doesStationIdExist(int stationId){
        return !fuelStationRepository.existsByStationId(stationId);
    }

    public void saveStation(Station station){

        station.setLoginCode(verificationCodeGenerator.generateVerificationCode());

        fuelStationRepository.save(station);
    }

}
