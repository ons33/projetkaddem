package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Reclamation;

public interface RecRepository extends JpaRepository<Reclamation,Long> {
}
