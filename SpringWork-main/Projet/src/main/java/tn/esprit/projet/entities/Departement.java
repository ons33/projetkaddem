package tn.esprit.projet.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "Departement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departement   implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idDepart")
    private Long idDepart; // Clé primaire
    private String nomDepart;
    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private Set<Etudiant> etudiants;

}
