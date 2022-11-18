package com.tp.compiladores;

import java.util.List;

public class AccionSemantica1 extends AccionSemantica{
	//esta accion concatena los simbolos con los anteriores 

	public AccionSemantica1() {
		super.lexema = null;
	}

	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) {
		super.lexema = l.concat(sim);
		super.nuevaPosicion = nuevaPos + 1;
		return 0;
	}

}