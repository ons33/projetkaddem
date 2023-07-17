package tn.esprit.projet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "Contrat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contrat implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idContrat")
    private Long idContrat; // Clé primaire
    @Temporal (TemporalType.DATE)
    private Date DateDebutContrat;
    @Temporal (TemporalType.DATE)
    private Date DateFinContrat;
    private  boolean archive;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    @ManyToOne()
    Etudiant etudiant;


// Constructeur et accesseurs (getters) et mutateurs (setters)

}
