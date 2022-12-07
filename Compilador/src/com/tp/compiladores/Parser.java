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
   18,   30,   30,   30,   30,   30,
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
    3,    3,    3,    4,    5,    5,    5,    4,    4,    4,
    2,    1,    2,    3,    4,    4,
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
    0,    0,    0,    0,  106,  172,  107,    0,  119,  139,
    0,  157,    0,  152,  158,  104,    0,   48,    0,    0,
    0,   59,    0,    0,    0,    0,  150,   72,   74,   73,
   75,   30,   93,   88,    0,   91,    0,   68,   69,    0,
    0,    0,    0,    0,   27,   25,   15,    0,    0,   95,
    0,    0,    0,    0,  171,    0,    0,    0,    0,    1,
   50,   49,   45,   67,  117,   13,   12,    0,   82,   86,
   83,   87,   81,   85,    0,    0,    0,   44,    0,    0,
    0,    0,   76,   78,   77,   79,   70,   71,    0,    0,
    0,    0,    0,   60,    0,    0,  173,    0,    0,    0,
    0,    0,  114,  109,  112,    0,  113,    0,    0,  145,
  146,  147,    0,    0,   66,    0,   47,    0,    0,   56,
    0,   57,   55,   92,    0,   89,  136,   21,   23,    0,
   20,   94,    0,    0,    0,    0,    0,    0,    0,   46,
   43,    0,    0,    0,  128,   64,    0,    0,  174,  100,
  108,  101,    0,  156,  155,  143,  142,  144,    0,    0,
   38,    0,    0,   58,    0,   22,   24,  153,  154,    0,
    0,    0,    0,    0,   35,    0,    0,  176,  175,  111,
  110,    0,  159,    0,   37,   90,    0,    0,  165,  166,
  167,    0,   34,  160,    0,    0,    0,    0,    0,    0,
    0,    0,   36,  164,  149,    0,   33,  148,
};
final static short yydgoto[] = {                          3,
    4,   98,   30,   31,   32,   33,   34,   35,   83,   36,
   84,   37,   38,   39,   40,  141,   92,   93,   41,   42,
   43,   44,   45,   46,   47,   48,   79,  157,  221,  222,
   49,  128,  226,  227,   50,   51,  233,   52,  174,  291,
   53,   54,   55,  132,
};
final static short yysindex[] = {                      -229,
 -115,    0,    0,  186,    0,  461,   14,  -10,  -35,    0,
    0,    0,    0,    0,  520,    0,    0,    0, -231,   -1,
   30,    0,    0,  146,  337,    0,    0,    0,  -70,  212,
  212,  212,   34,   36,    0,    0, -221,  580,  -18,   70,
    0,    0,   55,  589,   -5,   14, -136,    0,   -3,   99,
  -91,   64,   64, -101,   64,    0,    0,    0,    0,    0,
    0, -166,   26,  540,  -60,  -69,    0,   48,  158,  -41,
  282,  285,  330,  -42,   63,    0,    0,  376,    0,   92,
 -151, -221,  -38,    0,   17,    9,  -23,  496,   84,  146,
  146,  120,    0,  223,  227,    0,    0,    0,    0,  238,
    0,  248,    0,  410,  445,  448,  455, -205,  110,  383,
    0,  458,  466,  487,  169,  212,   81,    0,  508, -238,
  298,  -17,  171,   72,    0,    0,    0, -207,    0,    0,
  265,    0,  111,    0,    0,    0,  317,    0,  323,  119,
  270,    0,  631, -221,   35,  622,    0,    0,    0,    0,
    0,    0,    0,    0, -221,    0,  308,    0,    0,  735,
 -221,  549,  659,   66,    0,    0,    0, -231,  735,    0,
  549,  470,  129,  332,    0,  225,  528,  496,  496,    0,
    0,    0,    0,    0,    0,    0,    0,  237,    0,    0,
    0,    0,    0,    0,  735,  549,  659,    0,  280,  291,
  368,  309,    0,    0,    0,    0,    0,    0,  735, -221,
  549,  659,  305,    0,  349,  164,    0, -221,  398,  171,
  310,  171,    0,    0,    0,   -3,    0,   54,  399,    0,
    0,    0,  268,  150,    0, -180,    0,  131,  387,    0,
  405,    0,    0,    0,  386,    0,    0,    0,    0,   56,
    0,    0,  -79,  197,  701,  651,  777,  735,  659,    0,
    0, -180,  131,  409,    0,    0,  416,  424,    0,    0,
    0,    0, -164,    0,    0,    0,    0,    0, -221,  425,
    0,  449, -180,    0,  457,    0,    0,    0,    0,  410,
  464,  468,  471,  473,    0,  467, -180,    0,    0,    0,
    0,  474,    0,  462,    0,    0,  361,  -31,    0,    0,
    0,  482,    0,    0, -180,    0,  735,  249,  659,  501,
  278, -180,    0,    0,    0,  514,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  517,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  507,   13,
   19,   33,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  517,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  459,
  484,    0,    0,    0, -107,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  490,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -40,
    0,  -33,    3,  -24,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  517,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    4,   38,   40,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   78,    0,
  198,  224,    0,    0,    0,    0,    0,    0,    0,  499,
    0,  510,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  316,  321,    0,
    0,  492,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   59,   85,  240,    0,    0,  523,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  644,  578,    0,  597,    0,
    0,  534,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  707, -120,  571,  609,  -59,  533,    0,    0,    0,
  419,  -15,   61,    0,    0,  -78,  530,  446,    0,    0,
    0,   44,    0,    0,   15,    0,    0,  415,   -7,  -34,
    0,  437,    0,    0,  -21,  -13,    0,  -47,    0,    0,
    0,    0,    0,  168,
};
final static int YYTABLESIZE=864;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
  137,   80,   74,   95,   68,  168,  134,  135,  140,    5,
  230,   22,   10,   23,  127,  118,  176,   69,   11,   26,
  167,   65,  107,  147,  108,   22,    1,   23,  114,   66,
  115,  202,    9,  215,   26,  175,  124,   81,   85,    2,
   82,  217,   95,  132,  133,   13,   14,   78,  223,   80,
  140,  224,  225,   64,   96,  144,   22,  170,   23,  155,
  117,   13,   14,  195,  161,   75,   61,   72,   22,   86,
   23,   20,   73,   26,   28,   27,  105,   22,  138,   23,
  129,  106,  137,   10,   11,   26,   28,   27,  139,  135,
  188,  300,  101,  242,  103,  301,  232,  104,  168,  210,
   13,   14,   59,  154,  109,   22,  218,   23,   22,  110,
   23,   76,  276,  111,  146,   13,   14,  116,  131,  123,
  164,  118,   61,  163,  169,  132,  133,   78,  172,  107,
  114,  145,  149,  151,   22,  156,   23,   10,  159,  129,
  162,  165,  166,   11,   78,  171,  107,  114,  250,   25,
  118,  197,   22,   78,   23,  107,  114,    9,  212,  282,
  138,   78,  129,   80,  130,  190,  192,  194,  196,   26,
   28,   27,  204,  206,  208,  211,  281,   21,  140,   78,
  107,  114,  219,  168,  296,   25,  131,  288,   22,  289,
   23,  127,   22,   78,   23,  107,  114,  136,  278,  144,
  131,  137,  295,  140,  241,   26,   28,   27,  180,  169,
  124,   22,  270,   23,  272,  244,  142,  137,  279,  257,
  133,  247,  135,  305,  135,   25,   70,   70,   22,  155,
   23,  259,   88,   67,  198,  320,  256,  313,  127,   78,
  107,  114,   78,  114,  183,   26,   28,   27,  171,   13,
   14,   25,  119,    7,   22,  323,   23,    8,  146,   87,
  132,  133,  327,  184,  126,  120,  121,  185,   88,   62,
  265,   26,   28,   27,  122,  145,  307,  173,  268,  170,
   13,   14,   63,   13,   14,  169,   16,   17,   18,  100,
   20,  318,  321,   20,  280,  138,  186,  129,   16,   17,
   18,   78,   70,  114,  124,  156,  187,  124,   24,   69,
  239,   10,   11,  238,  168,  168,  138,  168,  168,  168,
  127,  168,  286,  287,   22,   71,   23,  168,  168,   13,
   14,  153,  248,  249,   88,  131,  168,  168,  214,  302,
  169,  169,   94,  169,  169,  169,  126,  169,  246,  264,
  319,  245,  263,  169,  169,  216,  134,  235,   13,   14,
  160,  130,  169,  169,  170,    6,    7,  171,   20,  234,
    8,    9,   87,   10,   11,   12,   13,   14,   15,   22,
  236,   23,   16,   17,   18,   19,   20,  237,   21,  229,
  254,   22,  275,   23,   10,   11,   26,   28,   27,  138,
  253,    6,    7,   22,  260,   23,    8,    9,   87,   10,
   11,   12,   13,   14,   15,  261,   13,   14,   16,   17,
   18,   19,   20,  201,   21,  262,  119,    7,   22,  266,
   23,    8,  267,   87,  271,   13,   14,  209,  134,  120,
  121,    6,    7,  130,  283,   20,    8,    9,  122,   10,
   11,   12,   13,   14,   15,  127,  269,  274,   16,   17,
   18,   19,   20,  284,   21,  290,  297,    6,    7,   26,
   28,   27,    8,    9,  298,   10,   11,   12,   13,   14,
   15,  126,  299,  303,   16,   17,   18,   19,   20,  304,
   21,   13,   14,  255,  126,  170,  170,  306,  170,  170,
  170,   20,  170,   13,   14,  258,    3,  312,  170,  170,
  252,  112,   22,   20,   23,  324,  113,  170,  170,  315,
  119,    7,  308,  119,    7,    8,  309,   87,    8,  310,
   87,  311,  314,  228,  121,   25,  228,  121,   22,  322,
   23,  325,  122,   21,  326,  122,   21,   77,   13,   14,
  143,   13,   14,  148,  328,   26,   28,   27,   20,   75,
   21,   72,   22,   74,   23,  102,   73,   75,  126,   72,
   22,   74,   23,  134,   73,   31,  126,   76,  130,   26,
   28,   27,   22,   11,   23,   60,  251,   26,   28,   27,
  105,   22,   62,   23,   90,  106,   13,   14,  150,   26,
   28,   27,   77,   13,   14,   63,  152,   94,    9,   16,
   17,   18,   62,   20,   41,   13,   14,  143,   88,  181,
  182,  105,   22,  102,   23,   20,  106,  316,   14,  317,
  112,   22,   91,   23,  103,  113,  161,   20,  200,   26,
   28,   27,   13,   14,  158,   40,   10,   11,   26,   28,
   27,  138,   13,   14,  153,  162,   39,  125,  178,  285,
   90,   90,  273,  112,   22,  126,   23,  126,  113,    0,
   75,  126,   72,   22,    0,   23,    0,   73,  126,   90,
  243,    0,   16,   17,   18,   99,   99,    0,   99,  240,
   99,  293,  105,   22,    0,   23,  179,  106,   91,   91,
  112,   22,  163,   23,    0,  113,    0,  181,  182,   77,
   29,   13,   14,  189,   13,   14,  191,   91,   56,   57,
   58,   13,   14,  193,   13,   14,  203,   13,   14,   59,
   89,  220,   13,   14,  205,    0,   97,    0,   99,  231,
   75,  292,   72,   22,    0,   23,    0,   73,  178,  178,
    0,    6,    7,   13,   14,  207,    8,    9,   87,   10,
   11,   12,   13,   14,  177,   56,   57,   58,   16,   17,
   18,   19,   20,    0,   75,   69,   72,   22,    0,   23,
    0,   73,    0,   69,    0,    0,  179,  179,   70,    0,
    0,   71,   16,   17,   18,   62,   70,   99,    0,  215,
   16,   17,   18,    0,    0,    0,   13,   14,   63,    0,
    0,    0,   16,   17,   18,  199,   20,  294,  112,   22,
    0,   23,  213,  113,    0,    0,    0,    0,  220,    0,
  220,    0,    0,    0,  125,    0,    0,    0,    0,    0,
    0,  277,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   16,   17,   18,    0,    0,    0,    0,    0,
    0,   16,   17,   18,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         15,
   41,   15,   44,   25,   40,   44,   54,   41,   68,  125,
  131,   43,    0,   45,   49,  123,   40,  256,    0,   44,
   59,    7,   38,   71,   38,   43,  256,   45,   44,   40,
   44,  110,    0,  272,   59,   59,   40,  269,   40,  269,
  272,   59,   64,   41,   41,  267,  268,   63,  256,   63,
  110,  259,  260,   40,  125,   71,   43,   41,   45,   75,
   46,  267,  268,  269,   80,   40,    6,   42,   43,   40,
   45,  277,   47,   60,   61,   62,   42,   43,   41,   45,
   41,   47,  123,  264,  265,   60,   61,   62,   41,  123,
  104,  256,   59,   59,   59,  260,  131,   37,   40,  115,
  267,  268,  269,   41,  123,   43,  122,   45,   43,   40,
   45,   58,  233,   59,   71,  267,  268,  123,   41,  123,
  272,  258,   62,   80,   40,  123,  123,  143,   85,  145,
  146,   71,   72,   73,   43,   75,   45,  125,   78,   41,
   80,   81,   82,  125,  160,   85,  162,  163,  164,   40,
  258,  108,   43,  169,   45,  171,  172,  125,  115,  238,
  123,  177,  123,  177,  256,  105,  106,  107,  108,   60,
   61,   62,  112,  113,  114,  115,  236,  279,  238,  195,
  196,  197,  122,  125,  263,   40,  123,  267,   43,  269,
   45,  226,   43,  209,   45,  211,  212,  258,  233,  215,
  123,  271,  262,  263,  144,   60,   61,   62,  125,  125,
   40,   43,  220,   45,  222,  155,   59,  258,  234,  176,
   53,  161,   55,  283,  258,   40,  269,  269,   43,  245,
   45,  188,  123,  269,  125,  267,  176,  297,   41,  255,
  256,  257,  258,  259,  125,   60,   61,   62,  188,  267,
  268,   40,  256,  257,   43,  315,   45,  261,  215,  263,
  258,  258,  322,   41,   41,  269,  270,   41,  123,  256,
  210,   60,   61,   62,  278,  215,  290,  269,  218,   40,
  267,  268,  269,  267,  268,  269,  273,  274,  275,  256,
  277,  307,  308,  277,  234,  258,   59,  258,  273,  274,
  275,  317,  269,  319,   40,  245,   59,   40,  123,  256,
   41,  264,  265,   44,  256,  257,  269,  259,  260,  261,
  123,  263,  267,  268,   43,  272,   45,  269,  270,  267,
  268,  269,  267,  268,  123,  258,  278,  279,  258,  279,
  256,  257,  271,  259,  260,  261,  123,  263,   41,   41,
  307,   44,   44,  269,  270,   58,   41,   41,  267,  268,
  269,   41,  278,  279,  125,  256,  257,  307,  277,  259,
  261,  262,  263,  264,  265,  266,  267,  268,  269,   43,
   58,   45,  273,  274,  275,  276,  277,  269,  279,  125,
   59,   43,  125,   45,  264,  265,   60,   61,   62,  269,
  272,  256,  257,   43,  125,   45,  261,  262,  263,  264,
  265,  266,  267,  268,  269,  125,  267,  268,  273,  274,
  275,  276,  277,   41,  279,   58,  256,  257,   43,  125,
   45,  261,  269,  263,  125,  267,  268,  269,  123,  269,
  270,  256,  257,  123,   58,  277,  261,  262,  278,  264,
  265,  266,  267,  268,  269,  258,   59,   59,  273,  274,
  275,  276,  277,   59,  279,  269,   58,  256,  257,   60,
   61,   62,  261,  262,   59,  264,  265,  266,  267,  268,
  269,  258,   59,   59,  273,  274,  275,  276,  277,   41,
  279,  267,  268,  269,   49,  256,  257,   41,  259,  260,
  261,  277,  263,  267,  268,  269,    0,   41,  269,  270,
   41,   42,   43,  277,   45,  267,   47,  278,  279,   58,
  256,  257,   59,  256,  257,  261,   59,  263,  261,   59,
  263,   59,   59,  269,  270,   40,  269,  270,   43,   58,
   45,   41,  278,  279,  267,  278,  279,   15,  267,  268,
  269,  267,  268,  269,   41,   60,   61,   62,  277,   40,
  279,   42,   43,   44,   45,   33,   47,   40,  123,   42,
   43,   44,   45,  258,   47,   59,  131,   58,  258,   60,
   61,   62,   43,  125,   45,  125,  168,   60,   61,   62,
   42,   43,  256,   45,   24,   47,  267,  268,  269,   60,
   61,   62,   70,  267,  268,  269,   74,  271,  125,  273,
  274,  275,  123,  277,  123,  267,  268,  269,  123,   90,
   91,   42,   43,  125,   45,  277,   47,  267,  268,  269,
   42,   43,   24,   45,  125,   47,   59,  277,  109,   60,
   61,   62,  267,  268,  269,  123,  264,  265,   60,   61,
   62,  269,  267,  268,  269,   59,  123,   49,   88,  245,
   90,   91,  226,   42,   43,  220,   45,  222,   47,   -1,
   40,  226,   42,   43,   -1,   45,   -1,   47,  233,  109,
   59,   -1,  273,  274,  275,   42,   43,   -1,   45,   59,
   47,   41,   42,   43,   -1,   45,   88,   47,   90,   91,
   42,   43,   59,   45,   -1,   47,   -1,  178,  179,  177,
    4,  267,  268,  269,  267,  268,  269,  109,  258,  259,
  260,  267,  268,  269,  267,  268,  269,  267,  268,  269,
   24,  123,  267,  268,  269,   -1,   30,   -1,   32,  131,
   40,   41,   42,   43,   -1,   45,   -1,   47,  178,  179,
   -1,  256,  257,  267,  268,  269,  261,  262,  263,  264,
  265,  266,  267,  268,  269,  258,  259,  260,  273,  274,
  275,  276,  277,   -1,   40,  256,   42,   43,   -1,   45,
   -1,   47,   -1,  256,   -1,   -1,  178,  179,  269,   -1,
   -1,  272,  273,  274,  275,  256,  269,   91,   -1,  272,
  273,  274,  275,   -1,   -1,   -1,  267,  268,  269,   -1,
   -1,   -1,  273,  274,  275,  109,  277,   41,   42,   43,
   -1,   45,  116,   47,   -1,   -1,   -1,   -1,  220,   -1,
  222,   -1,   -1,   -1,  226,   -1,   -1,   -1,   -1,   -1,
   -1,  233,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,
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

