package com.tp.compiladores;

import java.util.List;

public abstract class AccionSemantica {

  protected String lexema;
  protected int nuevaPosicion;
  
	public AccionSemantica() {
		// TODO Auto-generated constructor stub
	}

	public abstract int ejecutar(String lexema, String sim);
  
  public int getPosicion(){
    return nuevaPosicion;
  }

	public String getLexema(){
    return lexema;
  }

}
