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
					| WhenCuerpo
					| ListVariables ';' {AgregarErrorSintactico("Se espera el tipo de la variable");}
					| Tipo ';' {AgregarErrorSintactico("Se espera identificador de la variable");}
                    ;

ListCte : AsigCte
	    | ListCte ',' AsigCte
        ;


AsigCte: ID ASIGNACION CTE_ENTERA { tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "I8", $3.sval));}
	   | ID ASIGNACION signo CTE_ENTERA {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "I8", $3.sval+$4.sval));}
	   | ID ASIGNACION  CTE_FLOTANTE {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "F32", $3.sval));}
	   | ID ASIGNACION signo CTE_FLOTANTE {tablaDeSimbolos.add(new Simbolo($1.sval+":"+ambito, 269, "constante", "F32", $3.sval+$4.sval));}

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

HeaderFuncion: Fun '(' Parametro ',' Parametro ')' ':' Tipo {tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
															if (!parametrosFormales.containsKey(funcionActual+":"+ambito)){
																List<Simbolo> listparametros=parametros;
																parametrosFormales.put(funcionActual+":"+ambito, listparametros);
															}
															parametros=new ArrayList<>();
															ambito= ambito+":"+funcionActual;
															}
			 | Fun '(' Parametro ')' ':' Tipo {tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
			 									if (!parametrosFormales.containsKey(funcionActual+":"+ambito)){
													List<Simbolo> listparametros=parametros;
													parametrosFormales.put(funcionActual+":"+ambito, listparametros);
												}
												parametros=new ArrayList<>();
												ambito= ambito+":"+funcionActual;
												}
			 | Fun '(' ')' ':' Tipo {tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
			 						if (!parametrosFormales.containsKey(funcionActual+":"+ambito)){
										List<Simbolo> listparametros=parametros;
										parametrosFormales.put(funcionActual+":"+ambito, listparametros);
									}
									parametros=new ArrayList<>();
									ambito= ambito+":"+funcionActual;
									}

			 | FUN '(' Parametro ',' Parametro ')' ':' Tipo {AgregarErrorSintactico("Se espera el identificador de la funcion ");}
	   		 | FUN '(' Parametro ')' ':' Tipo {AgregarErrorSintactico("Se espera el identificador de la funcion ");}
	   		 | FUN '(' ')' ':' Tipo {AgregarErrorSintactico("Se espera el identificador de la funcion ");}
			 | Fun '(' Parametro ',' Parametro ')' ':' {AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
	   		 | Fun '(' Parametro ')' ':'  {AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
	   		 | Fun '(' ')' ':'  {AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
			 ;

Fun: FUN ID {tablaDeSimbolos.add(new Simbolo($2.sval+":"+ambito, 269, "identificador_funcion"));
			 funcionActual=$2.sval;
			 estructuraActual=new EstructuraTercetos($2.sval+":"+ambito);
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 dentroDeFun=true;
			 }
	;

Funcion: HeaderFuncion '{' Cuerpo '}' {desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   dentroDeFun=false;
									   }
	   | HeaderFuncion '{' '}' {AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
	   | '{' Cuerpo '}' {AgregarErrorSintactico("Se espera el header de la funcion");}
	   | HeaderFuncion'{' ListSentencias '}' {AgregarErrorSintactico("Se espera el retorno de la funcion");}
	   ;


Parametro: Tipo ID {Simbolo simbolo= new Simbolo($2.sval+":"+ambito+":"+funcionActual,269,"parametro", estructuraActual.getTipo());
					tablaDeSimbolos.add(simbolo);
					parametros.add(simbolo);}
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

Asig: ID ASIGNACION Expresion ';'{String lex=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
								if(tablaDeSimbolos.getUso(lex).equals("constante"))
									errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
								if (!mismoTipoExpID($1.sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
								estructuraActual.crearTerceto($2.sval, lex, $3.sval);
								} 

	| ID ASIGNACION ID ';' {String lex=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
							if (!mismoTipoIds($1.sval, $3.sval)) 
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto($2.sval, lex, tablaDeSimbolos.getRefSimbolo($3.sval, ambito));}

    | ID ASIGNACION cte ';' {String lex=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
							if (!mismoTipoIDCte($1.sval, $3.sval))
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto($2.sval, lex, $3.sval);}

	| ID ASIGNACION signo cte ';' {String lex=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
									if(tablaDeSimbolos.getUso(lex).equals("constante"))
										errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
									if (!mismoTipoIDCte($1.sval, $4.sval))
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
									tablaDeSimbolos.setLexema($3.sval, $4.sval);
									estructuraActual.crearTerceto($2.sval, lex, $3.sval + $4.sval);}

	| ID error ';' {AgregarErrorSintactico("Se espera '=:'");}

HeaderWhen: When Condicion THEN {chequearCondicionWhen();
								}

		  | Condicion THEN {AgregarErrorSintactico("Se espera un if o un when");}
		  | When Condicion {AgregarErrorSintactico("Se espera la palabra reservada then");}
		  ;

When: WHEN {dentroDeWhen=true;
			valores1.clear();
			valores2.clear();
			listOperadores1.clear();
			listOperadores2.clear();
			valores=valores1;
			listOperadores=listOperadores1;
			}

WhenCuerpo: HeaderWhen '{' ListSentencias '}' {estructuraActual.completarTercetoIf(1);
												estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
												dentroDeWhen=false; condicionWhenFalse=false;
												}
			 | error '}' {AgregarErrorSintactico("Se espera ';'");}
			 ;


Salida : OUT '(' CADENA_MULT ')' {estructuraActual.crearTerceto("OUT", $3.sval, null);}
	   |'(' CADENA_MULT ')' {AgregarErrorSintactico("Se espera un out");}
	   ;
			
						 
Expresion: ID signo ID {
					if (!mismoTipoIds($1.sval, $3.sval)) 
						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
					
					estructuraActual.crearTerceto($2.sval, lex1, lex2);
					$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					estructuraActual.addTercetoWhen();
					listOperadores.add($2.sval);
					valores.add(lex1);
					valores.add(lex2);
					}

	     | ID signo cte {
			if (!mismoTipoIDCte($1.sval, $3.sval)) 
		 		errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			
			String lex1 =tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
			estructuraActual.crearTerceto($2.sval, lex1, $3.sval);
			$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add(lex1);
			valores.add($3.sval);
			listOperadores.add($2.sval);
			}  

	     | Expresion signo ID {
			if (!mismoTipoExpID($3.sval))
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			String lex=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
			valores.add(lex);
			
			estructuraActual.crearTerceto($2.sval, $1.sval, lex);
			$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			listOperadores.add($2.sval);
			} 

	     | Expresion signo cte {
			if (!mismoTipoExpCte($3.sval)) 
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);
			$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add($3.sval);
			listOperadores.add($2.sval);
			} 
	     | ID '*' ID {if (!mismoTipoIds($1.sval, $3.sval))
						 errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
					
					estructuraActual.crearTerceto($2.sval, lex1, lex2);
					$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add($2.sval);
					}

		 | ID '/' ID {if (!mismoTipoIds($1.sval, $3.sval)) 
		 				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
					
					estructuraActual.crearTerceto($2.sval, lex1, lex2);
					$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add($2.sval);
					} 
	     | ID '*' cte {if (!mismoTipoIDCte($1.sval, $3.sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex= tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
							estructuraActual.crearTerceto($2.sval, lex, $3.sval);
							$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							valores.add($3.sval);
							estructuraActual.addTercetoWhen();
							listOperadores.add($2.sval);
							} 
	     | ID '/' cte {if (!mismoTipoIDCte($1.sval, $3.sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex= tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
						estructuraActual.crearTerceto($2.sval, lex, $3.sval);
						$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add($3.sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add($2.sval);
						}

	     | Expresion '*' ID  {if (!mismoTipoExpID($3.sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
							estructuraActual.crearTerceto($2.sval, $1.sval, lex);
							$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add($2.sval);
							}

	     | Expresion '/' ID {if (!mismoTipoExpID($3.sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex= tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
							estructuraActual.crearTerceto($2.sval, $1.sval, lex);
							$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add($2.sval);
							}

	     | Expresion '*' cte {if (!mismoTipoExpCte($3.sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);
							$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add($3.sval);
							listOperadores.add($2.sval);
							} 
	     | Expresion '/' cte {if (!mismoTipoExpCte($3.sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);
							$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add($3.sval);
							listOperadores.add($2.sval);
							}

		 | ConversionExplicita

		 | cte signo ID {if (!mismoTipoIDCte($3.sval, $1.sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
						estructuraActual.crearTerceto($2.sval, $1.sval, lex);
						$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add($1.sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add($2.sval);
						}

		 | cte '*' ID {if (!mismoTipoIDCte($3.sval, $1.sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
						estructuraActual.crearTerceto($2.sval, $1.sval, lex);
						$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add($1.sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add($2.sval);
						} 
		 | cte '/' ID {if (!mismoTipoIDCte($3.sval, $1.sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
						estructuraActual.crearTerceto($2.sval, $1.sval, lex);
						$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add($1.sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add($2.sval);
						}

		 | ID ListParametrosInv {String funcion=tablaDeSimbolos.getRefFuncion($1.sval, ambito);
								if(funcion!=null){
		 						 	chequearYAsignarParametros(parametrosReales, parametrosFormales.get(funcion));
									
								}
								estructuraActual.crearTerceto("BI", funcion, null);
								estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);

								$$.sval=funcion;
								valores.add(funcion);
								parametrosReales.clear();
								}
         ;

ListParametrosInv: '(' ')'
				 | '(' ParametroReal ')'
				 | '(' ParametroReal ',' ParametroReal ')'
				 ;

ParametroReal: cte {parametrosReales.add($1.sval);}
			 | signo cte {parametrosReales.add($1.sval+$2.sval);}
			 | ID {parametrosReales.add(tablaDeSimbolos.getRefSimbolo($1.sval, ambito));}
             ;

ConversionExplicita: TOF32 '(' Expresion ')' {if (conversionValida()){
												estructuraActual.crearTerceto("tof32", $3.sval, null);
											  }
											  	else errores_semanticos.add(new ErrorLinea("No se puede realizar la conversion", this.linea.getNumeroLinea()));
											  $$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
				   | TOF32 '('')' {AgregarErrorSintactico("Se espera expresion");}
				   ;

signo: '+'
	 | '-'
	 ;


cte: CTE_FLOTANTE
   | CTE_ENTERA {chequearRangoEntero($1.sval);}
   ;

ListSentenciasIf: SentenciaEjecutable ListSentenciasIf
                | SentenciaCorte ListSentenciasIf
                | SentenciaEjecutable
                | SentenciaCorte
                ;

HeaderIf: IF Condicion THEN
        | error THEN {yyerror("falta cerrar parentesis");}
        ;


CuerpoIf: SentenciaEjecutable
		| SentenciaCorte
        | '{' ListSentenciasIf '}'
        ;

Else: ELSE {estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();
			estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
	;

CuerpoElse: Else CuerpoIf END_IF 
          | Else CuerpoIf error {yyerror("falta palabra reservada end_if");}
          ;

Seleccion: HeaderIf CuerpoIf END_IF {estructuraActual.completarTercetoIf(1);
									 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
         | HeaderIf CuerpoIf CuerpoElse {estructuraActual.completarTercetoIf(1);
								  		 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}

         | HeaderIf CuerpoIf error {yyerror("falta palabra reservada end_if");}
		 | error ELSE {AgregarErrorSintactico("Se espera '{' '}' ");}
		 | error END_IF {AgregarErrorSintactico("Se espera '{' '}' ");}
		 ;

Condicion: '(' Comparacion ')' {estructuraActual.crearTerceto("BF", $2.sval, null);
								estructuraActual.addTercetoIf();
								estructuraActual.addTercetoWhen();
								}
		 | '(' Comparacion {AgregarErrorSintactico("Falta cerrar parentesis");}
		 | Comparacion ')' {AgregarErrorSintactico("Falta abrir parentesis");}
		 ;

Comparador: '<' {listOperadores=listOperadores2; comparador=$1.sval; valores=valores2;}
          | '>' {listOperadores=listOperadores2; comparador=$1.sval; valores=valores2;}
          | MAYOR_IGUAL {listOperadores=listOperadores2; comparador=$1.sval;valores=valores2;}
          | MENOR_IGUAL {listOperadores=listOperadores2; comparador=$1.sval;valores=valores2;}
          | '=' {listOperadores=listOperadores2; comparador=$1.sval;valores=valores2;}
          | DISTINTO {listOperadores=listOperadores2; comparador=$1.sval;valores=valores2;}
          ;

Comparacion: Expresion Comparador Expresion {if (!mismoTipo()) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);
												estructuraActual.addTercetoWhen();
												$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											
											}
           | Expresion Comparador cte {if (!mismoTipoExpCte($3.sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										
											estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);
											estructuraActual.addTercetoWhen();
											valores2.add($3.sval);
											$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										
										}
		   | Expresion Comparador signo cte {if (!mismoTipoExpCte($4.sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval + $4.sval );
												
												estructuraActual.addTercetoWhen();
												valores2.add($3.sval+$4.sval);
												$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema($3.sval, $4.sval);
											}
           | cte Comparador Expresion {if (!mismoTipoExpCte($1.sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   								
											estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);
											estructuraActual.addTercetoWhen();
											valores1.add($1.sval);
											$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
		   | signo cte Comparador Expresion {if (!mismoTipoExpCte($2.sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto($3.sval, $1.sval + $2.sval, $4.sval);
												estructuraActual.addTercetoWhen();
												valores1.add($1.sval+$2.sval);
												$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema($1.sval, $2.sval);
											}
           | Expresion Comparador ID {if (!mismoTipoExpID($3.sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   								String lex=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
										estructuraActual.crearTerceto($2.sval, $1.sval, lex);
										estructuraActual.addTercetoWhen();
										valores2.add(lex);
										$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
           | ID Comparador Expresion {if (!mismoTipoExpID($1.sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

										String lex=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
										estructuraActual.crearTerceto($2.sval, lex, $3.sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
		   | cte Comparador ID {if (!mismoTipoIDCte($3.sval, $1.sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
									estructuraActual.crearTerceto($2.sval, $1.sval, lex);
									estructuraActual.addTercetoWhen();
									valores1.add($1.sval);
									valores2.add(lex);
									$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
		   | signo cte Comparador ID {if (!mismoTipoIDCte($4.sval, $2.sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo($4.sval, ambito);
										estructuraActual.crearTerceto($3.sval, $1.sval + $2.sval, lex);
										estructuraActual.addTercetoWhen();
										valores1.add($1.sval +$2.sval);
										valores2.add(lex);
										$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema($1.sval, $2.sval);
									}
		   | ID Comparador cte {if (!mismoTipoIDCte($1.sval, $3.sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
									estructuraActual.crearTerceto($2.sval, lex, $3.sval);
									estructuraActual.addTercetoWhen();
									valores1.add($3.sval);
									valores2.add(lex);
									$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
		   | ID Comparador signo cte {if (!mismoTipoIDCte($1.sval, $4.sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
										estructuraActual.crearTerceto($2.sval, lex, $3.sval + $4.sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										valores2.add($3.sval+$4.sval);
										$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema($3.sval, $4.sval);
									}
		   | ID Comparador ID {if (!mismoTipoIds($1.sval, $3.sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   						
									String lex1=tablaDeSimbolos.getRefSimbolo($1.sval, ambito);
									String lex2=tablaDeSimbolos.getRefSimbolo($3.sval, ambito);
									estructuraActual.crearTerceto($2.sval, lex1, lex2);
									estructuraActual.addTercetoWhen();
									
									valores1.add(lex1);
									valores2.add(lex2);
									$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
		   | cte Comparador cte {if (!mismoTipoCtes($1.sval, $3.sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							estructuraActual.crearTerceto($2.sval, $1.sval, $3.sval);
									estructuraActual.addTercetoWhen();
									valores1.add($1.sval);
									valores2.add($3.sval);
									$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
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
				{estructuraActual.crearTerceto("BF", $5.sval, null);
				 refEtiqueta = $3.sval;
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto($7.sval, estructuraActual.getIdFor(), $8.sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
		 | FOR '(' AsigFor ';' CondicionFor ';' CTE_ENTERA ')'
				{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", $5.sval, null);
				 refEtiqueta = $3.sval;
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

Etiqueta: ID ':' {estructuraActual.addEtiquetaFor($1.sval);}
		;

HeaderEtiqueta: Etiqueta HeaderFor {estructuraActual.addRefEtiqueta(refEtiqueta);}
			  ;

AsigFor: ID ASIGNACION CTE_ENTERA
		{if (!mismoTipoIDCte($1.sval, $3.sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo($1.sval, ambito));
		estructuraActual.crearTerceto($2.sval, estructuraActual.getIdFor(), $3.sval);
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
		$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
	   | ID ASIGNACION ID
		{if (!mismoTipoIds($1.sval, $3.sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo($1.sval, ambito));
		estructuraActual.crearTerceto($2.sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
		$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
       ;

CuerpoFor: '{' ListSentenciasFor '}'
					{estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.completarTercetoFor(1);
					 estructuraActual.completarTercetosBreak(1);
					 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
					 estructuraActual.popIdFor();

					 estructuraActual.borrarListTercetosBreak();
					 dentroDeFor=!estructuraActual.vaciaListTercetoBreak();
					 }
					 
		| '{''}' ';' {AgregarErrorSintactico("Se esperan sentencias ejecutables");}
		;


SentenciaControl: HeaderFor CuerpoFor 
				| HeaderEtiqueta CuerpoFor {estructuraActual.eliminarEtiqueta();}
				| HeaderForID CuerpoFor ELSE cte ';' {
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $4.sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);

					estructuraActual.borrarListTercetosBreakCte();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					}

				| HeaderForID CuerpoFor ELSE signo cte ';' {
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $4.sval + $5.sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);

					estructuraActual.borrarListTercetosBreakCte();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					tablaDeSimbolos.setLexema($4.sval, $5.sval);
				}
				;


CondicionFor: ID Comparador ID {if (!mismoTipoIds($1.sval, $3.sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
								estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));
								$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}

			| ID Comparador Expresion {if (!mismoTipoExpID($1.sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
									   estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);
									   $$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									   }
			
			| ID Comparador CTE_ENTERA {if (!mismoTipoIDCte($1.sval, $3.sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval);
										$$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
			
			| ID Comparador signo CTE_ENTERA {if (!mismoTipoIDCte($1.sval, $3.sval)) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											  tablaDeSimbolos.setLexema($3.sval, $4.sval);
											  estructuraActual.crearTerceto($2.sval, tablaDeSimbolos.getRefSimbolo($1.sval, ambito), $3.sval+$4.sval);
											  $$.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
            ;

SentenciaReturn: RETURN '(' ID ')' ';' {
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una funcion", this.linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefFuncion(funcionActual, ambito), tablaDeSimbolos.getRefSimbolo($3.sval, ambito));
						estructuraActual.crearTerceto("BI",null, null);
					}	
						
				}
	  		  | RETURN '(' cte ')' ';' {
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefFuncion(funcionActual, ambito), $3.sval);
						estructuraActual.crearTerceto("BI",null, null);
					}	
				}
	  		  | RETURN '(' Expresion ')' ';' {
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefFuncion(funcionActual, ambito), $3.sval);
						estructuraActual.crearTerceto("BI",null, null);
					}	
				}
			  | RETURN '(' ID ')' {AgregarErrorSintactico("Falta ;");}
	  		  | RETURN '(' cte ')' {AgregarErrorSintactico("Falta ;");}
	  		  | RETURN '(' Expresion ')' {AgregarErrorSintactico("Falta ;");}
	  		  | RETURN ';' {AgregarErrorSintactico("Falta expresion de retorno");}
			  ;

SentenciaCorte: SentenciaReturn
			  | BREAK ';' {
				if(dentroDeFor){
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreak();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una sentencia de control", this.linea.getNumeroLinea()));
					System.out.println("No se encuentra dentro de una sentencia de control");
				}
			  }
        	  | BREAK cte ';' {
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $2.sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se espera el retorno de una sentencia de control", this.linea.getNumeroLinea()));
					System.out.println("No se espera el retorno de una sentencia de control");
				}
				}
		 	  | BREAK signo cte ';' {
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), $2.sval + $3.sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se espera el retorno de una sentencia de control", this.linea.getNumeroLinea()));
					System.out.println("No se espera el retorno de una sentencia de control");
			  	}
			  }
			  | CONTINUE ':' ID ';' {
				if (estructuraActual.existeEtiquetaFor($3.sval)){
					estructuraActual.crearTerceto("BI", estructuraActual.getRefEtiqueta($3.sval), null);
				}
				else
					errores_semanticos.add(new ErrorLinea("No existe la etiqueta de salto", linea.getNumeroLinea()));
			  }
			  ;


%%

public static String input;
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

public static HashMap<String, List<Simbolo>> parametrosFormales= new HashMap<>();
public static List<Simbolo> parametros= new ArrayList<>();
public static List<String> parametrosReales= new ArrayList<>();

public static String comparador;
public static List<String> listOperadores= new ArrayList<>();
public static final List<String> listOperadores1= new ArrayList<>();
public static final List<String> listOperadores2= new ArrayList<>();
public static List<String> valores= new ArrayList<>();
public static final List<String> valores1= new ArrayList<>();
public static final List<String> valores2= new ArrayList<>();

public static boolean condicionWhenFalse=false;
public static boolean dentroDeWhen=false;
public static boolean dentroDeFor=false;
public static boolean esperandoBreakcte=false;
public static boolean dentroDeFun=false;
public static String funcionActual;
public static String refEtiqueta = null;
public static String tipoActual = "";
public static String tipoAnterior = "";

//private String tipo; //guarda el tipo de la lista de id que se estan declarando

public static String ambito = "";

//-----------------------------------------------------------------------

public static AnalizadorLexico lexico;

    public static void chequearYAsignarParametros(List<String> lista1, List<Simbolo> lista2){
		int n=lista1.size();
		boolean errorTipo=false;
		
		if(n==lista2.size()){
			for(int i=0; i<n; i++)
				if(!tablaDeSimbolos.getTipo(lista1.get(i)).equals(lista2.get(i).getTipo()))
					errorTipo=true;
				
			
			if(errorTipo){
				System.out.println("Hay parametro/s de distinto tipo");
				errores_semanticos.add(new ErrorLinea("Hay parametro/s de distinto tipo", linea.getNumeroLinea()));
			}
			else
				for(int i=0; i<n;i++){
					estructuraActual.crearTerceto("=:", lista1.get(i), lista2.get(i).getLexema());
				}			
		}
		else{
			System.out.println("La cantidad de parametros es incorrecta");
			errores_semanticos.add(new ErrorLinea("La cantidad de parametros es incorrecta", linea.getNumeroLinea()));
		}
	}

	public int yylex(){
		Simbolo simbolo = lexico.yyLex();

		if ((simbolo.getToken() != 0) || (simbolo.getToken() != -1))
			yylval = new ParserVal(simbolo.getLexema());
		
		return simbolo.getToken();

	}

	public static void AgregarErrorSintactico(String error){
	    ErrorLinea err= new ErrorLinea(error, linea.getNumeroLinea());
		errores_sintacticos.add(err);
	}
	
	public static void yyerror(String error){
	    System.out.println(error);
	    ErrorLinea err= new ErrorLinea(error, linea.getNumeroLinea());
		errores_yacc.add(err);
	}


	public static void main(String[] args) {
	    try{
	        input = new String(Files.readAllBytes(Paths.get("codigo.txt")));
	        AnalizadorLexico.addInput(input);
			Parser parser= new Parser();
    		System.out.println("parse: "+parser.yyparse());

			if((errores_lexicos.isEmpty()) && (errores_sintacticos.isEmpty()) && (errores_semanticos.isEmpty())){
				GeneradorCodigo.setListaEstructuras(listEstructurasTercetos);
				GeneradorCodigo.generarCodigo();
			}
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
		System.out.println("Codigo assembler generado");
		System.out.println(GeneradorCodigo.getCodigoGenerado());
	}


    public static void chequearRangoEntero(String entero){
            int number = Integer.parseInt("-"+entero);
            if(number<-128){
                ErrorLinea err=new ErrorLinea("Constante entera fuera de rango", linea.getNumeroLinea());
                errores_lexicos.add(err);
            }

    }

    public static void desconcatenarAmbito(){
		String [] a = ambito.split(":");
		String nuevoAmbito = a[0];
		int len = a.length -1;
		for (int i = 1 ; i < len ; i++)
			nuevoAmbito = nuevoAmbito + ":" + a[i];
		ambito = nuevoAmbito;
	}

	public static boolean mismoTipoIds(String val1, String val2){
		String lex1 = tablaDeSimbolos.getRefSimbolo(val1, ambito);
		String lex2 = tablaDeSimbolos.getRefSimbolo(val2, ambito);
		String tipo1 = tablaDeSimbolos.getTipo(lex1);
		String tipo2 = tablaDeSimbolos.getTipo(lex2);
		if (tipo1 == tipo2){
			tipoAnterior = tipoActual;
			tipoActual = tipo1;
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public static boolean mismoTipoCtes(String val1, String val2){
		String tipo1 = tablaDeSimbolos.getTipo(val1);
		String tipo2 = tablaDeSimbolos.getTipo(val2);
		if (tipo1.equals(tipo2)){
			tipoAnterior = tipoActual;
			tipoActual = tipo1;
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public static boolean mismoTipoIDCte(String val1, String val2){
		String lex1 = tablaDeSimbolos.getRefSimbolo(val1, ambito);
		String tipo = tablaDeSimbolos.getTipo(val2);
		if (tablaDeSimbolos.getTipo(lex1) == tipo){
			tipoAnterior = tipoActual;
			tipoActual = tipo;
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public static boolean mismoTipoExpCte(String val){
		String tipo = tablaDeSimbolos.getTipo(val);
		if (tipoActual == tipo){
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public static boolean mismoTipoExpID(String val){
		String lex = tablaDeSimbolos.getRefSimbolo(val, ambito);
        String tipo = tablaDeSimbolos.getTipo(lex);
		if (tipoActual == tipo){
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public static boolean mismoTipo(){
		if (tipoActual == tipoAnterior){
			return true;
		}
		else {
			tipoActual = "";
			tipoAnterior = "";
			return false;
			}
	}

	public static boolean conversionValida(){
		if (tipoActual.equals("I8")){
			tipoAnterior = "F32";
			tipoActual = "F32";
			return true;
		}
		else return false;
	}

	public static boolean getVerificacionCondicion(float r1, float r2, String comparador){
		switch(comparador){
			case "<":
				return(r1 < r2);
			case "<=":
				return(r1 <= r2);
			case ">":
				return(r1 > r2);
			case ">=":
				return(r1 >= r2);
			case "=":
				return(r1 == r2);
			default:
				return(r1 != r2);
		}
	}

	public static float getResultado(float num1, float num2, String operador){
		switch(operador){
			case "+":
				return (num1+num2);
			case "-":
				return (num1-num2);
			case "/":
				return (num1/num2);
			default:
				return (num1*num2);
		}
	}

	public static void chequearCondicionWhen(){
		String uso, valor;
		List<Float> val1=new ArrayList<>();
		List<Float> val2=new ArrayList<>();
		System.out.println("chequeo");
		for(String s: valores1){
			uso=tablaDeSimbolos.getUso(s);
			valor=tablaDeSimbolos.getValor(s);
			if((!uso.equals("constante")) && (!uso.equals("valor_numerico"))){
				errores_semanticos.add(new ErrorLinea("La condicion del when tiene un ID que no corresponde a una constante", linea.getNumeroLinea()));
				System.out.println("La condicion del when tiene un ID que no corresponde a una constante");
				condicionWhenFalse=true;
				return;
			}
			else{
				if(valor!=null)
					val1.add(Float.valueOf(valor));
			}
		}
		

		for(String s: valores2){
			uso=tablaDeSimbolos.getUso(s);
			valor=tablaDeSimbolos.getValor(s);
			if((!uso.equals("constante")) && (!uso.equals("valor_numerico"))){
				errores_semanticos.add(new ErrorLinea("La condicion del when tiene un ID que no corresponde a una constante", linea.getNumeroLinea()));
				System.out.println("La condicion del when tiene un ID que no corresponde a una constante");
				condicionWhenFalse=true;
				return;
			}
			else{
				if(valor!=null)
					val2.add(Float.valueOf(valor));
			}
		}

		float resultado1=0;
		float resultado2=0;

		System.out.println("cantidad op1 "+listOperadores1.size());
		System.out.println("cantidad op2 "+listOperadores2.size());

		System.out.println("cantidad val1 "+val1.size()+" - "+"cantidad valores1 "+ valores1.size());
		System.out.println("cantidad val2 "+val2.size()+" - "+"cantidad valores2 "+ valores2.size());

		if((val1.size()==listOperadores1.size()+1) && (val2.size()==listOperadores2.size()+1)){
			int n=listOperadores1.size();
			float valor1, valor2;
			String op;
			float result;
			for(int i=0; i<n; i++){
				op=listOperadores1.get(i);
				if(op.matches("[*|/]")){
					valor1=val1.get(i);
					valor2=val1.get(i+1);
					result=getResultado(valor1, valor2, op);
					val1.remove(i);
					val1.remove(i);
					val1.add(i, result);
					listOperadores1.remove(i);
				}
			}
			n=listOperadores1.size();
			for(int i=0; i<n; i++){
				op=listOperadores1.get(i);
				valor1=val1.get(i);
				valor2=val1.get(i+1);
				result=getResultado(valor1, valor2, op);
				val1.remove(i);
				val1.remove(i);
				val1.add(i, result);
				listOperadores1.remove(i);
			}

			n=listOperadores2.size();
			for(int i=0; i<n; i++){
				op=listOperadores2.get(i);
				if(op.matches("[*|/]")){
					valor1=val2.get(i);
					valor2=val2.get(i+1);
					result=getResultado(valor1, valor2, op);
					val2.remove(i);
					val2.remove(i);
					val2.add(i, result);
					listOperadores2.remove(i);
				}
			}
			n=listOperadores2.size();
			for(int i=0; i<n; i++){
				op=listOperadores2.get(i);
				valor1=val2.get(i);
				valor2=val2.get(i+1);
				result=getResultado(valor1, valor2, op);
				val2.remove(i);
				val2.remove(i);
				val2.add(i, result);
				listOperadores2.remove(i);
			}
		}
		
		if(val1.size()==1 && val2.size()==1){
			resultado1=val1.get(0);
			resultado2=val2.get(0);
			System.out.println("resultado1: "+resultado1);
			System.out.println("resultado2: "+resultado2);
			condicionWhenFalse=!getVerificacionCondicion(resultado1, resultado2, comparador);
		}

		if(condicionWhenFalse){
			estructuraActual.eliminarTercetosWhen();
		}

		listOperadores1.clear();
		listOperadores2.clear();
		valores1.clear();
		valores2.clear();
		
	}

	
