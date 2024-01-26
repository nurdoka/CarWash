package com.skilldistillery.carwash.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class StoreRatingTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private StoreRating storeRating;

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
//		storeRating = em.find(StoreRating.class, 1);

		StoreRatingId uSRID = new StoreRatingId(1, 1);
		storeRating = em.find(StoreRating.class, uSRID);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		storeRating = null;
	}

	@Test
	void test_StoreRating_entity_mapping() {
		assertNotNull(storeRating);
		assertEquals(5, storeRating.getRating());
	}
	
	

}
