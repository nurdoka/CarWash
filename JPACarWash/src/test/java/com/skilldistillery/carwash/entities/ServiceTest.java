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

class ServiceTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Service service;

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
		service = em.find(Service.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		service = null;
	}

	@Test
	void test_Service_entity_mapping() {
		assertNotNull(service);
		assertEquals("basic", service.getName());
		assertEquals("basic wash", service.getDescription());
		assertEquals(8.99, service.getCost());
	}

	@Test
	void test_Service_entity_has_Store() {
		assertNotNull(service);
		assertEquals("CarWash", service.getStore().getName());
	}

	@Test
	void test_Service_entity_has_Washes_access() {
		assertNotNull(service);
		assertTrue(service.getWashes().size() > 0);
	}
	
	

}
