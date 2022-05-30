package comidev.dswgrupo5.usuario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comidev.dswgrupo5.jwt.JwtDTO;

@RestController
@RequestMapping("/api/v0/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<JwtDTO> tokenRefresh(HttpServletRequest request) {
        String tokenRefresh = request.getHeader("Authorization");
        return ResponseEntity.ok(usuarioService.createNewsTokens(tokenRefresh));
    }

    @PostMapping("/admin")
    public ResponseEntity<Usuario> registrarAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.registrarAdmin(usuarioDTO, null));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Usuario> actualizarAdmin(@RequestBody UsuarioDTO usuarioDTO,
            @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.registrarAdmin(usuarioDTO, id));
    }
}
