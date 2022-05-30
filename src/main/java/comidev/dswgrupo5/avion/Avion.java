package comidev.dswgrupo5.avion;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import comidev.dswgrupo5.empleado.Empleado;
import comidev.dswgrupo5.vuelo.Vuelo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String placa;
    @ManyToOne
    private Empleado piloto;
    @ManyToOne
    private Empleado copiloto;
    @OneToMany(mappedBy = "avion")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Vuelo> vuelos;

    public Avion() {

    }

    public Avion(Long id, String placa, Empleado piloto, Empleado copiloto, List<Vuelo> vuelos) {
        this.id = id;
        this.placa = placa;
        this.piloto = piloto;
        this.copiloto = copiloto;
        this.vuelos = vuelos;
    }
}
