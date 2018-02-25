package com.olengski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import the domain
import com.olengski.web.domain.Vehicle;
import com.olengski.common.ListWrapper;
import com.olengski.dao.VehicleDAO;

@Service
public class VehicleService {

	@Autowired
	VehicleDAO vehicleDAO;

	public ListWrapper<Vehicle> getVehicles(int pageNumber, int pageSize, String sortByAttribute,
			String sortDirection) {
		return vehicleDAO.getVehicles(pageNumber, pageSize, sortByAttribute, sortDirection);
	}

	public Vehicle getVehicle(String id) {
		return vehicleDAO.getVehicle(id);
	}

	public void saveNewVehicle(Vehicle vehicle) {
		vehicleDAO.saveNewVehicle(vehicle);
	}

	public void saveVehicle(Vehicle vehicle) {
		vehicleDAO.saveVehicle(vehicle);
	}
}
