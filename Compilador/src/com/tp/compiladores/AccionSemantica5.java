package com.tp.compiladores;

import java.util.List;

public class AccionSemantica5 extends AccionSemantica{

  private TokenSimbolos tipos;
  
	public AccionSemantica5() {
		super.lexema = null;
    	tipos = new TokenSimbolos();
	}
	
	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) {
		super.lexema= l.concat(sim);
		super.nuevaPosicion= nuevaPos + 1;

		//System.out.println(super.lexema+" "+tipos.getToken(lexema));
		return tipos.getToken(lexema);
	}
}