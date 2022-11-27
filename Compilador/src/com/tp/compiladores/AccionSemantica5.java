package com.tp.compiladores;

import java.util.List;

public class AccionSemantica5 extends AccionSemantica{

  private TokenSimbolos tipos;
  
	public AccionSemantica5() {
		super.lexema = null;
    	tipos = new TokenSimbolos();
	}
	
	public int ejecutar(String l, String sim) {
		super.lexema= l.concat(sim);
		super.nuevaPosicion= AnalizadorLexico.posicion + 1;

		//System.out.println(super.lexema+" "+tipos.getToken(lexema));
		return tipos.getToken(lexema);
	}
}