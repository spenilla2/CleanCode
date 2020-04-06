package dev.abel.springbootredis.domain;

import java.io.Serializable;

public class Bet implements Serializable {
    private String idBet;
    private String idRoulette;
    private String idcliente;
    private String vlrBet;


    public String getId() {
        return idBet;
    }

    public void setId(String idApuesta) {
        this.idBet = idApuesta;
    }

    public String getIdRoulette() {
        return idRoulette;
    }

    public void setIdRoulette(String idRoulette) {
        this.idRoulette = idRoulette;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getVlrBet() {
        return vlrBet;
    }

    public void setVlrBet(String vlrApuesta) {
        this.vlrBet = vlrApuesta;
    }
}
