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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	
	// MANY TO ONE MAPPING
	@Test
	void test_User_to_Address() {
		assertNotNull(user);
		assertEquals("123 Fake st", user.getAddress().getStreet());
		assertEquals("Denver", user.getAddress().getCity());
	}

	// ONE TO MANY VEHICLE MAPPING
	@Test
	void test_User_to_Vehicle() {
		assertNotNull(user);
		assertTrue(user.getVehicles().size() > 0);
	}

	
	// ONE TO MANY Store MAPPING
	@Test
	void test_User_is_associate_to_Store() {
		assertNotNull(user);
		assertTrue(user.getStores().size() > 0);
	}
	
	

}
