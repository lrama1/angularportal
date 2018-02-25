package com.olengski.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import the domain
import com.olengski.web.domain.Vehicle;
import com.olengski.common.ListWrapper;
import com.olengski.dao.VehicleDAO;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Repository
public class VehicleDAO {

	//private List<Vehicle> allVehicle = new ArrayList<Vehicle>();
	private Map<String, Vehicle> allData = new LinkedHashMap<String, Vehicle>();

	@PostConstruct
	public void init() {

		InputStream is = getClass().getResourceAsStream("/sampledata/Vehicles.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				String data[] = line.split(",");
				Vehicle vehicle = new Vehicle();
				//person.setId(data[0]);
				//person.setFirstName(data[1]);
				//person.setLastName(data[2]);
				vehicle.setVin(data[0]);
				vehicle.setYear(data[1]);
				vehicle.setMake(data[2]);
				vehicle.setModel(data[3]);
				allData.put(data[0], vehicle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vehicle getVehicle(String id) {
		return allData.get(id);
	}

	public void saveNewVehicle(Vehicle vehicle) {
	}

	public void saveVehicle(Vehicle vehicle) {
	}

	public ListWrapper<Vehicle> getVehicles(int page, int pageSize, String sortByAttributeName, String sortDirection) {

		List<Vehicle> allDataList = new ArrayList<Vehicle>(allData.values());
		List<Vehicle> partialPage = new ArrayList<Vehicle>();
		int end = (page * pageSize);
		int start = (end) - pageSize;
		int totalPages = roundUp(allDataList.size(), pageSize);

		if (end > allDataList.size())
			end = allDataList.size();
		if (start < allDataList.size())
			partialPage = allDataList.subList(start, end);
		Long totalRows = new Long(allDataList.size());

		ListWrapper<Vehicle> listWrapper = new ListWrapper<Vehicle>();
		listWrapper.setRows(partialPage);
		listWrapper.setTotalRecords(totalRows.intValue());
		listWrapper.setLastPage(totalPages);
		return listWrapper;
	}

	private int roundUp(int num, int divisor) {
		return (num + divisor - 1) / divisor;
	}
}
