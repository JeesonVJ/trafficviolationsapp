package com.jeeson.trafficviolationsmonitor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublishSNSTest {

	@Autowired
	public Alerter alerter;
	
	@Test
	void test() {
		alerter.alert("Hello Jeeson");
	}

}
