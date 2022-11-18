package com.tp.compiladores;

import java.util.HashMap;
public class PalabrasReservadas {

  private HashMap<String,Integer> palabrasReservadas;
  
  PalabrasReservadas(){
    palabrasReservadas = new HashMap<String, Integer>();
		palabrasReservadas.put("if",257);
		palabrasReservadas.put("then",258);
		palabrasReservadas.put("else",259);
		palabrasReservadas.put("end_if",260);
		palabrasReservadas.put("out",261);
		palabrasReservadas.put("fun",262);
	  palabrasReservadas.put("return",263);
	  palabrasReservadas.put("i8",264);
	  palabrasReservadas.put("f32",265);
	  palabrasReservadas.put("when",266);
	  palabrasReservadas.put("continue",270);
	  palabrasReservadas.put("const",276);
	  palabrasReservadas.put("tof32",277);
	  palabrasReservadas.put("break",278);
	  palabrasReservadas.put("for",279);
  }

  public int getToken(String p){
	  return palabrasReservadas.getOrDefault(p, -1);
  }
}