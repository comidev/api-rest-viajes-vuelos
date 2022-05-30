package comidev.dswgrupo5.pasaje;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comidev.dswgrupo5.cliente.Cliente;
import comidev.dswgrupo5.vuelo.Vuelo;

@Repository
public interface PasajeRepo extends JpaRepository<Pasaje, Long> {
    List<Pasaje> findByCliente(Cliente cliente);

    List<Pasaje> findByVuelo(Vuelo vuelo);
}
