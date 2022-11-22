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
   23,   23,   10,   10,   21,   21,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   26,   26,   26,   27,   27,   27,   25,
   25,   12,   12,   13,   13,   28,   28,   28,   28,   30,
   30,   31,   31,   32,   33,   33,   19,   19,   19,   19,
   19,   24,   24,   24,   35,   35,   35,   35,   35,   35,
   34,   34,   34,   34,   34,   34,   34,   34,   34,   34,
   34,   34,   34,   34,   34,   34,   36,   36,   36,   36,
   36,   36,   37,   37,   40,   41,   38,   38,   42,   42,
    3,    3,    3,    3,   39,   39,   39,   39,   18,   18,
   18,   18,   18,   18,   18,   29,   29,   29,   29,   29,
};
final static short yylen[] = {                            2,
    4,    3,    2,    2,    1,    2,    2,    2,    1,    1,
    1,    3,    3,    1,    3,    1,    2,    2,    1,    3,
    3,    4,    3,    4,    2,    2,    2,    1,    1,    3,
    1,    2,    8,    6,    5,    8,    6,    5,    7,    5,
    4,    2,    4,    3,    3,    4,    2,    1,    2,    2,
    1,    1,    1,    2,    4,    4,    4,    5,    3,    3,
    2,    2,    4,    2,    4,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    1,    3,
    3,    3,    2,    2,    3,    5,    1,    2,    1,    4,
    3,    1,    1,    1,    1,    2,    2,    1,    1,    3,
    2,    1,    3,    1,    3,    3,    3,    3,    3,    2,
    2,    3,    2,    2,    1,    1,    1,    1,    1,    1,
    3,    3,    4,    3,    4,    3,    3,    3,    4,    3,
    4,    3,    3,    2,    2,    2,    2,    2,    2,    1,
    1,    1,    9,    8,    3,    3,    3,    3,    3,    3,
    2,    2,    5,    6,    3,    3,    3,    4,    5,    5,
    5,    4,    4,    4,    2,    1,    2,    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    5,    0,    0,    4,    0,    0,    0,    0,   28,
   29,    0,   95,   94,    0,  117,  118,  120,    0,    0,
    0,   92,   93,    0,    0,  115,  116,  119,    0,    0,
    0,    0,    0,    0,   14,   16,    0,    0,    0,    0,
   52,   53,    0,    0,    0,    0,   79,    0,    0,    0,
    0,    0,    0,  101,  110,  111,  135,   64,  136,    0,
    0,    0,    0,    0,   42,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   32,    0,   83,    0,    0,
    0,    0,   19,    0,    0,    0,    0,    0,    0,    0,
    0,   51,    0,    0,    2,    6,    7,    8,    0,   18,
    0,   17,    0,    0,    0,    0,    0,    0,    0,   54,
    0,    0,    0,    0,    0,   61,    0,    0,    0,    0,
  102,    0,  114,  134,    0,  151,    0,  152,  100,    0,
   48,    0,    0,    0,   60,   59,    0,    0,    0,    0,
  145,   71,   73,   72,   74,   30,   89,   84,    0,   87,
    0,  146,   67,   68,    0,    0,    0,    0,    0,   27,
   25,   15,    0,    0,   91,    0,    0,    0,    0,  165,
    0,    0,    0,    0,    1,   50,   49,   45,   66,  112,
   13,   12,    0,   81,   82,   80,    0,    0,    0,   44,
    0,    0,    0,    0,   75,   77,   76,   78,   69,   70,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  166,
    0,    0,  109,  104,  107,    0,  108,    0,    0,  140,
  141,  142,    0,    0,   65,    0,   47,    0,    0,   56,
    0,   57,   55,   88,    0,   85,  131,   21,   23,    0,
   20,   90,    0,    0,    0,    0,    0,    0,    0,   46,
   43,    0,    0,    0,  123,   63,    0,  167,    0,    0,
   96,  103,   97,    0,  150,  149,  138,  137,  139,    0,
    0,   38,    0,    0,   58,    0,   22,   24,  147,  148,
    0,    0,    0,    0,    0,   35,    0,    0,    0,    0,
  168,  106,  105,    0,  153,    0,   37,   86,    0,    0,
  159,  160,  161,    0,   34,  170,  169,  154,    0,    0,
    0,    0,    0,    0,    0,    0,   36,  158,  144,    0,
   33,  143,
};
final static short yydgoto[] = {                          3,
    4,   97,   30,   31,   32,   33,   34,   35,   82,   36,
   83,   37,   38,   39,   40,  134,   91,   92,   41,   42,
   43,   44,   45,   46,   47,   78,  151,  211,  212,   48,
  122,  216,  217,   49,   50,  223,   51,  169,  282,   52,
   53,  126,
};
final static short yysindex[] = {                      -194,
 -110,    0,    0,  155,    0,  396,  365,   -1,  -34,    0,
    0,  365,    0,    0,  462,    0,    0,    0, -185,   10,
   57,    0,    0,  123,  426,    0,    0,    0,  -13,  179,
  179,  179,  224,   67,    0,    0, -201,  555,   33,  135,
    0,    0,   86,  562,   82,  -79,    0,  374,  167,  -60,
   87,   87,   87,    0,    0,    0,    0,    0,    0,  343,
  321,  482,  -44,  -59,    0,    8,  -40,  190,  -25,   -3,
  412,  423,    6,  -31,  -24,    0,  485,    0,  126,  105,
 -201,   -6,    0,   41,   18,  -22,  314,  143,  123,  123,
  154,    0,  260,  266,    0,    0,    0,    0,  257,    0,
  277,    0,  414,   89,   91,   96, -198,   76,  230,    0,
  524,  530,  533,  278,  179,    0,  545, -231,   -9,  130,
    0, -182,    0,    0,   25,    0,  108,    0,    0,  328,
    0,  349,  140,   79,    0,    0,  674, -201,    9,   68,
    0,    0,    0,    0,    0,    0,    0,    0, -201,    0,
  146,    0,    0,    0,  605, -201,  737,  743,  -17,    0,
    0,    0, -185,  605,    0,  737,  420,  141,  356,    0,
  360,  470,  314,  314,    0,    0,    0,    0,    0,    0,
    0,    0,  399,    0,    0,    0,  605,  737,  743,    0,
  293,  308,  384,  153,    0,    0,    0,    0,    0,    0,
  605, -201,  737,  743,  325,  284,  410,  103,   -9,    0,
  332,   -9,    0,    0,    0,  374,    0,  106,  418,    0,
    0,    0,   36,   63,    0, -161,    0,  294,  431,    0,
  425,    0,    0,    0,  -10,    0,    0,    0,    0,  -65,
    0,    0, -181,  221,  723,  682,  731,  605,  743,    0,
    0, -161,  294,  438,    0,    0,  229,    0, -201,  444,
    0,    0,    0, -243,    0,    0,    0,    0,    0, -201,
  449,    0,  475, -161,    0,  477,    0,    0,    0,    0,
  414,  452,  460,  467,  469,    0,  488, -161,  479,  490,
    0,    0,    0,  491,    0,  476,    0,    0,  406,  -41,
    0,    0,    0,  498,    0,    0,    0,    0, -161,    0,
  605,  270,  743,  523,  298, -161,    0,    0,    0,  528,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  513,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  574,    5,
   16,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  461,    0,  513,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  468,  474,
    0,    0,    0,  -91,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -33,    0,   -5,   31,    1,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  513,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   32,   42,   53,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   88,    0,   90,   98,    0,    0,    0,    0,  481,    0,
    0,  483,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  281,  283,    0,
    0,  463,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,  203,  222,    0,    0,  480,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  663,
  542,    0,  554,    0,    0,  495,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  536, -116,  446,  622,  -46,  -12,    0,    0,    0,
  451,  -15,   21,    0,    0,  -75,  588,  448,    0,    0,
    0,  478,    0,   73,    0,    0,  390,  -95, -114,    0,
  437,    0,    0,  -18,  -14,    0,  -27,    0,    0,    0,
    0,  386,
};
final static int YYTABLESIZE=845;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
   79,   22,   76,   23,   10,   66,   94,  132,  220,  148,
  222,   22,  292,   23,    5,   11,  293,  171,   73,  133,
  101,    9,  106,  107,   68,   22,   59,   23,  113,  114,
  120,  113,   22,  194,   23,  130,  170,  163,   64,   22,
  206,   23,  141,   94,   26,   77,   79,  152,  132,   84,
  104,   22,  162,   23,  138,  105,   76,  103,  149,   26,
  146,    1,  133,  156,  120,   13,   14,  232,   13,   14,
  187,  127,  128,  213,    2,  120,  214,  215,   20,   63,
   59,  165,  133,   80,   67,  279,   81,  280,  183,  132,
  139,  143,  145,  124,  150,  162,   85,  154,  202,  157,
  160,  161,   10,   11,  166,   22,  267,   23,  269,  111,
   22,   95,   23,  261,  112,   25,  263,  130,   22,  229,
   23,   77,  228,  106,  113,  102,  233,  188,  126,   10,
  122,  196,  198,  200,  203,   26,   28,   27,  121,   77,
   11,  106,  113,  240,  110,   22,    9,   23,   77,  219,
  106,  113,  273,  127,  128,  108,   77,   79,  231,   76,
  266,  258,   25,   75,  133,   22,  113,   23,   22,  234,
   23,   77,  106,  113,  109,  124,  237,  287,  116,  272,
  162,  133,   26,   28,   27,   77,  236,  106,  113,  235,
  138,  246,  259,  254,   25,  124,  253,   22,   87,   23,
  190,  277,  278,  166,  115,  286,  133,  123,  270,  125,
  126,  130,  122,  129,   26,   28,   27,  135,   25,  149,
  121,   22,  255,   23,  132,  314,  139,  297,  260,   77,
  106,  113,   77,  113,   65,   13,   14,  147,   26,   28,
   27,  305,  163,   69,  271,   87,  117,    7,  136,  238,
  239,    8,  130,   86,   21,  150,   13,   14,  147,  118,
  207,  164,  317,   13,   14,  137,  299,  175,  208,  321,
  193,   10,   11,   20,   69,   21,  131,   24,  178,  290,
  117,    7,  100,  312,  315,    8,  168,   86,  127,  128,
  294,  117,    7,  218,  207,   77,    8,  113,   86,  133,
  179,   87,  208,   21,  218,  207,  180,   13,   14,  164,
  124,  162,  162,  208,   21,  181,  162,   20,  162,  166,
   22,  129,   23,  125,  162,  162,   22,  163,   23,   13,
   14,    6,    7,  162,  162,  182,    8,    9,   86,   10,
   11,   12,   13,   14,   15,  126,  164,  122,   16,   17,
   18,   19,   20,   25,   21,  121,   22,  184,   23,  185,
   74,   68,   71,   22,  186,   23,  224,   72,  225,   13,
   14,   13,   14,   26,   28,   27,  159,   70,    6,    7,
   26,   28,   27,    8,    9,   86,   10,   11,   12,   13,
   14,   15,   13,   14,  155,   16,   17,   18,   19,   20,
   93,   21,   20,  129,   62,  125,  226,   22,  227,   23,
    6,    7,  243,  120,  244,    8,    9,  250,   10,   11,
   12,   13,   14,   15,   26,   28,   27,   16,   17,   18,
   19,   20,  251,   21,    6,    7,   87,  127,  128,    8,
    9,  252,   10,   11,   12,   13,   14,   15,   22,  256,
   23,   16,   17,   18,   19,   20,  262,   21,  163,  163,
  242,  111,   22,  163,   23,  163,  112,  257,   22,   89,
   23,  163,  163,   26,   28,   27,  265,  164,  164,   99,
  163,  163,  164,  275,  164,   26,   28,   27,  274,  281,
  164,  164,   69,   10,   11,  288,  119,  289,  131,  164,
  164,   74,  291,   71,   22,   73,   23,  295,   72,   74,
  300,   71,   22,   73,   23,  296,   72,  298,  301,   75,
   58,   26,   28,   27,   22,  302,   23,  303,  304,   26,
   28,   27,  173,  309,   89,   89,  318,  306,  129,   29,
  125,   26,   28,   27,   13,   14,  201,  140,  307,  308,
   13,   14,  137,   89,   20,  316,  158,   10,   11,   88,
   20,  167,  131,  319,  320,   96,  210,   98,  322,    6,
    7,   31,  210,    3,    8,    9,   86,   10,   11,   12,
   13,   14,  172,   62,  189,   41,   16,   17,   18,   19,
   20,  204,   11,   16,   17,   18,  104,   22,    9,   23,
  155,  105,   40,  111,   22,   98,   23,   99,  112,   13,
   14,   57,  156,  241,   26,   28,   27,   39,  173,  173,
   60,   26,   28,   27,  276,   98,   13,   14,  245,  117,
    7,   13,   14,   61,    8,    0,   20,   16,   17,   18,
    0,   20,  118,  191,   74,   90,   71,   22,  247,   23,
  205,   72,  264,   54,   55,   56,  210,    0,    0,  210,
  249,    0,   13,   14,   57,   13,   14,  248,    0,  121,
  210,    0,  310,   14,  311,   20,  176,  177,   13,   14,
  142,   60,   20,  140,    0,    0,   16,   17,   18,   13,
   14,  144,   13,   14,   61,  192,   93,    0,   16,   17,
   18,    0,   20,    0,   95,   95,    0,   95,  174,   95,
   90,   90,    0,   74,    0,   71,   22,   68,   23,    0,
   72,  157,  284,  104,   22,   68,   23,    0,  105,   90,
   69,    0,  230,   70,   16,   17,   18,   60,   69,    0,
  209,  206,   16,   17,   18,    0,  221,    0,   13,   14,
   61,   13,   14,  153,   16,   17,   18,    0,   20,    0,
  176,  177,   74,  283,   71,   22,    0,   23,    0,   72,
    0,  285,  111,   22,    0,   23,  313,  112,  104,   22,
    0,   23,    0,  105,  111,   22,    0,   23,    0,  112,
   13,   14,  195,    0,  174,  174,   13,   14,  197,   13,
   14,  199,   54,   55,   56,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   16,   17,   18,
  209,    0,    0,  209,   16,   17,   18,  121,    0,    0,
    0,    0,    0,    0,  268,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         15,
   15,   43,   15,   45,    0,   40,   25,   41,  125,   41,
  125,   43,  256,   45,  125,    0,  260,   40,   44,   66,
   33,    0,   38,   38,  256,   43,    6,   45,   44,   44,
   40,  123,   43,  109,   45,   41,   59,   44,   40,   43,
  272,   45,   70,   62,   44,   61,   61,   75,   41,   40,
   42,   43,   59,   45,   70,   47,   69,   37,   74,   59,
   73,  256,  109,   79,   40,  267,  268,   59,  267,  268,
  269,   41,   41,  256,  269,   40,  259,  260,  277,    7,
   60,   41,   41,  269,   12,  267,  272,  269,  103,  123,
   70,   71,   72,   41,   74,   40,   40,   77,  114,   79,
   80,   81,  264,  265,   84,   43,  223,   45,  223,   42,
   43,  125,   45,  209,   47,   40,  212,  123,   43,   41,
   45,  137,   44,  139,  140,   59,   59,  107,   41,  125,
   41,  111,  112,  113,  114,   60,   61,   62,   41,  155,
  125,  157,  158,  159,   59,   43,  125,   45,  164,  125,
  166,  167,  228,  123,  123,  123,  172,  172,  138,  172,
  125,   59,   40,   58,  123,   43,  258,   45,   43,  149,
   45,  187,  188,  189,   40,  123,  156,  253,  258,  226,
  125,  228,   60,   61,   62,  201,   41,  203,  204,   44,
  206,  171,  208,   41,   40,  256,   44,   43,  123,   45,
  125,  267,  268,  183,  123,  252,  253,   41,  224,  123,
  123,  271,  123,  258,   60,   61,   62,  258,   40,  235,
  123,   43,  202,   45,  258,  267,  206,  274,  208,  245,
  246,  247,  248,  249,  269,  267,  268,  269,   60,   61,
   62,  288,   40,  269,  224,  123,  256,  257,   59,  267,
  268,  261,  258,  263,  279,  235,  267,  268,  269,  269,
  270,   40,  309,  267,  268,  269,  281,  125,  278,  316,
   41,  264,  265,  277,  269,  279,  269,  123,  125,  259,
  256,  257,   59,  299,  300,  261,  269,  263,  258,  258,
  270,  256,  257,  269,  270,  311,  261,  313,  263,  258,
   41,  123,  278,  279,  269,  270,   41,  267,  268,  269,
  258,  256,  257,  278,  279,   59,  261,  277,  263,  299,
   43,   41,   45,   41,  269,  270,   43,  125,   45,  267,
  268,  256,  257,  278,  279,   59,  261,  262,  263,  264,
  265,  266,  267,  268,  269,  258,  125,  258,  273,  274,
  275,  276,  277,   40,  279,  258,   43,  269,   45,  269,
   40,  256,   42,   43,  269,   45,  259,   47,   41,  267,
  268,  267,  268,   60,   61,   62,  272,  272,  256,  257,
   60,   61,   62,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  267,  268,  269,  273,  274,  275,  276,  277,
  271,  279,  277,  123,   40,  123,   58,   43,  269,   45,
  256,  257,  272,   40,   59,  261,  262,  125,  264,  265,
  266,  267,  268,  269,   60,   61,   62,  273,  274,  275,
  276,  277,  125,  279,  256,  257,  123,   52,   53,  261,
  262,   58,  264,  265,  266,  267,  268,  269,   43,  125,
   45,  273,  274,  275,  276,  277,  125,  279,  256,  257,
   41,   42,   43,  261,   45,  263,   47,   58,   43,   24,
   45,  269,  270,   60,   61,   62,   59,  256,  257,  256,
  278,  279,  261,   59,  263,   60,   61,   62,   58,  269,
  269,  270,  269,  264,  265,   58,  123,  269,  269,  278,
  279,   40,   59,   42,   43,   44,   45,   59,   47,   40,
   59,   42,   43,   44,   45,   41,   47,   41,   59,   58,
  125,   60,   61,   62,   43,   59,   45,   59,   41,   60,
   61,   62,   87,   58,   89,   90,  267,   59,  258,    4,
  258,   60,   61,   62,  267,  268,  269,   70,   59,   59,
  267,  268,  269,  108,  277,   58,   79,  264,  265,   24,
  277,   84,  269,   41,  267,   30,  119,   32,   41,  256,
  257,   59,  125,    0,  261,  262,  263,  264,  265,  266,
  267,  268,  269,  123,  107,  123,  273,  274,  275,  276,
  277,  114,  125,  273,  274,  275,   42,   43,  125,   45,
   59,   47,  123,   42,   43,  125,   45,  125,   47,  267,
  268,  269,   59,  163,   60,   61,   62,  123,  173,  174,
  256,   60,   61,   62,  235,   90,  267,  268,  269,  256,
  257,  267,  268,  269,  261,   -1,  277,  273,  274,  275,
   -1,  277,  269,  108,   40,   24,   42,   43,  171,   45,
  115,   47,  216,  258,  259,  260,  209,   -1,   -1,  212,
  183,   -1,  267,  268,  269,  267,  268,  269,   -1,   48,
  223,   -1,  267,  268,  269,  277,   89,   90,  267,  268,
  269,  256,  277,  206,   -1,   -1,  273,  274,  275,  267,
  268,  269,  267,  268,  269,  108,  271,   -1,  273,  274,
  275,   -1,  277,   -1,   42,   43,   -1,   45,   87,   47,
   89,   90,   -1,   40,   -1,   42,   43,  256,   45,   -1,
   47,   59,   41,   42,   43,  256,   45,   -1,   47,  108,
  269,   -1,   59,  272,  273,  274,  275,  256,  269,   -1,
  119,  272,  273,  274,  275,   -1,  125,   -1,  267,  268,
  269,  267,  268,  269,  273,  274,  275,   -1,  277,   -1,
  173,  174,   40,   41,   42,   43,   -1,   45,   -1,   47,
   -1,   41,   42,   43,   -1,   45,  299,   47,   42,   43,
   -1,   45,   -1,   47,   42,   43,   -1,   45,   -1,   47,
  267,  268,  269,   -1,  173,  174,  267,  268,  269,  267,
  268,  269,  258,  259,  260,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  209,   -1,   -1,  212,  273,  274,  275,  216,   -1,   -1,
   -1,   -1,   -1,   -1,  223,
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

