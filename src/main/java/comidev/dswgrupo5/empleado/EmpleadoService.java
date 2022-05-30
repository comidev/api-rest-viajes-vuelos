package comidev.dswgrupo5.empleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.exceptions.notFound.NotFoundException;
import comidev.dswgrupo5.utils.EmpleadoRol;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepo empleadoRepo;

    public List<Empleado> getEmpleados() {
        return empleadoRepo.findAll();
    }

    public Empleado registrarPiloto(EmpleadoDTO empleadoDTO) {
        return empleadoRepo.save(new Empleado(empleadoDTO, EmpleadoRol.PILOTO.toString()));
    }

    public List<Empleado> getPilotos() {
        return empleadoRepo.findByRol(EmpleadoRol.PILOTO.toString());
    }

    public Empleado registrarCopiloto(EmpleadoDTO empleadoDTO) {
        return empleadoRepo.save(new Empleado(empleadoDTO, EmpleadoRol.COPILOTO.toString()));
    }

    public List<Empleado> getCopilotos() {
        return empleadoRepo.findByRol(EmpleadoRol.COPILOTO.toString());
    }

    public Empleado getEmpleadoById(Long id) {
        return empleadoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado con id: [" + id + "] no encontrado"));
    }
}
