package com.skilldistillery.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.Vehicle;
import com.skilldistillery.carwash.entities.Wash;
import com.skilldistillery.carwash.repositories.VehicleRepository;
import com.skilldistillery.carwash.repositories.WashRepository;

@Service
public class WashServiceImpl implements WashService {

	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private WashRepository washRepo;
	
	@Override
	public boolean isVehicleBelongsToUser(int vehicleId, String userName) {
		Vehicle vehicle = vehicleRepo.findByIdAndUser_Username(vehicleId, userName);
        return vehicle != null;
		
	}

	 @Override
	    public List<Wash> returnWashLogsByVehicleService(int vehicleId, String userName) {
	        // Retrieve wash logs based on the vehicleId and the userName
	        return washRepo.findByVehicleIdAndUsername(vehicleId, userName);
	    }



	    // Other service methods...
}
