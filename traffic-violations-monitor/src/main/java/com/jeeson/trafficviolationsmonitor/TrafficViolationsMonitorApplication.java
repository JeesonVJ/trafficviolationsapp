package com.jeeson.trafficviolationsmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrafficViolationsMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficViolationsMonitorApplication.class, args);
	}

}
