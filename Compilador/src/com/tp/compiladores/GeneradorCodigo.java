package com.tp.compiladores;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class GeneradorCodigo{
    
    private static List<EstructuraTercetos> listEstructuraTercetos= new ArrayList<EstructuraTercetos>();
    private static Terceto tercetoActual;
    private static String value1;
    private static String value2;
    private static String value3;
    private static EstructuraTercetos estructuraActual;
    private static StringBuilder assembler=new StringBuilder();
    private static HashMap<String, Boolean> registros;

    public GeneradorCodigo(List<EstructuraTercetos> lista){
        listEstructuraTercetos=lista;        
    }

    public static void setListaEstructuras(List<EstructuraTercetos> lista){
        listEstructuraTercetos=lista;
    }

    public static void generarArchivo(){
        try {
            String ruta = "filename.asm";
            
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(assembler.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copiarTercetos(){
        List<EstructuraTercetos> nuevaLista= new ArrayList<>();
        for(EstructuraTercetos estructura : listEstructuraTercetos){
            EstructuraTercetos estructuraNueva= new EstructuraTercetos(estructura.getNombre());
            for(int i=0; i<estructura.cantTercetos(); i++){
                Terceto terceto=estructura.getTerceto(i);
                estructuraNueva.crearTerceto(terceto.getValor1(), cambiarFormato(terceto.getValor2()), cambiarFormato(terceto.getValor3()));
            }
            nuevaLista.add(estructuraNueva);

        }
        listEstructuraTercetos=nuevaLista;
    }

    public static void generarCodigoPrincipal(){

        copiarTercetos();
        registros = new HashMap<String,Boolean>();
        registros.put("eax", false);
        registros.put("ebx", false);
        registros.put("ecx", false);
        registros.put("edx", false);
        assembler.append(".386\n");
        assembler.append(".model flat, stdcall\n");
        assembler.append("option casemap :none\n");
        assembler.append("include \\masm32\\include\\windows.inc\n");
        assembler.append("include \\masm32\\include\\kernel32.inc\n");
        assembler.append("include \\masm32\\include\\masm32.inc\n");
        assembler.append("includelib \\masm32\\lib\\kernel32.lib\n");
        assembler.append("includelib \\masm32\\lib\\masm32.lib\n");
        assembler.append(".data\n");
        generarDatos();
        assembler.append(".CODE\n");

        generarExcepciones();

        definirFunciones();

        assembler.append("START:\n");
        
        
        estructuraActual= listEstructuraTercetos.get(0); 
        //toma la estructura de tercetos del programa principal
        //se asume que se encuentra en la primera posicion de la lista
        int n=estructuraActual.cantTercetos();
        for(int i=0; i<n; i++){
            tercetoActual=estructuraActual.getTerceto(i);
            value1=tercetoActual.getValor1();
            value2=tercetoActual.getValor2();
            value3=tercetoActual.getValor3();
            chequearYgenerarInstruccion();
            /*
            if ((!value2.valor2EsRef()) && (!valor3.valor3EsRef)){ //para expresiones aritmeticas
                String reg = regLibre();
                assembler.append("mov reg,"+value2);
                addExpresionSimple(reg, value1, value3);
                // checkAndAddValue1();
                // checkAndAddValue2();
                // checkAndAddValue3();
            }else if ((valor2.valor2EsRef) && (!valor3.valor3EsRef)){
                checkAndAddValue1();
                checkAndAddValue3();
            }else if ((valor3.valor3EsRef) && (!valor2.valor2EsRef)){
                checkAndAddValue1();
                checkAndAddValue2();
            }else
            */
        }
        //assembler.append("");
        assembler.append("END START");

        generarArchivo();
    }

    public static void chequearYgenerarInstruccion(){
        //resetearRegistros();
        switch(value1){
            case "+":
                generarOperacion("add");
                break;
            case "-":
                generarOperacionNoConmutativa("sub");
                break;
            case "/":
                generarOperacionNoConmutativa("idiv");
                break;
            case "*":
                generarOperacion("imul");
                break;
            case "=:":
                generarAsignacion();
                break;
            case "<":
                generarComparacion("jl");
                break;
            case ">":
                generarComparacion("jg");
                break;
            case "<=":
                generarComparacion("jle");
                break;
            case ">=":
                generarComparacion("jge");
                break;
            case "=":
                generarComparacion("je");
                break;
            case "BI":
                generarSaltoIncondicional();
                break;
            case "BF":
                generarSaltoCondicional();
                break;
            case "tof32":
                // codigo
                break;
            default: //LABEL
                assembler.append(value1+": ");
                break;
        }
    }

    public static void generarOperacion(String op){
        int numTerceto1;
        int numTerceto2;
        Terceto t1;
        Terceto t2;
        String registro1;
        String registro2;
        if(value2!=null && value2.startsWith("[")){
            numTerceto1=Integer.valueOf(value2.substring(1, value2.length()-1));
            t1= estructuraActual.getTerceto(numTerceto1);
            registro1=t1.getRegistro();
            assembler.append(op+ " "+ registro1+", ");
            tercetoActual.setRegistro(registro1);
            if(value3!=null && value3.startsWith("[")){
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                assembler.append(registro2+"\n");
                registros.put(t2.getRegistro(), false);
            }
            else
                assembler.append(value3+"\n");
        }
        else{
            if(value3!=null && value3.startsWith("[")){
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                tercetoActual.setRegistro(registro2);
                assembler.append(op+" "+ registro2+", "+ value2 +"\n");
            }
            else{
                asignarRegistroTerceto();
                assembler.append("mov "+tercetoActual.getRegistro()+", "+value2+"\n");
                assembler.append(op+" "+tercetoActual.getRegistro()+", "+value3+"\n");
            }
        }
    }

    public static void generarAsignacion(){
        int numTerceto1;
        Terceto t1;
        String registro1;
        if(value3!=null && value3.startsWith("[")){
            numTerceto1=Integer.valueOf(value3.substring(1, value3.length()-1));
            t1= estructuraActual.getTerceto(numTerceto1);
            registro1=t1.getRegistro();
            assembler.append("mov "+value2+", "+registro1+"\n");
        }
        else{
            assembler.append("mov bl, "+value3+ "\n");
            assembler.append("mov "+value2+", "+"bl"+"\n");
        }
    }


    public static void generarComparacion(String comp){
        int numTerceto1;
        int numTerceto2;
        Terceto t1;
        Terceto t2;
        String registro1;
        String registro2;
        if(value2!=null && value2.startsWith("[")){
            numTerceto1=Integer.valueOf(value2.substring(1, value2.length()-1));
            t1= estructuraActual.getTerceto(numTerceto1);
            registro1=t1.getRegistro();
            assembler.append("cmp "+ registro1+", ");
            registros.put(t1.getRegistro(),false);
            if(value3!=null && value3.startsWith("[")){
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                assembler.append(registro2+"\n");
                registros.put(t2.getRegistro(), false);
            }
            else
                assembler.append(value3+"\n");
        }
        else{
            if(value3!=null && value3.startsWith("[")){
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                registros.put(t2.getRegistro(),false);
                assembler.append("cmp "+ registro2+", "+ value2 +"\n");
            }
            else{
                //asignarRegistroTerceto();
                assembler.append("mov "+"ebx"+", "+value2+"\n");
                assembler.append("cmp "+"ebx"+", "+value3+"\n");
                
            }
        }
        assembler.append(comp+" ");
    }

    public static void generarSaltoCondicional(){
        int numTerceto=Integer.valueOf(value3.substring(1,value3.length()-1));
        String label=estructuraActual.getTerceto(numTerceto).getValor1();
        
        assembler.append(label+"\n");
    }

    public static void generarSaltoIncondicional(){
        if((value2!=null) && (value2.startsWith("["))){
            int direccionSalto= Integer.valueOf(value2.substring(1, value2.length()-1));
            assembler.append("jmp ");
            assembler.append(estructuraActual.getTerceto(direccionSalto).getValor1()+ "\n");
        }
        else
            if(value2!=null){
                assembler.append("call "+"LABEL_"+cambiarFormato(value2)+ "\n");
            }
            else
                if(value2==null){
                    assembler.append("ret \n");
                }
    }





    public static void generarOperacionNoConmutativa(String op){
        int numTerceto1;
        int numTerceto2;
        Terceto t1;
        Terceto t2;
        String registro1;
        String registro2;
        if(value2!=null && value2.startsWith("[")){
            numTerceto1=Integer.valueOf(value2.substring(1, value2.length()-1));
            t1= estructuraActual.getTerceto(numTerceto1);
            registro1=t1.getRegistro();
            assembler.append(op+ " "+ registro1+", ");
            tercetoActual.setRegistro(registro1);
            if(value3!=null && value3.startsWith("[")){
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                assembler.append(registro2+"\n");
                registros.put(t2.getRegistro(), false);
            }
            else
                assembler.append(value3+"\n");
        }
        else{
            asignarRegistroTerceto();
            if(value3!=null && value3.startsWith("[")){
                assembler.append("mov "+tercetoActual.getRegistro()+", "+value2);
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                assembler.append(op+" "+ tercetoActual.getRegistro()+", "+ registro2 +"\n");
            }
            else{
                assembler.append("mov "+tercetoActual.getRegistro()+", "+value2+"\n");
                assembler.append(op+" "+tercetoActual.getRegistro()+", "+value3+"\n");
            }
        }
    }







    public static void asignarRegistroTerceto(){
        if(!registros.get("eax")){
            tercetoActual.setRegistro("al");
            registros.put("eax", true);
        }
        else{
            if(!registros.get("ebx")){
                tercetoActual.setRegistro("bl");
                registros.put("ebx", true);
            }
            else{
                if(!registros.get("ecx")){
                    tercetoActual.setRegistro("cl");
                    registros.put("ecx", true);
                }
                else{
                    if(!registros.get("edx")){
                        tercetoActual.setRegistro("dl");
                        registros.put("edx", true);
                    }
                }
            }

        }
    }

    public static String cambiarFormato(String lexema){
        if(lexema!=null){
            String resultado="";
            for(int i=0; i< lexema.length(); i++){
                resultado=resultado.concat(lexema.substring(i, i+1));
                if(lexema.substring(i,i+1).equals(":")){
                    resultado=resultado.substring(0, resultado.length()-1).concat("_");
                }
            }
            return resultado;
        }
        else
            return null;
    }

    public static void generarDatos(){
        List<Simbolo> constantes= Parser.tablaDeSimbolos.getConstantes();
        //List<String> numeros= Parser.tablaDeSimbolos.getNumeros();
        List<Simbolo> ids= Parser.tablaDeSimbolos.getIds();
        for(Simbolo s: constantes){
            assembler.append(cambiarFormato(s.getLexema())+" db "+ s.getValor()+"\n");
        }
        //for(String s: numeros){
            //assembler.append(s+" db "+ s+"\n");
        //}
        for(Simbolo s: ids){
            assembler.append(cambiarFormato(s.getLexema())+" db "+ "?"+ "\n");
        }
        
    }

    

    public static void definirFunciones(){
        int n=listEstructuraTercetos.size();
        String nombreFuncion;
        
        for(int i=1; i<n; i++){
            estructuraActual=listEstructuraTercetos.get(i);
            nombreFuncion=estructuraActual.getNombre();
            assembler.append("LABEL_"+cambiarFormato(nombreFuncion)+":");
            
            int n1=estructuraActual.cantTercetos();
            
            for(int j=0; j<n1; j++){
                tercetoActual=estructuraActual.getTerceto(j);
                value1=tercetoActual.getValor1();
                value2=tercetoActual.getValor2();
                value3=tercetoActual.getValor3();

                chequearYgenerarInstruccion();
                
            }
            
        }
    }

    public static void generarExcepciones(){
        assembler.append("OVERFLOWENTERO: ");
        chequearYgenerarExcepcionOverflowEntero();
        assembler.append("OVERFLOWFLOTANTE: ");
        chequearYgenerarExcepcionOverflowFlotante();
        assembler.append("RECURSIONMUTUA: ");
        chequearYgenerarExcepcionRecursionMutua();

    }

    public static void chequearYgenerarExcepcionOverflowEntero(){
        

        assembler.append("cmp "+"ebp"+", "+"128 \n");
        assembler.append("je "+"FIN_EXCEPCION_OVERFLOW_ENTERO \n");
        assembler.append("invoke MessageBox, NULL, addr msj_exc1, addr msj_exc1, MB_OK \n");
        assembler.append("invoke ExitProcess, 0 \n");
        assembler.append("FIN_EXCEPCION_OVERFLOW_ENTERO: \n");
        assembler.append("ret \n");
    }

    public static void chequearYgenerarExcepcionOverflowFlotante(){
        assembler.append("ret \n");
    }

    public static void chequearYgenerarExcepcionRecursionMutua(){
        assembler.append("ret \n");
    }

    /*
    public static void resetearRegistros(){
        //limpiar contenido de los registros que no estan ocupados
        for(Map.Entry<String, Boolean> registro : registros.entrySet())
            if(!registro.getValue())
                assembler.append("and "+registro.getKey()+", "+"0 \n");
    }
    */
    /*
    public static String regLibre(){
        for(Map.Entry<String, Simbolo> entry : tabla.entrySet()){
            if(entry.getValue()){
                return entry.getKey();
            }
        }
        return null;
    }
    */

    public static void addExpresionSimple(String reg, String value1, String value3){
        switch(value1){
            case "+":
                assembler.append("add "+reg+","+value3);
            case "-":
                assembler.append("sub "+reg+","+value3);
            case "/":
                assembler.append("div "+reg+","+value3);
            case "*":
                assembler.append("mul "+reg+","+value3);
            case "=:":
            case "<":
            case ">":
            case "<=":
            case ">=":
            case "=":
                //generarOperador();
                break;
            default:
                //generarSalto();
        }
    }

    public static String getCodigoGenerado(){
        return assembler.toString();
    }
}