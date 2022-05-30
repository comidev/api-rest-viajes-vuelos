package comidev.dswgrupo5.vuelo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.avion.AvionService;
import comidev.dswgrupo5.exceptions.notFound.NotFoundException;
import comidev.dswgrupo5.pasaje.Pasaje;
import comidev.dswgrupo5.viaje.ViajeService;

@Service
public class VueloService {
    @Autowired
    private VueloRepo vueloRepo;
    @Autowired
    private ViajeService viajeService;
    @Autowired
    private AvionService avionService;

    public Vuelo registrarVuelo(VueloDTO vueloDTO, Long id) {
        Vuelo vuelo = id == null ? new Vuelo() : getById(id);
        vuelo.setViaje(viajeService.getViajeById(vueloDTO.getIdViaje()));
        vuelo.setAvion(avionService.getById(vueloDTO.getIdAvion()));
        Fecha fecha = vueloDTO.getFecha();
        vuelo.setFecha(LocalDateTime.of(fecha.getYear(),
                fecha.getMonth(),
                fecha.getDayOfMonth(),
                fecha.getHour(),
                fecha.getMinute()));
        return vueloRepo.save(vuelo);
    }

    public Vuelo getById(Long id) {
        return vueloRepo.findById(id)
                .orElseThrow(() -> {
                    String detail = "El vuelo: " + id + " no existe.";
                    return new NotFoundException(detail);
                });
    }

    public List<Vuelo> getVuelos() {
        return vueloRepo.findAll();
    }

    public List<Pasaje> getPasajesByIdVuelo(Long id) {
        return getById(id).getPasajes();
    }

    public List<Vuelo> getVuelosByAvion(Long id) {
        return vueloRepo.findByAvion(avionService.getById(id));
    }

    public List<Vuelo> getVuelosByViaje(Long id) {
        return vueloRepo.findByViaje(viajeService.getViajeById(id));
    }
}
