package com.tp.compiladores;

import java.util.List;

public class AccionSemantica3 extends AccionSemantica {

	public AccionSemantica3() {
		super.lexema = null;
	}

	@Override
	public int ejecutar(String l, String sim) {  //devuelve token
		super.lexema = l;
		super.nuevaPosicion = AnalizadorLexico.posicion;

		if(sim.equals("\n")){
			Parser.linea.retrocederLinea();
		}
		
		try{
			int number = Integer.parseInt(l);
			if (number<=128){
				//System.out.println("Constante entera: "+l+" "+267);
				Simbolo simbolo=new Simbolo(lexema, 267, "valor_numerico", "I8", lexema);
				Parser.tablaDeSimbolos.add(simbolo);
				return 267;
			}
			else{
				Parser.errores_lexicos.add(new ErrorLinea("Error: Rango de int no permitido", Parser.linea.getNumeroLinea()));
				System.out.println("Linea: "+Parser.linea.getNumeroLinea()+" Error: Rango de int no permitido");
				return -1 ;
			}
		}catch (NumberFormatException ex){
			Parser.errores_lexicos.add(new ErrorLinea("Error: Formato de int no permitido", Parser.linea.getNumeroLinea()));
			System.out.println("Linea: "+Parser.linea.getNumeroLinea()+" Error: Formato de int no permitido");
			return -1 ;
		}
	}
}