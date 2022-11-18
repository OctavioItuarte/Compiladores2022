package com.tp.compiladores;

import java.util.List;

public class AccionSemantica9 extends AccionSemantica{
    private TokenSimbolos tipos;

    public AccionSemantica9() {
        super.lexema = null;
    }

    public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) { //devuelve token
        super.lexema= l.concat(sim);
        super.nuevaPosicion = nuevaPos + 1;
        Simbolo simbolo=new Simbolo(lexema, 271);
		tabla.add(simbolo);
        System.out.println("Cadena multilinea: "+ super.lexema+" "+271);
        return 271;
    }
}
