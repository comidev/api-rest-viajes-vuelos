package comidev.dswgrupo5.viaje;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.exceptions.conflict.FieldAlreadyExistException;
import comidev.dswgrupo5.exceptions.notFound.NotFoundException;
import comidev.dswgrupo5.vuelo.Vuelo;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepo viajeRepo;

    public Viaje registrarViaje(Viaje viaje) {
        String destino = viaje.getDestino();
        boolean isPresent = viajeRepo.findByDestino(destino).isPresent();
        if (isPresent) {
            throw new FieldAlreadyExistException("El destino: " + destino + " ya existe.");
        }
        return viajeRepo.save(viaje);
    }

    public List<Viaje> getViajes() {
        return viajeRepo.findAll();
    }

    public Viaje getViajeById(Long id) {
        return viajeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaje con id: " + id + " no existente."));
    }

    public List<Vuelo> getVuelosByIdViaje(Long id) {
        return getViajeById(id).getVuelos();
    }
}
