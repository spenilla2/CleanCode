package dev.abel.springbootredis.domain;

import java.io.Serializable;

public class Roulette implements Serializable {
    private String idRoulette;
    private String state;
    private String name;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(String idRoulette){
        this.idRoulette=idRoulette;
    }
    public String getId() {
        return idRoulette;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
