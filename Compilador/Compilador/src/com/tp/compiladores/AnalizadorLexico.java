package com.tp.compiladores;

public class AnalizadorLexico {

	private static int[][] matrizDeTransicion= new int[][] {     //////
		{1,	2,	5,	6,	102,-1,	7,	8,	102,102,	102,102,102,0,	0,	0,	-1,	11,	-1,0},
		{1,	1,	102,102,102,1,102,102,102,102,102,102,102,102,102,102,102,102,102,1},
		{102,2,	3,	102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,2},
		{4,	3,	102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,3},
		{-1,	14,	-1,	-1,	-1,	-1,	-1,	-1,	13,	13,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,-1},
		{-1,	3,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,-1},
		{102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,6},
		{102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,7},
		{102,102,102,102,102,102,102,9,102,102,102,102,102,102,102,102,102,102,102,8},
		{9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	0,	9,	9,	9,9},
		{102,2  ,5  ,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,10},
		{11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	12,	11,	11,	11,	-1,	11,	102, 11,-1},
		{-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	11,	-1,	102, -1,12},
		{-1,	14,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,-1},
		{102, 14, 102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,14},
	};
	private static AccionSemantica1 AS1 = new AccionSemantica1();
	private static AccionSemantica2 AS2 = new AccionSemantica2();
	private static AccionSemantica3 AS3=new AccionSemantica3();
	private static AccionSemantica4 AS4=new AccionSemantica4();
	private static AccionSemantica5 AS5=new AccionSemantica5();
	private static AccionSemantica6 AS6=new AccionSemantica6();
	private static AccionSemantica7 AS7=new AccionSemantica7();
	private static AccionSemantica8 AS8=new AccionSemantica8();
	private static AccionSemantica9 AS9=new AccionSemantica9();

	private static AccionSemantica[][] matrizDeAccionesSemanticas=new AccionSemantica[][] {           ///////////
		{AS1,	AS1,	AS1,	AS1,	AS5,	AS8,	AS1,	AS1,	AS5,	AS5,	AS5,	AS5,	AS5,	AS8,	AS8,	AS8,	AS8,	AS1,	AS8,	AS8},
		{AS1,	AS1,	AS2,	AS2,	AS2,	AS1,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS2,	AS8},
		{AS3,	AS1,	AS1,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS3,	AS8},
		{AS7,	AS1,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS8},
		{AS8,	AS1,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS1,	AS1,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8},
		{AS8,	AS1,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8},
		{AS6,	AS6,	AS6,	AS6,	AS5,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS5,	AS6,	AS6,	AS8},
		{AS6,	AS6,	AS6,	AS5,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS8},
		{AS6,	AS6,	AS6,	AS5,	AS6,	AS6,	AS6,	AS8,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS8},
		{AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8},
		{AS6,	AS1,	AS1,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS6,	AS8},
		{AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS8,	AS1,	AS1,	AS1,	AS8,	AS1,	AS9,	AS8,	AS8},
		{AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8},
		{AS8,	AS1,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8},
		{AS4,	AS1,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS8}
		};

	public static int posicion=0;
	public static int estado=0;
	public static String codigo;

	public static void addInput(String input){
		codigo=input;
		posicion=0;
	}

	public static Simbolo yyLex() {
		if(posicion==codigo.length())
			return new Simbolo(null, 0);
		String sim;
		estado = 0;
		int columna;
		String lexema = "";
		int valor = 0;
		AccionSemantica AS;
		while ((estado != 102) && (estado != -1) && (posicion<=codigo.length()-1) ) {
			sim = codigo.substring(posicion, posicion+1);
			columna = matcher(sim);
			AS= matrizDeAccionesSemanticas[estado][columna];
			valor = AS.ejecutar(lexema, sim); //la accion semantica me tiene que devolver el string que va armando
			lexema = AS.getLexema();
			posicion = AS.getPosicion();
			estado = matrizDeTransicion[estado][columna];
			//System.out.println(lexema);
		}
		if (estado  == -1){
			System.out.println(" Linea: " + Parser.linea.getNumeroLinea() + " Error: simbolo no reconocido");
		}
		
		return new Simbolo(lexema, valor);
	}

	

	/**
	 * @param sim
	 * @return
	 */
	private static int matcher(String sim) {  
		if (sim.matches("[a-zA-Z]"))
			return 0;
		if (sim.matches("[0-9]"))
			return 1;
		if (sim.equals("."))
			return 2;
		if (sim.equals("="))
			return 3;
		if (sim.equals(":"))
			return 4;
		if (sim.equals("_"))
			return 5;
		if (sim.equals(">"))
			return 6;
		if (sim.equals("<"))
			return 7;
		if (sim.equals("+"))
			return 8;
		if (sim.equals("-"))
			return 9;
		if (sim.equals("*"))
			return 10;
		if (sim.equals("/"))
			return 11;
		if (sim.matches("[(|)|{|}|;|,]"))
			return 12;
		if (sim.equals(" "))
			return 13;
		if (sim.matches("\t"))
			return 14;
		if (sim.equals("\n")){
			Parser.linea.avanzarLinea();
			return 15;
		}
		if (sim.equals("!"))
			return 16;
		if (sim.equals("'"))
			return 17;
		if (sim.equals("\r"))
			return 19;
		else
			return 18;
	}
}