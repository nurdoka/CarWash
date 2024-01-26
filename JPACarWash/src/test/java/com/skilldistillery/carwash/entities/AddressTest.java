package com.skilldistillery.carwash.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class AddressTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address address;

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
		address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}

	@Test
	void test_Address_entity_mapping() {
		assertNotNull(address);
		assertEquals("123 Fake st", address.getStreet());
	}

	@Test
	void test_Address_entity_mapping_to_User() {
		assertNotNull(address);
		assertTrue(address.getUsers().size() > 0);
	}

	@Test
	void test_Address_entity_can_access_Store() {
		assertNotNull(address);
		assertEquals("CarWash", address.getStore().getName());
	}
	
	

}
