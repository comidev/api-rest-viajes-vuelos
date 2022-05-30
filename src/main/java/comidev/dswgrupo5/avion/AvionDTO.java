package comidev.dswgrupo5.avion;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AvionDTO {
    private String placa;
    private Long idPiloto;
    private Long idCopiloto;

    public AvionDTO() {

    }

    public AvionDTO(String placa, Long idPiloto, Long idCopiloto) {
        this.placa = placa;
        this.idPiloto = idPiloto;
        this.idCopiloto = idCopiloto;
    }
}
