package org.example.fuel_management_system.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.Request.StationRequest;
import org.example.fuel_management_system.model.*;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public interface StationService {



 Response getAllStations();

 Response findStationById(int id) ;

 boolean doesStationIdExist(String stationName);

boolean isStationIdValid (int dealer_id);

 Response deleteStationById(int id);

 Response saveOrUpdateStation(Station station);

Response findByLoginCode(String loginCode);

 Response updateStation(StationRequest stationRequest,int stationId);


}















