package tn.esprit.projet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "Reclamation")
@Getter
@Setter

public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRec")
    private Long idRec; // Clé primaire
    @Enumerated(EnumType.STRING)
    private TypeRec typeReclamation;
    private String description;
    private String etatRec="Not Treated";



}
