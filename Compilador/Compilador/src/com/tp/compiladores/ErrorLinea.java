package com.tp.compiladores;

public class ErrorLinea {

    private String error;
    private int linea;

    public ErrorLinea(String error, int linea){
        this.error=error;
        this.linea=linea;
    }

    public void imprimirError(){
        System.out.println("Linea "+linea+": "+ error);
    }
}
