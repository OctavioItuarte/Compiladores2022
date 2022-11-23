package com.tp.compiladores;

import java.util.*;

public class TablaSimbolos{
    HashMap<String, Simbolo> tabla;
    List<ErrorLinea> erroresSemanticos;
    NumeroLinea linea;

    TablaSimbolos(List<ErrorLinea> erroresSemanticos, NumeroLinea linea){
        this.erroresSemanticos=erroresSemanticos;
        this.linea=linea;
        tabla=new HashMap<>();
    }

    public List<String> getNumeros(){
        List<String> resultado=new ArrayList<>();
        for(Map.Entry<String, Simbolo> entry : tabla.entrySet()){
            if(entry.getValue().getUso().equals("valor_numerico")){
                resultado.add(entry.getKey());
            }
        }
        return resultado;
    }

    public List<Simbolo> getConstantes(){
        List<Simbolo> resultado=new ArrayList<>();
        String uso;
        for(Map.Entry<String, Simbolo> entry : tabla.entrySet()){
            uso=entry.getValue().getUso();
            if((uso!=null) && (uso.equals("constante"))){
                resultado.add(entry.getValue());
            }
        }
        return resultado;
    }

    public List<Simbolo> getIds(){
        List<Simbolo> resultado= new ArrayList<>();
        String uso;
        for(Map.Entry<String, Simbolo> entry : tabla.entrySet()){
            uso=entry.getValue().getUso();
            if((uso!=null) && ((uso.equals("variable")) || (uso.equals("identificador_funcion")) || (uso.equals("parametro")))){
                resultado.add(entry.getValue());
            }
        }
        return resultado;
    }

    private String cambiarAmbito(String[] ambitos, int n){
        String resultado=ambitos[0];
        for(int i=1; i<n; i++){
            resultado=resultado+":"+ambitos[i];
        }
        return resultado;
    }

    public String existeSimboloAmbito(String lexema, String ambito){
        // chequear que exista el id en el ambito 
        String [] ambitos = ambito.split(":");
        String [] lex = lexema.split(":");
        
        for (int i = ambitos.length; i > 0; i--){
            //System.out.println("ambito comparado"+ var);
            String ambitoNuevo=this.cambiarAmbito(ambitos, i);
            if (tabla.containsKey(lex[0]+":"+ambitoNuevo)){
                //System.out.println("variable por la que busco"+ lex[0]+":"+var);
                return lex[0]+":"+ambitoNuevo;
            }
        }
        return null;
    }

    public Simbolo getSimbolo(String lexema){
        return(tabla.get(lexema));
    }


    public String getTipo(String lexema){
        if(tabla.containsKey(lexema))
            return(tabla.get(lexema).getTipo());
        return null;
    }

    public void setTipo(String lexema, String tipo){

        tabla.get(lexema).setTipo(tipo);
    }

    public void add(Simbolo e){  //agregar simbolo a la tabla
        if(!tabla.containsKey(e.getLexema()))   //chequear que no exista otro id con el mismo nombre en el mismo ambito
            this.tabla.put(e.getLexema(), e);
        else
            if (e.getToken() == 269) {
                if (tabla.get(e.getLexema()).getUso().equals("identificador_funcion")) {
                    System.out.println("Funcion " + e.getLexema() + " redeclarada");
                    ErrorLinea err = new ErrorLinea("Funcion " + e.getLexema() + " redeclarada", this.linea.getNumeroLinea());
                    erroresSemanticos.add(err);
                } else {
                    System.out.println("Error: variable " + e.getLexema() + " redeclarada");
                    erroresSemanticos.add(new ErrorLinea("Error: variable " + e.getLexema() + " redeclarada", this.linea.getNumeroLinea()));
                }
            }
        }

    public String getRefFuncion(String lexema, String ambito){
        String existe=existeSimboloAmbito(lexema, ambito);
        // debe buscar el simbolo en la tabla en dicho ambito.
        if((existe!=null) && (tabla.get(existe).getUso().equals("identificador_funcion")))
            return (existe);
        else{
            System.out.println("Funcion " + lexema +" no declarada");
            ErrorLinea err=new ErrorLinea("Funcion " + lexema +" no declarada", this.linea.getNumeroLinea());
            erroresSemanticos.add(err);
        }
        return null;
    }

    public String getRefSimbolo(String lexema, String ambito){ //devuelve el lexema al simbolo en la tabla
        String existe=existeSimboloAmbito(lexema, ambito);
        if(existe!=null)
        // debe buscar el simbolo en la tabla en dicho ambito.
            return (existe);
        else{
            System.out.println("Variable " + lexema +" no declarada");
            ErrorLinea err=new ErrorLinea("Variable " + lexema +" no declarada", this.linea.getNumeroLinea());
            erroresSemanticos.add(err);
        }
        return null;      //devuelve referencia al simbolo en la tabla
    }

     

    public void imprimir(){
        // imprimir contenido de la tabla
        for (Map.Entry<String, Simbolo> entry : tabla.entrySet()) {
            System.out.println(entry.getValue().imprimir());
        }
    }
    
    

}
