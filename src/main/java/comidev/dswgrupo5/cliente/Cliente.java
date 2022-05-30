package comidev.dswgrupo5.cliente;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import comidev.dswgrupo5.pasaje.Pasaje;
import comidev.dswgrupo5.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cliente")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Usuario usuario;

    private String nombre;

    @Column(unique = true)
    private String DNI;

    private String sexo;

    @OneToMany(mappedBy = "cliente")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Pasaje> pasajes;

    public Cliente(ClienteDTO clienteDTO) {
        this.nombre = clienteDTO.getNombre();
        this.DNI = clienteDTO.getDNI();
    }

    public Cliente() {

    }
}