//#line 881 "Gramatica.y"

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

public static int cantLabel= 0;

public static String ambito = "";

//-----------------------------------------------------------------------


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
					estructuraActual.crearTerceto("=:", lista2.get(i).getLexema(), lista1.get(i));
				}			
		}
		else{
			System.out.println("La cantidad de parametros es incorrecta");
			errores_semanticos.add(new ErrorLinea("La cantidad de parametros es incorrecta", linea.getNumeroLinea()));
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

			if((errores_lexicos.isEmpty()) && (errores_sintacticos.isEmpty()) && (errores_semanticos.isEmpty())){
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

	public static boolean mismoTipoIds(String val1, String val2){
		String lex1 = tablaDeSimbolos.getRefSimbolo(val1, ambito);
		String lex2 = tablaDeSimbolos.getRefSimbolo(val2, ambito);
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

	public static boolean mismoTipoIDCte(String val1, String val2){
		String lex1 = tablaDeSimbolos.getRefSimbolo(val1, ambito);
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

	public static boolean mismoTipoExpID(String val){
		String lex = tablaDeSimbolos.getRefSimbolo(val, ambito);
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

//#line 1054 "Parser.java"
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
{ tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "I8", val_peek(0).sval));}
break;
case 22:
//#line 61 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "I8", val_peek(1).sval+val_peek(0).sval));}
break;
case 23:
//#line 62 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "F32", val_peek(0).sval));}
break;
case 24:
//#line 63 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "F32", val_peek(1).sval+val_peek(0).sval));}
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
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
break;
case 31:
//#line 75 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
break;
case 32:
//#line 76 "Gramatica.y"
{AgregarErrorSintactico("Se espera ',' ");}
break;
case 33:
//#line 79 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
															if (!parametrosFormales.containsKey(ambito+":"+funcionActual)){
																List<Simbolo> listparametros=parametros;
																parametrosFormales.put(ambito+":"+funcionActual, listparametros);
															}
															parametros=new ArrayList<>();
															ambito= ambito+":"+funcionActual;
															}
break;
case 34:
//#line 87 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
			 									if (!parametrosFormales.containsKey(ambito+":"+funcionActual)){
													List<Simbolo> listparametros=parametros;
													parametrosFormales.put(ambito+":"+funcionActual, listparametros);
												}
												parametros=new ArrayList<>();
												ambito= ambito+":"+funcionActual;
												}
break;
case 35:
//#line 95 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
			 						if (!parametrosFormales.containsKey(ambito+":"+funcionActual)){
										List<Simbolo> listparametros=parametros;
										parametrosFormales.put(ambito+":"+funcionActual, listparametros);
									}
									parametros=new ArrayList<>();
									ambito= ambito+":"+funcionActual;
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
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito, 269, "identificador_funcion"));
			 funcionActual=val_peek(0).sval;
			 estructuraActual=new EstructuraTercetos(ambito+":"+val_peek(0).sval);
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 dentroDeFun=true;
			 }
