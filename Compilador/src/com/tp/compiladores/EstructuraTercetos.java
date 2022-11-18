package com.tp.compiladores;

import java.util.*;

public class EstructuraTercetos {
    private List<Terceto> listTercetos = new ArrayList<>();
    private String nombre;

    //guarda el numero de tercetos con BI y BF para ser completados posteriormente
    private Stack<Integer> numTercetosInc = new Stack<>();

    private String tipo; //guarda el tipo de la lista de id que se estan declarando

    private Stack<Integer> numTercetosCondicionFor = new Stack<>();
    //guarda el numero de terceto de la condicion for para retornar desde BI

    private List<String> idsFor = new ArrayList<>();
    //guarda en el tope el id usado para la iteracion del for

    private List<Terceto> tercetosBreakCte = new ArrayList<>();
    //guarda los tercetos BI incompletos creados por un break cte;

//private List<List<String>>


    //
    private List<String> listIdAsigFor= new ArrayList<>();

    //public List<ErrorLinea> errores_semanticos;

    public EstructuraTercetos(String nombre) {
        this.nombre=nombre;
        //this.errores_semanticos=errores_semanticos;
    }
    public void guardarTercetoBreak(){

    }
   // public void crearListFor()

    public void guardarTercetoBreakCte(){

        this.tercetosBreakCte.add(this.listTercetos.get(this.listTercetos.size()-1));
    }

    public void completarTercetosBreakCte(int num){
        for(Terceto t: tercetosBreakCte){
            t.setValor2(String.valueOf(this.listTercetos.size()-1+num));
        }
    }

    public void borrarListTercetosBreakCte(){
        if(!this.tercetosBreakCte.isEmpty())
            this.tercetosBreakCte.clear();
    }

    public void borrarIdAsigFor(){
        if(!this.listIdAsigFor.isEmpty())
            this.listIdAsigFor.remove(this.listIdAsigFor.size()-1);
    }



    public void addIdAsigFor(String id){ //guarda el id usado en la asignacion seguida de una sentencia de control
        this.listIdAsigFor.add(id);
    }

    public String getIdAsigFor(){
        if(!this.listIdAsigFor.isEmpty())
            return this.listIdAsigFor.get(this.listIdAsigFor.size()-1);
        else
            return null;
    }

    public String getIdFor(){
        if(!idsFor.isEmpty())
            return idsFor.get(idsFor.size()-1);
        return null;
    }

    public void addIdFor(String id){ //guarda el id usado en la iteracion del for
        idsFor.add(id);
    }
    public void popIdFor(){
        if(!idsFor.isEmpty())
            idsFor.remove(idsFor.size()-1);
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public String getTipo(){
        return this.tipo;
    }
    
    public void crearTerceto(String valor1, String valor2, String valor3){
        Terceto terceto= new Terceto(valor1, valor2, valor3);
        this.listTercetos.add(terceto);
        if(valor1.equals("BF") || valor1.equals("BI")){
            numTercetosInc.add(this.cantTercetos()-1); 
            //si se apila terceto con BI y BF, se guarda el numero de terceto en la pila
        }
    }

    public int cantTercetos(){
        return listTercetos.size();
    }

    public void addNumCondicionFor(){
        numTercetosCondicionFor.add(this.cantTercetos()-1);
    }

    public Terceto getTerceto(int i){
    return listTercetos.get(i);
    }

    public String getNumeroTercetoCondicionFor(){
        return String.valueOf(numTercetosCondicionFor.pop());
    }

    public void desapilarYCompletar(int num){
        //cuando llega el momento de completar un terceto (por llegar al final del cuerpo del for o del if)
        
        Terceto t= this.getTerceto(numTercetosInc.pop());
        String salto="[" + String.valueOf(this.cantTercetos()-1+num) + "]";
        if(t.getValor1().equals("BF"))
            t.setValor3(salto);
        else
            if(t.getValor1().equals("BI"))
                t.setValor2(salto); 
    }
    
    public void imprimir(){
        System.out.println("Estructura: "+nombre);
        for (int i=0; i < listTercetos.size(); i++){
            System.out.print(i);
            listTercetos.get(i).imprimir();
        }
    }
    

    public String getRefTerceto(int ref) {
        int num=this.cantTercetos()-ref;

        return ("["+ num + "]");     //devuelve la posicion dentro de estructuraTercetos, donde se encuentra el terceto referenciado
    }

}