//#line 516 "Gramatica.y"

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

AnalizadorLexico lexico;

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

		}
		else{
			System.out.println("La cantidad de parametros es incorrecta");
			errores_semanticos.add(new ErrorLinea("La cantidad de parametros es incorrecta", linea.getNumeroLinea()));
		}
	}

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
			if((errores_lexicos.isEmpty()) && (errores_sintacticos.isEmpty()) && (errores_semanticos.isEmpty())){
				System.out.println("no hay erroressssssssssssssssssssssssssssssssss");
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
		
		System.out.println(GeneradorCodigo.getCodigoGenerado());
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

	public boolean mismoTipoIds(String val1, String val2){
		String lex1 = tablaDeSimbolos.getRefSimbolo(val1, ambito);
		String lex2 = tablaDeSimbolos.getRefSimbolo(val2, ambito);
		if (tablaDeSimbolos.getTipo(lex1) == tablaDeSimbolos.getTipo(lex2)){
			tipoAnterior = tipoActual;
			tipoActual = tablaDeSimbolos.getTipo(lex1);
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public boolean mismoTipoCtes(String val1, String val2){
		String tipo1 = tablaDeSimbolos.getTipo(val1);
		String tipo2 = tablaDeSimbolos.getTipo(val2);
		if (tipo1.equals(tipo2)){
			tipoAnterior = tipoActual;
			tipoActual = tablaDeSimbolos.getTipo(lex1);
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public boolean mismoTipoIDCte(String val1, String val2){
		String lex1 = tablaDeSimbolos.getRefSimbolo(val1, ambito);
		String tipo = tablaDeSimbolos.getTipo(val2);
		if (tablaDeSimbolos.getTipo(lex1) == tipo){
			tipoAnterior = tipoActual;
			tipoActual = tablaDeSimbolos.getTipo(lex1);
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public boolean mismoTipoExpCte(String val){
		String tipo = tablaDeSimbolos.getTipo(val);
		if (tipoActual == tipo){
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public boolean mismoTipoExpId(String val){
		String tipo = tablaDeSimbolos.getRefSimbolo(val1, ambito);
		if (tipoActual == tipo){
			return true;
		}
		else {
			tipoActual = "";
			return false;
			}
	}

	public boolean mismoTipo(){
		if (tipoActual == tipoAnterior){
			return true;
		}
		else {
			tipoActual = "";
			tipoAnterior = "";
			return false;
			}
	}

//#line 885 "Parser.java"
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
//#line 68 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
									tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 22:
//#line 71 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(0).sval)) 
	   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
	   									tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval + val_peek(0).sval);}
break;
case 23:
//#line 74 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
	   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
									tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 24:
//#line 77 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval)) 
	   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
	   									  tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval + val_peek(0).sval);}
break;
case 25:
//#line 80 "Gramatica.y"
{AgregarErrorSintactico("Se espera un identificador");}
break;
case 26:
//#line 81 "Gramatica.y"
{AgregarErrorSintactico("Se espera una constante ");}
break;
case 27:
//#line 82 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:' ");}
break;
case 28:
//#line 85 "Gramatica.y"
{estructuraActual.setTipo("I8");}
break;
case 29:
//#line 86 "Gramatica.y"
{estructuraActual.setTipo("F32");}
break;
case 30:
//#line 89 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
break;
case 31:
//#line 90 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
break;
case 32:
//#line 91 "Gramatica.y"
{AgregarErrorSintactico("Se espera ',' ");}
break;
case 33:
//#line 94 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
															if (!parametrosFormales.containsKey(funcionActual+":"+ambito)){
																List<Simbolo> listparametros=parametros;
																parametrosFormales.put(funcionActual+":"+ambito, listparametros);
															}
															parametros=new ArrayList<>();
															ambito= ambito+":"+funcionActual;
															}
break;
case 34:
//#line 102 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
			 									if (!parametrosFormales.containsKey(funcionActual+":"+ambito)){
													List<Simbolo> listparametros=parametros;
													parametrosFormales.put(funcionActual+":"+ambito, listparametros);
												}
												parametros=new ArrayList<>();
												ambito= ambito+":"+funcionActual;
												}
break;
case 35:
//#line 110 "Gramatica.y"
{tablaDeSimbolos.setTipo(funcionActual+":"+ambito, estructuraActual.getTipo());
			 						if (!parametrosFormales.containsKey(funcionActual+":"+ambito)){
										List<Simbolo> listparametros=parametros;
										parametrosFormales.put(funcionActual+":"+ambito, listparametros);
									}
									parametros=new ArrayList<>();
									ambito= ambito+":"+funcionActual;
									}
break;
case 36:
//#line 119 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 37:
//#line 120 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 38:
//#line 121 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 39:
//#line 122 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 40:
//#line 123 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 41:
//#line 124 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 42:
//#line 127 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito, 269, "identificador_funcion"));
			 funcionActual=val_peek(0).sval;
			 estructuraActual=new EstructuraTercetos(ambito+":"+val_peek(0).sval);
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 dentroDeFun=true;
			 }
break;
case 43:
//#line 136 "Gramatica.y"
{desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   dentroDeFun=false;
									   }
break;
case 44:
//#line 141 "Gramatica.y"
{AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
break;
case 45:
//#line 142 "Gramatica.y"
{AgregarErrorSintactico("Se espera el header de la funcion");}
break;
case 46:
//#line 143 "Gramatica.y"
{AgregarErrorSintactico("Se espera el retorno de la funcion");}
break;
case 47:
//#line 147 "Gramatica.y"
{Simbolo simbolo= new Simbolo(val_peek(0).sval+":"+ambito+":"+funcionActual,269,"parametro", estructuraActual.getTipo());
					tablaDeSimbolos.add(simbolo);
					parametros.add(simbolo);}
break;
case 48:
//#line 150 "Gramatica.y"
{AgregarErrorSintactico("Se espera tipo de parametro");}
break;
case 55:
//#line 163 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(3).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
								estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 56:
//#line 166 "Gramatica.y"
{if (!mismoTipoIds(val_peek(3).sval, val_peek(1).sval)) 
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
							estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(1).sval, ambito));}
break;
case 57:
//#line 169 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval))
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval);}
break;
case 58:
//#line 172 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(4).sval, val_peek(1).sval))
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
									estructuraActual.crearTerceto(val_peek(3).sval, tablaDeSimbolos.getRefSimbolo(val_peek(4).sval, ambito), val_peek(2).sval + val_peek(1).sval);}
