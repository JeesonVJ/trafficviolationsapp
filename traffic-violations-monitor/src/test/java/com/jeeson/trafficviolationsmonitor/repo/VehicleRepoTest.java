package com.jeeson.trafficviolationsmonitor.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jeeson.trafficviolationsmonitor.entity.Vehicle;
import com.jeeson.trafficviolationsmonitor.entity.Violation;
import com.jeeson.trafficviolationsmonitor.service.Alerter;

@SpringBootTest
class VehicleRepoTest {

	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Autowired
	private Alerter alerter;
	
	//@Test
	void addVehicle() {
		Vehicle vehicle = Vehicle.builder()
				.color("Green")
				.dateOfPurchase(new Date())
				.make("Honda")
				.vehicleRegistrationNumber("KA01007")
				.build();
		vehicleRepo.save(vehicle);
	}
	
//	@Test
	void addViolation() {
		
		Vehicle vehicle = vehicleRepo.findById("KA01007").get();
		vehicle.getViolations().add(Violation.builder()
				.dateOfViolation(new Date())
				.penaltyAmount(new BigDecimal(1000))
				.violationName("Signal Jump")
				.build());
		vehicleRepo.save(vehicle);
	}
	
	@Test
	void getViolations() {
		Vehicle vehicle = vehicleRepo.findById("KA01007").get();
		List<Violation> violations = vehicle.getViolations();
		violations.stream().forEach(System.out::println);
		if(violations.size()>0) {
			alerter.alert("Vehicle: "+vehicle.getVehicleRegistrationNumber()
				+" Violations: "+violations.size()
				+" Total fine: "+violations.stream().mapToInt(p->p.getPenaltyAmount().intValue()).sum());
		}
	}

}
