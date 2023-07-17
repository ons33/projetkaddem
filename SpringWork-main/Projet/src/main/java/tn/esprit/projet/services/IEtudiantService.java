package tn.esprit.projet.services;

import org.springframework.data.repository.query.Param;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.entities.Option;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IEtudiantService {

    List<Etudiant> getAllEtudiant();
    Etudiant addEtudiant(Etudiant E);
    Etudiant updateEtudiant(Etudiant E);
    void deleteEtudiant(long id);
    Etudiant getEtudiantbyid(long id);

    Etudiant findEtudiantByprenom(String prenom);

    void updateEtudiantByOption(Option op , Long idEtudiant);

    List<Etudiant> retrieveEtudiantByEquipeThematique (  String thematique);
    public  void AssignEtudtoDepartement(Long idEtudiant, Long idDepart);

     Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e,Long idContract,Long idEquipe);

    Set<Etudiant> getEtudiantsByDepartement (Long idDepartement);
    int countEtudiant();

    int countEtudiantByOptionAndContratsIsNotNull(Option Option);

    int countEtudiantByDepartementNomDepart(String dept);

    //List<Etudiant>findEtudiantByContratsDateFinContratLessThan(Date date);

    List<Etudiant>findEtudiantByOptionOrderByNom(Option option);

    List<Etudiant>findEtudiantByContratsArchiveEquals(Boolean val);

    List<Etudiant>findByOrderByNomAsc();

    List<Etudiant>allget();

    Etudiant addAndAssignEtudiantToDepart(Etudiant e,Long idDepart);
}

