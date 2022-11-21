%{
package com.tp.compiladores;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
%}

%token IF THEN ELSE END_IF OUT FUN RETURN I8 F32 WHEN CTE_ENTERA CTE_FLOTANTE ID CONTINUE CADENA_MULT ASIGNACION 
//    257  258  259 260    261 262  263  264  265 266  267          268       269  270     271            272     
MAYOR_IGUAL MENOR_IGUAL DISTINTO CONST TOF32
//273         274        275      276   277
BREAK FOR 
//278  279

%left ASIGNACION
%left '+' '-'
%left '*' '/'

%start Programa
%%


Programa: Nombre_programa '{' ListSentencias '}' {desconcatenarAmbito();}
		| Nombre_programa ListSentencias '}' {AgregarErrorSintactico("Se espera '{' ");}
		| Nombre_programa ListSentencias {AgregarErrorSintactico("Se esperan '{' '}' ");}
		| error '}' {AgregarErrorSintactico("Se espera ';'");}
        ;

Nombre_programa: ID {ambito= $1.sval; tablaDeSimbolos.add(new Simbolo($1.sval, 269,"nombre_programa"));
					 estructuraActual=estructuraTercetosPrincipal;
					 listEstructurasSeguimiento.add(estructuraActual);
					 listEstructurasTercetos.add(estructuraActual);
					 }
               ;

ListSentencias: SentenciaControl ListSentencias
			  | SentenciaDeclarativa ListSentencias
			  | SentenciaEjecutable ListSentencias
			  | SentenciaEjecutable
			  | SentenciaControl
			  | SentenciaDeclarativa
              ;


SentenciaDeclarativa: Tipo ListVariables ';'
					| Tipo error ';' {AgregarErrorSintactico("Se espera identificador");}
			        | Funcion
			        | CONST ListCte ';'
					| WhenCondicion
					| ListVariables ';' {AgregarErrorSintactico("Se espera el tipo de la variable");}
					| Tipo ';' {AgregarErrorSintactico("Se espera identificador de la variable");}
                    ;

ListCte : AsigCte
	    | ListCte ',' AsigCte
        ;

AsigCte: ID ASIGNACION CTE_ENTERA {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);}
	   | ID ASIGNACION signo CTE_ENTERA {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval + $4.sval);}
	   | ID ASIGNACION  CTE_FLOTANTE {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);}
	   | ID ASIGNACION signo CTE_FLOTANTE {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval + $4.sval);}
	   | ASIGNACION cte {AgregarErrorSintactico("Se espera un identificador");}
	   | ID ASIGNACION  {AgregarErrorSintactico("Se espera una constante ");}
	   | ID cte {AgregarErrorSintactico("Se espera '=:' ");}
       ;

Tipo: I8 {estructuraActual.setTipo("I8");}
    | F32 {estructuraActual.setTipo("F32");}
    ;

