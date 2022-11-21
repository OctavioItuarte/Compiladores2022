//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "Gramatica.y"
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
//#line 31 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short THEN=258;
public final static short ELSE=259;
public final static short END_IF=260;
public final static short OUT=261;
public final static short FUN=262;
public final static short RETURN=263;
public final static short I8=264;
public final static short F32=265;
public final static short WHEN=266;
public final static short CTE_ENTERA=267;
public final static short CTE_FLOTANTE=268;
public final static short ID=269;
public final static short CONTINUE=270;
public final static short CADENA_MULT=271;
public final static short ASIGNACION=272;
public final static short MAYOR_IGUAL=273;
public final static short MENOR_IGUAL=274;
public final static short DISTINTO=275;
public final static short CONST=276;
public final static short TOF32=277;
public final static short BREAK=278;
public final static short FOR=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    0,    1,    2,    2,    2,    2,    2,
    2,    4,    4,    4,    4,    4,    4,    4,    9,    9,
   11,   11,   11,   11,   11,   11,   11,    6,    6,    7,
    7,    7,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   15,    8,    8,    8,    8,   16,   16,   17,   17,
   17,    5,    5,    5,   20,   20,   20,   20,   20,   23,
   23,   23,   10,   10,   21,   21,   25,   25,   25,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   27,   27,   27,   26,
   26,   12,   12,   13,   13,   18,   18,   18,   18,   18,
   18,   18,   28,   28,   28,   28,   30,   30,   31,   31,
   32,   33,   34,   34,   19,   19,   19,   19,   19,   24,
   24,   24,   36,   36,   36,   36,   36,   36,   35,   35,
   35,   35,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,   35,   35,   37,   37,   37,   37,   37,   37,
   38,   38,   41,   42,   39,   39,   43,   43,    3,    3,
    3,    3,   40,   40,   40,   40,   29,   29,   29,   29,
   29,
};
final static short yylen[] = {                            2,
    4,    3,    2,    2,    1,    2,    2,    2,    1,    1,
    1,    3,    3,    1,    3,    1,    2,    2,    1,    3,
    3,    4,    3,    4,    2,    2,    2,    1,    1,    3,
    1,    2,    8,    6,    5,    8,    6,    5,    7,    5,
    4,    2,    4,    3,    3,    4,    2,    1,    2,    2,
    1,    1,    1,    2,    4,    4,    4,    5,    3,    3,
    2,    2,    4,    2,    4,    3,    1,    2,    1,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    1,    3,    3,    3,    2,    2,    3,    5,    4,
    3,    1,    1,    1,    1,    5,    5,    5,    4,    4,
    4,    2,    2,    2,    1,    1,    3,    2,    1,    3,
    1,    1,    3,    3,    3,    3,    3,    2,    2,    3,
    2,    2,    1,    1,    1,    1,    1,    1,    3,    3,
    4,    3,    4,    3,    3,    3,    4,    3,    4,    3,
    3,    2,    2,    2,    2,    2,    2,    1,    1,    1,
    9,    8,    3,    3,    3,    3,    3,    3,    2,    2,
    5,    6,    3,    3,    3,    4,    1,    2,    3,    4,
    4,
};
final static short yydefred[] = {                         0,
    0,    5,    0,    0,    4,    0,    0,    0,    0,   28,
   29,    0,   95,   94,    0,  125,  126,  128,    0,    0,
    0,   92,   93,    0,    0,  123,  124,  127,    0,    0,
    0,    0,    0,    0,   14,   16,    0,    0,    0,    0,
   52,   53,    0,    0,    0,    0,   82,    0,    0,    0,
    0,    0,    0,  108,  118,  119,  143,   64,  144,    0,
    0,    0,    0,    0,   42,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   32,    0,   86,    0,    0,
    0,    0,   19,    0,    0,    0,    0,    0,    0,    0,
    0,   51,    0,    0,    2,    6,    7,    8,    0,   18,
    0,   17,    0,    0,    0,    0,    0,    0,    0,   54,
    0,    0,    0,    0,    0,   61,    0,    0,    0,    0,
  109,  111,    0,  122,  142,    0,  159,    0,  160,  107,
    0,   48,    0,    0,    0,   60,   59,    0,    0,    0,
    0,  153,   74,   76,   75,   77,   30,   69,   87,    0,
   67,    0,  154,   70,   71,    0,    0,    0,    0,    0,
   27,   25,   15,    0,    0,   91,    0,    0,    0,    0,
  102,    0,    0,    0,    0,    1,   50,   49,   45,   66,
  120,   13,   12,    0,   84,   85,   83,    0,    0,    0,
   44,    0,    0,    0,    0,   78,   80,   79,   81,   72,
   73,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  167,    0,    0,  117,  112,  115,    0,  116,    0,    0,
  148,  149,  150,    0,    0,   65,    0,   47,    0,    0,
   56,    0,   57,   55,   68,    0,   88,  139,   21,   23,
    0,   20,   90,    0,    0,    0,    0,    0,    0,    0,
   46,   43,    0,    0,    0,  131,   63,    0,  168,    0,
    0,  103,  110,  104,    0,  158,  157,  146,  145,  147,
    0,    0,   38,    0,    0,   58,    0,   22,   24,  155,
  156,    0,    0,    0,    0,    0,   35,    0,    0,    0,
    0,  169,  114,  113,    0,  161,    0,   37,   89,    0,
    0,   96,   97,   98,    0,   34,  171,  170,  162,    0,
    0,    0,    0,    0,    0,    0,    0,   36,  166,  152,
    0,   33,  151,
};
final static short yydgoto[] = {                          3,
    4,   97,   30,   31,   32,   33,   34,   35,   82,   36,
   83,   37,   38,   39,   40,  135,   91,   92,   41,   42,
   43,   44,   45,   46,  152,   47,   78,  212,  213,   48,
  122,  123,  217,  218,   49,   50,  224,   51,  170,  283,
   52,   53,  127,
};
final static short yysindex[] = {                      -215,
  -82,    0,    0,  155,    0,  -56,  322,   10,  -33,    0,
    0,  322,    0,    0,  447,    0,    0,    0, -236,   25,
   40,    0,    0,  124,  390,    0,    0,    0,  -31,  180,
  180,  180,  -50,   29,    0,    0,  -71,  489,  -27,   78,
    0,    0,   68,  521,   34, -129,    0,   -6,   90, -110,
   86,   86,   86,    0,    0,    0,    0,    0,    0,  276,
  478,  419,  -95, -123,    0,    8,  -67,  159,  -13,  127,
  290,  319,  -55,  -30,  -34,    0,  325,    0,  245, -196,
  -71,    9,    0,   42,  -21,  -37,  365,  136,  124,  124,
  177,    0,  267,  271,    0,    0,    0,    0,  258,    0,
  272,    0,  410,   85,   91,   95, -207,   76,   15,    0,
  336,  339,  430,  279,  180,    0,  446, -244,  100,   97,
    0,    0, -147,    0,    0,   -3,    0,   88,    0,    0,
  333,    0,  308,  107,  374,    0,    0,  701,  -71,  712,
  725,    0,    0,    0,    0,    0,    0,    0,    0,  -71,
    0,  452,    0,    0,    0,  746,  -71,  573,  634,  334,
    0,    0,    0, -236,  746,    0,  573,  758,  130,  350,
    0,  208,  455,  365,  365,    0,    0,    0,    0,    0,
    0,    0,    0,  260,    0,    0,    0,  746,  573,  634,
    0,  318,  338,  420,  460,    0,    0,    0,    0,    0,
    0,  746,  -71,  573,  634,  359,  285,  428,  -41,  100,
    0,  378,  100,    0,    0,    0,   -6,    0,  103,  399,
    0,    0,    0,   26,  423,    0,    5,    0, -179,  448,
    0,  451,    0,    0,    0,  104,    0,    0,    0,    0,
  146,    0,    0, -253,  242,  740,  765,  772,  746,  634,
    0,    0,    5, -179,  466,    0,    0,  261,    0,  -71,
  474,    0,    0,    0, -153,    0,    0,    0,    0,    0,
  -71,  476,    0,  500,    5,    0,  514,    0,    0,    0,
    0,  410,  501,  502,  506,  508,    0,  528,    5,  515,
  517,    0,    0,    0,  518,    0,  526,    0,    0,  303,
   -5,    0,    0,    0,  540,    0,    0,    0,    0,    5,
    0,  746,  306,  634,  538,  344,    5,    0,    0,    0,
  571,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  558,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  619,   17,
   51,   52,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  512,    0,  558,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  498,  511,
    0,    0,    0, -104,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -36,    0,   -9,    1,   23,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  558,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    7,   16,   33,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,    0,   65,  211,    0,    0,    0,    0,  520,
    0,    0,  524,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  284,  317,
    0,    0,  529,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   37,   57,  204,    0,    0,  532,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  731,  566,    0,  584,    0,    0,  533,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  650, -120,  561,  623,  -46,    6,    0,    0,    0,
  496,  -15,   21,    0,    0,  -74,  -64,  400,    0,    0,
    0,  530,    0,   66,  417,    0,    0,  -45, -116,    0,
    0,  445,    0,    0,  -17,  -14,    0,   45,    0,    0,
    0,    0,  386,
};
final static int YYTABLESIZE=847;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
   79,   22,  172,   23,  140,  221,   66,   94,  100,  223,
  149,   68,   22,  280,   23,  281,   10,  259,  121,  134,
   76,  171,  106,  107,  177,  178,   59,  207,  113,  114,
   73,  138,   80,  120,  195,   81,  120,   22,  101,   23,
    1,  135,    5,  193,   94,   77,   79,  136,  133,   64,
   11,    9,  164,    2,  139,  194,  141,  103,  150,   13,
   14,  188,  134,  157,   84,  120,   26,  163,   58,   20,
   13,   14,   63,  132,   76,  160,   99,   67,  147,   85,
   59,   26,  166,  134,   10,   11,  140,  102,  184,  132,
  140,  144,  146,   95,  151,  108,  100,  155,  203,  158,
  161,  162,  293,  268,  167,  130,  294,  270,  214,  177,
  178,  215,  216,  138,  142,   25,  119,  109,   22,  153,
   23,  220,   77,  135,  106,  113,  110,  189,  116,  136,
  124,  197,  199,  201,  204,   26,   28,   27,  141,  120,
   77,   10,  106,  113,  241,  125,   22,  131,   23,   77,
  267,  106,  113,  121,  274,  132,  115,   77,   79,  232,
   75,   99,  130,   25,  262,  134,   22,  264,   23,   22,
  235,   23,   77,  106,  113,   11,    9,  238,   76,  288,
  273,  100,  134,   26,   28,   27,   77,  130,  106,  113,
  136,  139,  247,  260,   25,   13,   14,   22,   87,   23,
  191,   54,   55,   56,  167,   99,  287,  134,  126,  271,
   13,   14,   57,   69,   26,   28,   27,  137,   69,   25,
  150,  140,   22,  256,   23,   13,   14,  140,  298,  261,
   77,  106,  113,   77,  113,   65,   13,   14,  148,   26,
   28,   27,  306,  101,   21,  272,   87,  169,  138,  117,
    7,  129,  117,    7,    8,   69,  151,    8,  135,   86,
  176,  315,  118,  318,  136,  219,  208,  300,   10,   11,
  322,   10,   11,  141,  209,   21,  132,   24,   10,   11,
  291,  117,    7,  132,  313,  316,    8,   22,   86,   23,
  132,  295,   99,   99,  219,  208,   77,   99,  113,   99,
  134,  179,   87,  209,   21,   99,   99,  180,   13,   14,
  165,  181,  100,  100,   99,   99,  182,  100,   20,  100,
  167,   22,  130,   23,  137,  100,  100,   22,  101,   23,
  183,    6,    7,  129,  100,  100,    8,    9,   86,   10,
   11,   12,   13,   14,   15,   22,  225,   23,   16,   17,
   18,   19,   20,  185,   21,  117,    7,  133,   68,  186,
    8,   62,   86,  187,   22,  227,   23,   93,  118,  208,
   13,   14,  148,  226,   70,  228,   22,  209,   23,    6,
    7,   26,   28,   27,    8,    9,   86,   10,   11,   12,
   13,   14,   15,   13,   14,  138,   16,   17,   18,   19,
   20,  244,   21,   20,   25,   21,  137,   22,  245,   23,
    6,    7,  278,  279,  230,    8,    9,  229,   10,   11,
   12,   13,   14,   15,   26,   28,   27,   16,   17,   18,
   19,   20,   22,   21,   23,    6,    7,  128,  129,  133,
    8,    9,  251,   10,   11,   12,   13,   14,   15,   26,
   28,   27,   16,   17,   18,   19,   20,  266,   21,  101,
  101,   22,  252,   23,  101,   22,  101,   23,  129,   26,
   28,   27,  101,  101,   13,   14,  246,  253,   26,   28,
   27,  101,  101,  257,   20,  258,   74,   87,   71,   22,
   73,   23,  237,   72,   74,  236,   71,   22,   73,   23,
  255,   72,  263,  254,   75,  275,   26,   28,   27,  276,
  282,   13,   14,  156,   26,   28,   27,   74,  211,   71,
   22,   20,   23,  289,   72,  211,   13,   14,  249,  290,
  104,   22,  292,   23,  296,  105,   20,   26,   28,   27,
  297,  137,   13,   14,   57,   13,   14,  202,   26,   28,
   27,   13,   14,  138,  299,   20,   13,   14,  143,  301,
  302,   20,  111,   22,  303,   23,  304,  112,  305,  311,
   14,  312,  319,  307,  133,  308,  309,   60,  320,   20,
   26,   28,   27,  310,   89,   13,   14,  145,   13,   14,
   61,   13,   14,  154,   16,   17,   18,  317,   20,  141,
  239,  240,   13,   14,  196,   13,   14,  198,  159,  211,
  321,  323,  211,  168,  104,   22,   31,   23,    3,  105,
    6,    7,   11,  211,  163,    8,    9,   86,   10,   11,
   12,   13,   14,  173,   62,    9,  190,   16,   17,   18,
   19,   20,  164,  205,  105,   60,   90,  174,  106,   89,
   89,   41,  277,   29,   40,   39,   13,   14,   61,  242,
   93,  265,   16,   17,   18,    0,   20,    0,   89,    0,
  121,    0,    0,   88,   60,  111,   22,    0,   23,   96,
  112,   98,   16,   17,   18,   13,   14,   61,    0,   13,
   14,   16,   17,   18,    0,   20,   13,   14,  200,    0,
    0,  248,   68,   54,   55,   56,    0,    0,    0,  175,
   68,   90,   90,  250,    0,   69,    0,    0,   70,   16,
   17,   18,    0,   69,    0,    0,  207,   16,   17,   18,
   90,    0,    0,    0,  174,  174,  141,    0,    0,   98,
   74,  210,   71,   22,    0,   23,    0,   72,  222,    0,
   16,   17,   18,  104,   22,    0,   23,  192,  105,  231,
    0,   16,   17,   18,  206,    0,  111,   22,    0,   23,
  233,  112,   95,   95,    0,   95,    0,   95,    0,   74,
  284,   71,   22,  234,   23,   74,   72,   71,   22,  165,
   23,    0,   72,   16,   17,   18,  175,  175,  243,  111,
   22,    0,   23,    0,  112,  285,  104,   22,    0,   23,
    0,  105,  286,  111,   22,    0,   23,    0,  112,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  314,
    0,    0,  210,    0,    0,  210,    0,    0,    0,  121,
    0,    0,    0,    0,    0,    0,  269,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         15,
   15,   43,   40,   45,   41,  126,   40,   25,   59,  126,
   41,  256,   43,  267,   45,  269,    0,   59,  123,   66,
   15,   59,   38,   38,   89,   90,    6,  272,   44,   44,
   44,   41,  269,   40,  109,  272,   40,   43,   33,   45,
  256,   41,  125,  108,   62,   61,   61,   41,   41,   40,
    0,    0,   44,  269,   70,   41,   41,   37,   74,  267,
  268,  269,  109,   79,   40,   40,   44,   59,  125,  277,
  267,  268,    7,   41,   69,  272,   40,   12,   73,   40,
   60,   59,   41,   41,  264,  265,  123,   59,  103,  269,
   70,   71,   72,  125,   74,  123,   40,   77,  114,   79,
   80,   81,  256,  224,   84,   41,  260,  224,  256,  174,
  175,  259,  260,  123,   70,   40,  123,   40,   43,   75,
   45,  125,  138,  123,  140,  141,   59,  107,  258,  123,
   41,  111,  112,  113,  114,   60,   61,   62,  123,   40,
  156,  125,  158,  159,  160,  256,   43,  271,   45,  165,
  125,  167,  168,  258,  229,  123,  123,  173,  173,  139,
   58,  125,  258,   40,  210,  123,   43,  213,   45,   43,
  150,   45,  188,  189,  190,  125,  125,  157,  173,  254,
  227,  125,  229,   60,   61,   62,  202,  123,  204,  205,
  258,  207,  172,  209,   40,  267,  268,   43,  123,   45,
  125,  258,  259,  260,  184,  256,  253,  254,  123,  225,
  267,  268,  269,  269,   60,   61,   62,   59,  269,   40,
  236,  258,   43,  203,   45,  267,  268,  207,  275,  209,
  246,  247,  248,  249,  250,  269,  267,  268,  269,   60,
   61,   62,  289,   40,  279,  225,  123,  269,  258,  256,
  257,   41,  256,  257,  261,  269,  236,  261,  258,  263,
  125,  267,  269,  310,  258,  269,  270,  282,  264,  265,
  317,  264,  265,  258,  278,  279,  269,  123,  264,  265,
  260,  256,  257,  269,  300,  301,  261,   43,  263,   45,
  258,  271,  256,  257,  269,  270,  312,  261,  314,  263,
  258,  125,  123,  278,  279,  269,  270,   41,  267,  268,
  269,   41,  256,  257,  278,  279,   59,  261,  277,  263,
  300,   43,  258,   45,   41,  269,  270,   43,  125,   45,
   59,  256,  257,  123,  278,  279,  261,  262,  263,  264,
  265,  266,  267,  268,  269,   43,  259,   45,  273,  274,
  275,  276,  277,  269,  279,  256,  257,   41,  256,  269,
  261,   40,  263,  269,   43,   58,   45,  271,  269,  270,
  267,  268,  269,   41,  272,  269,   43,  278,   45,  256,
  257,   60,   61,   62,  261,  262,  263,  264,  265,  266,
  267,  268,  269,  267,  268,  269,  273,  274,  275,  276,
  277,  272,  279,  277,   40,  279,  123,   43,   59,   45,
  256,  257,  267,  268,   41,  261,  262,   44,  264,  265,
  266,  267,  268,  269,   60,   61,   62,  273,  274,  275,
  276,  277,   43,  279,   45,  256,  257,   52,   53,  123,
  261,  262,  125,  264,  265,  266,  267,  268,  269,   60,
   61,   62,  273,  274,  275,  276,  277,   59,  279,  256,
  257,   43,  125,   45,  261,   43,  263,   45,  258,   60,
   61,   62,  269,  270,  267,  268,  269,   58,   60,   61,
   62,  278,  279,  125,  277,   58,   40,  123,   42,   43,
   44,   45,   41,   47,   40,   44,   42,   43,   44,   45,
   41,   47,  125,   44,   58,   58,   60,   61,   62,   59,
  269,  267,  268,  269,   60,   61,   62,   40,  119,   42,
   43,  277,   45,   58,   47,  126,  267,  268,  269,  269,
   42,   43,   59,   45,   59,   47,  277,   60,   61,   62,
   41,  258,  267,  268,  269,  267,  268,  269,   60,   61,
   62,  267,  268,  269,   41,  277,  267,  268,  269,   59,
   59,  277,   42,   43,   59,   45,   59,   47,   41,  267,
  268,  269,  267,   59,  258,   59,   59,  256,   41,  277,
   60,   61,   62,   58,   24,  267,  268,  269,  267,  268,
  269,  267,  268,  269,  273,  274,  275,   58,  277,   70,
  267,  268,  267,  268,  269,  267,  268,  269,   79,  210,
  267,   41,  213,   84,   42,   43,   59,   45,    0,   47,
  256,  257,  125,  224,   59,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  123,  125,  107,  273,  274,  275,
  276,  277,   59,  114,  125,  256,   24,   87,  125,   89,
   90,  123,  236,    4,  123,  123,  267,  268,  269,  164,
  271,  217,  273,  274,  275,   -1,  277,   -1,  108,   -1,
   48,   -1,   -1,   24,  256,   42,   43,   -1,   45,   30,
   47,   32,  273,  274,  275,  267,  268,  269,   -1,  267,
  268,  273,  274,  275,   -1,  277,  267,  268,  269,   -1,
   -1,  172,  256,  258,  259,  260,   -1,   -1,   -1,   87,
  256,   89,   90,  184,   -1,  269,   -1,   -1,  272,  273,
  274,  275,   -1,  269,   -1,   -1,  272,  273,  274,  275,
  108,   -1,   -1,   -1,  174,  175,  207,   -1,   -1,   90,
   40,  119,   42,   43,   -1,   45,   -1,   47,  126,   -1,
  273,  274,  275,   42,   43,   -1,   45,  108,   47,   59,
   -1,  273,  274,  275,  115,   -1,   42,   43,   -1,   45,
   59,   47,   42,   43,   -1,   45,   -1,   47,   -1,   40,
   41,   42,   43,   59,   45,   40,   47,   42,   43,   59,
   45,   -1,   47,  273,  274,  275,  174,  175,   41,   42,
   43,   -1,   45,   -1,   47,   41,   42,   43,   -1,   45,
   -1,   47,   41,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  300,
   -1,   -1,  210,   -1,   -1,  213,   -1,   -1,   -1,  217,
   -1,   -1,   -1,   -1,   -1,   -1,  224,
};
}
final static short YYFINAL=3;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"IF","THEN","ELSE","END_IF","OUT","FUN",
"RETURN","I8","F32","WHEN","CTE_ENTERA","CTE_FLOTANTE","ID","CONTINUE",
"CADENA_MULT","ASIGNACION","MAYOR_IGUAL","MENOR_IGUAL","DISTINTO","CONST",
"TOF32","BREAK","FOR",
};
final static String yyrule[] = {
"$accept : Programa",
"Programa : Nombre_programa '{' ListSentencias '}'",
"Programa : Nombre_programa ListSentencias '}'",
"Programa : Nombre_programa ListSentencias",
"Programa : error '}'",
"Nombre_programa : ID",
"ListSentencias : SentenciaControl ListSentencias",
"ListSentencias : SentenciaDeclarativa ListSentencias",
"ListSentencias : SentenciaEjecutable ListSentencias",
"ListSentencias : SentenciaEjecutable",
"ListSentencias : SentenciaControl",
"ListSentencias : SentenciaDeclarativa",
"SentenciaDeclarativa : Tipo ListVariables ';'",
"SentenciaDeclarativa : Tipo error ';'",
"SentenciaDeclarativa : Funcion",
"SentenciaDeclarativa : CONST ListCte ';'",
"SentenciaDeclarativa : WhenCondicion",
"SentenciaDeclarativa : ListVariables ';'",
"SentenciaDeclarativa : Tipo ';'",
"ListCte : AsigCte",
"ListCte : ListCte ',' AsigCte",
"AsigCte : ID ASIGNACION CTE_ENTERA",
"AsigCte : ID ASIGNACION signo CTE_ENTERA",
"AsigCte : ID ASIGNACION CTE_FLOTANTE",
"AsigCte : ID ASIGNACION signo CTE_FLOTANTE",
"AsigCte : ASIGNACION cte",
"AsigCte : ID ASIGNACION",
"AsigCte : ID cte",
"Tipo : I8",
"Tipo : F32",
"ListVariables : ID ',' ListVariables",
"ListVariables : ID",
"ListVariables : ID ListVariables",
"HeaderFuncion : Fun '(' Parametro ',' Parametro ')' ':' Tipo",
"HeaderFuncion : Fun '(' Parametro ')' ':' Tipo",
"HeaderFuncion : Fun '(' ')' ':' Tipo",
"HeaderFuncion : FUN '(' Parametro ',' Parametro ')' ':' Tipo",
"HeaderFuncion : FUN '(' Parametro ')' ':' Tipo",
"HeaderFuncion : FUN '(' ')' ':' Tipo",
"HeaderFuncion : Fun '(' Parametro ',' Parametro ')' ':'",
"HeaderFuncion : Fun '(' Parametro ')' ':'",
"HeaderFuncion : Fun '(' ')' ':'",
"Fun : FUN ID",
"Funcion : HeaderFuncion '{' Cuerpo '}'",
"Funcion : HeaderFuncion '{' '}'",
"Funcion : '{' Cuerpo '}'",
"Funcion : HeaderFuncion '{' ListSentencias '}'",
"Parametro : Tipo ID",
"Parametro : ID",
"Cuerpo : SentenciaEjecutable Cuerpo",
"Cuerpo : SentenciaDeclarativa Cuerpo",
"Cuerpo : SentenciaReturn",
"SentenciaEjecutable : Seleccion",
"SentenciaEjecutable : Asig",
"SentenciaEjecutable : Salida ';'",
"Asig : ID ASIGNACION Expresion ';'",
"Asig : ID ASIGNACION ID ';'",
"Asig : ID ASIGNACION cte ';'",
"Asig : ID ASIGNACION signo cte ';'",
"Asig : ID error ';'",
"HeaderWhen : WHEN Condicion THEN",
"HeaderWhen : Condicion THEN",
"HeaderWhen : WHEN Condicion",
"WhenCondicion : HeaderWhen '{' ListSentencias '}'",
"WhenCondicion : error '}'",
"Salida : OUT '(' CADENA_MULT ')'",
"Salida : '(' CADENA_MULT ')'",
"ParametroReal : cte",
"ParametroReal : signo cte",
"ParametroReal : ID",
"Expresion : ID signo ID",
"Expresion : ID signo cte",
"Expresion : Expresion signo ID",
"Expresion : Expresion signo cte",
"Expresion : ID '*' ID",
"Expresion : ID '/' ID",
"Expresion : ID '*' cte",
"Expresion : ID '/' cte",
"Expresion : Expresion '*' ID",
"Expresion : Expresion '/' ID",
"Expresion : Expresion '*' cte",
"Expresion : Expresion '/' cte",
"Expresion : ConversionExplicita",
"Expresion : cte signo ID",
"Expresion : cte '*' ID",
"Expresion : cte '/' ID",
"Expresion : ID ListParametrosInv",
"ListParametrosInv : '(' ')'",
"ListParametrosInv : '(' ParametroReal ')'",
"ListParametrosInv : '(' ParametroReal ',' ParametroReal ')'",
"ConversionExplicita : TOF32 '(' Expresion ')'",
"ConversionExplicita : TOF32 '(' ')'",
"signo : '+'",
"signo : '-'",
"cte : CTE_FLOTANTE",
"cte : CTE_ENTERA",
"SentenciaReturn : RETURN '(' ID ')' ';'",
"SentenciaReturn : RETURN '(' cte ')' ';'",
"SentenciaReturn : RETURN '(' Expresion ')' ';'",
"SentenciaReturn : RETURN '(' ID ')'",
"SentenciaReturn : RETURN '(' cte ')'",
"SentenciaReturn : RETURN '(' Expresion ')'",
"SentenciaReturn : RETURN ';'",
"ListSentenciasIf : SentenciaEjecutable ListSentenciasIf",
"ListSentenciasIf : SentenciaCorte ListSentenciasIf",
"ListSentenciasIf : SentenciaEjecutable",
"ListSentenciasIf : SentenciaCorte",
"HeaderIf : IF Condicion THEN",
"HeaderIf : error THEN",
"SentenciasIf : SentenciaEjecutable",
"SentenciasIf : '{' ListSentenciasIf '}'",
"CuerpoIf : SentenciasIf",
"Else : ELSE",
"CuerpoElse : Else CuerpoIf END_IF",
"CuerpoElse : Else CuerpoIf error",
"Seleccion : HeaderIf CuerpoIf END_IF",
"Seleccion : HeaderIf CuerpoIf CuerpoElse",
"Seleccion : HeaderIf CuerpoIf error",
"Seleccion : error ELSE",
"Seleccion : error END_IF",
"Condicion : '(' Comparacion ')'",
"Condicion : '(' Comparacion",
"Condicion : Comparacion ')'",
"Comparador : '<'",
"Comparador : '>'",
"Comparador : MAYOR_IGUAL",
"Comparador : MENOR_IGUAL",
"Comparador : '='",
"Comparador : DISTINTO",
"Comparacion : Expresion Comparador Expresion",
"Comparacion : Expresion Comparador cte",
"Comparacion : Expresion Comparador signo cte",
"Comparacion : cte Comparador Expresion",
"Comparacion : signo cte Comparador Expresion",
"Comparacion : Expresion Comparador ID",
"Comparacion : ID Comparador Expresion",
"Comparacion : cte Comparador ID",
"Comparacion : signo cte Comparador ID",
"Comparacion : ID Comparador cte",
"Comparacion : ID Comparador signo cte",
"Comparacion : ID Comparador ID",
"Comparacion : cte Comparador cte",
"Comparacion : Comparador error",
"Comparacion : error ID",
"Comparacion : error cte",
"ListSentenciasFor : ListSentenciasFor SentenciaEjecutable",
"ListSentenciasFor : ListSentenciasFor SentenciaControl",
"ListSentenciasFor : ListSentenciasFor SentenciaCorte",
"ListSentenciasFor : SentenciaControl",
"ListSentenciasFor : SentenciaEjecutable",
"ListSentenciasFor : SentenciaCorte",
"HeaderFor : FOR '(' AsigFor ';' CondicionFor ';' signo CTE_ENTERA ')'",
"HeaderFor : FOR '(' AsigFor ';' CondicionFor ';' CTE_ENTERA ')'",
"HeaderForID : ID ASIGNACION HeaderFor",
"HeaderEtiqueta : ID ':' HeaderFor",
"AsigFor : ID ASIGNACION CTE_ENTERA",
"AsigFor : ID ASIGNACION ID",
"CuerpoFor : '{' ListSentenciasFor '}'",
"CuerpoFor : '{' '}' ';'",
"SentenciaControl : HeaderFor CuerpoFor",
"SentenciaControl : HeaderEtiqueta CuerpoFor",
"SentenciaControl : HeaderForID CuerpoFor ELSE cte ';'",
"SentenciaControl : HeaderForID CuerpoFor ELSE signo cte ';'",
"CondicionFor : ID Comparador ID",
"CondicionFor : ID Comparador Expresion",
"CondicionFor : ID Comparador CTE_ENTERA",
"CondicionFor : ID Comparador signo CTE_ENTERA",
"SentenciaCorte : SentenciaReturn",
"SentenciaCorte : BREAK ';'",
"SentenciaCorte : BREAK cte ';'",
"SentenciaCorte : BREAK signo cte ';'",
"SentenciaCorte : CONTINUE ':' ID ';'",
};

