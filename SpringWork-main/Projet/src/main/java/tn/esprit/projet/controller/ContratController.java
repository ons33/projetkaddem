package tn.esprit.projet.controller;


import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.services.IContratService;
import tn.esprit.projet.services.IEtudiantService;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContratController {

    IContratService contratService;
    IEtudiantService etudiantService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getC")
    public List<Contrat> GetC(){

        return  contratService.getAllContrat();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addC")
    public void  addContrat(@RequestBody Contrat C){
        contratService.addContrat(C);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getCbyid/{idContrat}")
    public Contrat getContbyid(@PathVariable("idContrat") Long id){
        return contratService.getContratbyid(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/putC/{idContrat}")
    public void updateC(@PathVariable("idContrat") Long id, @RequestBody Contrat C){

        C.setIdContrat(id);
        contratService.updateContrat(C);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delC/{idContrat}")
    public  void deleteC(@PathVariable("idContrat") Long id){

        contratService.deleteContrat(id);
    }
    @PutMapping("/addCtrTo/{prenom}")
    public void  addContratToStudent(@RequestBody Contrat C,@PathVariable("prenom")String pre){

        contratService.affectContratToEtudiant(C,pre);
    }
    @GetMapping("/getValide/{DateDebutContrat}/{DateFinContrat}")
    public Integer GetValide(@PathVariable("DateDebutContrat") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)Date dateD, @PathVariable("DateFinContrat")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date dateF){

        return  contratService.nbContratsValides(dateD,dateF);
    }


    @GetMapping("/getCA/{DateDebutContrat}/{DateFinContrat}")
    public Float GetCA(@PathVariable("DateDebutContrat") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)Date dateD, @PathVariable("DateFinContrat")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date dateF){

        return  contratService.getChiffreAffaireEntreDeuxDate(dateD,dateF);
    }


//Les Fonctionnalités avancées


    @GetMapping("/calculduree/{idContrat}")
    public String Getduree(@PathVariable("idContrat") Long id){

       String c= contratService.NombreDejourValidation(id);
        return c;
    }

    @GetMapping("/getContratsEtudiant/{idEtudiant}")
    public List<Contrat> getContratsEtudiant(@PathVariable("idEtudiant") Long id){
Etudiant e=  etudiantService.getEtudiantbyid(id);

        return contratService.getContratsDeLEtudiant(e);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getContratsEtudiantByPre/{prenom}")
    public List<Contrat> getContratsEtudiant(@PathVariable("prenom") String prenom){
        Etudiant e=  etudiantService.findEtudiantByprenom(prenom);

        return contratService.getContratsDeLEtudiant(e);
    }

    @PutMapping("/Achiver/{idContrat}")
    public String ArchiverContrat(@PathVariable("idContrat") Long id){
Contrat c=contratService.getContratbyid(id);
      String s= contratService.ArchiverContrat(c);
       return s;
    }
}
