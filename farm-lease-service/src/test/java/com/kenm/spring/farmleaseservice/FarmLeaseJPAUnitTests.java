package com.kenm.spring.farmleaseservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.kenm.spring.farmleaseservice.entity.FarmLease;
import com.kenm.spring.farmleaseservice.repository.FarmLeaseRepository;

@DataJpaTest
class FarmLeaseJPAUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FarmLeaseRepository leaseRepo;


	@Test
	void testSaveFarmLease() {
		// Create a FarmLease entity
		FarmLease farmLease = FarmLease.builder().farmId(1L).leaseTenant("John Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();

		// Save the entity to the database
		FarmLease savedFarmLease = leaseRepo.save(farmLease);

		// Verify that the entity was saved
		assertThat(savedFarmLease.getLeaseId()).isNotNull();
		assertThat(savedFarmLease.getFarmId()).isEqualTo(1L);
		assertThat(savedFarmLease.getLeaseTenant()).isEqualTo("John Doe");

	}

	@Test
	void testFindFarmLeaseById() {
		// Create and persist a FarmLease entity
		FarmLease farmLease1 = FarmLease.builder().farmId(1L).leaseTenant("John Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(farmLease1);

		FarmLease farmLease2 = FarmLease.builder().farmId(1L).leaseTenant("Jane Doe").leaseType("Long-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("5 year").leaseStart("2024-04-22")
				.leaseEnd("2029-04-22").build();
		entityManager.persist(farmLease2);

		FarmLease farmLease3 = FarmLease.builder().farmId(1L).leaseTenant("Brian Smith").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(200.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(farmLease3);

		// Find the farm lease by ID
		Optional<FarmLease> optionalFarmLease = leaseRepo.findById(farmLease2.getLeaseId());

		// Verify that the farm lease was found
		assertTrue(optionalFarmLease.isPresent());
		FarmLease foundFarmLease = optionalFarmLease.get();
		assertEquals(foundFarmLease.getLeaseId(), farmLease2.getLeaseId());

	}

	@Test
	void testFindAllLeases() {

		FarmLease lease1 = FarmLease.builder().farmId(1L).leaseTenant("John Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease1);

		FarmLease lease2 = FarmLease.builder().farmId(2L).leaseTenant("Jane Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease2);

		FarmLease lease3 = FarmLease.builder().farmId(3L).leaseTenant("Brian Smithe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease3);


		Long leases = leaseRepo.count();

		// Check if all leases are deleted
		assertThat(leases).isPositive();

	}
	
	@Test
	void testUpdateFarmLease() {
		// Create and persist a FarmLease entity
		FarmLease farmLease = FarmLease.builder().farmId(1L).leaseTenant("John Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(farmLease);

		// Modify some properties of the farm lease
		farmLease.setLeaseTenant("Jane Doe");

		// Update the farm lease
		FarmLease updatedFarmLease = leaseRepo.save(farmLease);

		// Verify that the farm lease was updated
		assertEquals(updatedFarmLease.getLeaseTenant(), "Jane Doe");

	}

	@Test
	void testDeleteFarmLease() {
		// Create and persist a FarmLease entity
		FarmLease farmLease = FarmLease.builder().farmId(1L).leaseTenant("John Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(farmLease);

		// Delete the farm lease
		leaseRepo.delete(farmLease);

		// Verify that the farm lease was deleted
		Optional<FarmLease> optionalFarmLease = leaseRepo.findById(farmLease.getLeaseId());
		assertTrue(optionalFarmLease.isEmpty());
	}

	@Test
	public void testDeleteFarmLeaseById() {
		// Save some leases
		FarmLease lease1 = FarmLease.builder().farmId(11L).leaseTenant("John Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease1);

		FarmLease lease2 = FarmLease.builder().farmId(12L).leaseTenant("Jane Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease2);

		FarmLease lease3 = FarmLease.builder().farmId(13L).leaseTenant("Brian Smithe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease3);

		// Delete Farm
		leaseRepo.deleteById(lease3.getLeaseId());

		// Retrieve all leases
		Optional<FarmLease> optionalFarmLease = leaseRepo.findById(lease3.getLeaseId());
		assertTrue(optionalFarmLease.isEmpty());

       // Long leasesCount = leaseRepo.count();
       // assertThat(leasesCount).isEqualTo(2L);
	}

	@Test
	public void testDeleteAll() {
		// Save some leases
		FarmLease lease1 = FarmLease.builder().farmId(1001L).leaseTenant("John Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease1);

		FarmLease lease2 = FarmLease.builder().farmId(1002L).leaseTenant("Jane Doe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease2);

		FarmLease lease3 = FarmLease.builder().farmId(1003L).leaseTenant("Brian Smithe").leaseType("short-term lease")
				.leaseStatus("active").leaseRent(1000.0).leaseDuration("1 year").leaseStart("2024-04-22")
				.leaseEnd("2025-04-22").build();
		entityManager.persist(lease3);

		// Delete Farm
		leaseRepo.deleteAll();

		// Retrieve all leases
		Long leases = leaseRepo.count();

		// Check if all leases are deleted
		assertThat(leases).isNotPositive();
	}

}
