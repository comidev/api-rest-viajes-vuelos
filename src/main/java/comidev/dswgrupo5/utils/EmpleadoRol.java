package comidev.dswgrupo5.utils;

public enum EmpleadoRol {
    PILOTO("piloto"),
    COPILOTO("copiloto");

    private EmpleadoRol(String content) {
        this.content = content;
    }

    private String content;

    @Override
    public String toString() {
        return content;
    }
}