break;
case 59:
//#line 175 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 61:
//#line 178 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
//#line 179 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 64:
//#line 183 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 65:
//#line 187 "Gramatica.y"
{estructuraActual.crearTerceto("OUT", val_peek(1).sval, null);}
break;
case 66:
//#line 188 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 67:
//#line 194 "Gramatica.y"
{
					if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
					estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
					}
break;
case 68:
//#line 199 "Gramatica.y"
{
			if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 		errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
			estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);
			}
break;
case 69:
//#line 204 "Gramatica.y"
{
			if (!mismoTipoExpID(val_peek(0).sval))
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 70:
//#line 208 "Gramatica.y"
{
			if (!mismoTipoExpCte(val_peek(0).sval)) 
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);
			}
break;
case 71:
//#line 213 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval))
						 errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 72:
//#line 216 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 73:
//#line 217 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 74:
//#line 218 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 75:
//#line 219 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 76:
//#line 220 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 77:
//#line 221 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 78:
//#line 222 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 80:
//#line 224 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 81:
//#line 225 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 82:
//#line 226 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 87:
//#line 235 "Gramatica.y"
{parametrosReales.add(val_peek(0).sval);}
break;
case 88:
//#line 236 "Gramatica.y"
{parametrosReales.add(val_peek(1).sval+val_peek(0).sval);}
break;
case 89:
//#line 237 "Gramatica.y"
{parametrosReales.add(tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 91:
//#line 241 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 95:
//#line 250 "Gramatica.y"
{chequearRangoEntero(val_peek(0).sval);}
break;
case 101:
//#line 260 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 104:
//#line 268 "Gramatica.y"
{estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();
			estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 106:
//#line 275 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 107:
//#line 278 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
									 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 108:
//#line 280 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
								  		 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 109:
//#line 283 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 110:
//#line 284 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 111:
//#line 285 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 112:
//#line 288 "Gramatica.y"
{estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
								estructuraActual.addTercetoIf();}
break;
case 113:
//#line 290 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 114:
//#line 291 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 121:
//#line 302 "Gramatica.y"
{if (!mismoTipo()) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(2), estructuraActual.getRefTerceto(1));}
break;
case 122:
//#line 303 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 123:
//#line 304 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getRefTerceto(1), val_peek(1).sval + val_peek(0).sval );}
break;
case 124:
//#line 305 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, estructuraActual.getRefTerceto(1));}
break;
case 125:
//#line 306 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, estructuraActual.getRefTerceto(1));}
break;
case 126:
//#line 307 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 127:
//#line 308 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 128:
//#line 309 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 129:
//#line 310 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 130:
//#line 311 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 131:
//#line 312 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval + val_peek(0).sval);}
break;
case 132:
//#line 313 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 133:
//#line 314 "Gramatica.y"
{if (!mismoTipoCtes(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);}
break;
case 134:
//#line 315 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 135:
//#line 316 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 136:
//#line 317 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 143:
//#line 331 "Gramatica.y"
{estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 refEtiqueta = estructuraActual.getRefTerceto(2);
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 144:
//#line 340 "Gramatica.y"
{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 refEtiqueta = estructuraActual.getRefTerceto(2);
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto("+", estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 145:
//#line 351 "Gramatica.y"
{estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval,ambito));
										}
break;
case 146:
//#line 357 "Gramatica.y"
{estructuraActual.addEtiquetaFor(val_peek(2).sval); estructuraActual.addRefEtiqueta(refEtiqueta);}
break;
case 147:
//#line 361 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), val_peek(0).sval);
	 	}
break;
case 148:
//#line 367 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
	 	}
break;
case 149:
//#line 375 "Gramatica.y"
{
					 estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.completarTercetoFor(1);
					 estructuraActual.popIdFor();}
break;
case 150:
//#line 380 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 151:
//#line 384 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
				}
break;
case 152:
//#line 390 "Gramatica.y"
{
					/*estructuraActual.addRefEtiqueta();*/
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
				}
break;
case 153:
//#line 397 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
					esperandoBreakcte=false;
					estructuraActual.borrarIdAsigFor();
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
					}
