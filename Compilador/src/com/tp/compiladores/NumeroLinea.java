package com.tp.compiladores;

public class NumeroLinea {

    private int linea;

    public NumeroLinea(){
        linea = 1;
    }
    
    public int getNumeroLinea(){
        return linea;
    }

    public void avanzarLinea(){
        linea++;
    }
    public void retrocederLinea(){
        linea--;
    }
}