//#line 410 "Gramatica.y"

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

//#line 780 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 32 "Gramatica.y"
{desconcatenarAmbito();}
break;
case 2:
//#line 33 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' ");}
break;
case 3:
//#line 34 "Gramatica.y"
{AgregarErrorSintactico("Se esperan '{' '}' ");}
break;
case 4:
//#line 35 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 5:
//#line 38 "Gramatica.y"
{ambito= val_peek(0).sval; tablaDeSimbolos.add(new Simbolo(val_peek(0).sval, 269,"nombre_programa"));
					 estructuraActual=estructuraTercetosPrincipal;
					 listEstructurasSeguimiento.add(estructuraActual);
					 listEstructurasTercetos.add(estructuraActual);
					 }
break;
case 13:
//#line 55 "Gramatica.y"
{AgregarErrorSintactico("Se espera identificador");}
break;
case 17:
//#line 59 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de la variable");}
break;
case 18:
//#line 60 "Gramatica.y"
{AgregarErrorSintactico("Se espera identificador de la variable");}
break;
case 21:
//#line 67 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 22:
//#line 68 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval + val_peek(0).sval);}
break;
case 23:
//#line 69 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 24:
//#line 70 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval + val_peek(0).sval);}
break;
case 25:
//#line 71 "Gramatica.y"
{AgregarErrorSintactico("Se espera un identificador");}
break;
case 26:
//#line 72 "Gramatica.y"
{AgregarErrorSintactico("Se espera una constante ");}
break;
case 27:
//#line 73 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:' ");}
break;
case 28:
//#line 76 "Gramatica.y"
{estructuraActual.setTipo("I8");}
break;
case 29:
//#line 77 "Gramatica.y"
{estructuraActual.setTipo("F32");}
break;
case 30:
//#line 80 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
break;
case 31:
//#line 81 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
break;
case 32:
//#line 82 "Gramatica.y"
{AgregarErrorSintactico("Se espera ',' ");}
break;
case 33:
//#line 85 "Gramatica.y"
{tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo()); }
break;
case 34:
//#line 86 "Gramatica.y"
{tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo());}
break;
case 35:
//#line 87 "Gramatica.y"
{tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo());}
break;
case 36:
//#line 89 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 37:
//#line 90 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 38:
//#line 91 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 39:
//#line 92 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 40:
//#line 93 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 41:
//#line 94 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 42:
//#line 97 "Gramatica.y"
{estructuraActual=new EstructuraTercetos(val_peek(0).sval+":"+ambito);
			 ambito= ambito+":"+val_peek(0).sval; tablaDeSimbolos.add(new Simbolo(ambito, 269, "identificador_funcion"));
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 }
break;
case 43:
//#line 104 "Gramatica.y"
{desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   }
break;
case 44:
//#line 108 "Gramatica.y"
{AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
break;
case 45:
//#line 109 "Gramatica.y"
{AgregarErrorSintactico("Se espera el header de la funcion");}
break;
case 46:
//#line 110 "Gramatica.y"
{AgregarErrorSintactico("Se espera el retorno de la funcion");}
break;
case 47:
//#line 114 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito,269,"parametro", estructuraActual.getTipo()));}
break;
case 48:
//#line 115 "Gramatica.y"
{AgregarErrorSintactico("Se espera tipo de parametro");}
break;
case 55:
//#line 128 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 56:
//#line 129 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(1).sval, ambito));}
break;
case 57:
//#line 130 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval);}
break;
case 58:
//#line 131 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(3).sval, tablaDeSimbolos.getRefSimbolo(val_peek(4).sval, ambito), val_peek(2).sval + val_peek(1).sval);}
break;
case 59:
//#line 132 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 60:
//#line 135 "Gramatica.y"
{ambito= ambito+":"+"when";}
break;
case 61:
//#line 136 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
//#line 137 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 64:
//#line 141 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 66:
//#line 146 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 70:
//#line 155 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 71:
//#line 156 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 72:
//#line 157 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 73:
//#line 158 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 74:
//#line 159 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 75:
//#line 160 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 76:
//#line 161 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 77:
//#line 162 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 78:
//#line 163 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 79:
//#line 164 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 80:
//#line 165 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 81:
//#line 166 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 83:
//#line 168 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 84:
//#line 169 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 85:
//#line 170 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 91:
//#line 180 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 95:
//#line 189 "Gramatica.y"
{chequearRangoEntero(val_peek(0).sval);}
break;
case 99:
//#line 195 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 100:
//#line 196 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 101:
//#line 197 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 102:
//#line 198 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 108:
//#line 208 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 112:
//#line 219 "Gramatica.y"
{estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();}
break;
case 113:
//#line 224 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);}
break;
case 114:
//#line 225 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 115:
//#line 228 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);}
break;
case 117:
//#line 230 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 118:
//#line 231 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 119:
//#line 232 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 120:
//#line 235 "Gramatica.y"
{estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
								estructuraActual.addTercetoIf();}
break;
case 121:
//#line 237 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 122:
//#line 238 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 129:
//#line 249 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(2), estructuraActual.getRefTerceto(1));}
break;
case 130:
//#line 250 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 131:
//#line 251 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getRefTerceto(1), val_peek(1).sval + val_peek(0).sval );}
break;
case 132:
//#line 252 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, estructuraActual.getRefTerceto(1));}
break;
case 133:
//#line 253 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, estructuraActual.getRefTerceto(1));}
break;
case 134:
//#line 254 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 135:
//#line 255 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 136:
//#line 256 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 137:
//#line 257 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 138:
//#line 258 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 139:
//#line 259 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval + val_peek(0).sval);}
break;
case 140:
//#line 260 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 141:
//#line 261 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);}
break;
case 142:
//#line 262 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 143:
//#line 263 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 144:
//#line 264 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 151:
//#line 277 "Gramatica.y"
{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 152:
//#line 286 "Gramatica.y"
{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto("+", estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 153:
//#line 296 "Gramatica.y"
{estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval,ambito));
										}
break;
case 154:
//#line 302 "Gramatica.y"
{/*tablaDeSimbolos.add(new Simbolos($1.sval+":"+ambito, 269, "etiqueta_for")); estructuraActual.addEtiquetaFor($1.sval);*/
									}
break;
case 155:
//#line 307 "Gramatica.y"
{estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), val_peek(0).sval);
	 	}
