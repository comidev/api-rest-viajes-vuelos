package comidev.dswgrupo5.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.exceptions.conflict.ConflictException;
import comidev.dswgrupo5.exceptions.notFound.NotFoundException;
import comidev.dswgrupo5.pasaje.Pasaje;
import comidev.dswgrupo5.usuario.Usuario;
import comidev.dswgrupo5.usuario.UsuarioDTO;
import comidev.dswgrupo5.usuario.UsuarioService;
import comidev.dswgrupo5.utils.Sexo;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private UsuarioService usuarioService;

    public Usuario registrarClienteHombre(ClienteDTO clienteDTO) {
        return registrarCliente(clienteDTO, Sexo.MASCULINO.toString());
    }

    public Usuario registrarClienteMujer(ClienteDTO clienteDTO) {
        return registrarCliente(clienteDTO, Sexo.FEMENINO.toString());
    }

    private Usuario registrarCliente(ClienteDTO clienteDTO, String sexo) {
        String DNI = clienteDTO.getDNI();
        boolean isPresent = clienteRepo.existsByDNI(DNI);
        if (isPresent) {
            String detail = "El DNI: " + DNI + " ya existe. Cliente no registrado.";
            throw new ConflictException(detail);
        }
        Cliente cliente = new Cliente(clienteDTO);
        cliente.setSexo(sexo);
        return usuarioService
                .registrarCliente(new UsuarioDTO(clienteDTO.getUsername(),
                        clienteDTO.getPassword()),
                        null,
                        cliente);
    }

    public Cliente getById(Long id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("El cliente: " + id + " no existe"));
    }

    public Cliente getByDNI(String DNI) {
        return clienteRepo.findByDNI(DNI)
                .orElseThrow(() -> {
                    String detail = "El cliente DNI: " + DNI + " no existe";
                    return new NotFoundException(detail);
                });
    }

    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    public List<Cliente> getClientesHombres() {
        return clienteRepo.findBySexo(Sexo.MASCULINO.toString());
    }

    public List<Cliente> getClientesMujeres() {
        return clienteRepo.findBySexo(Sexo.FEMENINO.toString());
    }

    public List<Pasaje> getPasajes(Long id) {
        return getById(id).getPasajes();
    }
}
