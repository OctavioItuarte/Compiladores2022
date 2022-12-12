package com.tp.compiladores;


public class AccionSemantica6 extends AccionSemantica {

  private TokenSimbolos tipos;
  
	public AccionSemantica6() {
		super.lexema = null;
    tipos = new TokenSimbolos();
	}

	@Override
	public int ejecutar(String l, String sim) {
		super.lexema = l;
		super.nuevaPosicion = AnalizadorLexico.posicion;

		if(sim.equals("\n")){
			Parser.linea.retrocederLinea();
		}
		
		//System.out.println(super.lexema+" "+tipos.getToken(lexema));
		return tipos.getToken(l);
	}

}