package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Etudiant;

import java.util.List;


public interface ContratRepository extends JpaRepository<Contrat,Long> {

    List<Contrat> findContratByEtudiant(Etudiant etudiant) ;

    Contrat findContratByEtudiantPrenom(String prenom);
}
