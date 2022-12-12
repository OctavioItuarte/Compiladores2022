package com.tp.compiladores;


public class AccionSemantica1 extends AccionSemantica{
	//esta accion concatena los simbolos con los anteriores 

	public AccionSemantica1() {
		super.lexema = null;
	}

	public int ejecutar(String l, String sim) {
		super.lexema = l.concat(sim);
		super.nuevaPosicion = AnalizadorLexico.posicion + 1;
		return 0;
	}

	

}