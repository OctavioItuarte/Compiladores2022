package com.tp.compiladores;

import java.util.List;

public class AccionSemantica2 extends AccionSemantica {
	
	private PalabrasReservadas palabrasReservadas;

	public AccionSemantica2() {
		palabrasReservadas = new PalabrasReservadas();
		super.lexema = null;
	}

	@Override
	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) { //devuelve token
		super.nuevaPosicion = nuevaPos;
		super.lexema = l;
        int token = palabrasReservadas.getToken(l);

		if(sim.equals("\n")){
			linea.retrocederLinea();
		}
		
		if (token != -1){
			System.out.println("Palabra reservada: "+l+" "+token);
			return token;
		}
		else {
			if (l.length() > 25) {
				super.lexema = l.substring(0, 25);
				System.out.println("Linea: "+linea+" WARNING: longitud de identificador no permitida");
			}
			
			//Simbolo simbolo=new Simbolo(lexema, 269);
			//tabla.add(simbolo);
			
			System.out.println("ID: "+ super.lexema+" "+269);
			return 269;
		}
	}

}