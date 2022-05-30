package comidev.dswgrupo5.usuario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    private String username;
    private String password;

    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsuarioDTO() {

    }
}
