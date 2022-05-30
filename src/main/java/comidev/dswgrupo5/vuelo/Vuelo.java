package comidev.dswgrupo5.vuelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import comidev.dswgrupo5.avion.Avion;
import comidev.dswgrupo5.pasaje.Pasaje;
import comidev.dswgrupo5.viaje.Viaje;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vuelo")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Viaje viaje;

    @ManyToOne()
    private Avion avion;

    @OneToMany(mappedBy = "vuelo")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Pasaje> pasajes;

    private LocalDateTime fecha;

    public Vuelo() {

    }
}
