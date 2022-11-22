package com.tp.compiladores;

import java.util.List;

public class AccionSemantica3 extends AccionSemantica {

	public AccionSemantica3() {
		super.lexema = null;
	}

	@Override
	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) {  //devuelve token
		super.lexema = l;
		super.nuevaPosicion = nuevaPos;

		if(sim.equals("\n")){
			linea.retrocederLinea();
		}
		
		try{
			int number = Integer.parseInt(l);
			if (number<=128){
				//System.out.println("Constante entera: "+l+" "+267);
				Simbolo simbolo=new Simbolo(lexema, 267, "valor_numerico");
				tabla.add(simbolo);
				return 267;
			}
			else{
				System.out.println("Linea: "+linea.getNumeroLinea()+" Error: Rango de int no permitido");
				return -1 ;
			}
		}catch (NumberFormatException ex){
			System.out.println("Linea: "+linea.getNumeroLinea()+" Error: Formato de int no permitido");
			return -1 ;
		}
	}
}