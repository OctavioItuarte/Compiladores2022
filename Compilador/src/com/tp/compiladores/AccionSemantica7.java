package com.tp.compiladores;

import java.util.List;

public class AccionSemantica7 extends AccionSemantica {

	public AccionSemantica7() {
		super.lexema = null;
	}

	@Override
	public int ejecutar(String l, String sim) {
		if (sim.equals("F")) {
			super.lexema = l.concat(sim);
			super.nuevaPosicion = AnalizadorLexico.posicion + 1;
			return 0;
		}else {
			Parser.errores_lexicos.add(new ErrorLinea("Formato de float no reconocido", Parser.linea.getNumeroLinea()));
			System.out.print("Linea: "+Parser.linea.getNumeroLinea()+" Formato de float no reconocido");
			return -1 ;
		}
	}
}