break;
case 43:
//#line 121 "Gramatica.y"
{desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   dentroDeFun=false;
									   }
break;
case 44:
//#line 126 "Gramatica.y"
{AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
break;
case 45:
//#line 127 "Gramatica.y"
{AgregarErrorSintactico("Se espera el header de la funcion");}
break;
case 46:
//#line 128 "Gramatica.y"
{AgregarErrorSintactico("Se espera el retorno de la funcion");}
break;
case 47:
//#line 132 "Gramatica.y"
{Simbolo simbolo= new Simbolo(val_peek(0).sval+":"+ambito+":"+funcionActual,269,"parametro", estructuraActual.getTipo());
					tablaDeSimbolos.add(simbolo);
					parametros.add(simbolo);}
break;
case 48:
//#line 135 "Gramatica.y"
{AgregarErrorSintactico("Se espera tipo de parametro");}
break;
case 55:
//#line 148 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
								if(tablaDeSimbolos.getUso(lex).equals("constante"))
									errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
								if (!mismoTipoExpID(val_peek(3).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea())); 
								estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval);
								}
break;
case 56:
//#line 156 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
							if (!mismoTipoIds(val_peek(3).sval, val_peek(1).sval)) 
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, lex, tablaDeSimbolos.getRefSimbolo(val_peek(1).sval, ambito));}
break;
case 57:
//#line 163 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
							if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval))
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval);}
break;
case 58:
//#line 170 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(4).sval, ambito);
									if(tablaDeSimbolos.getUso(lex).equals("constante"))
										errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", linea.getNumeroLinea()));
									if (!mismoTipoIDCte(val_peek(4).sval, val_peek(1).sval))
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea())); 
									tablaDeSimbolos.setLexema(val_peek(2).sval, val_peek(1).sval);
									estructuraActual.crearTerceto(val_peek(3).sval, lex, val_peek(2).sval + val_peek(1).sval);}
