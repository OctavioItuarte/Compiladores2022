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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
//#line 23 "Parser.java"




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
   23,   23,   24,   10,   10,   21,   21,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   27,   27,   27,
   28,   28,   28,   26,   26,   12,   12,   13,   13,   29,
   29,   29,   29,   31,   31,   32,   32,   32,   33,   34,
   34,   19,   19,   19,   19,   19,   25,   25,   25,   36,
   36,   36,   36,   36,   36,   35,   35,   35,   35,   35,
   35,   35,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   37,   37,   37,   37,   37,   37,   38,   38,   41,
   42,   43,   39,   39,   44,   44,    3,    3,    3,    3,
   40,   40,   40,   40,   18,   18,   18,   18,   18,   18,
   18,   18,   30,   30,   30,   30,   30,
};
final static short yylen[] = {                            2,
    4,    3,    2,    2,    1,    2,    2,    2,    1,    1,
    1,    3,    3,    1,    3,    1,    2,    2,    1,    3,
    3,    4,    3,    4,    2,    2,    2,    1,    1,    3,
    1,    2,    8,    6,    5,    8,    6,    5,    7,    5,
    4,    2,    4,    3,    3,    4,    2,    1,    2,    2,
    1,    1,    1,    2,    4,    4,    4,    5,    3,    3,
    2,    2,    1,    4,    2,    4,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    1,
    3,    3,    3,    2,    3,    3,    3,    2,    3,    5,
    1,    2,    1,    4,    3,    1,    1,    1,    1,    2,
    2,    1,    1,    3,    2,    1,    1,    3,    1,    3,
    3,    3,    3,    3,    2,    2,    3,    2,    2,    1,
    1,    1,    1,    1,    1,    3,    3,    4,    3,    4,
    3,    3,    3,    4,    3,    4,    3,    3,    2,    2,
    2,    2,    2,    2,    1,    1,    1,    9,    8,    3,
    2,    2,    3,    3,    3,    3,    2,    2,    5,    6,
    3,    3,    3,    4,    5,    5,    6,    5,    4,    4,
    4,    2,    1,    2,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    5,    0,    0,    4,    0,    0,    0,    0,   28,
   29,   63,   99,   98,    0,  122,  123,  125,    0,    0,
    0,   96,   97,    0,    0,  120,  121,  124,    0,    0,
    0,    0,    0,    0,   14,   16,    0,    0,    0,    0,
   52,   53,    0,    0,    0,    0,    0,   80,    0,    0,
    0,    0,    0,    0,    0,  105,  115,  116,  140,   65,
  141,    0,    0,    0,    0,    0,   42,    0,    0,    0,
    0,    0,    0,    0,    0,  151,   32,    0,   84,    0,
    0,    0,    0,   19,    0,    0,    0,    0,    0,    0,
    0,    0,   51,    0,    0,    2,    6,    7,    8,    0,
   18,    0,   17,    0,    0,    0,    0,    0,    0,    0,
   54,    0,    0,    0,    0,    0,    0,   61,    0,    0,
    0,    0,    0,    0,  106,  173,  107,    0,  119,  139,
    0,  157,    0,  152,  158,  104,    0,   48,    0,    0,
    0,   59,    0,    0,    0,    0,  150,   72,   74,   73,
   75,   30,   93,   88,    0,   91,    0,   68,   69,    0,
    0,    0,    0,    0,   27,   25,   15,    0,    0,   95,
    0,    0,    0,    0,  172,    0,    0,    0,    0,    1,
   50,   49,   45,   67,  117,   13,   12,    0,   82,   86,
   83,   87,   81,   85,    0,    0,    0,   44,    0,    0,
    0,    0,   76,   78,   77,   79,   70,   71,    0,    0,
    0,    0,    0,   60,    0,    0,  174,    0,    0,    0,
    0,    0,  114,  109,  112,    0,  113,    0,    0,  145,
  146,  147,    0,    0,   66,    0,   47,    0,    0,   56,
    0,   57,   55,   92,    0,   89,  136,   21,   23,    0,
   20,   94,    0,    0,    0,    0,    0,    0,    0,    0,
   46,   43,    0,    0,    0,  128,   64,    0,    0,  175,
  100,  108,  101,    0,  156,  155,  143,  142,  144,    0,
    0,   38,    0,    0,   58,    0,   22,   24,  153,  154,
    0,    0,    0,    0,    0,    0,   35,    0,    0,  177,
  176,  111,  110,    0,  159,    0,   37,   90,    0,    0,
  165,    0,  166,  168,    0,   34,  160,    0,    0,    0,
    0,    0,    0,    0,  167,    0,   36,  164,  149,    0,
   33,  148,
};
final static short yydgoto[] = {                          3,
    4,   98,   30,   31,   32,   33,   34,   35,   83,   36,
   84,   37,   38,   39,   40,  141,   92,   93,   41,   42,
   43,   44,   45,   46,   47,   48,   79,  157,  221,  222,
   49,  128,  226,  227,   50,   51,  233,   52,  174,  292,
   53,   54,   55,  132,
};
final static short yysindex[] = {                      -146,
 -114,    0,    0,  256,    0,  430,  418,  -22,  -37,    0,
    0,    0,    0,    0,   37,    0,    0,    0,  -84,   54,
   82,    0,    0,  229,  466,    0,    0,    0, -100,  321,
  321,  321,  -49,   75,    0,    0,  129,   12,  -72,  124,
    0,    0,  115,  617,  -19,  418,  -79,    0,  169,  160,
  -73,   76,   76,  -76,   76,    0,    0,    0,    0,    0,
    0,  155,  585,  588,  -48,  -57,    0,  109,  163,  -42,
  204,  220,  343,  -30,  324,    0,    0,  389,    0,   58,
  -97,  129,    3,    0,  -13,  -21,  -24,  511,  116,  229,
  229,  128,    0,  237,  241,    0,    0,    0,    0,  211,
    0,  225,    0,  408,  452,  458,  478, -199,   81,  135,
    0,  555,  558,  563,   63,  321,   30,    0,  576, -219,
  240,  456,  345,   53,    0,    0,    0, -167,    0,    0,
  280,    0,   56,    0,    0,    0,  287,    0,  278,   70,
  151,    0,   -1,  129,  521,  659,    0,    0,    0,    0,
    0,    0,    0,    0,  129,    0,  164,    0,    0,  719,
  129,   90,  773,   -5,    0,    0,    0,  -84,  719,    0,
   90,  562,   79,  294,    0,  386,  577,  511,  511,    0,
    0,    0,    0,    0,    0,    0,    0,  -56,    0,    0,
    0,    0,    0,    0,  719,   90,  773,    0,  234,  245,
  304,  232,    0,    0,    0,    0,    0,    0,  719,  129,
   90,  773,  247,    0,  398,  111,    0,  129,  325,  345,
  261,  345,    0,    0,    0,  169,    0,  -54,  331,    0,
    0,    0,  283,   66,    0,  142,    0,  185,  336,    0,
  344,    0,    0,    0,  108,    0,    0,    0,    0,  145,
    0,    0, -155,  147,  713,  129,  751,  767,  719,  773,
    0,    0,  142,  185,  362,    0,    0,  375,  376,    0,
    0,    0,    0, -189,    0,    0,    0,    0,    0,  129,
  381,    0,  401,  142,    0,  404,    0,    0,    0,    0,
  408,  392,  405,  419,  417,  423,    0,  426,  142,    0,
    0,    0,    0,  425,    0,  442,    0,    0,  403,  -38,
    0,  448,    0,    0,  461,    0,    0,  142,    0,  719,
  210,  773,  469,  249,    0,  142,    0,    0,    0,  473,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  475,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  538,   13,
   14,   19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  475,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  420,
  422,    0,    0,    0, -103,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  434,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -35,
    0,   -8,   -7,   17,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  475,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   23,   42,   44,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,    0,
   46,   55,    0,    0,    0,    0,    0,    0,    0,  435,
    0,  440,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   61,   64,
    0,    0,  444,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  132,    0,  158,  196,    0,    0,  446,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  670,  483,
    0,  489,    0,    0,    0,  447,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  712, -116,  552,  660,  -47,  103,    0,    0,    0,
  406,  -15,  153,    0,    0,  -98,  -59,  580,    0,    0,
    0,  -63,    0,    0,   20,    0,    0,  330,  -93,  -40,
    0,  353,    0,    0,   11,  -14,    0,  -28,    0,    0,
    0,    0,    0,  105,
};
final static int YYTABLESIZE=893;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
   80,   74,   68,   76,   22,  137,   23,  146,  127,  101,
    5,  202,   10,   11,  230,  176,  163,   66,    9,  118,
  140,  172,  107,  108,   96,  134,   65,  170,  114,  115,
  181,  182,  135,  132,  175,   95,   69,   22,   75,   23,
   72,   22,  147,   23,  197,   73,  168,   78,   80,  200,
  109,  212,  215,  105,   22,  144,   23,  240,  106,  155,
   26,  167,  140,  133,  161,  117,  302,   13,   14,  195,
  303,   26,   28,   27,   95,   26,   75,   20,   72,   22,
   74,   23,  138,   73,  129,  131,  127,  137,  223,  188,
  232,  224,  225,   85,   76,  126,   26,   28,   27,  210,
   22,  134,   23,  116,  130,   22,  218,   23,   22,    1,
   23,  289,  258,  290,  135,  132,  277,   77,  181,  182,
   25,   86,    2,   22,  260,   23,  271,   78,  273,  107,
  114,  105,   22,  103,   23,  102,  106,   10,   11,  283,
   26,   28,   27,    9,   78,  133,  107,  114,  250,  139,
   22,  146,   23,   78,  118,  107,  114,  133,   61,  135,
  256,   78,   80,  110,  138,  298,  129,  131,  127,   13,
   14,  169,   77,  111,  164,  201,  152,  126,  118,   78,
  107,  114,  130,  134,   81,  127,  130,   82,  282,  104,
  140,  239,  279,   78,  238,  107,  114,  170,  131,  144,
  129,   69,   21,   88,  246,  198,  100,  245,  124,  136,
   13,   14,  259,  137,   61,  297,  140,   71,  280,   70,
   20,  142,  137,  145,  149,  151,   70,  156,  323,  155,
  159,   67,  162,  165,  166,  171,  307,  171,   70,   78,
  180,  107,  114,   78,  114,  322,   22,  173,   23,  135,
  132,  316,  183,   13,   14,  169,  169,  190,  192,  194,
  196,  248,  249,   20,  204,  206,  208,  211,   25,  186,
  327,   22,  265,   23,  219,  264,  309,  184,  331,   77,
  133,  185,  170,  187,   16,   17,   18,  214,   26,   28,
   27,  123,   69,  321,  324,   25,  241,  216,   22,  138,
   23,  129,  131,  127,   78,   70,  114,  244,   71,   16,
   17,   18,  126,  247,  234,   26,   28,   27,  134,  124,
  171,  130,  124,   94,   13,   14,  160,  235,  257,   13,
   14,  209,   13,   14,   20,  236,    6,    7,  237,   20,
  171,    8,    9,   87,   10,   11,   12,   13,   14,   15,
  253,   88,  254,   16,   17,   18,   19,   20,  261,   21,
   25,  263,  266,   22,  154,   23,   22,  145,   23,  262,
  269,  267,   10,   11,   13,   14,  153,  138,   24,  268,
   26,   28,   27,  270,  124,  272,  281,  169,  169,  275,
  169,  169,  169,  284,  169,   13,   14,  156,   10,   11,
  169,  169,  285,  138,  229,   10,   11,  276,  294,  169,
  169,  287,  288,  170,  170,  291,  170,  170,  170,  299,
  170,   13,   14,   59,  119,    7,  170,  170,   22,    8,
   23,   87,  304,  300,  301,  170,  170,  120,  121,  305,
   22,  306,   23,   88,  308,   22,  122,   23,   10,   11,
  310,  171,  171,  138,  171,  171,  171,   64,  171,  312,
   22,  171,   23,  311,  171,  171,  315,   26,   28,   27,
   13,   14,  143,  171,  171,  313,  328,   26,   28,   27,
   20,  314,   21,  317,    6,    7,   13,   14,  148,    8,
    9,   87,   10,   11,   12,   13,   14,   15,   22,  318,
   23,   16,   17,   18,   19,   20,  325,   21,   22,  329,
   23,    6,    7,  332,  217,  330,    8,    9,  326,   10,
   11,   12,   13,   14,   15,   26,   28,   27,   16,   17,
   18,   19,   20,   31,   21,  119,    7,    3,  119,    7,
    8,  161,   87,    8,   11,   87,    9,  162,  228,  121,
   25,  228,  121,   22,   60,   23,   62,  122,   21,  102,
  122,   21,  105,   22,  103,   23,   41,  106,   40,   39,
   26,   28,   27,  251,  286,   90,    6,    7,  274,  242,
    0,    8,    9,    0,   10,   11,   12,   13,   14,   15,
   13,   14,  153,   16,   17,   18,   19,   20,    0,   21,
  119,    7,  252,  112,   22,    8,   23,   87,  113,   13,
   14,  150,    0,  120,  121,    0,   75,    0,   72,   22,
   74,   23,  122,   73,   75,    0,   72,   22,  126,   23,
   22,   73,   23,   88,    0,    0,   26,   28,   27,  178,
    0,   90,   90,    0,   26,   28,   27,   26,   28,   27,
    0,    0,   13,   14,  255,   13,   14,  158,  112,   22,
   90,   23,   20,  113,   13,   14,  143,    0,    0,  319,
   14,  320,    0,   62,   20,    0,   26,   28,   27,   20,
   16,   17,   18,   91,   13,   14,   63,   56,   57,   58,
   16,   17,   18,    0,   20,    0,   13,   14,   59,    0,
  112,   22,  126,   23,    0,  113,    0,    0,  125,    0,
  126,   99,   99,    0,   99,   29,   99,  243,   13,   14,
  189,   62,   13,   14,   13,   14,  191,    0,  163,  178,
  178,    0,   13,   14,   63,   89,   94,    0,   16,   17,
   18,   97,   20,   99,   13,   14,  193,  179,    0,   91,
   91,    0,   75,  293,   72,   22,    0,   23,   75,   73,
   72,   22,    0,   23,    0,   73,    6,    7,   91,    0,
    0,    8,    9,   87,   10,   11,   12,   13,   14,  177,
    0,    0,  220,   16,   17,   18,   19,   20,    0,    0,
  231,  295,  105,   22,    0,   23,    0,  106,    0,  126,
    0,  126,   99,    0,    0,  126,    0,  296,  112,   22,
    0,   23,  126,  113,  112,   22,    0,   23,    0,  113,
  199,   13,   14,  203,   13,   14,  205,  213,    0,   13,
   14,  207,   69,   56,   57,   58,    0,  179,  179,    0,
    0,    0,    0,   62,    0,   70,    0,    0,  215,   16,
   17,   18,    0,    0,   13,   14,   63,   16,   17,   18,
   16,   17,   18,    0,   20,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  220,
    0,  220,    0,    0,    0,  125,    0,    0,    0,   16,
   17,   18,  278,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         15,
   15,   44,   40,   58,   43,   41,   45,   71,   49,   59,
  125,  110,    0,    0,  131,   40,   80,   40,    0,  123,
   68,   85,   38,   38,  125,   54,    7,   41,   44,   44,
   90,   91,   41,   41,   59,   25,  256,   43,   40,   45,
   42,   43,   71,   45,  108,   47,   44,   63,   63,  109,
  123,  115,  272,   42,   43,   71,   45,   59,   47,   75,
   44,   59,  110,   41,   80,   46,  256,  267,  268,  269,
  260,   60,   61,   62,   64,   59,   40,  277,   42,   43,
   44,   45,   41,   47,   41,   41,   41,  123,  256,  104,
  131,  259,  260,   40,   58,   41,   60,   61,   62,  115,
   43,   41,   45,  123,   41,   43,  122,   45,   43,  256,
   45,  267,  176,  269,  123,  123,  233,   15,  178,  179,
   40,   40,  269,   43,  188,   45,  220,  143,  222,  145,
  146,   42,   43,   59,   45,   33,   47,  125,  125,  238,
   60,   61,   62,  125,  160,  123,  162,  163,  164,   41,
   43,  215,   45,  169,  258,  171,  172,   53,    6,   55,
  176,  177,  177,   40,  123,  264,  123,  123,  123,  267,
  268,   40,   70,   59,  272,   41,   74,  123,  258,  195,
  196,  197,  256,  123,  269,  226,  123,  272,  236,   37,
  238,   41,  233,  209,   44,  211,  212,   40,  123,  215,
   41,  256,  279,  123,   41,  125,  256,   44,   40,  258,
  267,  268,  269,  271,   62,  263,  264,  272,  234,  269,
  277,   59,  258,   71,   72,   73,  269,   75,  267,  245,
   78,  269,   80,   81,   82,   40,  284,   85,  269,  255,
  125,  257,  258,  259,  260,  309,   43,  269,   45,  258,
  258,  299,  125,  267,  268,  269,  125,  105,  106,  107,
  108,  267,  268,  277,  112,  113,  114,  115,   40,   59,
  318,   43,   41,   45,  122,   44,  291,   41,  326,  177,
  258,   41,  125,   59,  273,  274,  275,  258,   60,   61,
   62,  123,  256,  309,  310,   40,  144,   58,   43,  258,
   45,  258,  258,  258,  320,  269,  322,  155,  272,  273,
  274,  275,  258,  161,  259,   60,   61,   62,  258,   40,
  125,  258,   40,  271,  267,  268,  269,   41,  176,  267,
  268,  269,  267,  268,  277,   58,  256,  257,  269,  277,
  188,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  272,  123,   59,  273,  274,  275,  276,  277,  125,  279,
   40,   58,  210,   43,   41,   45,   43,  215,   45,  125,
  218,  125,  264,  265,  267,  268,  269,  269,  123,  269,
   60,   61,   62,   59,   40,  125,  234,  256,  257,   59,
  259,  260,  261,   58,  263,  267,  268,  245,  264,  265,
  269,  270,   59,  269,  125,  264,  265,  125,  256,  278,
  279,  267,  268,  256,  257,  269,  259,  260,  261,   58,
  263,  267,  268,  269,  256,  257,  269,  270,   43,  261,
   45,  263,  280,   59,   59,  278,  279,  269,  270,   59,
   43,   41,   45,  123,   41,   43,  278,   45,  264,  265,
   59,  256,  257,  269,  259,  260,  261,   40,  263,   41,
   43,  309,   45,   59,  269,  270,   41,   60,   61,   62,
  267,  268,  269,  278,  279,   59,  267,   60,   61,   62,
  277,   59,  279,   59,  256,  257,  267,  268,  269,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   43,   58,
   45,  273,  274,  275,  276,  277,   59,  279,   43,   41,
   45,  256,  257,   41,   59,  267,  261,  262,   58,  264,
  265,  266,  267,  268,  269,   60,   61,   62,  273,  274,
  275,  276,  277,   59,  279,  256,  257,    0,  256,  257,
  261,   59,  263,  261,  125,  263,  125,   59,  269,  270,
   40,  269,  270,   43,  125,   45,  123,  278,  279,  125,
  278,  279,   42,   43,  125,   45,  123,   47,  123,  123,
   60,   61,   62,  168,  245,   24,  256,  257,  226,   59,
   -1,  261,  262,   -1,  264,  265,  266,  267,  268,  269,
  267,  268,  269,  273,  274,  275,  276,  277,   -1,  279,
  256,  257,   41,   42,   43,  261,   45,  263,   47,  267,
  268,  269,   -1,  269,  270,   -1,   40,   -1,   42,   43,
   44,   45,  278,   47,   40,   -1,   42,   43,   49,   45,
   43,   47,   45,  123,   -1,   -1,   60,   61,   62,   88,
   -1,   90,   91,   -1,   60,   61,   62,   60,   61,   62,
   -1,   -1,  267,  268,  269,  267,  268,  269,   42,   43,
  109,   45,  277,   47,  267,  268,  269,   -1,   -1,  267,
  268,  269,   -1,  256,  277,   -1,   60,   61,   62,  277,
  273,  274,  275,   24,  267,  268,  269,  258,  259,  260,
  273,  274,  275,   -1,  277,   -1,  267,  268,  269,   -1,
   42,   43,  123,   45,   -1,   47,   -1,   -1,   49,   -1,
  131,   42,   43,   -1,   45,    4,   47,   59,  267,  268,
  269,  256,  267,  268,  267,  268,  269,   -1,   59,  178,
  179,   -1,  267,  268,  269,   24,  271,   -1,  273,  274,
  275,   30,  277,   32,  267,  268,  269,   88,   -1,   90,
   91,   -1,   40,   41,   42,   43,   -1,   45,   40,   47,
   42,   43,   -1,   45,   -1,   47,  256,  257,  109,   -1,
   -1,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   -1,   -1,  123,  273,  274,  275,  276,  277,   -1,   -1,
  131,   41,   42,   43,   -1,   45,   -1,   47,   -1,  220,
   -1,  222,   91,   -1,   -1,  226,   -1,   41,   42,   43,
   -1,   45,  233,   47,   42,   43,   -1,   45,   -1,   47,
  109,  267,  268,  269,  267,  268,  269,  116,   -1,  267,
  268,  269,  256,  258,  259,  260,   -1,  178,  179,   -1,
   -1,   -1,   -1,  256,   -1,  269,   -1,   -1,  272,  273,
  274,  275,   -1,   -1,  267,  268,  269,  273,  274,  275,
  273,  274,  275,   -1,  277,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  220,
   -1,  222,   -1,   -1,   -1,  226,   -1,   -1,   -1,  273,
  274,  275,  233,
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
"SentenciaDeclarativa : WhenCuerpo",
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
"HeaderWhen : When Condicion THEN",
"HeaderWhen : Condicion THEN",
"HeaderWhen : When Condicion",
"When : WHEN",
"WhenCuerpo : HeaderWhen '{' ListSentencias '}'",
"WhenCuerpo : error '}'",
"Salida : OUT '(' CADENA_MULT ')'",
"Salida : '(' CADENA_MULT ')'",
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
"Expresion : cte signo cte",
"Expresion : cte '*' cte",
"Expresion : cte '/' cte",
"ListParametrosInv : '(' ')'",
"ListParametrosInv : '(' ParametroReal ')'",
"ListParametrosInv : '(' ParametroReal ',' ParametroReal ')'",
"ParametroReal : cte",
"ParametroReal : signo cte",
"ParametroReal : ID",
"ConversionExplicita : TOF32 '(' Expresion ')'",
"ConversionExplicita : TOF32 '(' ')'",
"signo : '+'",
"signo : '-'",
"cte : CTE_FLOTANTE",
"cte : CTE_ENTERA",
"ListSentenciasIf : SentenciaEjecutable ListSentenciasIf",
"ListSentenciasIf : SentenciaCorte ListSentenciasIf",
"ListSentenciasIf : SentenciaEjecutable",
"ListSentenciasIf : SentenciaCorte",
"HeaderIf : IF Condicion THEN",
"HeaderIf : error THEN",
"CuerpoIf : SentenciaEjecutable",
"CuerpoIf : SentenciaCorte",
"CuerpoIf : '{' ListSentenciasIf '}'",
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
"Etiqueta : ID ':'",
"HeaderEtiqueta : Etiqueta HeaderFor",
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
"SentenciaReturn : RETURN '(' ID ')' ';'",
"SentenciaReturn : RETURN '(' cte ')' ';'",
"SentenciaReturn : RETURN '(' signo cte ')' ';'",
"SentenciaReturn : RETURN '(' Expresion ')' ';'",
"SentenciaReturn : RETURN '(' ID ')'",
"SentenciaReturn : RETURN '(' cte ')'",
"SentenciaReturn : RETURN '(' Expresion ')'",
"SentenciaReturn : RETURN ';'",
"SentenciaCorte : SentenciaReturn",
"SentenciaCorte : BREAK ';'",
"SentenciaCorte : BREAK cte ';'",
"SentenciaCorte : BREAK signo cte ';'",
"SentenciaCorte : CONTINUE ':' ID ';'",
};

