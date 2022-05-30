package comidev.dswgrupo5.pasaje;

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
@RequestMapping("/api/v0/pasaje")
public class PasajeController {
    @Autowired
    private PasajeService pasajeService;

    @PostMapping
    public ResponseEntity<Pasaje> registrarPasaje(@RequestBody PasajeDTO pasajeDTO) {
        return ResponseEntity.ok(pasajeService.registrarPasaje(pasajeDTO));
    }

    @GetMapping
    public ResponseEntity<List<Pasaje>> getPasajes() {
        return ResponseEntity.ok(pasajeService.getPasajes());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Pasaje>> getPasajesByIdCliente(@PathVariable Long id) {
        return ResponseEntity.ok(pasajeService.getPasajesByIdCliente(id));
    }

    @GetMapping("/vuelo/{id}")
    public ResponseEntity<List<Pasaje>> getPasajesByIdVuelo(@PathVariable Long id) {
        return ResponseEntity.ok(pasajeService.getPasajesByIdVuelo(id));
    }
}