break;
case 59:
//#line 178 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 60:
//#line 181 "Gramatica.y"
{chequearCondicionWhen();
								}
break;
case 61:
//#line 184 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
//#line 185 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 63:
//#line 188 "Gramatica.y"
{dentroDeWhen=true;
			valores1.clear();
			valores2.clear();
			listOperadores1.clear();
			listOperadores2.clear();
			valores=valores1;
			listOperadores=listOperadores1;
			}
break;
case 64:
//#line 197 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
												estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
												
					 							cantLabel++;
												dentroDeWhen=false; condicionWhenFalse=false;
												}
break;
case 65:
//#line 203 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 66:
//#line 207 "Gramatica.y"
{estructuraActual.crearTerceto("OUT", val_peek(1).sval, null);}
break;
case 67:
//#line 208 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 68:
//#line 212 "Gramatica.y"
{
					if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
					
					estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					estructuraActual.addTercetoWhen();
					listOperadores.add(val_peek(1).sval);
					valores.add(lex1);
					valores.add(lex2);
					}
break;
case 69:
//#line 226 "Gramatica.y"
{
			if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 		errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
			
			String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
			estructuraActual.crearTerceto(val_peek(1).sval, lex1, val_peek(0).sval);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add(lex1);
			valores.add(val_peek(0).sval);
			listOperadores.add(val_peek(1).sval);
			}
