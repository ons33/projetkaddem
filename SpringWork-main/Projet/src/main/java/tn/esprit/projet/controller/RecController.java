package tn.esprit.projet.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.services.IRecService;

import java.util.List;

@AllArgsConstructor
@RestController

@CrossOrigin(origins = "http://localhost:4200")
public class RecController {
    IRecService recService;

    @GetMapping("/getRec")
    public List<Reclamation> getAllRec(){
        return  recService.getAllRec();
    }
    @PostMapping("/addRec")
    public void  addEqp(@RequestBody Reclamation D){

        recService.addRec(D);
    }


    @PutMapping("/putRec/{IdRec}")
    public void updateRec(@PathVariable("IdRec") Long id, @RequestBody Reclamation C){

        C.setIdRec(id);
        recService.updateRec(C);
    }
    @DeleteMapping("/delRec/{IdRec}")
    public  void deleteRec(@PathVariable("IdRec") Long id){

        recService.deleteRec(id);
    }


    @GetMapping("/getbyidRec/{IdRec}")
    public Reclamation getRecbyid(@PathVariable("IdRec") Long id){
        return recService.getRecbyid(id);
    }




}
