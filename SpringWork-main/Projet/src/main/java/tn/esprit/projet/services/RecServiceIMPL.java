package tn.esprit.projet.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.repository.RecRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor

public class RecServiceIMPL implements IRecService{

    RecRepository recRepository;
    @Override
    public List<Reclamation> getAllRec() {
        return recRepository.findAll();
    }

    @Override
    public Reclamation addRec(Reclamation E) {
       String R =badWords(E.getDescription());
       E.setDescription(R);
        return recRepository.save(E);
    }

    @Override
    public Reclamation updateRec(Reclamation E) {
        return recRepository.save(E);
    }

    @Override
    public void deleteRec(long id) {
        recRepository.deleteById(id);

    }

    @Override
    public Reclamation getRecbyid(long id) {
        return recRepository.findById(id).orElse(null);
    }

    @Override
    public String badWords(String val) {
        List<String> badWords = new ArrayList<>();
        badWords.add("test");
        badWords.add("shit");
        badWords.add("merde");


        String[] splited = val.split("\\s+");//split  bel espace
        String newval = "";//where we gonna stock
        for(String word : splited){
            String stars = "";//string for affectings stars
            for(String bad : badWords){
                if(word.toLowerCase().equals(bad.toLowerCase())){//low or uppercase
                    for(int i = 0; i<= word.length()-1; i++){
                        stars += "*";//get the stars
                    }

                    newval += stars + " ";//affect it to newval
                    break;
                }
            }
            if(stars.equals("")){
                newval += word + " ";//concat
            }
        }
        return newval;
    }

}
