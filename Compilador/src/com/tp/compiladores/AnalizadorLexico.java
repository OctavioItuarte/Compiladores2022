package com.tp.compiladores;

import java.util.*;

public class AnalizadorLexico {

	private int[][] matrizDeTransicion;
	private AccionSemantica[][] matrizDeAccionesSemanticas;
	final String input; // para trabajar con la entrada
	private TablaSimbolos tablaDeSimbolos;
	private int posicion; //guardo la posicion para leer de a un caracter
	private int estado;
	private List<ErrorLinea> errores;
	private NumeroLinea linea; 

	public AnalizadorLexico(TablaSimbolos tablaDeSimbolos, String input, List<ErrorLinea> errores, NumeroLinea linea) {
		// TODO Auto-generated constructor stub
		this.tablaDeSimbolos=tablaDeSimbolos;
		this.errores=errores;
		this.linea=linea;
		this.matrizDeTransicion = new int[][] {     //////
			{1,	2,	5,	6,	102,	-1,	7,	8,	102,102,	102,102,102,0,	0,	0,	-1,	11,	-1,0},
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
		this.posicion=0;
		this.input = input;
		AccionSemantica1 AS1 = new AccionSemantica1();
		AccionSemantica2 AS2 = new AccionSemantica2();
		AccionSemantica3 AS3=new AccionSemantica3();
		AccionSemantica4 AS4=new AccionSemantica4();
		AccionSemantica5 AS5=new AccionSemantica5();
		AccionSemantica6 AS6=new AccionSemantica6();
		AccionSemantica7 AS7=new AccionSemantica7();
		AccionSemantica8 AS8=new AccionSemantica8();
		AccionSemantica9 AS9=new AccionSemantica9();
		this.matrizDeAccionesSemanticas=new AccionSemantica[][] {           ///////////
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
			{AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS1,	AS8,	AS1,	AS8,	AS8,	AS8,	AS2,	AS9,	AS2,	AS8},
			{AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8},
			{AS8,	AS1,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8,	AS8},
			{AS4,	AS1,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS4,	AS8}
	};

	}

	public Simbolo yyLex() {
		if(posicion==input.length())
			return new Simbolo(null, 0);
		String sim;
		this.estado = 0;
		int columna;
		String lexema = "";
		int valor = 0;
		AccionSemantica AS;
		while ((estado != 102) && (estado != -1) && (posicion<=input.length()-1) ) {
			sim = input.substring(posicion, posicion+1);
			columna = Matcher(sim);
			AS= matrizDeAccionesSemanticas[estado][columna];
			valor = AS.Ejecutar(tablaDeSimbolos, lexema, sim, posicion, linea, errores); //la accion semantica me tiene que devolver el string que va armando
			lexema = AS.getLexema();
			posicion = AS.getPosicion();
			this.estado = matrizDeTransicion[estado][columna];
		}
		if (estado  == -1){
			System.out.println(" Linea: " + linea + " Error: simbolo no reconocido");
		}
		
		return new Simbolo(lexema, valor);
	}

	

	/**
	 * @param sim
	 * @return
	 */
	private int Matcher(String sim) {  
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
			this.linea.avanzarLinea();
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


	// public static void main(String[] args) {
	//     try{
	//         String contenido = new String(Files.readAllBytes(Paths.get("codigo.txt")));
	//         AnalizadorLexico AL=new AnalizadorLexico(contenido);
    // 	    Parser parser= new Parser(AL);
    //         System.out.println("parse: "+parser.yyparse());
    //     }
    //     catch (IOException e) {
    //           // TODO Auto-generated catch blockVAST:
    //           e.printStackTrace();
    //     }
	// }
	/*
	 public static void main(String[] args) throws IOException {
		 File doc = new File("codigo.txt");
		 String contenido="";
		 Scanner obj = new Scanner(doc);

		 while (obj.hasNextLine())
			 contenido=contenido+"\n"+obj.nextLine();
	 	AnalizadorLexico AL=new AnalizadorLexico(contenido);
	 	Token token;
	 	for (int i=0; i<10; i++){
	 		token=AL.yyLex();
	 		System.out.println("token: "+ token.getValor()+" - lexema: "+token.getLexema());
	 		System.out.println("---------------------");
	 	}
	 }
	*/
}