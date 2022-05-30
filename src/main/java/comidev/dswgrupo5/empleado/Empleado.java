package comidev.dswgrupo5.empleado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String rol;

    public Empleado() {

    }

    public Empleado(EmpleadoDTO empleadoDTO, String rol) {
        this.nombre = empleadoDTO.getNombre();
        this.rol = rol;
    }
}