ListVariables: ID ',' ListVariables {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
			 | ID {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
			 | ID ListVariables {AgregarErrorSintactico("Se espera ',' ");}
             ;

HeaderFuncion: Fun '(' Parametro ',' Parametro ')' ':' Tipo {tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo()); }
			 | Fun '(' Parametro ')' ':' Tipo {tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo());}
			 | Fun '(' ')' ':' Tipo {tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo());}

			 | FUN '(' Parametro ',' Parametro ')' ':' Tipo {AgregarErrorSintactico("Se espera el identificador de la funcion ");}
	   		 | FUN '(' Parametro ')' ':' Tipo {AgregarErrorSintactico("Se espera el identificador de la funcion ");}
	   		 | FUN '(' ')' ':' Tipo {AgregarErrorSintactico("Se espera el identificador de la funcion ");}
			 | Fun '(' Parametro ',' Parametro ')' ':' {AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
	   		 | Fun '(' Parametro ')' ':'  {AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
	   		 | Fun '(' ')' ':'  {AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
			 ;

Fun: FUN ID {estructuraActual=new EstructuraTercetos($2.sval+":"+ambito);
			 ambito= ambito+":"+$2.sval; tablaDeSimbolos.add(new Simbolo(ambito, 269, "identificador_funcion"));
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 }
	;

Funcion: HeaderFuncion '{' Cuerpo '}' {desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   }
	   | HeaderFuncion '{' '}' {AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
	   | '{' Cuerpo '}' {AgregarErrorSintactico("Se espera el header de la funcion");}
	   | HeaderFuncion'{' ListSentencias '}' {AgregarErrorSintactico("Se espera el retorno de la funcion");}
	   ;


Parametro: Tipo ID {tablaDeSimbolos.add(new Simbolo($2.sval+":"+ambito,269,"parametro", estructuraActual.getTipo()));}
	     | ID {AgregarErrorSintactico("Se espera tipo de parametro");}
		 ;

Cuerpo: SentenciaEjecutable Cuerpo
      | SentenciaDeclarativa Cuerpo
	  | SentenciaReturn
	  ;

SentenciaEjecutable: Seleccion
                   | Asig
		           | Salida ';'
                   ;

Asig: ID ASIGNACION Expresion ';'{estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), estructuraActual.getRefTerceto(1));}
    | ID ASIGNACION ID ';' {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
    | ID ASIGNACION cte ';' {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);}
	| ID ASIGNACION signo cte ';' {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval + $4.sval);}
	| ID error ';' {AgregarErrorSintactico("Se espera '=:'");}
    ;

HeaderWhen: WHEN Condicion THEN {ambito= ambito+":"+"when";}
		  | Condicion THEN {AgregarErrorSintactico("Se espera un if o un when");}
		  | WHEN Condicion {AgregarErrorSintactico("Se espera la palabra reservada then");}
		  ;

WhenCondicion: HeaderWhen '{' ListSentencias '}'
			 | error '}' {AgregarErrorSintactico("Se espera ';'");}
			 ;


Salida : OUT '(' CADENA_MULT ')' 
	   |'(' CADENA_MULT ')' {AgregarErrorSintactico("Se espera un out");}
	   ;
			

ParametroReal: cte
			 | signo cte
			 | ID 
             ;
			 
Expresion: ID signo ID {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
	     | ID signo cte {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);} 
	     | Expresion signo ID {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
	     | Expresion signo cte {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), $3.sval);}
	     | ID '*' ID {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
	     | ID '/' ID {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
	     | ID '*' cte {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);}
	     | ID '/' cte {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);}
	     | Expresion '*' ID  {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
	     | Expresion '/' ID {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
	     | Expresion '*' cte {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), $3.sval);}
	     | Expresion '/' cte {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), $3.sval);}
		 | ConversionExplicita
		 | cte signo ID {estructuraActual.crearTerceto($2.sval, $1.sval, tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
		 | cte '*' ID {estructuraActual.crearTerceto($2.sval, $1.sval, tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
		 | cte '/' ID {estructuraActual.crearTerceto($2.sval, $1.sval, tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
		 | ID ListParametrosInv
         ;

ListParametrosInv: '(' ')'
				 | '(' ParametroReal ')'
				 | '(' ParametroReal ',' ParametroReal ')'
				 ;

ConversionExplicita: TOF32 '(' Expresion ')'
				   | TOF32 '('')' {AgregarErrorSintactico("Se espera expresion");}
				   ;

signo: '+'
	 | '-'
	 ;


cte: CTE_FLOTANTE
   | CTE_ENTERA {chequearRangoEntero($1.sval);}
   ;

SentenciaReturn: RETURN '(' ID ')' ';'
	  		   | RETURN '(' cte ')' ';'
	  		   | RETURN '(' Expresion ')' ';'
			   | RETURN '(' ID ')' {AgregarErrorSintactico("Falta ;");}
	  		   | RETURN '(' cte ')' {AgregarErrorSintactico("Falta ;");}
	  		   | RETURN '(' Expresion ')' {AgregarErrorSintactico("Falta ;");}
	  		   | RETURN ';' {AgregarErrorSintactico("Falta expresion de retorno");}
			   ;

ListSentenciasIf: SentenciaEjecutable ListSentenciasIf
                | SentenciaCorte ListSentenciasIf
                | SentenciaEjecutable
                | SentenciaCorte
                ;

HeaderIf: IF Condicion THEN
        | error THEN {yyerror("falta cerrar parentesis");}
        ;

SentenciasIf: SentenciaEjecutable
        	| '{' ListSentenciasIf '}'
        	;


CuerpoIf: SentenciasIf
        ;

Else: ELSE {estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();}
	;

CuerpoElse: Else CuerpoIf END_IF {estructuraActual.completarTercetoIf(1);}
          | Else CuerpoIf error {yyerror("falta palabra reservada end_if");}
          ;

Seleccion: HeaderIf CuerpoIf END_IF {estructuraActual.completarTercetoIf(1);}
         | HeaderIf CuerpoIf CuerpoElse
         | HeaderIf CuerpoIf error {yyerror("falta palabra reservada end_if");}
		 | error ELSE {AgregarErrorSintactico("Se espera '{' '}' ");}
		 | error END_IF {AgregarErrorSintactico("Se espera '{' '}' ");}
		 ;

Condicion: '(' Comparacion ')' {estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
								estructuraActual.addTercetoIf();}
		 | '(' Comparacion {AgregarErrorSintactico("Falta cerrar parentesis");}
		 | Comparacion ')' {AgregarErrorSintactico("Falta abrir parentesis");}
		 ;

Comparador: '<'
          | '>'
          | MAYOR_IGUAL
          | MENOR_IGUAL
          | '='
          | DISTINTO
          ;

Comparacion: Expresion Comparador Expresion {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(2), estructuraActual.getRefTerceto(1));}
           | Expresion Comparador cte {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), $3.sval);}
		   | Expresion Comparador signo cte {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), $3.sval + $4.sval );}
           | cte Comparador Expresion {estructuraActual.crearTerceto($2.sval, $1.sval, estructuraActual.getRefTerceto(1));}
		   | signo cte Comparador Expresion {estructuraActual.crearTerceto($3.sval, $1.sval + $2.sval, estructuraActual.getRefTerceto(1));}
           | Expresion Comparador ID {estructuraActual.crearTerceto($2.sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
           | ID Comparador Expresion {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), estructuraActual.getRefTerceto(1));}
		   | cte Comparador ID {estructuraActual.crearTerceto($2.sval, $1.sval, tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
		   | signo cte Comparador ID {estructuraActual.crearTerceto($3.sval, $1.sval + $2.sval, tablaDeSimbolos.getRefSimbolo($4.sval, ambito));}
		   | ID Comparador cte {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);}
		   | ID Comparador signo cte {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval + $4.sval);}
		   | ID Comparador ID {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
		   | cte Comparador cte {estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);}
		   | Comparador error {yyerror("falta expresion en la comparacion");}
		   | error ID {yyerror("falta comparador");}
		   | error cte {yyerror("falta comparador");}
           ;


ListSentenciasFor: ListSentenciasFor SentenciaEjecutable
                 | ListSentenciasFor SentenciaControl
				 | ListSentenciasFor SentenciaCorte
                 | SentenciaControl
                 | SentenciaEjecutable
                 | SentenciaCorte
                 ;

HeaderFor: FOR '(' AsigFor ';' CondicionFor ';' signo CTE_ENTERA ')' 
				{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto($7.sval, estructuraActual.getIdFor(), $8.sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
		 | FOR '(' AsigFor ';' CondicionFor ';' CTE_ENTERA ')'
				{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto("+", estructuraActual.getIdFor(), $7.sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 } 
		  ;

HeaderForID: ID ASIGNACION HeaderFor {estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo($1.sval,ambito));
										}
		   ;

HeaderEtiqueta: ID ':' HeaderFor {//tablaDeSimbolos.add(new Simbolos($1.sval+":"+ambito, 269, "etiqueta_for")); estructuraActual.addEtiquetaFor($1.sval);
									}
			  ;

AsigFor: ID ASIGNACION CTE_ENTERA
		{estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo($1.sval, ambito));
		estructuraActual.crearTerceto($2.sval, estructuraActual.getIdFor(), $3.sval);
	 	}
	   | ID ASIGNACION ID
		{estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo($1.sval, ambito));
		estructuraActual.crearTerceto($2.sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));
	 	}
       ;

