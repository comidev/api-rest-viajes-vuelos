package comidev.dswgrupo5.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDNI(String DNI);

    boolean existsByDNI(String DNI);

    List<Cliente> findBySexo(String sexo);
}
