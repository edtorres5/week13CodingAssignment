package pet.store.controller1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore (@RequestBody PetStoreData petStoreData) {
		log.info("Creating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);

	}
	
	
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId,
		@RequestBody PetStoreData petStoreData) {
	  petStoreData.setPetStoreId(petStoreId);
	  log.info("Updating pet store {}", petStoreData);
	  return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId,
		@RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Adding employee {} to pet store with ID={}", petStoreEmployee,
			petStoreId);
		
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}
	
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer addCustomerToPetStore(@PathVariable Long petStoreId,
		@RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Adding customer {} to pet store with ID={}", petStoreCustomer,
			petStoreId);
		
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
}
	
	@GetMapping
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieving all pet stores");
		return petStoreService.retrieveAllPetStores();
	}
	
	@GetMapping("/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving pet store with ID={}", petStoreId);
		return petStoreService.retrievePetStoreById(petStoreId);
	}
	
	@DeleteMapping("/petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleteing pet store with ID={}", petStoreId);
	
	petStoreService.deletePetStoreById(petStoreId);
	
	return Map.of("message", "Pet store with ID=" + petStoreId + "deleted.");

	}
}
	

