package com.tp.compiladores;

public class Simbolo {
    private String lexema;
    private int token;
    private String tipo;
    private String uso;
    private String valor;

    Simbolo(String lexema, int token){
        this.lexema=lexema;
        this.token=token;
    }

    Simbolo(String lexema, int token, String uso){
        this.lexema=lexema;
        this.token=token;
        this.uso=uso;
    }

    Simbolo(String lexema, int token, String uso, String tipo){
        this.lexema=lexema;
        this.token=token;
        this.uso=uso;
        this.tipo = tipo;
    }
    public void setUso(String uso){
        this.uso=uso;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public void setValor(String valor){
        this.valor=valor;
    }
    public String getLexema(){
        return this.lexema;
    }
    public int getToken(){
        return this.token;
    }

    public String getUso(){
        return this.uso;
    }

    public String imprimir(){
        return ("Lexema: "+ lexema+" - Token: "+token + " - Tipo: "+tipo+" - Uso: "+uso );
    }
    
}
