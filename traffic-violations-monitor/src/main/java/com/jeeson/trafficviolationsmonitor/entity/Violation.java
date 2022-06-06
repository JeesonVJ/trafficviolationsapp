package com.jeeson.trafficviolationsmonitor.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Violation implements Serializable {

	private String violationName;
	private Date dateOfViolation;
	private BigDecimal penaltyAmount;	
}
