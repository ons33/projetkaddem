package tn.esprit.projet.services;

import tn.esprit.projet.entities.Reclamation;

import java.util.List;

public interface IRecServiceee {
    List<Reclamation> getAllRec();
    Reclamation addRec(Reclamation E);
    Reclamation updateRec(Reclamation E);
    void deleteRec(long id);
    Reclamation getRecbyid(long id);
    public  String badWords(String v);


}
