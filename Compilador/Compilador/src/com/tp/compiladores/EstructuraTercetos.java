package com.tp.compiladores;

import java.util.*;

public class EstructuraTercetos {
    private List<Terceto> listTercetos = new ArrayList<>();
    private String nombre;


    private String tipo; //guarda el tipo de la lista de id que se estan declarando

    private Stack<Integer> numTercetosCondicionFor = new Stack<>();
    //guarda el numero de terceto de la condicion for para retornar desde BI

    private List<String> idsFor = new ArrayList<>();
    //guarda en el tope el id usado para la iteracion del for

    private List<List<Terceto>> tercetosBreakCte = new ArrayList<>();
    //guarda los tercetos BI incompletos creados por un break cte;

    private List<List<Terceto>> tercetosBreak= new ArrayList<>();
    //guarda los tercetos BI incompletos creados por un break;

    private List<Terceto> tercetosBFif=new ArrayList<>();

    private List<Terceto> tercetosBFfor=new ArrayList<>();

    private HashMap<String,String> refEtiquetasFor = new HashMap<>();

    private List<String> etiquetasFor = new ArrayList<>();

    private List<String> listIdAsigFor= new ArrayList<>();

    private List<Terceto> tercetosWhen = new ArrayList<>();
    //public List<ErrorLinea> errores_semanticos;



    public EstructuraTercetos(String nombre) {
        this.nombre=nombre;
        //this.errores_semanticos=errores_semanticos;
    }