break;
case 70:
//#line 239 "Gramatica.y"
{
			if (!mismoTipoExpID(val_peek(0).sval))
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
			String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
			valores.add(lex);
			
			estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			listOperadores.add(val_peek(1).sval);
			}
break;
case 71:
//#line 251 "Gramatica.y"
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
//#line 260 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval))
						 errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
					
					estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add(val_peek(1).sval);
					}
break;
case 73:
//#line 273 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
		 				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
					
					estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add(val_peek(1).sval);
					}
break;
case 74:
//#line 285 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							String lex= tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
							estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							valores.add(val_peek(0).sval);
							estructuraActual.addTercetoWhen();
							listOperadores.add(val_peek(1).sval);
							}
break;
case 75:
//#line 295 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						String lex= tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
						estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(0).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 76:
//#line 306 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(val_peek(1).sval);
							}
break;
case 77:
//#line 316 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
							String lex= tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(val_peek(1).sval);
							}
break;
case 78:
//#line 326 "Gramatica.y"
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
//#line 334 "Gramatica.y"
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
//#line 345 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(2).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 82:
//#line 356 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(2).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 83:
//#line 366 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
						estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(val_peek(2).sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(val_peek(1).sval);
						}
break;
case 84:
//#line 377 "Gramatica.y"
{String funcion=tablaDeSimbolos.getRefFuncion(val_peek(1).sval, ambito);
								if(funcion!=null){
		 						 	chequearYAsignarParametros(parametrosReales, parametrosFormales.get(funcion));
									
								}
								estructuraActual.crearTerceto("BI", funcion, null);
								String simboloFuncion= tablaDeSimbolos.getRefSimbolo(val_peek(1).sval, ambito);
								tipoActual=tablaDeSimbolos.getTipo(simboloFuncion);
								yyval.sval=simboloFuncion;
								valores.add(simboloFuncion);
								parametrosReales.clear();
								}
