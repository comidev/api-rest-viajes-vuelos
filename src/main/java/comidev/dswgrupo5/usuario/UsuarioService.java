package comidev.dswgrupo5.usuario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.cliente.Cliente;
import comidev.dswgrupo5.exceptions.conflict.ConflictException;
import comidev.dswgrupo5.exceptions.notFound.NotFoundException;
import comidev.dswgrupo5.exceptions.unauthorized.JwtException;
import comidev.dswgrupo5.jwt.JwtDTO;
import comidev.dswgrupo5.jwt.JwtService;
import comidev.dswgrupo5.rol.Rol;
import comidev.dswgrupo5.rol.RolService;
import comidev.dswgrupo5.utils.UsuarioRol;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private RolService rolService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByUsername(username);
        if (!usuarioOpt.isPresent()) {
            throw new UsernameNotFoundException("Username: " + username + " no encontrado.");
        }

        Usuario usuario = usuarioOpt.get();
        List<GrantedAuthority> roles = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolName()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    public Usuario getById(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario: " + id + " no encontrado"));
    }

    public JwtDTO createNewsTokens(String tokenRefresh) {
        if (!jwtService.isBearer(tokenRefresh)) {
            throw new JwtException("No es bearer.");
        }
        String username = jwtService.username(tokenRefresh);
        Optional<Usuario> usuarioOpt = usuarioRepo.findByUsername(username);
        if (!usuarioOpt.isPresent()) {
            throw new NotFoundException("Usuario: " + username + " del TokenRefresh no encontrado.");
        }
        Usuario usuario = usuarioOpt.get();
        List<String> roles = usuario.getRoles().stream()
                .map(Rol::getRolName)
                .collect(Collectors.toList());
        String accessToken = jwtService.createToken(usuario.getUsername(), roles);
        String refreshToken = jwtService.createRefreshToken(usuario.getUsername(), roles);
        return new JwtDTO(accessToken, refreshToken);
    }

    public Usuario registrarCliente(UsuarioDTO usuarioDTO, Long id, Cliente cliente) {
        return guardarUsuario(usuarioDTO, UsuarioRol.CLIENTE.toString(), id, cliente);
    }

    public Usuario registrarAdmin(UsuarioDTO usuarioDTO, Long id) {
        return guardarUsuario(usuarioDTO, UsuarioRol.ADMIN.toString(), id, null);
    }

    private Usuario guardarUsuario(UsuarioDTO usuarioDTO, String rol, Long id, Cliente cliente) {
        Usuario usuario;
        if (id == null) {
            String username = usuarioDTO.getUsername();
            boolean exists = usuarioRepo.existsByUsername(username);
            if (exists) {
                String detail = "Usuario no creado. El username: " + username + " ya existe.";
                throw new ConflictException(detail);
            }
            usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setRoles(Stream
                    .of(rolService.getRolByNameRol(rol))
                    .collect(Collectors.toSet()));
            if (cliente != null) {
                usuario.setCliente(cliente);
            }
        } else {
            usuario = getById(id);
            usuario.setUsername(usuarioDTO.getUsername());
        }
        String passwordBCrypt = passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(passwordBCrypt);
        return usuarioRepo.save(usuario);
    }
}
