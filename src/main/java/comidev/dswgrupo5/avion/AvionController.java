package comidev.dswgrupo5.avion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comidev.dswgrupo5.vuelo.Vuelo;

@RestController
@RequestMapping("/api/v0/avion")
public class AvionController {
    @Autowired
    private AvionService avionService;

    @GetMapping
    public ResponseEntity<List<Avion>> getAviones() {
        return ResponseEntity.ok(avionService.getAviones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avion> getById(@PathVariable Long id) {
        return ResponseEntity.ok(avionService.getById(id));
    }

    @GetMapping("/vuelo/{id}")
    public ResponseEntity<List<Vuelo>> getVuelosById(@PathVariable Long id) {
        return ResponseEntity.ok(avionService.getVuelosById(id));
    }

    @PostMapping
    public ResponseEntity<Avion> guardarAvion(@RequestBody AvionDTO avionDTO) {
        return ResponseEntity.ok(avionService.guardarAvion(avionDTO, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avion> guardarAvion(@RequestBody AvionDTO avionDTO,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(avionService.guardarAvion(avionDTO, id));
    }
}
