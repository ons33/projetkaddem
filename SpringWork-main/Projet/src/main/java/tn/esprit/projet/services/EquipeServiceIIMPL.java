package tn.esprit.projet.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Niveau;
import tn.esprit.projet.repository.DetailEquipeRepository;
import tn.esprit.projet.repository.EquipeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EquipeServiceIIMPL implements  IEquipeService{



    EquipeRepository equipeRepository;
    DetailEquipeRepository detailEquipeRepository;

    @Override
    public List<Equipe> getAlleqp() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe addeqp(Equipe E) {
        return equipeRepository.save(E);
    }

    @Override
    public Equipe updateeqp(Equipe E) {
        return equipeRepository.save(E);
    }

    @Override
    public void deleteeqp(long id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public Equipe geteqbyid(long id) {
        return equipeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Equipe> findEquipesByEtudiantsIdEtudiant(Long idEtudiant) {
        return equipeRepository.findEquipesByEtudiantsIdEtudiant(idEtudiant);
    }

    @Override
    public List<Equipe>findEquipeByDetailEquipe1ThematiqueLike(String th) {
        return equipeRepository.findEquipeByDetailEquipe1ThematiqueLike(th);
    }

    @Override
    public List<Equipe> findEquipesByEtudiantsIdEtudiantAndDetailEquipe1ThematiqueNotNull(Long id) {
        return equipeRepository.findEquipesByEtudiantsIdEtudiantAndDetailEquipe1ThematiqueNotNull(id);
    }

    @Override
    public List<Equipe> findEquipesByEtudiantsIdEtudiantAndEtudiantsDepartementIdDepart(Long id1, Long id2) {
        return equipeRepository.findEquipesByEtudiantsIdEtudiantAndEtudiantsDepartementIdDepart(id1, id2);
    }

    @Override
    public void deleteEquipeByNiveau(Niveau niveau) {
        equipeRepository.deleteEquipeByNiveau(niveau);
    }

    @Override
    public List<Equipe> retriveEquipeByNiveauAndThematique(Niveau niveau, String thematique) {
        return equipeRepository.retriveEquipeByNiveauAndThematique(niveau,thematique);
    }



    public void findnbrEqpbyniveau(){
        List<Equipe> eq =equipeRepository.findAll();
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        map.put("JUNIOR",0);
        map.put("SENIOR",0);
        map.put("EXPERT",0);
        for(int i=0;i<eq.size();i++){
            Equipe eqp= eq.get(i);
            map.put(eqp.getNiveau().toString(),map.get(eqp.getNiveau().toString())+1);
        }
        for(Map.Entry m : map.entrySet()){
            System.out.println("Il y a "+m.getValue()+" "+m.getKey());
        }

    }

    @Override
    public Equipe AddAndAssigntoDetail(Equipe e, Long idDet) {
        DetailEquipe detailEquipe=detailEquipeRepository.findById(idDet).orElse(null);
        e.setDetailEquipe1(detailEquipe);
        equipeRepository.save(e);
        return  e;
    }

    @Override
    public DetailEquipe Lawej3alID(Long idE) {
        return  equipeRepository.findById(idE).orElse(null).getDetailEquipe1();
    }

    @Override
    public List<DetailEquipe> findIFnotAssigned() {
        List<DetailEquipe> detailEquipes= detailEquipeRepository.findAll();
        List<DetailEquipe> detailEquipesNOTASS= new ArrayList<>();

        for(int i =0;i<detailEquipes.size();i++){
            if( detailEquipes.get(i).getEquipe() == null    ){
                detailEquipesNOTASS.add(detailEquipes.get(i));
            }
        }
        return  detailEquipesNOTASS;
    }

    @Override
    public void ListEtddansEquipe() {
        List<Equipe> eq =equipeRepository.findAll();
        for(int i=0;i<eq.size();i++){
            Equipe eqp= eq.get(i);
            System.out.println("----->"+eqp.getEtudiants());
        }
    }
}
