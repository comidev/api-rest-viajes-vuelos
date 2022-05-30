package comidev.dswgrupo5.vuelo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VueloDTO {
    private Long idViaje;
    private Long idAvion;
    private Fecha fecha;
}

@Setter
@Getter
class Fecha {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;
}
