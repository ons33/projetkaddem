package tn.esprit.projet.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.entities.Option;
import tn.esprit.projet.services.EtudiantServiceIMPL;
import tn.esprit.projet.services.IEtudiantService;

import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EtudiantController {


    IEtudiantService etudiantService;

    @GetMapping("/getone/{idEtudiant}")
    public Etudiant getEtudiantbyid(@PathVariable("idEtudiant") Long id){

        return  etudiantService.getEtudiantbyid(id);
    }
    @GetMapping("/getall")
    public List<Etudiant>GetEtudiant(){

        return  etudiantService.getAllEtudiant();
    }
    @PostMapping("/addetude")
    public void addEtud(@RequestBody Etudiant E){

        etudiantService.addEtudiant(E);
    }
    @PutMapping("/put/{idEtudiant}")
    public void updateEtud(@PathVariable("idEtudiant") Long id, @RequestBody Etudiant E){

        E.setIdEtudiant(id);
        etudiantService.updateEtudiant(E);
    }
    @DeleteMapping("/del/{idEtudiant}")
    public  void deleteEtud(@PathVariable("idEtudiant") Long id){

        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/find/{prenom}")
    public Etudiant findEtd(@PathVariable("prenom") String prenom){
        return etudiantService.findEtudiantByprenom(prenom);
    }
    @PutMapping("/putq/{option}/{idEtudiant}")
    public void updateEtudq(@PathVariable("option")Option option, @PathVariable("idEtudiant") Long id){

       etudiantService.updateEtudiantByOption(option,id);
    }


    @GetMapping("/findQuery/{thematique}")
    public List<Etudiant> findEtdQuery(@PathVariable("thematique") String th){
        return etudiantService.retrieveEtudiantByEquipeThematique(th);
    }

    @PutMapping("/AsignE/{idEtudiant}/{idDepart}")
    public  void assignEtudtoDep(@PathVariable("idEtudiant") Long idEtudiant,@PathVariable("idDepart") Long idDepart){
        etudiantService.AssignEtudtoDepartement(idEtudiant,idDepart);
    }
    @PostMapping("/addeqpC/{idContrat}/{idEquipe}")
    public void  addEtudtoEqpandContrat(@RequestBody Etudiant E,@PathVariable("idContrat") Long idC,@PathVariable("idEquipe") Long idEquipe){
            etudiantService.addAndAssignEtudiantToEquipeAndContract(E,idC,idEquipe);
    }


    @GetMapping("/getDepById/{idDepart}")
    public Set<Etudiant> getDepartmntbyid(@PathVariable("idDepart") Long id){

        return  etudiantService.getEtudiantsByDepartement(id);
    }

    @GetMapping("/getcount")
    public int countEtudiants(){
        return etudiantService.countEtudiant();

    }


    @GetMapping("getcount/{option}")
    public int count(@PathVariable("option") Option Option){
        return     etudiantService.countEtudiantByOptionAndContratsIsNotNull(Option);
    }

    @GetMapping("/getOption/{option}/{option2}/{option3}/{option4}")
    public String pourcentage(@PathVariable("option") Option Option,@PathVariable("option2") Option Option2,@PathVariable("option3") Option Option3,@PathVariable("option4") Option Option4){
        int op,op2,op3,op4,total, p, p2,p3,p4;
        op=etudiantService.countEtudiantByOptionAndContratsIsNotNull(Option);
        op2=etudiantService.countEtudiantByOptionAndContratsIsNotNull(Option2);
        op3=etudiantService.countEtudiantByOptionAndContratsIsNotNull(Option3);
        op4=etudiantService.countEtudiantByOptionAndContratsIsNotNull(Option4);
        total=op+op2+op3+op4;
        p=(op*100)/total;
        p2=(op2*100)/total;
        p3=(op3*100)/total;
        p4=(op4*100)/total;
        return (Option+": "+p+"% " +Option2+": "+p2+"% "+Option3+": "+p3+"% " +Option4+": "+p4+"%");
    }



    @GetMapping("/deptcount/{dept}")
    int countEtudiantByDepartement(@PathVariable("dept") String dept){
        return etudiantService.countEtudiantByDepartementNomDepart(dept);
    }


    //@GetMapping("/verifContrat/{date}")
   // List<Etudiant>findbydate(@PathVariable("date") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) Date date){

       // return   etudiantService.findEtudiantByContratsDateFinContratLessThan(date);

   // }

    @GetMapping("/trieEtudiant/{option}")
    public  List<Etudiant>trieparOption(@PathVariable("option") Option option){
        return etudiantService.findEtudiantByOptionOrderByNom(option);
    }

    @GetMapping("/archived/{val}")
    public List<Etudiant>Archived(@PathVariable("val") Boolean val){
        return etudiantService.findEtudiantByContratsArchiveEquals(val);
    }

    @GetMapping("/trieParNom")
    public List<Etudiant>trieparnom(){
        return etudiantService.findByOrderByNomAsc();
    }

    @PutMapping("/addAssEtoD/{idDepart}")
    public void  addassignEtudtoDepar(@RequestBody Etudiant E,@PathVariable("idDepart") Long idDepart){
        etudiantService.addAndAssignEtudiantToDepart(E,idDepart);
    }
}