CuerpoFor: '{' ListSentenciasFor '}'
					{
					 estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.completarTercetoFor(1);
					 estructuraActual.popIdFor();}
					 
		| '{''}' ';' {AgregarErrorSintactico("Se esperan sentencias ejecutables");}
		;


SentenciaControl: HeaderFor CuerpoFor {
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
				}
				| HeaderEtiqueta CuerpoFor {
					//estructuraActual.addRefEtiqueta();
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
				}
				| HeaderForID CuerpoFor ELSE cte ';' {
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $4.sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
					esperandoBreakcte=false;
					estructuraActual.borrarIdAsigFor();
					}

				| HeaderForID CuerpoFor ELSE signo cte ';' {
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $4.sval + $5.sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.borrarListTercetosBreak();
					estructuraActual.borrarListTercetosBreakCte();
					dentroDeFor=false;
					esperandoBreakcte=false;
					estructuraActual.borrarIdAsigFor();
				}
				;


CondicionFor: ID Comparador ID {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}
			| ID Comparador Expresion {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), estructuraActual.getRefTerceto(1));}
			| ID Comparador CTE_ENTERA {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);}
			| ID Comparador signo CTE_ENTERA {estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval+$4.sval);}
            ;

SentenciaCorte: SentenciaReturn 
        	  | BREAK ';' {
				if(dentroDeFor){
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreak();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
					System.out.println("No existe ID para la asignacion");
				}
			  }
        	  | BREAK cte ';' {
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $2.sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
					System.out.println("No existe ID para la asignacion");
				}
				}
		 	  | BREAK signo cte ';' {
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $2.sval + $3.sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
					System.out.println("No existe ID para la asignacion");
			  	}
			  }
        	  | CONTINUE ':' ID ';' {
				//if ((dentroDeFor) && (estructuraActual.existeEtiqueta($3.sval))){
					//estructuraActual.crearTerceto("BI", estructuraActual.getRefTercetoEtiqueta($3.sval), null);
					//estructuraActual.guardarTercetoEtiquetaFor();

				//}
			  }
			  ;


