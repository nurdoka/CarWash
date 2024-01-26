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

class StoreTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Store store;

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
		store = em.find(Store.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		store = null;
	}

	@Test
	void test_Store_entity_mapping() {
		assertNotNull(store);
		assertEquals("some@gmail.com", store.getEmail());
	}

	@Test
	void test_Store_has_manager() {
		assertNotNull(store);
		assertEquals("John", store.getUser().getFirstName());
	}

	@Test
	void test_Store_has_Address() {
		assertNotNull(store);
		assertEquals("Denver", store.getAddress().getCity());
	}

	@Test
	void test_Store_has_Comments() {
		assertNotNull(store);
		assertTrue(store.getComments().size() > 0);
	}

	@Test
	void test_Store_has_Services() {
		assertNotNull(store);
		assertTrue(store.getServices().size() > 0);
	}

	@Test
	void test_Store_has_Wash_log_access() {
		assertNotNull(store);
		assertTrue(store.getWashes().size() > 0);
	}
	
	

}
