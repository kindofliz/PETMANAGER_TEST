package lv.petmanager.demo.controller;


import lv.petmanager.demo.exception.ResourceNotFoundException;
import lv.petmanager.demo.model.Pet;
import lv.petmanager.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    //get all pets
    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    //create pet rest api
    @PostMapping("/pets")
    public Pet createPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    //get pet by id rest api
    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet doesn't exist with id :" + id));
        return ResponseEntity.ok(pet);
    }

    // update pet rest api
    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet doesn't exist with id  :" + id));

        pet.setPetName(petDetails.getPetName());
        pet.setPetType(petDetails.getPetType());
        pet.setPetBreed(petDetails.getPetBreed());

        Pet updatedEmployee = petRepository.save(pet);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete pet rest api
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePet(@PathVariable Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not exist with id :" + id));

        petRepository.delete(pet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