//#line 931 "Gramatica.y"

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
public static int nivelDeAnidamiento=0;
public static List<String> funcionActual=new ArrayList<>();
public static String refEtiqueta = null;
public static String tipoActual = "";
public static String tipoAnterior = "";

public static int cantLabel= 0;

public static String ambito = "";

//-----------------------------------------------------------------------


    public static void chequearYAsignarParametros(List<String> lista1, List<Simbolo> lista2){
		int n=lista1.size();
		boolean errorTipo=false;
		if((lista1!=null) && (lista2!=null)){
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
                        estructuraActual.crearTerceto("=:", lista2.get(i).getLexema(), lista1.get(i));
                    }
            }
            else{
                System.out.println("La cantidad de parametros es incorrecta");
                errores_semanticos.add(new ErrorLinea("La cantidad de parametros es incorrecta", linea.getNumeroLinea()));
            }
		}
	}

	public int yylex(){
		Simbolo simbolo = AnalizadorLexico.yyLex();

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

			if((errores_lexicos.isEmpty()) && (errores_sintacticos.isEmpty()) && (errores_semanticos.isEmpty()) && (errores_yacc.isEmpty())){
				GeneradorCodigo.setListaEstructuras(listEstructurasTercetos);
				GeneradorCodigo.generarCodigoPrincipal();
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

	public static boolean mismoTipoIds(String lex1, String lex2){
		String tipo1 = tablaDeSimbolos.getTipo(lex1);
		String tipo2 = tablaDeSimbolos.getTipo(lex2);
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

	public static boolean mismoTipoIDCte(String lex1, String val2){
		String tipo = tablaDeSimbolos.getTipo(val2);
		if (tablaDeSimbolos.getTipo(lex1).equals(tipo)){
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
		if (tipoActual.equals(tipo)){
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public static boolean mismoTipoExpID(String lex){
        String tipo = tablaDeSimbolos.getTipo(lex);
		if (tipoActual.equals(tipo)){
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	

	public static boolean mismoTipo(){
		if (tipoActual.equals(tipoAnterior)){
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

		if((val1.size()==listOperadores1.size()+1) && (val2.size()==listOperadores2.size()+1)){
			int n=listOperadores1.size();
			float valor1, valor2;
			String op;
			float result;
			int i=0;
			while(i<n){
				op=listOperadores1.get(i);
				if(op.matches("[*|/]")){
					valor1=val1.get(0);
					valor2=val1.get(1);
					result=getResultado(valor1, valor2, op);
					val1.remove(0);
					val1.remove(0);
					val1.add(0, result);
					listOperadores1.remove(0);
					i++;
				}
				i++;
			}
			n=listOperadores1.size();
			for(i=0; i<n; i++){
				op=listOperadores1.get(0);
				valor1=val1.get(0);
				valor2=val1.get(1);
				result=getResultado(valor1, valor2, op);
				val1.remove(0);
				val1.remove(0);
				val1.add(0, result);
				listOperadores1.remove(0);
			}

			n=listOperadores2.size();
			i=0;
			while(i<n){
				op=listOperadores2.get(i);
				if(op.matches("[*|/]")){
					valor1=val2.get(0);
					valor2=val2.get(1);
					result=getResultado(valor1, valor2, op);
					val2.remove(0);
					val2.remove(0);
					val2.add(0, result);
					listOperadores2.remove(0);
					i++;
				}
				i++;
			}
			n=listOperadores2.size();
			for(i=0; i<n; i++){
				op=listOperadores2.get(0);
				valor1=val2.get(0);
				valor2=val2.get(1);
				result=getResultado(valor1, valor2, op);
				val2.remove(0);
				val2.remove(0);
				val2.add(0, result);
				listOperadores2.remove(0);
			}
		}
		
		if(val1.size()==1 && val2.size()==1){
			resultado1=val1.get(0);
			resultado2=val2.get(0);
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

//#line 1068 "Parser.java"
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
//#line 24 "Gramatica.y"
{desconcatenarAmbito();}
break;
case 2:
//#line 25 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' ");}
break;
case 3:
//#line 26 "Gramatica.y"
{AgregarErrorSintactico("Se esperan '{' '}' ");}
break;
case 4:
//#line 27 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 5:
//#line 30 "Gramatica.y"
{ambito= val_peek(0).sval; tablaDeSimbolos.add(new Simbolo(val_peek(0).sval, 269,"nombre_programa"));
					 estructuraActual=estructuraTercetosPrincipal;
					 listEstructurasSeguimiento.add(estructuraActual);
					 listEstructurasTercetos.add(estructuraActual);
					 }
break;
case 13:
//#line 47 "Gramatica.y"
{AgregarErrorSintactico("Se espera identificador");}
break;
case 17:
//#line 51 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de la variable");}
break;
case 18:
//#line 52 "Gramatica.y"
{AgregarErrorSintactico("Se espera identificador de la variable");}
break;
case 21:
//#line 60 "Gramatica.y"
{ if((!dentroDeWhen) || (!condicionWhenFalse)) tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "I8", val_peek(0).sval));}
break;
case 22:
//#line 61 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse)) tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "I8", val_peek(1).sval+val_peek(0).sval));}
break;
case 23:
//#line 62 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse)) tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "F32", val_peek(0).sval));}
break;
case 24:
//#line 63 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse)) tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "F32", val_peek(1).sval+val_peek(0).sval));}
break;
case 25:
//#line 65 "Gramatica.y"
{AgregarErrorSintactico("Se espera un identificador");}
break;
case 26:
//#line 66 "Gramatica.y"
{AgregarErrorSintactico("Se espera una constante ");}
break;
case 27:
//#line 67 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:' ");}
break;
case 28:
//#line 70 "Gramatica.y"
{estructuraActual.setTipo("I8");}
break;
case 29:
//#line 71 "Gramatica.y"
{estructuraActual.setTipo("F32");}
break;
case 30:
//#line 74 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse)) tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
break;
case 31:
//#line 75 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse)) tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
break;
case 32:
//#line 76 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse)) AgregarErrorSintactico("Se espera ',' ");}
break;
case 33:
//#line 79 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual.get(0), estructuraActual.getTipo());
															if (!parametrosFormales.containsKey(funcionActual.get(0))){
																List<Simbolo> listparametros=parametros;
																parametrosFormales.put(funcionActual.get(0), listparametros);
															}
															parametros=new ArrayList<>();
															
															}
break;
case 34:
//#line 87 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual.get(0), estructuraActual.getTipo());
			 									if (!parametrosFormales.containsKey(funcionActual.get(0))){
													List<Simbolo> listparametros=parametros;
													parametrosFormales.put(funcionActual.get(0), listparametros);
												}
												parametros=new ArrayList<>();
												
												}
break;
case 35:
//#line 95 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual.get(0), estructuraActual.getTipo());
			 						if (!parametrosFormales.containsKey(funcionActual.get(0))){
										List<Simbolo> listparametros=parametros;
										parametrosFormales.put(funcionActual.get(0), listparametros);
									}
									parametros=new ArrayList<>();
									
									}
break;
case 36:
//#line 104 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 37:
//#line 105 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 38:
//#line 106 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 39:
//#line 107 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 40:
//#line 108 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 41:
//#line 109 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 42:
//#line 112 "Gramatica.y"
{nivelDeAnidamiento++;
			tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito, 269, "identificador_funcion"));
			 funcionActual.add(0, val_peek(0).sval+":"+ambito);
			 estructuraActual=new EstructuraTercetos(val_peek(0).sval+":"+ambito);
			 ambito=ambito+":"+val_peek(0).sval;
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 dentroDeFun=true;
			 }
break;
case 43:
//#line 123 "Gramatica.y"
{desconcatenarAmbito();
									   funcionActual.remove(0);
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   nivelDeAnidamiento--;
									   dentroDeFun=(nivelDeAnidamiento>0);
									   }
break;
case 44:
//#line 130 "Gramatica.y"
{AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
break;
case 45:
//#line 131 "Gramatica.y"
{AgregarErrorSintactico("Se espera el header de la funcion");}
break;
case 46:
//#line 132 "Gramatica.y"
{AgregarErrorSintactico("Se espera el retorno de la funcion");}
break;
case 47:
//#line 136 "Gramatica.y"
{Simbolo simbolo= new Simbolo(val_peek(0).sval+":"+ambito,269,"parametro", estructuraActual.getTipo());
					tablaDeSimbolos.add(simbolo);
					parametros.add(simbolo);}
break;
case 48:
//#line 139 "Gramatica.y"
{AgregarErrorSintactico("Se espera tipo de parametro");}
break;
case 55:
//#line 152 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
								if(tablaDeSimbolos.getUso(lex).equals("constante"))
									errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
								if (!mismoTipoExpID(lex)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea())); 
								estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval);
								}
break;
case 56:
//#line 160 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
							String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(1).sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
							if (!mismoTipoIds(lex, lex1)) 
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, lex, lex1);}
break;
case 57:
//#line 168 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
							if (!mismoTipoIDCte(lex, val_peek(1).sval))
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval);}
break;
case 58:
//#line 175 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(4).sval, ambito);
									if(tablaDeSimbolos.getUso(lex).equals("constante"))
										errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
									if (!mismoTipoIDCte(lex, val_peek(1).sval))
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
									tablaDeSimbolos.setLexema(val_peek(2).sval, val_peek(1).sval);
									estructuraActual.crearTerceto(val_peek(3).sval, lex, val_peek(2).sval + val_peek(1).sval);}
