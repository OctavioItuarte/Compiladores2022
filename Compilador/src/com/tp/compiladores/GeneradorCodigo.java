package com.tp.compiladores;
import java.util.*;

public class GeneradorCodigo{
    
    private static List<EstructuraTercetos> listEstructuraTercetos= new ArrayList<EstructuraTercetos>();
    private static Terceto tercetoActual;
    private static String value1;
    private static String value2;
    private static String value3;
    private static EstructuraTercetos estructuraActual;
    private static StringBuilder assembler=new StringBuilder();

    public GeneradorCodigo(List<EstructuraTercetos> lista){
        listEstructuraTercetos=lista;
    }

    public static void setListaEstructuras(List<EstructuraTercetos> lista){
        listEstructuraTercetos=lista;
    }

    public static void generarCodigo(){
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
        assembler.append("START:\n");
        int n=listEstructuraTercetos.size();
        estructuraActual= listEstructuraTercetos.get(0);
        
        for(int i=0; i<n; i++){
            tercetoActual=estructuraActual.getTerceto(i);
            checkAndAddValue1();
            checkAndAddValue2();
            checkAndAddValue3();
        }
        assembler.append("END START");
    }

    public static void generarDatos(){
        List<Simbolo> constantes= Parser.tablaDeSimbolos.getConstantes();
        List<String> numeros= Parser.tablaDeSimbolos.getNumeros();
        List<Simbolo> ids= Parser.tablaDeSimbolos.getIds();
        for(Simbolo s: constantes){
            assembler.append(s.getLexema()+" db "+ s.getValor()+"\n");
        }
        for(String s: numeros){
            assembler.append(s+" db "+ s+"\n");
        }
        for(Simbolo s: ids){
            assembler.append(s.getLexema()+" db "+ "?"+ "\n");
        }
        
    }

    public static void checkAndAddValue1(){
        value1=tercetoActual.getValor1();
        switch(value1){
            case "+":
            case "-":
            case "/":
            case "*":
            case "=:":
            case "<":
            case ">":
            case "<=":
            case ">=":
            case "=":
                generarOperador();
                break;
            default:
                generarSalto();
        }
    }

    public static void checkAndAddValue2(){
        value2=tercetoActual.getValor2();

        if(value2.startsWith("[")){
            value2=value2.substring(1, value2.length()-1);
            int num=Integer.valueOf(value2);
            
        }
    }

    public static void checkAndAddValue3(){

    }

    public static void generarOperador(){

    }

    public static void generarSalto(){

    }
    public static String getCodigoGenerado(){
        return assembler.toString();
    }
}