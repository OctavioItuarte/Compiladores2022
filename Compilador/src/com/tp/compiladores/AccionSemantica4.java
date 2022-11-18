package com.tp.compiladores;

import java.util.List;

public class AccionSemantica4 extends AccionSemantica {

	public AccionSemantica4() {
		super.lexema = null;
	}

	@Override
	public int Ejecutar(TablaSimbolos tabla, String l, String sim, int nuevaPos, NumeroLinea linea, List<ErrorLinea> error) {
		super.lexema = l;
		super.nuevaPosicion = nuevaPos;

		if(sim.equals("\n")){
			linea.retrocederLinea();
		}
		
		String n = "";
		String ant = "";
		for (int i = 0; i < lexema.length(); i++){

			if (lexema.charAt(i) != 'F'){
				if (ant.equals("F")) {
					if (lexema.substring(i, i + 1).matches("[0-9]")) {
						n = n.concat("+");
					}
					ant = "null";
				}

				n = n.concat(lexema.substring(i,i+1));
			}
			else {
				n = n.concat("E");
				ant = "F";
			}
		}
		try {
			double number = Float.parseFloat(n);
			super.lexema = n;
			System.out.println(number);
			if( ( (number > 1.17549435E-38 ) && (number < 3.40282347E+38) )|| (number == 0.0) ){
				System.out.println("Constante punto flotante: "+ l+" "+ 268);
				Simbolo simbolo=new Simbolo(lexema, 268);
				tabla.add(simbolo);
				return 268;
			} else {
				System.out.println("Linea: "+linea.getNumeroLinea()+" Error: Rango de float no permitido");
				return -1 ;
			}
		}
		catch (NumberFormatException ex){
			System.out.println("Linea: "+linea.getNumeroLinea()+" Error: Formato de float no permitido");
			return -1;
		}
	}

}