break;
case 154:
//#line 408 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(2).sval + val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.borrarListTercetosBreak();
					estructuraActual.borrarListTercetosBreakCte();
					dentroDeFor=false;
					esperandoBreakcte=false;
					estructuraActual.borrarIdAsigFor();
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
				}
break;
case 155:
//#line 422 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
								estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
								estructuraActual.addNumCondicionFor();
								estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 156:
//#line 427 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval, val_peek(0).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
									   estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
									   estructuraActual.addNumCondicionFor();
									   estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 157:
//#line 432 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
										estructuraActual.addNumCondicionFor();
										estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 158:
//#line 437 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval)) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											  estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
											  estructuraActual.addNumCondicionFor();
											  estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval+val_peek(0).sval);}
break;
case 159:
//#line 444 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una funcion", this.linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro de una funcion");
					}
					else
						estructuraActual.crearTerceto("=:", ambito, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
				}
break;
case 160:
//#line 452 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else
						estructuraActual.crearTerceto("=:", ambito, val_peek(2).sval);
				}
break;
case 161:
//#line 460 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else
						estructuraActual.crearTerceto("=:", ambito, estructuraActual.getRefTerceto(1));
				}
break;
case 162:
//#line 468 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 163:
//#line 469 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 164:
//#line 470 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 165:
//#line 471 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 167:
//#line 475 "Gramatica.y"
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
case 168:
//#line 485 "Gramatica.y"
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
case 169:
//#line 496 "Gramatica.y"
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
case 170:
//#line 507 "Gramatica.y"
{
				if ((dentroDeFor) && (estructuraActual.existeEtiquetaFor(val_peek(1).sval))){
					estructuraActual.crearTerceto("BI", estructuraActual.getRefEtiqueta(val_peek(1).sval), null);
				}
			  }
break;
//#line 1719 "Parser.java"
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