break;
case 85:
//#line 389 "Gramatica.y"
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
//#line 402 "Gramatica.y"
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
//#line 414 "Gramatica.y"
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
//#line 433 "Gramatica.y"
{parametrosReales.add(val_peek(0).sval);}
break;
case 92:
//#line 434 "Gramatica.y"
{parametrosReales.add(val_peek(1).sval+val_peek(0).sval);}
break;
case 93:
//#line 435 "Gramatica.y"
{parametrosReales.add(tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 94:
//#line 438 "Gramatica.y"
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
//#line 447 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 99:
//#line 456 "Gramatica.y"
{chequearRangoEntero(val_peek(0).sval);}
break;
case 105:
//#line 466 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 109:
//#line 475 "Gramatica.y"
{estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();
			estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
			
					 cantLabel++;
			}
break;
case 111:
//#line 485 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 112:
//#line 488 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
									 estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
									 
					 				cantLabel++;
									 }
break;
case 113:
//#line 493 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
								  		 estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
										 
					 					cantLabel++;
										 }
break;
case 114:
//#line 499 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 115:
//#line 500 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 116:
//#line 501 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 117:
//#line 504 "Gramatica.y"
{estructuraActual.crearTerceto("BF", val_peek(1).sval, null);
								estructuraActual.addTercetoIf();
								estructuraActual.addTercetoWhen();
								}
