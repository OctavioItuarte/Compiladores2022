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
        if (token == 267)
            this.tipo = "I8";
        if (token == 268)
            this.tipo = "F32";
    }

    Simbolo(String lexema, int token, String uso){
        this.lexema=lexema;
        this.token=token;
        this.uso=uso;
        if (token == 267)
            this.tipo = "I8";
        if (token == 268)
            this.tipo = "F32";
    }

    Simbolo(String lexema, int token, String uso, String tipo){
        this.lexema=lexema;
        this.token=token;
        this.uso=uso;
        this.tipo = tipo;
    }
    Simbolo(String lexema, int token, String uso, String tipo, String valor){
        this.lexema=lexema;
        this.token=token;
        this.uso=uso;
        this.tipo = tipo;
        this.valor=valor;
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

    public String getTipo(){
        return this.tipo;
    }
    public String getValor(){
        return this.valor;
    }

    public String imprimir(){
        return ("Lexema: "+ lexema+"\t"+"Token: "+token + "\t"+"Tipo: "+tipo+"\t"+"Uso: "+uso +"\t"+"Valor: "+valor);
    }
    
}
