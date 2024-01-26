package com.skilldistillery.carwash.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class WashTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Wash wash;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPACarWash");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		wash = em.find(Wash.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		wash = null;
	}

	@Test
	void test_Wash_entity_mapping() {
		assertNotNull(wash);
		assertEquals(1, wash.getId());
	}

	@Test
	void test_Wash_has_vehicle_access() {
		assertNotNull(wash);
		assertEquals("F150", wash.getVehicle().getModel());
	}

	@Test
	void test_Wash_has_Store_access() {
		assertNotNull(wash);
		assertEquals("123 Fake st", wash.getStore().getAddress().getStreet());
	}

	@Test
	void test_Wash_has_Service_access() {
		assertNotNull(wash);
		assertEquals("basic wash", wash.getService().getDescription());
	}
	
	

}
