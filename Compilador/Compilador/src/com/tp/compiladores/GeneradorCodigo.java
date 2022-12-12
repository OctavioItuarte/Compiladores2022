package com.tp.compiladores;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.*;


public class GeneradorCodigo{
    
    private static List<EstructuraTercetos> listEstructuraTercetos= new ArrayList<EstructuraTercetos>();
    private static Terceto tercetoActual;
    private static String value1;
    private static String value2;
    private static String value3;
    private static EstructuraTercetos estructuraActual;
    private static StringBuilder assembler=new StringBuilder();
    private static HashMap<String, Boolean> registros;
    private static HashMap<String, Boolean> registrosFloat;
    private static int numTercetoActual=0;
    

    //private static StringBuilder impresion=new StringBuilder();

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

    public static String reemplazarDosPuntos(String lexema){
        String resultado=""; String simbolo;
        if(lexema!=null){
            for(int i=0; i<lexema.length(); i++){
                simbolo=String.valueOf(lexema.charAt(i));
                if(simbolo.equals(":")){
                    resultado=resultado.concat("_");
                }
                else
                    resultado=resultado.concat(simbolo);
            }
            return resultado;
        }
        return null;
    }

    public static void copiarTercetos(){
        List<EstructuraTercetos> nuevaLista= new ArrayList<>();
        for(EstructuraTercetos estructura : listEstructuraTercetos){
            EstructuraTercetos estructuraNueva= new EstructuraTercetos(estructura.getNombre());
            for(int i=0; i<estructura.cantTercetos(); i++){
                Terceto terceto=estructura.getTerceto(i);
                estructuraNueva.crearTerceto(terceto.getValor1(), reemplazarDosPuntos(terceto.getValor2()), reemplazarDosPuntos(terceto.getValor3()));
                estructuraNueva.getTerceto(i).setTipo(terceto.getTipo());
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
        registrosFloat = new HashMap<String,Boolean>();
        registrosFloat.put("st(0)", false);
        registrosFloat.put("st(1)", false);
        registrosFloat.put("st(2)", false);
        registrosFloat.put("st(3)", false);
        registrosFloat.put("st(4)", false);
        registrosFloat.put("st(5)", false);
        registrosFloat.put("st(6)", false);
        registrosFloat.put("st(7)", false);
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
        for(numTercetoActual=0; numTercetoActual<n; numTercetoActual++){
            tercetoActual=estructuraActual.getTerceto(numTercetoActual);
            value1=tercetoActual.getValor1();
            value2=tercetoActual.getValor2();
            value3=tercetoActual.getValor3();
            chequearYgenerarInstruccion();
        }
        //assembler.append(impresion.toString());
        assembler.append("end_program:");
        assembler.append("END START");

        generarArchivo();
    }

    

    public static void chequearYgenerarInstruccion(){
        //resetearRegistros();
        String tipo=tercetoActual.getTipo();
        switch(value1){
            case "+":
                switch(tipo){
                    case "I8":
                        generarOperacion("add");
                        break;
                    case "F32":
                        generarOperacionFlotante("fadd");
                        break;
                }
                break;
            case "-":
                switch(tipo){
                    case "I8":
                        generarOperacion("sub");
                        break;
                    case "F32":
                        generarOperacionFlotante("fsub");
                        break;
                }
                break;
                
            case "/":
                switch(tipo){
                    case "I8":
                        generarMultDiv("idiv");
                        break;
                    case "F32":
                        generarOperacionFlotante("fdiv");
                        break;
                }
                break;
            case "*":
                switch(tipo){
                    case "I8":
                        generarMultDiv("imul");
                        break;
                    case "F32":
                        generarOperacionFlotante("fmul");
                        break;
                }
                break;
            case "=:":
                switch(tipo){
                    case "I8":
                        generarAsignacion();
                        break;
                    case "F32":
                        generarAsignacionFlotante();
                        break;
                }
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
            case "=!":
                generarComparacion("jne");
                break;
            case "BI":
                generarSaltoIncondicional();
                break;
            case "BF":
                generarSaltoCondicional();
                break;
            case "tof32":
                generarConversion();
                break;
            case "OUT":
                assembler.append("invoke StdOut, addr "+reemplazarSimbolo(value2.substring(1, value2.length()-1))+"\n");
                break;
            default: //LABEL
                assembler.append(value1+": ");
                break;
        }
    }

    public static void generarAsignacionFlotante(){
        if(value3!=null && value3.startsWith("[")){
            assembler.append("fst "+ value2+"\n");
        }else {
            if((value3.startsWith("-")) || (value3.startsWith("+")) || (value3.matches("^[0-9]")) || (value3.startsWith("."))){
                value3="@varFloat"+reemplazarSimbolo(value3);
            }
            assembler.append("fld "+ value3+"\n");
            assembler.append("fst "+ value2+"\n");
        }
    }

    public static void generarOperacionFlotante(String op){
        
        if((value3.startsWith("-")) || (value3.startsWith("+")) || (value3.matches("^[0-9]")) || (value3.startsWith(".")))
            value3="@varFloat"+reemplazarSimbolo(value3);
        if(value2!=null && value2.startsWith("[")){
            assembler.append(op+" "+value3+"\n");
        }
        else {
            if((value2.startsWith("-")) || (value2.startsWith("+")) || (value2.matches("^[0-9]")) || (value2.startsWith(".")))
                value2="@varFloat"+reemplazarSimbolo(value2);
            assembler.append("fld "+ value2+"\n");
            assembler.append(op+" "+value3+"\n"); //segun las filminas fadd [mem] hace la suma de st(0) y el valor
        }
        assembler.append("FNSTSW ax \n");
        assembler.append("jo "+"OVERFLOW_FLOTANTE"+"\n");

    }

    public static void generarComparacionFlotante(String comp){
        if((value2.startsWith("-")) || (value2.startsWith("+")) || (value2.matches("^[0-9]")) || (value2.startsWith(".")))
            value2="@varFloat"+reemplazarSimbolo(value2);
        if((value3.startsWith("-")) || (value3.startsWith("+")) || (value3.matches("^[0-9]")) || (value3.startsWith(".")))
            value3="@varFloat"+value3;

        if(value2!=null && value2.startsWith("[")){
            if(value3!=null && value3.startsWith("[")){
                assembler.append("fcomp \n");
            }
            else
                assembler.append("fcomp " + value3+"\n");
        }
        else{
            if(value3!=null && value3.startsWith("[")){
                assembler.append("fcomp "+value2+"\n");
            }
            else{
                assembler.append("fld "+ value2+"\n");
                assembler.append("fcomp " + value3+"\n");
            }
        }
        assembler.append(comp+" ");
    }

    public static void generarConversion(){
        if(value2!=null){
            Terceto terceto=estructuraActual.getTerceto(Integer.valueOf(value2.substring(1, value2.length()-1)));
            String registro=terceto.getRegistro();
            assembler.append("mov @conv, "+getRegistroConversion(registro)+"\n");
            assembler.append("cbw \n");
            assembler.append("fild @conv \n");
            liberarRegistro(registro);
        }
    }

    public static String getRegistroConversion(String registro){
        if(registro!=null){
            switch(registro){
                case "eax":
                case "al":
                    return "eax";
                case "ebx":
                case "bl":
                    return "ebx";
                case "ecx":
                case "cl":
                    return "ecx";
                case "edx":
                case "dl":
                    return "edx";
                default: return null;
            }
        }
        return null;
    }

    public static void generarMultDiv(String op){
        int numTerceto1;
        Terceto t1;
        String registro1;
        if(value3.matches("-|[+]|[0-9]")){
            assembler.append("mov @auxOpInt, "+value3+"\n");
            value3="@auxOpInt";
        }
        if(value2!=null && value2.startsWith("[")){
            numTerceto1=Integer.valueOf(value2.substring(1, value2.length()-1));
            t1= estructuraActual.getTerceto(numTerceto1);
            registro1=t1.getRegistro();
            
            assembler.append("mov al, "+registro1+"\n");
            liberarRegistro(registro1);
            
            tercetoActual.setRegistro("al");
            registros.put("eax",true);
            assembler.append(op+" "+ value3+"\n");
            
        }
        else{
            asignarRegistroMultDiv();
            
            assembler.append("mov al, "+value2+"\n");
            tercetoActual.setRegistro("al");
            registros.put("eax",true);
            assembler.append(op+" "+ value3+"\n");
            
        }
    }

    public static void asignarRegistroMultDiv(){
        if(!registros.get("eax")){
            tercetoActual.setRegistro("al");
            registros.put("eax", true);
        }
        else{
            Terceto terceto=estructuraActual.ultimoTercetoConRegistro("eax|ax|al", numTercetoActual);
            if(terceto!=null)
                terceto.setRegistro("auxOpInt");
            tercetoActual.setRegistro("al");
        }
    }

    public static void generarOperacion(String op){
        int numTerceto1;
        Terceto t1;
        String registro1;
        if(value2!=null && value2.startsWith("[")){
            numTerceto1=Integer.valueOf(value2.substring(1, value2.length()-1));
            t1= estructuraActual.getTerceto(numTerceto1);
            registro1=t1.getRegistro();
            
            assembler.append(op+ " "+ registro1+", ");
            tercetoActual.setRegistro(registro1);

            assembler.append(value3+"\n");
        }
        else{
            asignarRegistroTerceto();
            assembler.append("mov "+tercetoActual.getRegistro()+", "+value2+"\n");
            assembler.append(op+" "+tercetoActual.getRegistro()+", "+value3+"\n");
        }
        if(value1!=null && value1.equals("+")){
            
            assembler.append("jo "+"OVERFLOW_ENTERO"+"\n");
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
            
            liberarRegistro(registro1);
        }
        else{
            asignarRegistroTerceto();
            assembler.append("mov "+ tercetoActual.getRegistro()+", " +value3+ "\n");
            assembler.append("mov "+value2+", "+tercetoActual.getRegistro()+"\n");
            //assembler.append("mov ah, 02h \n");
            //assembler.append("int 21h \n");
            liberarRegistro(tercetoActual.getRegistro());
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
            liberarRegistro(registro1);
            
            if(value3!=null && value3.startsWith("[")){
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                assembler.append(registro2+"\n");
                liberarRegistro(registro2);
                
            }
            else
                assembler.append(value3+"\n");
        }
        else{
            if(value3!=null && value3.startsWith("[")){
                numTerceto2=Integer.valueOf(value3.substring(1, value3.length()-1));
                t2= estructuraActual.getTerceto(numTerceto2);
                registro2=t2.getRegistro();
                
                liberarRegistro(registro2);
                assembler.append("cmp "+ value2+", "+ registro2+"\n");
            }
            else{
                asignarRegistroTerceto();
                assembler.append("mov "+tercetoActual.getRegistro()+", "+value2+"\n");
                assembler.append("cmp "+tercetoActual.getRegistro()+", "+value3+"\n");
                liberarRegistro(tercetoActual.getRegistro());
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
        else{
            if(value2!=null){
                assembler.append("cmp "+"FUNCION_"+reemplazarSimbolo(value2)+", 1\n");
                assembler.append("je RECURSION_MUTUA \n");
                assembler.append("mov "+reemplazarSimbolo("FUNCION_"+estructuraActual.getNombre())+", 1\n");
                
                assembler.append("call "+"LABEL_"+reemplazarSimbolo(value2)+ "\n");
                assembler.append("mov "+reemplazarSimbolo("FUNCION_"+estructuraActual.getNombre())+", 0\n");
                
                
            }
            else
                if(value2==null){
                    assembler.append("ret \n");
                }
        }
    }

    public static void asignarRegistroTerceto(){
        String tipo=tercetoActual.getTipo();
        if(tipo!=null && tipo.equals("I8")){
            tercetoActual.setRegistro(getRegistroDisponible(8));
        }
        else
            if(tipo!=null && tipo.equals("F32")){
                tercetoActual.setRegistro("st(0)");
            }
        
    }


    public static String getRegistroDisponible(int cantBits){
        String registro="";
        if(!registros.get("ebx")){
            registros.put("ebx", true);
            registro="ebx";
        }
        else{
            if(!registros.get("ecx")){
                registros.put("ecx", true);
                registro="ecx";
            }
            else{
                assembler.append("mov @aux1Int, ecx \n");
                registro="ecx";
                Terceto terceto=estructuraActual.ultimoTercetoConRegistro("ecx|ex|el", numTercetoActual);
                if(terceto!=null){
                    terceto.setRegistro("@aux1Int");
                }
            }
        
        }
        
        switch(cantBits){
            case 8:
                switch(registro){
                    case "ebx": return "bl";
                    case "ecx": return "cl";
                }
            case 16:
                switch(registro){
                    case "ebx": return "bx";
                    case "ecx": return "cx";
                }
            case 32:
                switch(registro){
                    case "ebx": return "ebx";
                    case "ecx": return "ecx";
                }
        }
        return null;
        
    }
    
    public static void liberarRegistro(String registro){
        if(registro!=null){
            switch(registro){
                case "eax":
                case "ax":
                case "ah":
                case "al":
                    registros.put("eax", false);
                    break;
                case "ebx":
                case "bx":
                case "bh":
                case "bl":
                    registros.put("ebx", false);
                    break;
                case "ecx":
                case "cx":
                case "ch":
                case "cl":
                    registros.put("ecx", false);
                    break;
                case "edx":
                case "dx":
                case "dh":
                case "dl":
                    registros.put("edx", false);
                    break;
                default: break;
            }
        }
    }


    public static String reemplazarSimbolo(String lexema){
        HashMap<String, String> mapa= new HashMap<>();
        mapa.put(":", "_");
        mapa.put("+","/");
        mapa.put(".","@");
        mapa.put("-","$");
        mapa.put(" ","_");
        String simbolo;
        if(lexema!=null){
            String resultado="";
            for(int i=0; i< lexema.length(); i++){
                simbolo=String.valueOf(lexema.charAt(i));
                if(mapa.containsKey(simbolo)){
                    resultado=resultado.concat(mapa.get(simbolo));
                }
                else
                resultado=resultado.concat(simbolo);
            }
            return resultado;
        }
        return null;
    }


    public static void generarDatos(){
        List<Simbolo> constantes= Parser.tablaDeSimbolos.getConstantes();
        List<Simbolo> idsEnteros= Parser.tablaDeSimbolos.getIdsEnteros();
        List<Simbolo> idsFlotantes= Parser.tablaDeSimbolos.getIdsFlotantes();
        List<Simbolo> cadenas=Parser.tablaDeSimbolos.getCadenas();
        List<Simbolo> flotantes=Parser.tablaDeSimbolos.getNumerosFlotantes();
        
        for(Simbolo s:flotantes){
            assembler.append("@varFloat"+reemplazarSimbolo(s.getLexema())+" dd "+s.getValor()+"\n");
        }
        for(Simbolo s: constantes){
            assembler.append(reemplazarSimbolo(s.getLexema())+" db "+ s.getValor()+"\n");
            //impresion.append("invoke StdOut, addr CONST \n");
            //impresion.append("invoke StdOut, addr "+cambiarFormato(s.getLexema())+"\n");
        }
        for(Simbolo s: idsFlotantes){
            assembler.append(reemplazarSimbolo(s.getLexema())+" dd "+ "?"+ "\n");
        }
        for(Simbolo s: cadenas){
            assembler.append(reemplazarSimbolo(s.getLexema().substring(1,s.getLexema().length()-1))+" db "+ "\""+s.getLexema().substring(1,s.getLexema().length()-1)+ "\""+ "\n");
        }
        for(Simbolo s: idsEnteros){
            assembler.append(reemplazarSimbolo(s.getLexema())+" db "+ "?"+ "\n");
            //impresion.append("invoke StdOut, addr ID\n");
            //impresion.append("invoke StdOut, addr "+cambiarFormato(s.getLexema())+"\n");
        }
        assembler.append("@aux1Float"+" dd "+ "? \n");
        assembler.append("@aux1Int"+" db "+ "? \n");
        assembler.append("@conv"+" dd "+ "? \n");
        assembler.append("@auxOpInt db ?\n");
        assembler.append("ID db \"ID:\" \n");
       //assembler.append("CONST db \"Constante:\" \n");
        assembler.append("msj_exc1 db \"Exception: Resultado de suma entre enteros excede el rango permitido\" \n");
        assembler.append("msj_exc2 db \"Exception: Resultado de suma entre flotantes excede el rango permitido\" \n");
        assembler.append("msj_exc3 db \"Exception: invocacion a funcion no permitida (invocacion mutua)\" \n");
        for(EstructuraTercetos estructura: listEstructuraTercetos){
            assembler.append("FUNCION_"+reemplazarSimbolo(estructura.getNombre())+" db 0 \n");
        }
        assembler.append("LAST_FUNCTION db ? \n");
        assembler.append("AUX_NAME_LAST_FUNCTION db ? \n");
        
    }

    

    public static void definirFunciones(){
        int n=listEstructuraTercetos.size();
        String nombreFuncion;
        
        for(int i=1; i<n; i++){
            estructuraActual=listEstructuraTercetos.get(i);
            nombreFuncion=estructuraActual.getNombre();
            assembler.append("LABEL_"+reemplazarSimbolo(nombreFuncion)+":");
            
            int n1=estructuraActual.cantTercetos();
            
            for(numTercetoActual=0; numTercetoActual<n1; numTercetoActual++){
                tercetoActual=estructuraActual.getTerceto(numTercetoActual);
                value1=tercetoActual.getValor1();
                value2=tercetoActual.getValor2();
                value3=tercetoActual.getValor3();

                chequearYgenerarInstruccion();
                
            }
            
        }
    }

    public static void generarExcepciones(){
        assembler.append("OVERFLOW_ENTERO: ");
        chequearYgenerarExcepcionOverflowEntero();
        assembler.append("OVERFLOW_FLOTANTE: ");
        chequearYgenerarExcepcionOverflowFlotante();
        assembler.append("RECURSION_MUTUA: ");
        chequearYgenerarExcepcionRecursionMutua();

    }

    public static void chequearYgenerarExcepcionOverflowEntero(){
        assembler.append("invoke StdOut, addr msj_exc1 \n");
        assembler.append("jmp end_program\n");
        assembler.append("invoke ExitProcess, 0 \n");
        assembler.append("ret \n");
    }

    public static void chequearYgenerarExcepcionOverflowFlotante(){
        assembler.append("invoke StdOut, addr msj_exc2 \n");
        assembler.append("jmp end_program\n");
        assembler.append("invoke ExitProcess, 0 \n");
        assembler.append("ret \n");
    }

    public static void chequearYgenerarExcepcionRecursionMutua(){
        assembler.append("invoke StdOut, addr msj_exc3 \n");
        assembler.append("jmp end_program\n");
        assembler.append("invoke ExitProcess, 0 \n");
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

    public static String getCodigoGenerado(){
        return assembler.toString();
    }
}