package com.resolveservicos.Enums;

public enum Status {

    AGENDADO(1),
    CONCLUIDO(2),
    CANCELADO(3);

    private final int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
