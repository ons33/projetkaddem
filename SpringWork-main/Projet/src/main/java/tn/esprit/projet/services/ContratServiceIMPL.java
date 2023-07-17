package tn.esprit.projet.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.repository.ContratRepository;
import tn.esprit.projet.repository.EtudiantRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ContratServiceIMPL implements  IContratService{


    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> getAllContrat() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat addContrat(Contrat E) {
        return contratRepository.save(E);
    }

    @Override
    public Contrat updateContrat(Contrat E) {
        return contratRepository.save(E);
    }

    @Override
    public void deleteContrat(long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public Contrat getContratbyid(long id) {

        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public Contrat getContratbyprenom(String prenom) {
        return contratRepository.findContratByEtudiantPrenom(prenom);
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String prenomE) {
        //Contrat contrat = this.contratRepository.findById(ce.getIdContrat()).orElse(null);
        Etudiant etudiant;
       if ((etudiant=etudiantRepository.findEtudiantByprenom(prenomE)) ==null){

           System.out.println("sans prenom");
           contratRepository.save(ce);
       }
else{
            if (etudiant.getContrats().size() < 5) {

                ce.setEtudiant(etudiant);
                contratRepository.save(ce);
            } else {
                System.out.println("Cannot Affect anymore");
            }
        }
        return ce;
    }

    @Override
    public Integer nbContratsValides(Date startDate, Date endDate) {
        int j=0 ;

       List< Contrat> contrat= contratRepository.findAll();

       for(int i=0;i<contrat.size();i++){
           Contrat ct=contrat.get(i);


               if(ct.isArchive()==false){
                   j++;
                   System.out.println("famaa "+j+"contrat dispo");

               }

       }
       return j;
    }

    @Override
    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
        float CA=0;
        int nbOfMonths=1;

        List< Contrat> contrat= contratRepository.findAll();
        for(int i=0;i<contrat.size();i++){
        Contrat ct=contrat.get(i);
        int dd=Integer.parseInt(ct.getDateDebutContrat().toString().substring(5,7));
        int df=Integer.parseInt(ct.getDateFinContrat().toString().substring(5,7));

        System.out.println(dd);
           if((nbOfMonths*=(df-dd))==0){
                    nbOfMonths=1;
                }else{
                    nbOfMonths=(df-dd);
                }
                    if(ct.isArchive()==false){

                        System.out.println("*******"+nbOfMonths);

                        if(ct.getSpecialite().toString()=="IA"){
                            CA+=nbOfMonths*300;

                        }
                        else if(ct.getSpecialite().toString()=="RESEAUX"){
                            CA+=nbOfMonths*350;
                        }
                        else if(ct.getSpecialite().toString()=="CLOUD"){
                            CA+=nbOfMonths*400;
                        }
                        else if(ct.getSpecialite().toString()=="SECURITE"){
                            CA+=nbOfMonths*450;
                        }

                    }

        }
        return CA;
    }

    @Override
    public String NombreDejourValidation(long id) {
        Contrat contrat = this.contratRepository.findById(id).orElse(null);
//annee
        int dda=Integer.parseInt(contrat.getDateDebutContrat().toString().substring(0,4));
        int dfa=Integer.parseInt(contrat.getDateFinContrat().toString().substring(0,4));
int diffa =(dfa-dda)*365;
//mois
        int ddm=Integer.parseInt(contrat.getDateDebutContrat().toString().substring(5,7));
        int dfm=Integer.parseInt(contrat.getDateFinContrat().toString().substring(5,7));
int diffm = (dfm-ddm)*30;
//jours
        int ddj=Integer.parseInt(contrat.getDateDebutContrat().toString().substring(8,9));
        int dfj=Integer.parseInt(contrat.getDateFinContrat().toString().substring(8,9));
int diffj = dfj-ddj;

String nombre ="Le nombre de jours de validation de ce contrat est "+(diffj+diffm+diffa)+" jours";
        System.out.println( diffj+diffm+diffa);
return nombre;
    }

    @Override
    public List<Contrat> getContratsDeLEtudiant(Etudiant e) {
        List<Contrat> contrats=contratRepository.findContratByEtudiant(e);
       return contrats;

    }



    public String ArchiverContrat(Contrat c) {
        Date d= c.getDateFinContrat();

        LocalDate localDate = LocalDate.now();
        Date ld=convertToDateUsingDate(localDate);

      //  System.out.println("hedhy l date taa lyoum"+ld);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

       int b= sdf.format(d).compareTo(sdf.format(ld));

    //   System.out.println("hedhy l b"+b);
        if ( b== -2 ){
            c.setArchive(true);
            contratRepository.save(c);
            return "Contrat archivée avec succés";
        }
        else return "le contrat est encore valide";



    }


    public static Date convertToDateUsingDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }


}