break;
case 59:
//#line 183 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 60:
//#line 186 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse))
								chequearCondicionWhen();
								}
break;
case 61:
//#line 190 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
//#line 191 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 63:
//#line 194 "Gramatica.y"
{if((!dentroDeWhen) || (!condicionWhenFalse)){
				dentroDeWhen=true;
				valores1.clear();
				valores2.clear();
				listOperadores1.clear();
				listOperadores2.clear();
				valores=valores1;
				listOperadores=listOperadores1;
			}
			}
break;
case 64:
//#line 205 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
												estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
												
					 							cantLabel++;
												dentroDeWhen=false; condicionWhenFalse=false;
												}
break;
case 65:
//#line 211 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 66:
//#line 215 "Gramatica.y"
{estructuraActual.crearTerceto("OUT", val_peek(1).sval, null);}
break;
case 67:
//#line 216 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 68:
//#line 220 "Gramatica.y"
{
					String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
					if (!mismoTipoIds(lex1, lex2)) 
						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
					
					estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					estructuraActual.addTercetoWhen();
					listOperadores.add(val_peek(1).sval);
					valores.add(lex1);
					valores.add(lex2);
					}
break;
case 69:
//#line 234 "Gramatica.y"
{
			String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
			if (!mismoTipoIDCte(lex1, val_peek(0).sval)) 
		 		errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
			
			estructuraActual.crearTerceto(val_peek(1).sval, lex1, val_peek(0).sval);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add(lex1);
			valores.add(val_peek(0).sval);
			listOperadores.add(val_peek(1).sval);
			}
