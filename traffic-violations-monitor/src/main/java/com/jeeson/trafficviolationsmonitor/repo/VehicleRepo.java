package com.jeeson.trafficviolationsmonitor.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jeeson.trafficviolationsmonitor.entity.Vehicle;

public interface VehicleRepo extends MongoRepository<Vehicle, String> {

}
