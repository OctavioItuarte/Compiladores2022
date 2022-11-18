package com.tp.compiladores;

import java.util.HashMap;
public class TokenSimbolos{

  private HashMap<String,Integer> tokens;

  public TokenSimbolos(){
	    tokens = new HashMap<String, Integer>();
	  tokens.put(".",46);
	  tokens.put(">",62);
	  tokens.put("<",60);
	  tokens.put(">=",273);
	  tokens.put("<=",274);
	  tokens.put("(",40);
	  tokens.put(")",41);
	  tokens.put("{",123);
	  tokens.put("}",125);
	  tokens.put("+",43);
	  tokens.put("-",45);
	  tokens.put("=:",272);
	  tokens.put("=!",275);
	  tokens.put("'",39);
	  tokens.put("/",47);
	  tokens.put("*",42);
	  tokens.put(";",59);
	  tokens.put(",",44);
	  tokens.put(":",58);
	  tokens.put("=",61);
  }

  public int getToken(String p){
	  return tokens.getOrDefault(p, -1);
  }
}