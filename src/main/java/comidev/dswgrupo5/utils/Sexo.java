package comidev.dswgrupo5.utils;

public enum Sexo {
    MASCULINO("masculino"),
    FEMENINO("femenino");

    private Sexo(String content) {
        this.content = content;
    }

    private String content;

    @Override
    public String toString() {
        return content;
    }
}
