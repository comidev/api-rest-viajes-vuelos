package comidev.dswgrupo5.empleado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Long> {
    List<Empleado> findByRol(String rol);
}