break;
case 70:
//#line 247 "Gramatica.y"
{
			String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
			if (!mismoTipoExpID(lex))
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
			valores.add(lex);
			
			estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			listOperadores.add(val_peek(1).sval);
			}
break;
case 71:
//#line 259 "Gramatica.y"
{
			if (!mismoTipoExpCte(val_peek(0).sval)) 
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
			estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add(val_peek(0).sval);
			listOperadores.add(val_peek(1).sval);
			}
break;
case 72:
//#line 268 "Gramatica.y"
{
					String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
					if (!mismoTipoIds(lex1, lex2))
						 errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
					
					estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add(val_peek(1).sval);
					}
break;
case 73:
//#line 282 "Gramatica.y"
{
					String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
					if (!mismoTipoIds(lex1, lex2)) 
		 				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
					
					estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add(val_peek(1).sval);
					}
break;
case 74:
//#line 295 "Gramatica.y"
{
						String lex= tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
						if (!mismoTipoIDCte(lex, val_peek(0).sval)) 	
							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(0).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 75:
//#line 307 "Gramatica.y"
{String lex= tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
						if (!mismoTipoIDCte(lex, val_peek(0).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(0).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 76:
//#line 319 "Gramatica.y"
{
							String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
							if (!mismoTipoExpID(lex))
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(val_peek(1).sval);
							}
break;
case 77:
//#line 331 "Gramatica.y"
{
							String lex= tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
							if (!mismoTipoExpID(lex)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(val_peek(1).sval);
							}
break;
case 78:
//#line 343 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add(val_peek(0).sval);
							listOperadores.add(val_peek(1).sval);
							}
break;
case 79:
//#line 351 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add(val_peek(0).sval);
							listOperadores.add(val_peek(1).sval);
							}
break;
case 81:
//#line 362 "Gramatica.y"
{
						String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
						if (!mismoTipoIDCte(lex, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(2).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 82:
//#line 375 "Gramatica.y"
{
						String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
						if (!mismoTipoIDCte(lex, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(2).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 83:
//#line 387 "Gramatica.y"
{
						String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
						if (!mismoTipoIDCte(lex, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(2).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 84:
//#line 400 "Gramatica.y"
{String funcion=tablaDeSimbolos.getRefFuncion(val_peek(1).sval, ambito);
								if(funcion!=null){
		 						 	chequearYAsignarParametros(parametrosReales, parametrosFormales.get(funcion));
									
								}
								estructuraActual.crearTerceto("BI", funcion, null);
								/*String simboloFuncion= tablaDeSimbolos.getRefSimbolo($1.sval, ambito);*/
								tipoActual=tablaDeSimbolos.getTipo(funcion);
								yyval.sval=funcion;
								valores.add(funcion);
								parametrosReales.clear();
								}
break;
case 85:
//#line 412 "Gramatica.y"
{
						if (!mismoTipoCtes(val_peek(2).sval, val_peek(0).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(val_peek(2).sval);
						valores.add(val_peek(0).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						
		 	}
break;
case 86:
//#line 425 "Gramatica.y"
{
						if (!mismoTipoCtes(val_peek(2).sval, val_peek(0).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(val_peek(2).sval);
						valores.add(val_peek(0).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
		 	}
break;
case 87:
//#line 437 "Gramatica.y"
{
						if (!mismoTipoCtes(val_peek(2).sval, val_peek(0).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(val_peek(2).sval);
						valores.add(val_peek(0).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
		 	}
break;
case 91:
//#line 456 "Gramatica.y"
{parametrosReales.add(val_peek(0).sval);}
break;
case 92:
//#line 457 "Gramatica.y"
{parametrosReales.add(val_peek(1).sval+val_peek(0).sval);}
break;
case 93:
//#line 458 "Gramatica.y"
{parametrosReales.add(tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 94:
//#line 461 "Gramatica.y"
{if (conversionValida()){
											    
												/*estructuraActual.crearTerceto("=:","@conv", $3.sval); //variable auxiliar*/
												estructuraActual.crearTerceto("tof32", estructuraActual.getRefTerceto(1), null);
												estructuraActual.getTerceto(estructuraActual.cantTercetos()-1).setTipo("F32");
											  }
												else errores_semanticos.add(new ErrorLinea("No se puede realizar la conversion", linea.getNumeroLinea()));
											  yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
break;
case 95:
//#line 470 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 99:
//#line 479 "Gramatica.y"
{chequearRangoEntero(val_peek(0).sval);}
break;
case 105:
//#line 489 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 109:
//#line 498 "Gramatica.y"
{estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();
			estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
			
					 cantLabel++;
			}
break;
case 111:
//#line 508 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 112:
//#line 511 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
									 estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
									 
					 				cantLabel++;
									 }
break;
case 113:
//#line 516 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
								  		 estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
										 
					 					cantLabel++;
										 }
break;
case 114:
//#line 522 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 115:
//#line 523 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 116:
//#line 524 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 117:
//#line 527 "Gramatica.y"
{estructuraActual.crearTerceto("BF", val_peek(1).sval, null);
								estructuraActual.addTercetoIf();
								estructuraActual.addTercetoWhen();
								}
break;
case 118:
//#line 531 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 119:
//#line 532 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 120:
//#line 535 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval; valores=valores2;}
break;
case 121:
//#line 536 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval; valores=valores2;}
break;
case 122:
//#line 537 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 123:
//#line 538 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 124:
//#line 539 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 125:
//#line 540 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 126:
//#line 543 "Gramatica.y"
{if (!mismoTipo()) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
												estructuraActual.addTercetoWhen();
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											
											}
break;
case 127:
//#line 551 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
										
											estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
											estructuraActual.addTercetoWhen();
											valores2.add(val_peek(0).sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										
										}
break;
case 128:
//#line 560 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(val_peek(2).sval, val_peek(3).sval, val_peek(1).sval + val_peek(0).sval );
												
												estructuraActual.addTercetoWhen();
												valores2.add(val_peek(1).sval+val_peek(0).sval);
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
											}
break;
case 129:
//#line 570 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   								
											estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
											estructuraActual.addTercetoWhen();
											valores1.add(val_peek(2).sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 130:
//#line 578 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, val_peek(0).sval);
												estructuraActual.addTercetoWhen();
												valores1.add(val_peek(3).sval+val_peek(2).sval);
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema(val_peek(3).sval, val_peek(2).sval);
											}
break;
case 131:
//#line 587 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
										if (!mismoTipoExpID(lex)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   								
										estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
										estructuraActual.addTercetoWhen();
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 132:
//#line 596 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
										if (!mismoTipoExpID(lex)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));

										estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 133:
//#line 605 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
								if (!mismoTipoIDCte(lex, val_peek(2).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
								estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
								estructuraActual.addTercetoWhen();
								valores1.add(val_peek(2).sval);
								valores2.add(lex);
								yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 134:
//#line 614 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
										if (!mismoTipoIDCte(lex, val_peek(2).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   							
										estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, lex);
										estructuraActual.addTercetoWhen();
										valores1.add(val_peek(3).sval +val_peek(2).sval);
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(val_peek(3).sval, val_peek(2).sval);
									}
break;
case 135:
//#line 625 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
								if (!mismoTipoIDCte(lex, val_peek(0).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));

									
									estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
									estructuraActual.addTercetoWhen();
									valores1.add(val_peek(0).sval);
									valores2.add(lex);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 136:
//#line 636 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
										if (!mismoTipoIDCte(lex, val_peek(0).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   							
										estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval + val_peek(0).sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										valores2.add(val_peek(1).sval+val_peek(0).sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
									}
break;
case 137:
//#line 647 "Gramatica.y"
{String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
									String lex2=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
								if (!mismoTipoIds(lex1, lex2)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));

									estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
									estructuraActual.addTercetoWhen();
									
									valores1.add(lex1);
									valores2.add(lex2);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 138:
//#line 659 "Gramatica.y"
{if (!mismoTipoCtes(val_peek(2).sval, val_peek(0).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
									estructuraActual.addTercetoWhen();
									valores1.add(val_peek(2).sval);
									valores2.add(val_peek(0).sval);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 139:
//#line 667 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 140:
//#line 668 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 141:
//#line 669 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 148:
//#line 683 "Gramatica.y"
{estructuraActual.crearTerceto("BF", val_peek(4).sval, null);
				 refEtiqueta = val_peek(6).sval;
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 149:
//#line 692 "Gramatica.y"
{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", val_peek(3).sval, null);
				 refEtiqueta = val_peek(5).sval;
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto("+", estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 150:
//#line 703 "Gramatica.y"
{estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval,ambito));
										}
break;
case 151:
//#line 709 "Gramatica.y"
{estructuraActual.addEtiquetaFor(val_peek(1).sval);}
break;
case 152:
//#line 712 "Gramatica.y"
{estructuraActual.addRefEtiqueta(refEtiqueta);}
break;
case 153:
//#line 716 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
		estructuraActual.addIdFor(lex);
		if (!mismoTipoIDCte(lex, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), val_peek(0).sval);
		estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
		
					 cantLabel++;
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 154:
//#line 729 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
		estructuraActual.addIdFor(lex);
		String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
		if (!mismoTipoIds(lex, lex1)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), lex1);
		estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
		
		cantLabel++;
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 155:
//#line 745 "Gramatica.y"
{estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.completarTercetoFor(1);
					 estructuraActual.completarTercetosBreak(1);
					 estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
					 
					 cantLabel++;
					 estructuraActual.popIdFor();

					 estructuraActual.borrarListTercetosBreak();
					 dentroDeFor=!estructuraActual.vaciaListTercetoBreak();
					 }
break;
case 156:
//#line 757 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 158:
//#line 762 "Gramatica.y"
{estructuraActual.eliminarEtiqueta();}
break;
case 159:
//#line 763 "Gramatica.y"
{
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.crearTerceto("LABEL"+cantLabel,null,null);
					
					cantLabel++;
					estructuraActual.borrarListTercetosBreakCte();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					}
break;
case 160:
//#line 775 "Gramatica.y"
{
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(2).sval + val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.crearTerceto("LABEL"+cantLabel,null,null);
					
					cantLabel++;
					estructuraActual.borrarListTercetosBreakCte();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					tablaDeSimbolos.setLexema(val_peek(2).sval, val_peek(1).sval);
				}
break;
case 161:
//#line 790 "Gramatica.y"
{String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
								String lex2=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
								if (!mismoTipoIds(lex1,lex2))
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
								estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
								yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 162:
//#line 798 "Gramatica.y"
{String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
										if (!mismoTipoExpID(lex1)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
									   estructuraActual.crearTerceto(val_peek(1).sval, lex1, val_peek(0).sval);
									   yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									   }
break;
case 163:
//#line 805 "Gramatica.y"
{String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
										if (!mismoTipoIDCte(lex1, val_peek(0).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
										estructuraActual.crearTerceto(val_peek(1).sval, lex1, val_peek(0).sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 164:
//#line 812 "Gramatica.y"
{String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
											if (!mismoTipoIDCte(lex1, val_peek(1).sval)) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
											  tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
											  estructuraActual.crearTerceto(val_peek(2).sval, lex1, val_peek(1).sval+val_peek(0).sval);
											  yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
break;
case 165:
//#line 821 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro del cuerpo de una funcion", linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
						estructuraActual.crearTerceto("=:", funcionActual.get(0), lex);
						estructuraActual.crearTerceto("BI",null, null);
						if(funcionActual.get(0)!=null && !mismoTipoIds(funcionActual.get(0), lex)){
							errores_semanticos.add(new ErrorLinea("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return", linea.getNumeroLinea()));
							System.out.println("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return");
						}
					}	
						
				}
break;
case 166:
//#line 837 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro del cuerpo de una funcion", linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", funcionActual.get(0), val_peek(2).sval);
						estructuraActual.crearTerceto("BI",null, null);
						if(funcionActual.get(0)!=null && !mismoTipoIDCte(funcionActual.get(0), val_peek(2).sval)){
							errores_semanticos.add(new ErrorLinea("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return", linea.getNumeroLinea()));
							System.out.println("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return");
						}
					}	
				}
break;
case 167:
//#line 851 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro del cuerpo de una funcion", linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						tablaDeSimbolos.setLexema(val_peek(3).sval, val_peek(2).sval);
						estructuraActual.crearTerceto("=:", funcionActual.get(0), val_peek(3).sval+val_peek(2).sval);
						estructuraActual.crearTerceto("BI",null, null);
						if(funcionActual.get(0)!=null && !mismoTipoIDCte(funcionActual.get(0), val_peek(2).sval)){
							errores_semanticos.add(new ErrorLinea("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return", linea.getNumeroLinea()));
							System.out.println("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return");
						}
					}	
				}
break;
case 168:
//#line 866 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro del cuerpo de una funcion", linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", funcionActual.get(0), val_peek(2).sval);
						estructuraActual.crearTerceto("BI",null, null);
						if(funcionActual.get(0)!=null && !mismoTipoExpID(funcionActual.get(0))){
							errores_semanticos.add(new ErrorLinea("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return", linea.getNumeroLinea()));
							System.out.println("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return");
						}
					}	
				}
break;
case 169:
//#line 880 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 170:
//#line 881 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 171:
//#line 882 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 172:
//#line 883 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 174:
//#line 887 "Gramatica.y"
{
				if(dentroDeFor){
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreak();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una sentencia de control", linea.getNumeroLinea()));
					System.out.println("No se encuentra dentro de una sentencia de control");
				}
			  }
break;
case 175:
//#line 897 "Gramatica.y"
{
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(1).sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se espera el retorno de una sentencia de control", linea.getNumeroLinea()));
					System.out.println("No se espera el retorno de una sentencia de control");
				}
				}
break;
case 176:
//#line 908 "Gramatica.y"
{
				tablaDeSimbolos.setLexema(val_peek(2).sval, val_peek(1).sval);
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(2).sval + val_peek(1).sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se espera el retorno de una sentencia de control", linea.getNumeroLinea()));
					System.out.println("No se espera el retorno de una sentencia de control");
			  	}
			  }
break;
case 177:
//#line 920 "Gramatica.y"
{
				if (estructuraActual.existeEtiquetaFor(val_peek(1).sval)){
					estructuraActual.crearTerceto("BI", estructuraActual.getRefEtiqueta(val_peek(1).sval), null);
				}
				else
					errores_semanticos.add(new ErrorLinea("No existe la etiqueta de salto", linea.getNumeroLinea()));
			  }
break;
//#line 2353 "Parser.java"
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
