package com.kenm.spring.farmservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.kenm.spring.farmservice.entity.Farm;
import com.kenm.spring.farmservice.repository.FarmRepository;

@DataJpaTest
class JPAUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	FarmRepository farmRepo;

	@Test
	public void should_find_no_farms_in_empty_repository() {
		Iterable<Farm> testFarms = farmRepo.findAll();
		assertThat(testFarms).isEmpty();
	}

	@Test
	public void should_create_and_save__farm_in_repository() {

		// The repository should be empty
		assertEquals(0, farmRepo.count());

		Farm testFarm = Farm.builder().farmName("Test Farm").farmOwner("Test Owner").farmLocation("Test Location")
				.farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();

		entityManager.persist(testFarm);

		assertEquals(1, farmRepo.count());

	}

	@Test
	public void should_store_a_farm() {

		Farm testFarm = Farm.builder().farmName("Test Farm").farmOwner("Test Owner").farmLocation("Test Location")
				.farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();

		Farm farm = farmRepo.save(testFarm);

		assertThat(farm).hasFieldOrPropertyWithValue("farmName", "Test Farm");
		assertThat(farm).hasFieldOrPropertyWithValue("farmOwner", "Test Owner");
		assertThat(farm).hasFieldOrPropertyWithValue("farmLocation", "Test Location");
		assertThat(farm).hasFieldOrPropertyWithValue("farmType", "Crop");
		assertThat(farm).hasFieldOrPropertyWithValue("farmStatus", "Active");
		assertThat(farm).hasFieldOrPropertyWithValue("farmSize", 100);
		assertThat(farm).hasFieldOrPropertyWithValue("pricePerAcre", 10.0);
	}

	@Test
	public void should_find_farm_by_id() {
		Farm farm1 = Farm.builder().farmName("Farm1").farmOwner("Farm1 Owner").farmLocation("Farm1 Location")
				.farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm1);

		Farm farm2 = Farm.builder().farmName("Farm2").farmOwner("Farm2 Owner").farmLocation("Farm2 Location")
				.farmType("Livestock").farmStatus("Inactive").farmSize(200).pricePerAcre(20.0).build();
		entityManager.persist(farm2);

		Farm foundFarm = farmRepo.findById(farm2.getId()).orElse(null);
		assertEquals(farm2, foundFarm);

	}

	@Test
	public void should_update_farm_by_id() {
		// Save a farm
		Farm farm = Farm.builder().farmName("Old Farm Name").farmOwner("Old Owner").farmLocation("Old Location")
				.farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm);

		// Retrieve the farm
		Farm savedFarm = farmRepo.findAll().iterator().next();
		assertEquals("Old Farm Name", savedFarm.getFarmName());

		// Update the farm
		savedFarm.setFarmName("New Farm Name");
		entityManager.persist(savedFarm);

		// Verify the update
		Farm updatedFarm = farmRepo.findById(savedFarm.getId()).orElse(null);
		assertEquals("New Farm Name", updatedFarm.getFarmName());
	}

	@Test
	public void should_find_all_farms() {
		// Save some farms
		Farm farm1 = Farm.builder().farmName("Farm1").farmOwner("Farm1 Owner").farmLocation("Farm1 Location")
				.farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm1);

		Farm farm2 = Farm.builder().farmName("Farm2").farmOwner("Farm2 Owner").farmLocation("Farm2 Location")
				.farmType("Livestock").farmStatus("Inactive").farmSize(200).pricePerAcre(20.0).build();
		entityManager.persist(farm2);

		Farm farm3 = Farm.builder().farmName("Farm3").farmOwner("Farm3 Owner").farmLocation("Farm3 Location")
				.farmType("Mixed").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm3);

		// Retrieve all farms
		Iterable<Farm> farms = farmRepo.findAll();

		// Check if all farms are retrieved
		assertThat(farms).hasSize(3).contains(farm1, farm2, farm3);
	}

	@Test
	public void should_delete_farm_by_id() {
		// Save some farms
		Farm farm1 = Farm.builder().farmName("Farm1").farmOwner("Farm1 Owner").farmLocation("Farm1 Location")
				.farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm1);

		Farm farm2 = Farm.builder().farmName("Farm2").farmOwner("Farm2 Owner").farmLocation("Farm2 Location")
				.farmType("Livestock").farmStatus("Inactive").farmSize(200).pricePerAcre(20.0).build();
		entityManager.persist(farm2);

		Farm farm3 = Farm.builder().farmName("Farm3").farmOwner("Farm3 Owner").farmLocation("Farm3 Location")
				.farmType("Mixed").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm3);
		
		// Delete Farm
		farmRepo.deleteById(farm2.getId());

		// Retrieve all farms
		Iterable<Farm> farms = farmRepo.findAll();

		// Check if all farms are retrieved
		assertThat(farms).hasSize(2).contains(farm1, farm3);
	}

	@Test
	public void should_delete_all_farms() {
		// Save some farms
		Farm farm1 = Farm.builder().farmName("Farm1").farmOwner("Farm1 Owner").farmLocation("Farm1 Location")
				.farmType("Crop").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm1);

		Farm farm2 = Farm.builder().farmName("Farm2").farmOwner("Farm2 Owner").farmLocation("Farm2 Location")
				.farmType("Livestock").farmStatus("Inactive").farmSize(200).pricePerAcre(20.0).build();
		entityManager.persist(farm2);

		Farm farm3 = Farm.builder().farmName("Farm3").farmOwner("Farm3 Owner").farmLocation("Farm3 Location")
				.farmType("Mixed").farmStatus("Active").farmSize(100).pricePerAcre(10.0).build();
		entityManager.persist(farm3);
		
		// Delete Farm
		farmRepo.deleteAll();

		// Retrieve all farms
		Iterable<Farm> farms = farmRepo.findAll();

		// Check if all farms are deleted
		assertThat(farms).isEmpty();
	}

}
