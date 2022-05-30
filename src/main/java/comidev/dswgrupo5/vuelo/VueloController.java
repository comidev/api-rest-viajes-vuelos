package comidev.dswgrupo5.vuelo;

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

import comidev.dswgrupo5.pasaje.Pasaje;

@RestController
@RequestMapping("/api/v0/vuelo")
public class VueloController {
    @Autowired
    private VueloService vueloService;

    @PostMapping
    public ResponseEntity<Vuelo> registrarVuelo(@RequestBody VueloDTO vueloDTO) {
        return ResponseEntity.ok(vueloService.registrarVuelo(vueloDTO, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vuelo> actualizarVuelo(@RequestBody VueloDTO vueloDTO,
            @PathVariable Long id) {
        return ResponseEntity.ok(vueloService.registrarVuelo(vueloDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vueloService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Vuelo>> getVuelos() {
        return ResponseEntity.ok(vueloService.getVuelos());
    }

    @GetMapping("/pasajes/{id}")
    public ResponseEntity<List<Pasaje>> getPasajesByIdVuelo(@PathVariable Long id) {
        return ResponseEntity.ok(vueloService.getPasajesByIdVuelo(id));
    }

    @GetMapping("/avion/{id}")
    public ResponseEntity<List<Vuelo>> getVuelosByAvion(@PathVariable Long id) {
        return ResponseEntity.ok(vueloService.getVuelosByAvion(id));
    }

    @GetMapping("/viaje/{id}")
    public ResponseEntity<List<Vuelo>> getVuelosByViaje(@PathVariable Long id) {
        return ResponseEntity.ok(vueloService.getVuelosByViaje(id));
    }
}
