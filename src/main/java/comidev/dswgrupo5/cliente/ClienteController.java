package comidev.dswgrupo5.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comidev.dswgrupo5.pasaje.Pasaje;
import comidev.dswgrupo5.usuario.Usuario;

@RestController
@RequestMapping("/api/v0/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/hombre")
    public ResponseEntity<Usuario> registrarClienteHombre(@RequestBody ClienteDTO cliente) {
        return ResponseEntity.ok(clienteService.registrarClienteHombre(cliente));
    }

    @PostMapping("/mujer")
    public ResponseEntity<Usuario> registrarClienteMujer(@RequestBody ClienteDTO cliente) {
        return ResponseEntity.ok(clienteService.registrarClienteMujer(cliente));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @GetMapping("/dni/{DNI}")
    public ResponseEntity<Cliente> getByDNI(@PathVariable("DNI") String DNI) {
        return ResponseEntity.ok(clienteService.getByDNI(DNI));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(clienteService.getClientes());
    }

    @GetMapping("/hombre")
    public ResponseEntity<List<Cliente>> getClientesHombres() {
        return ResponseEntity.ok(clienteService.getClientesHombres());
    }

    @GetMapping("/mujer")
    public ResponseEntity<List<Cliente>> getClientesMujeres() {
        return ResponseEntity.ok(clienteService.getClientesMujeres());
    }

    @GetMapping("/pasaje/{id}")
    public ResponseEntity<List<Pasaje>> getPasajes(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getPasajes(id));
    }
}
