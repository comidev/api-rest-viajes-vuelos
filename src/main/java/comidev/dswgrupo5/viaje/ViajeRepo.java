package comidev.dswgrupo5.viaje;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepo extends JpaRepository<Viaje, Long> {
    Optional<Viaje> findByDestino(String destino);
}
