package com.jeeson.trafficviolationsmonitor.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "vehicle")
public class Vehicle {
	
	@Id
	private String vehicleRegistrationNumber;
	private String color;
	private String make;
	private Date dateOfPurchase;
	private List<Violation> violations;
	

}
