package comidev.dswgrupo5.avion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.empleado.EmpleadoService;
import comidev.dswgrupo5.exceptions.badRequest.BadRequestException;
import comidev.dswgrupo5.exceptions.conflict.ConflictException;
import comidev.dswgrupo5.exceptions.notFound.NotFoundException;
import comidev.dswgrupo5.vuelo.Vuelo;

@Service
public class AvionService {
    @Autowired
    private AvionRepo avionRepo;

    @Autowired
    private EmpleadoService empleadoService;

    public Avion guardarAvion(AvionDTO avionDTO, Long id) {
        Avion avion;
        String placa = avionDTO.getPlaca();
        if (id == null) {
            if (placa == null) {
                String detail = "Error, la placa es un campo obligatorio";
                throw new BadRequestException(detail);
            }

            boolean isPresent = avionRepo.findByPlaca(placa).isPresent();
            if (isPresent) {
                String detail = "Avion no registrado: placa " + placa + " existente.";
                throw new ConflictException(detail);
            }

            avion = new Avion();
            avion.setPlaca(placa);
        } else {
            avion = getById(id);
            if (placa != null)
                avion.setPlaca(placa);
        }
        Long idPiloto = avionDTO.getIdPiloto();
        Long idCopiloto = avionDTO.getIdCopiloto();
        if (idPiloto != null)
            avion.setPiloto(empleadoService.getEmpleadoById(idPiloto));
        if (idCopiloto != null)
            avion.setCopiloto(empleadoService.getEmpleadoById(idCopiloto));
        return avionRepo.save(avion);
    }

    public Avion getById(Long id) {
        return avionRepo.findById(id)
                .orElseThrow(() -> {
                    String detail = "Avion id: " + id + " no encontrado.";
                    return new NotFoundException(detail);
                });
    }

    public List<Vuelo> getVuelosById(Long id) {
        return getById(id).getVuelos();
    }

    public List<Avion> getAviones() {
        return avionRepo.findAll();
    }
}