break;
case 118:
//#line 508 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 119:
//#line 509 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 120:
//#line 512 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval; valores=valores2;}
break;
case 121:
//#line 513 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval; valores=valores2;}
break;
case 122:
//#line 514 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 123:
//#line 515 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 124:
//#line 516 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 125:
//#line 517 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 126:
//#line 520 "Gramatica.y"
{if (!mismoTipo()) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
												estructuraActual.addTercetoWhen();
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											
											}
break;
case 127:
//#line 528 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
										
											estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
											estructuraActual.addTercetoWhen();
											valores2.add(val_peek(0).sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										
										}
break;
case 128:
//#line 537 "Gramatica.y"
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
//#line 547 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   								
											estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
											estructuraActual.addTercetoWhen();
											valores1.add(val_peek(2).sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 130:
//#line 555 "Gramatica.y"
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
//#line 564 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   								String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
										estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
										estructuraActual.addTercetoWhen();
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 132:
//#line 572 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));

										String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
										estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 133:
//#line 581 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
									estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
									estructuraActual.addTercetoWhen();
									valores1.add(val_peek(2).sval);
									valores2.add(lex);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 134:
//#line 591 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
										estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, lex);
										estructuraActual.addTercetoWhen();
										valores1.add(val_peek(3).sval +val_peek(2).sval);
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(val_peek(3).sval, val_peek(2).sval);
									}
