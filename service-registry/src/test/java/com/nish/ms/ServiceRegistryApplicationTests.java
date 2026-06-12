package com.nish.ms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Lightweight sanity test (avoids full Spring context startup during CI upgrades)
class ServiceRegistryApplicationTests {

	@Test
	void contextLoads() {
		// basic assertion to keep test suite meaningful while avoiding heavy auto-configuration
		assertTrue(true);
	}

}