    public String getNombre(){
        return this.nombre;
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public void eliminarTercetosWhen(){
        for(Terceto terceto: tercetosWhen)
            listTercetos.remove(terceto);
            
        tercetosWhen.clear();
        
    }

    public void addTercetoWhen(){
        tercetosWhen.add(this.getTerceto(listTercetos.size()-1));;
    }

    public Terceto getTercetoWhen(){
        if(!tercetosWhen.isEmpty()){
            return tercetosWhen.remove(0);
        }
        return null;
    }

    public void completarTercetoWhen(int num){
        //completa los tercetos con BF de las condiciones when
        String salto="[" + String.valueOf(this.cantTercetos()-1+num) + "]";
        
        if(tercetosWhen.get(tercetosWhen.size()-1).getValor1().equals("BF"))
            tercetosWhen.get(tercetosWhen.size()-1).setValor3(salto);
        tercetosWhen.remove(tercetosBFif.size()-1);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    public void addRefEtiqueta(String terceto){ // asocio la etiqueta a el lugar donde debo saltar
        if(!etiquetasFor.isEmpty()){
            String etiqueta= etiquetasFor.get(etiquetasFor.size()-1);
            if((terceto!=null) && (refEtiquetasFor.get(etiqueta)==null))
                this.refEtiquetasFor.put(etiqueta, terceto);
            else
                Parser.errores_semanticos.add(new ErrorLinea("Etiqueta redeclarada", Parser.linea.getNumeroLinea()));
        }
    }

    public void addEtiquetaFor(String e){ //agrego la etiqueta
        this.etiquetasFor.add(e);
    }

    public boolean existeEtiquetaFor(String e){
        if ((e!=null) && (etiquetasFor.contains(e)))
            return true;
        else return false;
    }

    public String getRefEtiqueta(String etiqueta){
        if(etiqueta!=null)
            return refEtiquetasFor.get(etiqueta);
        return null;
    }

    public void eliminarEtiqueta(){
        if(!etiquetasFor.isEmpty()){
            String ultimaEtiqueta=etiquetasFor.get(etiquetasFor.size()-1);
            if(refEtiquetasFor.containsKey(ultimaEtiqueta))
                refEtiquetasFor.remove(ultimaEtiqueta);
            etiquetasFor.remove(etiquetasFor.size()-1);
        }
    }
    

    ////////////////////////////////////////////////////////////////////////////////////
    
    public void guardarTercetoBreak(){
        //guarda el ultimo terceto de listTercetos en la ultima lista de tercetosBreak
        //esta funcion debe ser invocada despues de crear el terceto BI para break;
        tercetosBreak.get(tercetosBreak.size()-1).add(this.getTerceto(listTercetos.size()-1));
    }

    public void completarTercetosBreak(int num){
        String salto="[" + String.valueOf(this.cantTercetos()-1+num) + "]";
        for(Terceto t: this.tercetosBreak.get(this.tercetosBreak.size()-1)){
            t.setValor2(salto);
        }
    }

    public void borrarListTercetosBreak(){
        if(!this.tercetosBreak.isEmpty())
            this.tercetosBreak.remove(this.tercetosBreak.size()-1);
    }

    public void crearListTercetoBreak(){
        tercetosBreak.add(new ArrayList<Terceto>());
    }
    public boolean vaciaListTercetoBreak(){
        return(tercetosBreak.isEmpty());
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    public void guardarTercetoBreakCte(){
        //guarda el ultimo terceto de listTercetos en la ultima lista de tercetosBreakCte
        //esta funcion debe ser invocada despues de crear el terceto BI para break cte;
        this.tercetosBreakCte.get(this.tercetosBreakCte.size()-1).add(this.listTercetos.get(this.listTercetos.size()-1));
    }

    public void completarTercetosBreakCte(int num){
        String salto="[" + String.valueOf(this.cantTercetos()-1+num) + "]";
        for(Terceto t: this.tercetosBreakCte.get(this.tercetosBreakCte.size()-1)){
            t.setValor2(salto);
        }
    }

    public void borrarListTercetosBreakCte(){
        if(!this.tercetosBreakCte.isEmpty())
            this.tercetosBreakCte.remove(this.tercetosBreakCte.size()-1);
    }

    public void crearListTercetoBreakCte(){
        tercetosBreakCte.add(new ArrayList<Terceto>());
    }
    public boolean vaciaListTercetoBreakCte(){
        return(tercetosBreakCte.isEmpty());
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////
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
        if((!Parser.dentroDeWhen) || (!Parser.condicionWhenFalse)){
            Terceto terceto= new Terceto(valor1, valor2, valor3);
            this.listTercetos.add(terceto);
            int pos;
            if(valor1!=null && !valor1.equals("BI") && !valor1.equals("BF") && !valor1.startsWith("LABEL")){
                if(valor2!=null && valor2.startsWith("[")){
                    pos=Integer.valueOf(valor2.substring(1, valor2.length()-1));
                    if(pos<=cantTercetos())
                        terceto.setTipo(getTerceto(pos).getTipo());
                }
                else{
                    if(valor3!=null && valor3.startsWith("[")){
                        pos=Integer.valueOf(valor3.substring(1, valor3.length()-1));
                        if(pos<=cantTercetos())
                            terceto.setTipo(getTerceto(pos).getTipo());
                    }
                    else
                        if(Parser.tablaDeSimbolos.getTipo(valor2)!=null && Parser.tablaDeSimbolos.getTipo(valor3)!=null)
                            if(Parser.tablaDeSimbolos.getTipo(valor2).equals(Parser.tablaDeSimbolos.getTipo(valor3)))
                                terceto.setTipo(Parser.tablaDeSimbolos.getTipo(valor2));
                }
            }
        }
    }
    

    public int cantTercetos(){
        return listTercetos.size();
    }

    public void addNumCondicionFor(){
        //guarda el numero de terceto de la condicion_for, posicion hacia donde salta la BI
        numTercetosCondicionFor.add(this.cantTercetos()-1);
    }

    public Terceto getTerceto(int i){
        if((i>=0) && (i<listTercetos.size()))
            return listTercetos.get(i);
        return null;
    }

    public String getNumeroTercetoCondicionFor(){
        if(!numTercetosCondicionFor.isEmpty())
            return String.valueOf(numTercetosCondicionFor.pop());
        return null;
    }
//////////////////////////////////////////////////////////////////////
    public void completarTercetoIf(int num){
        //completa los tercetos con BF de las condiciones IF
        String salto="[" + String.valueOf(this.cantTercetos()-1+num) + "]";
        if(!tercetosBFif.isEmpty()){
            if(tercetosBFif.get(tercetosBFif.size()-1).getValor1().equals("BF"))
                tercetosBFif.get(tercetosBFif.size()-1).setValor3(salto);
            else 
                tercetosBFif.get(tercetosBFif.size()-1).setValor2(salto);
            tercetosBFif.remove(tercetosBFif.size()-1);
        }
    }

    public void addTercetoIf(){
        tercetosBFif.add(this.getTerceto(listTercetos.size()-1));
    }

//////////////////////////////////////////////////////////////////////
    public void completarTercetoFor(int num){
        //completa los tercetos con BF de las condiciones FOR
        String salto="[" + String.valueOf(this.cantTercetos()-1+num) + "]";
        if(!tercetosBFfor.isEmpty()){
            if(tercetosBFfor.get(tercetosBFfor.size()-1).getValor1().equals("BI"))
                tercetosBFfor.get(tercetosBFfor.size()-1).setValor2(salto);
            else 
                tercetosBFfor.get(tercetosBFfor.size()-1).setValor3(salto);
            tercetosBFfor.remove(tercetosBFfor.size()-1);
        }
    }
    
    public void addTercetoFor(){
        tercetosBFfor.add(this.getTerceto(listTercetos.size()-1));
    }
//////////////////////////////////////////////////////////////////////
    public void imprimir(){
        System.out.println("Estructura: "+nombre);
        for (int i=0; i < listTercetos.size(); i++){
            System.out.print(i);
            listTercetos.get(i).imprimir();
        }
    }
    

    public String getRefTerceto(int ref) {
        
        int num=this.cantTercetos()-ref;

        return ("["+ num + "]");
        //devuelve la posicion dentro de estructuraTercetos, donde se encuentra el terceto referenciado
    }


/////////////////////////////////////////////////////////////////////////////
    public Terceto ultimoTercetoConRegistro(String registro, int numTercetoActual){
        Terceto terceto;
        terceto=getTerceto(numTercetoActual);
        while((numTercetoActual>=0) && (terceto.getRegistro()!=null) && (!terceto.getRegistro().matches(registro))){
            terceto=getTerceto(numTercetoActual);
            numTercetoActual--;
        }
        if(numTercetoActual>=0 && terceto.getRegistro()!=null){
            return terceto;
        }
        return null;
    }

}