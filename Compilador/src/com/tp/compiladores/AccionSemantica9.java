package com.tp.compiladores;

import java.util.List;

public class AccionSemantica9 extends AccionSemantica{
    private TokenSimbolos tipos;

    public AccionSemantica9() {
        super.lexema = null;
    }

    public int ejecutar(String l, String sim) { //devuelve token
        super.lexema= l.concat(sim);
        super.nuevaPosicion = AnalizadorLexico.posicion + 1;
        Simbolo simbolo=new Simbolo(lexema, 271,"cadena_caracteres");
		Parser.tablaDeSimbolos.add(simbolo);
        //System.out.println("Cadena multilinea: "+ super.lexema+" "+271);
        return 271;
    }
}
