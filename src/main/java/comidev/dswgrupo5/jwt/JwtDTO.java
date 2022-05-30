package comidev.dswgrupo5.jwt;

import lombok.Getter;

@Getter
public class JwtDTO {
    private String accessToken;
    private String refreshToken;

    public JwtDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
