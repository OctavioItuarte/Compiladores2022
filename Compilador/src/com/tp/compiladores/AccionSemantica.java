package com.tp.compiladores;

import java.util.List;

public abstract class AccionSemantica {

  protected String lexema;
  protected int nuevaPosicion;
  
	public AccionSemantica() {
		// TODO Auto-generated constructor stub
	}

	public abstract int Ejecutar(TablaSimbolos tabla, String lexema, String sim, int nuevaPosicion, NumeroLinea linea, List<ErrorLinea> error);
  
  public int getPosicion(){
    return nuevaPosicion;
  }

	public String getLexema(){
    return lexema;
  }

}
