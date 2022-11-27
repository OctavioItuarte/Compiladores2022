package com.tp.compiladores;

import java.util.List;

public class AccionSemantica8 extends AccionSemantica {

	public AccionSemantica8() {
		super.lexema = null;
	}

	@Override
	public int ejecutar(String l, String sim) {
		int longitud=l.length();

		if((longitud>0) && (sim.equals("<")) && (l.charAt(longitud-1) == '<'))
			l=l.substring(0, longitud-1);
		super.lexema=l;
		super.nuevaPosicion=AnalizadorLexico.posicion+1;
		return 0;
	}
}