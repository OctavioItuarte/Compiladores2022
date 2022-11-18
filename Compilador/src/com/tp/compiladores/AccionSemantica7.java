package com.tp.compiladores;

import java.util.List;

public class AccionSemantica7 extends AccionSemantica {

	public AccionSemantica7() {
		super.lexema = null;
	}

	@Override
	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) {
		if (sim.equals("F")) {
			super.lexema = l.concat(sim);
			super.nuevaPosicion = nuevaPos + 1;
			return 0;
		}else {
			System.out.print("Linea: "+linea.getNumeroLinea()+" Formato de float no reconocido");
			return -1 ;
		}
	}
}
