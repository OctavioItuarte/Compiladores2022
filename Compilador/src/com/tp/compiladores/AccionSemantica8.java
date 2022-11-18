package com.tp.compiladores;

import java.util.List;

public class AccionSemantica8 extends AccionSemantica {

	public AccionSemantica8() {
		super.lexema = null;
	}

	@Override
	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) {
		int longitud=l.length();

		if((longitud>0) && (sim.equals("<")) && (l.charAt(longitud-1) == '<'))
			l=l.substring(0, longitud-1);
		super.lexema=l;
		super.nuevaPosicion=nuevaPos+1;
		return 0;
	}
}