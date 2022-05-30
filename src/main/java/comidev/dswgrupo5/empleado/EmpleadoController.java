package comidev.dswgrupo5.empleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        return ResponseEntity.ok(empleadoService.getEmpleados());
    }

    @PostMapping("/piloto")
    public ResponseEntity<Empleado> savePiloto(@RequestBody EmpleadoDTO piloto) {
        return ResponseEntity.ok(empleadoService.registrarPiloto(piloto));
    }

    @GetMapping("/piloto")
    public ResponseEntity<List<Empleado>> getPilotos() {
        return ResponseEntity.ok(empleadoService.getPilotos());
    }

    @PostMapping("/copiloto")
    public ResponseEntity<Empleado> saveCopiloto(@RequestBody EmpleadoDTO copiloto) {
        return ResponseEntity.ok(empleadoService.registrarCopiloto(copiloto));
    }

    @GetMapping("/copiloto")
    public ResponseEntity<List<Empleado>> getCopilotos() {
        return ResponseEntity.ok(empleadoService.getCopilotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(empleadoService.getEmpleadoById(id));
    }
}
