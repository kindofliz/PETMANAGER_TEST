package lv.petmanager.demo.repository;

import lv.petmanager.demo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
