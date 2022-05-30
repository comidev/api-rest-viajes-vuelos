package comidev.dswgrupo5.pasaje;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import comidev.dswgrupo5.cliente.Cliente;
import comidev.dswgrupo5.vuelo.Vuelo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pasaje")
public class Pasaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Vuelo vuelo;
    private LocalDateTime horaCompra;

    public Pasaje() {

    }
}