break;
case 135:
//#line 602 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
									estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
									estructuraActual.addTercetoWhen();
									valores1.add(val_peek(0).sval);
									valores2.add(lex);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 136:
//#line 612 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(0).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
										estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval + val_peek(0).sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										valores2.add(val_peek(1).sval+val_peek(0).sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
									}
break;
case 137:
//#line 623 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		   						
									String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
									String lex2=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
									estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
									estructuraActual.addTercetoWhen();
									
									valores1.add(lex1);
									valores2.add(lex2);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 138:
//#line 635 "Gramatica.y"
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
//#line 643 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 140:
//#line 644 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 141:
//#line 645 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 148:
//#line 659 "Gramatica.y"
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
//#line 668 "Gramatica.y"
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
//#line 679 "Gramatica.y"
{estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval,ambito));
										}
break;
case 151:
//#line 685 "Gramatica.y"
{estructuraActual.addEtiquetaFor(val_peek(1).sval);}
break;
case 152:
//#line 688 "Gramatica.y"
{estructuraActual.addRefEtiqueta(refEtiqueta);}
break;
case 153:
//#line 692 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), val_peek(0).sval);
		estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
		
					 cantLabel++;
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 154:
//#line 703 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
		estructuraActual.crearTerceto("LABEL"+cantLabel, null, null);
		
		cantLabel++;
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 155:
//#line 716 "Gramatica.y"
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
//#line 728 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 158:
//#line 733 "Gramatica.y"
{estructuraActual.eliminarEtiqueta();}
break;
case 159:
//#line 734 "Gramatica.y"
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
//#line 746 "Gramatica.y"
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
//#line 761 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
								estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
								yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 162:
//#line 767 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
									   estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);
									   yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									   }
break;
case 163:
//#line 773 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
										estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 164:
//#line 779 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval)) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", linea.getNumeroLinea()));
											  tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
											  estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval+val_peek(0).sval);
											  yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
break;
case 165:
//#line 787 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una funcion", linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefSimbolo(funcionActual, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
						estructuraActual.crearTerceto("BI",null, null);
						if(funcionActual!=null && !mismoTipoIds(funcionActual, val_peek(2).sval)){
							errores_semanticos.add(new ErrorLinea("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return", linea.getNumeroLinea()));
							System.out.println("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return");
						}
					}	
						
				}
break;
case 166:
//#line 802 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefSimbolo(funcionActual, ambito), val_peek(2).sval);
						estructuraActual.crearTerceto("BI",null, null);
						if(funcionActual!=null && !mismoTipoIDCte(funcionActual, val_peek(2).sval)){
							errores_semanticos.add(new ErrorLinea("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return", linea.getNumeroLinea()));
							System.out.println("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return");
						}
					}	
				}
break;
case 167:
//#line 816 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefSimbolo(funcionActual, ambito), val_peek(2).sval);
						estructuraActual.crearTerceto("BI",null, null);
						if(funcionActual!=null && !mismoTipoExpID(funcionActual)){
							errores_semanticos.add(new ErrorLinea("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return", linea.getNumeroLinea()));
							System.out.println("El tipo de retorno de la funcion no corresponde con el tipo del ID en el return");
						}
					}	
				}
break;
case 168:
//#line 830 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 169:
//#line 831 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 170:
//#line 832 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 171:
//#line 833 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 173:
//#line 837 "Gramatica.y"
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
case 174:
//#line 847 "Gramatica.y"
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
case 175:
//#line 858 "Gramatica.y"
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
case 176:
//#line 870 "Gramatica.y"
{
				if (estructuraActual.existeEtiquetaFor(val_peek(1).sval)){
					estructuraActual.crearTerceto("BI", estructuraActual.getRefEtiqueta(val_peek(1).sval), null);
				}
				else
					errores_semanticos.add(new ErrorLinea("No existe la etiqueta de salto", linea.getNumeroLinea()));
			  }
break;
//#line 2286 "Parser.java"
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
