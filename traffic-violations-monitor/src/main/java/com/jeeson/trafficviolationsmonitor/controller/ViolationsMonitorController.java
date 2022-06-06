package com.jeeson.trafficviolationsmonitor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jeeson.trafficviolationsmonitor.entity.Vehicle;
import com.jeeson.trafficviolationsmonitor.entity.Violation;
import com.jeeson.trafficviolationsmonitor.exception.VehicleNotFoundException;
import com.jeeson.trafficviolationsmonitor.repo.VehicleRepo;
import com.jeeson.trafficviolationsmonitor.service.Alerter;

@RestController
public class ViolationsMonitorController {

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private Alerter alerter;

	@GetMapping("/checkViolations/{regNo}")
	@Cacheable(value = "vehicle", key = "#p0")
	public List<Violation> checkViolations(@PathVariable String regNo) throws VehicleNotFoundException {
		
		List<Violation> violations = null;
		Vehicle vehicle = null;
		
		Optional<Vehicle> vehicleOpt = vehicleRepo.findById(regNo);
		if(vehicleOpt.isEmpty()) {
			//get vehicle data from govt. portal
			//save data in db
		}
		else {
			vehicle = vehicleOpt.get();
			violations = vehicle.getViolations();
			violations.stream().forEach(System.out::println);
			if (violations.size() > 0) {
				alerter.alert("Vehicle: " + vehicle.getVehicleRegistrationNumber() + " Violations: " + violations.size()
						+ " Total fine: " + violations.stream().mapToInt(p -> p.getPenaltyAmount().intValue()).sum());
			}
		}
		if (vehicle == null)
			throw new VehicleNotFoundException("Vehicle with registration number "+regNo+" not found in our database!");
		return violations;
	}
}
