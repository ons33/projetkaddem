package tn.esprit.projet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "DetailEquipe")

@Getter
@Setter
@NoArgsConstructor


public class DetailEquipe  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdEqp")
    private Long IdEqp;
    private Long salle;
    private String thematique;
    @OneToOne(mappedBy = "detailEquipe1")
    @JsonIgnore
    private  Equipe equipe;


}
