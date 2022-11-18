package com.tp.compiladores;

public class Terceto {

    private String valor1;
    private String valor2;
    private String valor3;

    public Terceto(String valor1, String valor2, String valor3) {
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
    }

    public String getValor1() {
        return valor1;
    }

    public String getValor2() {
        return valor2;
    }

    public String getValor3() {
        return valor3;
    }

    public void setValor1(String valor1){
        this.valor1=valor1;
    }
    public void setValor2(String valor2){
        this.valor2=valor2;
    }
    public void setValor3(String valor3){
        this.valor3=valor3;
    }
    public void imprimir(){
        System.out.println("("+valor1+","+valor2+","+valor3+")");
    }
}
