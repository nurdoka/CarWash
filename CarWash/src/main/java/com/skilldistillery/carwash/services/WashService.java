package com.skilldistillery.carwash.services;

import java.util.List;

import com.skilldistillery.carwash.entities.Wash;

public interface WashService {
	public boolean isVehicleBelongsToUser(int vehicleId, String userName);
	public List<Wash> returnWashLogsByVehicleService(int id, String userName);
}