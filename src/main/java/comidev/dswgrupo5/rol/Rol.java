package comidev.dswgrupo5.rol;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import comidev.dswgrupo5.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolName;

    @ManyToMany(mappedBy = "roles")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Set<Usuario> usuarios;

    public Rol() {

    }
}
