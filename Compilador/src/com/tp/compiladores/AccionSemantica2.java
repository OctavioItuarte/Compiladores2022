package com.tp.compiladores;

import java.util.List;

public class AccionSemantica2 extends AccionSemantica {
	
	private PalabrasReservadas palabrasReservadas;

	public AccionSemantica2() {
		palabrasReservadas = new PalabrasReservadas();
		super.lexema = null;
	}

	@Override
	public int ejecutar(String l, String sim) { //devuelve token
		super.nuevaPosicion = AnalizadorLexico.posicion;
		super.lexema = l;
        int token = palabrasReservadas.getToken(l);

		if(sim.equals("\n")){
			Parser.linea.retrocederLinea();
		}
		
		if (token != -1){
			//System.out.println("Palabra reservada: "+l+" "+token);
			return token;
		}
		else {
			if (l.length() > 25) {
				super.lexema = l.substring(0, 25);
				Parser.errores_lexicos.add(new ErrorLinea("WARNING: longitud de identificador no permitida", Parser.linea.getNumeroLinea()));
				System.out.println("Linea: "+Parser.linea+" WARNING: longitud de identificador no permitida");
			}
			
			//Simbolo simbolo=new Simbolo(lexema, 269);
			//tabla.add(simbolo);
			
			//System.out.println("ID: "+ super.lexema+" "+269);
			return 269;
		}
	}

}