%%

public static final List<ErrorLinea> errores_sintacticos = new ArrayList<>();
public static final List<ErrorLinea> errores_yacc = new ArrayList<>();
public static final List<ErrorLinea> errores_lexicos = new ArrayList<>();
public static final List<ErrorLinea> errores_semanticos = new ArrayList<>();
public static final NumeroLinea linea = new NumeroLinea();
public static final TablaSimbolos tablaDeSimbolos= new TablaSimbolos(errores_semanticos, linea);
public static final EstructuraTercetos estructuraTercetosPrincipal= new EstructuraTercetos("programa_principal");
public static List<EstructuraTercetos> listEstructurasSeguimiento=new ArrayList<>();
public static List<EstructuraTercetos> listEstructurasTercetos=new ArrayList<>();
public static EstructuraTercetos estructuraActual;

public static boolean dentroDeFor=false;
public static boolean esperandoBreakcte=false;
public static boolean dentroDeFun=false;

//private String tipo; //guarda el tipo de la lista de id que se estan declarando

public static String ambito = "";

//-----------------------------------------------------------------------

AnalizadorLexico lexico;



	public Parser(AnalizadorLexico lexico){
		this.lexico = lexico;
	}

	private int yylex(){
		Simbolo simbolo = lexico.yyLex();

		if ((simbolo.getToken() != 0) || (simbolo.getToken() != -1))
			yylval = new ParserVal(simbolo.getLexema());
		
		return simbolo.getToken();

	}

	public void AgregarErrorSintactico(String error){
	    ErrorLinea err= new ErrorLinea(error, linea.getNumeroLinea());
		errores_sintacticos.add(err);
	}
	
	public void yyerror(String error){
	    System.out.println(error);
	    ErrorLinea err= new ErrorLinea(error, linea.getNumeroLinea());
		errores_yacc.add(err);
	}

	public static void main(String[] args) {
	    try{
	        String contenido = new String(Files.readAllBytes(Paths.get("codigo.txt")));
	        AnalizadorLexico AL=new AnalizadorLexico(tablaDeSimbolos, contenido, errores_lexicos, linea);
    	    Parser parser= new Parser(AL);
            System.out.println("parse: "+parser.yyparse());
			
        }
        catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
        }
		 if (!errores_lexicos.isEmpty()){
		 	System.out.println("Errores lexicos");
		 	for(ErrorLinea e: errores_lexicos)
		 	    e.imprimirError();
		}
		if (!errores_sintacticos.isEmpty()){
			System.out.println("Errores Sintacticos");
			for(ErrorLinea e: errores_sintacticos)
		 	    e.imprimirError(); 
		}
		if (!errores_yacc.isEmpty()){
			System.out.println("Errores Yacc");
			for(ErrorLinea e: errores_yacc)
		 	    e.imprimirError();
		}
		if (!errores_semanticos.isEmpty()){
			System.out.println("Errores Semanticos");
			for(ErrorLinea e: errores_semanticos)
		 	    e.imprimirError();
		}
		System.out.println("Tabla de simbolos:");
		
		tablaDeSimbolos.imprimir();

		System.out.println("Tercetos:");
		int n=listEstructurasTercetos.size();
		
		for(int i=0; i<n; i++){
			listEstructurasTercetos.get(i).imprimir();
		}
	}


    public void chequearRangoEntero(String entero){
            int number = Integer.parseInt("-"+entero);
            if(number<-128){
                ErrorLinea err=new ErrorLinea("Constante entera fuera de rango", linea.getNumeroLinea());
                errores_lexicos.add(err);
            }

    }

    public void desconcatenarAmbito(){
		String [] a = ambito.split(":");
		String nuevoAmbito = a[0];
		int len = a.length -1;
		for (int i = 1 ; i < len ; i++)
			nuevoAmbito = nuevoAmbito + ":" + a[i];
		ambito = nuevoAmbito;
	}