break;
case 156:
//#line 311 "Gramatica.y"
{estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
	 	}
break;
case 157:
//#line 317 "Gramatica.y"
{
					 estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.completarTercetoFor(1);
					 estructuraActual.popIdFor();}
break;
case 158:
//#line 322 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 159:
//#line 326 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
				}
break;
case 160:
//#line 331 "Gramatica.y"
{
					/*estructuraActual.addRefEtiqueta();*/
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
				}
break;
case 161:
//#line 337 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
					esperandoBreakcte=false;
					estructuraActual.borrarIdAsigFor();
					}
break;
case 162:
//#line 347 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(2).sval + val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.borrarListTercetosBreak();
					estructuraActual.borrarListTercetosBreakCte();
					dentroDeFor=false;
					esperandoBreakcte=false;
					estructuraActual.borrarIdAsigFor();
				}
break;
case 163:
//#line 360 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 164:
//#line 361 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 165:
//#line 362 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 166:
//#line 363 "Gramatica.y"
{estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval+val_peek(0).sval);}
break;
case 168:
//#line 367 "Gramatica.y"
{
				if(dentroDeFor){
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreak();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
					System.out.println("No existe ID para la asignacion");
				}
			  }
break;
case 169:
//#line 377 "Gramatica.y"
{
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(1).sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
					System.out.println("No existe ID para la asignacion");
				}
				}
break;
case 170:
//#line 388 "Gramatica.y"
{
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(2).sval + val_peek(1).sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
					System.out.println("No existe ID para la asignacion");
			  	}
			  }
break;
case 171:
//#line 399 "Gramatica.y"
{
				/*if ((dentroDeFor) && (estructuraActual.existeEtiqueta($3.sval))){*/
					/*estructuraActual.crearTerceto("BI", estructuraActual.getRefTercetoEtiqueta($3.sval), null);*/
					/*estructuraActual.guardarTercetoEtiquetaFor();*/

				/*}*/
			  }
break;
//#line 1485 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
