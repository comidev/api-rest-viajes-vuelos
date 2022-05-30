package comidev.dswgrupo5.utils;

public enum UsuarioRol {
    CLIENTE("CLIENTE"),
    ADMIN("ADMIN");

    private UsuarioRol(String content) {
        this.content = content;
    }

    private String content;

    @Override
    public String toString() {
        return content;
    }
}
