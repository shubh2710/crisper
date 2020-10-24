package com.crisper.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
class ServerApplicationTests {

	@Test
	void contextLoads() {
		final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		System.out.println(now);
	}

}
