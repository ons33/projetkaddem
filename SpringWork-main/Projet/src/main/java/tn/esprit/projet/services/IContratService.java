package tn.esprit.projet.services;

import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Etudiant;

import java.util.Date;
import java.util.List;

public interface IContratService {

    List<Contrat> getAllContrat();
    Contrat addContrat(Contrat C);
    Contrat updateContrat(Contrat C);
    void deleteContrat(long id);
    Contrat getContratbyid(long id);
    Contrat getContratbyprenom(String prenom);

    Contrat affectContratToEtudiant (Contrat ce,String prenomE);


    Integer nbContratsValides(Date startDate, Date endDate);

   float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate);

    String NombreDejourValidation(long id);

    List<Contrat> getContratsDeLEtudiant(Etudiant etudiant);

    public String ArchiverContrat(Contrat c);

}
