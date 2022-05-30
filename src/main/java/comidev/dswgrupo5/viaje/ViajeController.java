package comidev.dswgrupo5.viaje;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comidev.dswgrupo5.vuelo.Vuelo;

@RestController
@RequestMapping("/api/v0/viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @PostMapping
    public ResponseEntity<Viaje> registrarViaje(@RequestBody Viaje viaje) {
        return ResponseEntity.ok(viajeService.registrarViaje(viaje));
    }

    @GetMapping
    public ResponseEntity<List<Viaje>> getViajes() {
        return ResponseEntity.ok(viajeService.getViajes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getViajeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(viajeService.getViajeById(id));
    }

    @GetMapping("/vuelos/{id}")
    public ResponseEntity<List<Vuelo>> getVuelosByIdViaje(@PathVariable Long id) {
        return ResponseEntity.ok(viajeService.getVuelosByIdViaje(id));
    }
}
