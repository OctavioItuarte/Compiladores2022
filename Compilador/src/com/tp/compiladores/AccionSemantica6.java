package com.tp.compiladores;

import java.util.List;

public class AccionSemantica6 extends AccionSemantica {

  private TokenSimbolos tipos;
  
	public AccionSemantica6() {
		super.lexema = null;
    tipos = new TokenSimbolos();
	}

	@Override
	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) {
		super.lexema = l;
		super.nuevaPosicion = nuevaPos;

		if(sim.equals("\n")){
			linea.retrocederLinea();
		}
		
		System.out.println(super.lexema+" "+tipos.getToken(lexema));
		return tipos.getToken(l);
	}

}