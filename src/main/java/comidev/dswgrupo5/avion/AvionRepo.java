package comidev.dswgrupo5.avion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvionRepo extends JpaRepository<Avion, Long> {
    Optional<Avion> findByPlaca(String placa);
}
