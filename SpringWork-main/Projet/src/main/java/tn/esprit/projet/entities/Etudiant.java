package tn.esprit.projet.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "Etudiant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Etudiant implements Serializable {
    //many to many uni wala bi +1 to many(uni) -->dima iji table association

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idEtudiant")
    private Long idEtudiant; // Clé primaire

    @NonNull
    private String prenom;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Option option;
    @ManyToOne
    Departement departement;
    @OneToMany(mappedBy = "etudiant",fetch =FetchType.EAGER)
    @JsonIgnore
    private Set<Contrat> contrats;
    @ManyToMany(mappedBy = "etudiants")
    @JsonIgnore
    private Set <Equipe> equipes;




// Constructeur et accesseurs (getters) et mutateurs (setters)

}
