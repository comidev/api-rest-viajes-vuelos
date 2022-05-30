package comidev.dswgrupo5.vuelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comidev.dswgrupo5.avion.Avion;
import comidev.dswgrupo5.viaje.Viaje;

@Repository
public interface VueloRepo extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByViaje(Viaje viaje);

    List<Vuelo> findByAvion(Avion avion);
}
