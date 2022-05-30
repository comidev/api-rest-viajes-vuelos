package comidev.dswgrupo5.pasaje;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.cliente.ClienteService;
import comidev.dswgrupo5.vuelo.VueloService;

@Service
public class PasajeService {
    @Autowired
    private PasajeRepo pasajeRepo;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VueloService vueloService;

    public Pasaje registrarPasaje(PasajeDTO pasajeDTO) {
        Pasaje pasaje = new Pasaje();
        pasaje.setCliente(clienteService.getById(pasajeDTO.getIdCliente()));
        pasaje.setVuelo(vueloService.getById(pasajeDTO.getIdVuelo()));
        pasaje.setHoraCompra(LocalDateTime.now());
        return pasajeRepo.save(pasaje);
    }

    public List<Pasaje> getPasajes() {
        return pasajeRepo.findAll();
    }

    public List<Pasaje> getPasajesByIdCliente(Long id) {
        return pasajeRepo.findByCliente(clienteService.getById(id));
    }

    public List<Pasaje> getPasajesByIdVuelo(Long id) {
        return pasajeRepo.findByVuelo(vueloService.getById(id));
    }
}
