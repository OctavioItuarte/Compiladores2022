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
   23,   23,   24,   10,   10,   21,   21,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   27,   27,   27,   28,   28,   28,
   26,   26,   12,   12,   13,   13,   29,   29,   29,   29,
   31,   31,   32,   32,   33,   34,   34,   19,   19,   19,
   19,   19,   25,   25,   25,   36,   36,   36,   36,   36,
   36,   35,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,   35,   35,   35,   35,   35,   37,   37,   37,
   37,   37,   37,   38,   38,   41,   42,   39,   39,   43,
   43,    3,    3,    3,    3,   40,   40,   40,   40,   18,
   18,   18,   18,   18,   18,   18,   30,   30,   30,   30,
   30,
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
    3,    3,    3,    2,    2,    3,    5,    1,    2,    1,
    4,    3,    1,    1,    1,    1,    2,    2,    1,    1,
    3,    2,    1,    3,    1,    3,    3,    3,    3,    3,
    2,    2,    3,    2,    2,    1,    1,    1,    1,    1,
    1,    3,    3,    4,    3,    4,    3,    3,    3,    4,
    3,    4,    3,    3,    2,    2,    2,    2,    2,    2,
    1,    1,    1,    9,    8,    3,    3,    3,    3,    3,
    3,    2,    2,    5,    6,    3,    3,    3,    4,    5,
    5,    5,    4,    4,    4,    2,    1,    2,    3,    4,
    4,
};
final static short yydefred[] = {                         0,
    0,    5,    0,    0,    4,    0,    0,    0,    0,   28,
   29,   63,   96,   95,    0,  118,  119,  121,    0,    0,
    0,   93,   94,    0,    0,  116,  117,  120,    0,    0,
    0,    0,    0,    0,   14,   16,    0,    0,    0,    0,
   52,   53,    0,    0,    0,    0,    0,   80,    0,    0,
    0,    0,    0,    0,  102,  111,  112,  136,   65,  137,
    0,    0,    0,    0,    0,   42,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   32,    0,   84,    0,    0,
    0,    0,   19,    0,    0,    0,    0,    0,    0,    0,
    0,   51,    0,    0,    2,    6,    7,    8,    0,   18,
    0,   17,    0,    0,    0,    0,    0,    0,    0,   54,
    0,    0,    0,    0,    0,    0,   61,    0,    0,    0,
    0,  103,    0,  115,  135,    0,  152,    0,  153,  101,
    0,   48,    0,    0,    0,   59,    0,    0,    0,    0,
  146,   72,   74,   73,   75,   30,   90,   85,    0,   88,
    0,  147,   68,   69,    0,    0,    0,    0,    0,   27,
   25,   15,    0,    0,   92,    0,    0,    0,    0,  166,
    0,    0,    0,    0,    1,   50,   49,   45,   67,  113,
   13,   12,    0,   82,   83,   81,    0,    0,    0,   44,
    0,    0,    0,    0,   76,   78,   77,   79,   70,   71,
    0,    0,    0,    0,    0,   60,    0,    0,    0,    0,
  167,    0,    0,  110,  105,  108,    0,  109,    0,    0,
  141,  142,  143,    0,    0,   66,    0,   47,    0,    0,
   56,    0,   57,   55,   89,    0,   86,  132,   21,   23,
    0,   20,   91,    0,    0,    0,    0,    0,    0,    0,
   46,   43,    0,    0,    0,  124,   64,    0,  168,    0,
    0,   97,  104,   98,    0,  151,  150,  139,  138,  140,
    0,    0,   38,    0,    0,   58,    0,   22,   24,  148,
  149,    0,    0,    0,    0,    0,   35,    0,    0,    0,
    0,  169,  107,  106,    0,  154,    0,   37,   87,    0,
    0,  160,  161,  162,    0,   34,  171,  170,  155,    0,
    0,    0,    0,    0,    0,    0,    0,   36,  159,  145,
    0,   33,  144,
};
final static short yydgoto[] = {                          3,
    4,   97,   30,   31,   32,   33,   34,   35,   82,   36,
   83,   37,   38,   39,   40,  135,   91,   92,   41,   42,
   43,   44,   45,   46,   47,   48,   78,  151,  212,  213,
   49,  123,  217,  218,   50,   51,  224,   52,  169,  283,
   53,   54,  127,
};
final static short yysindex[] = {                      -163,
 -113,    0,    0,  201,    0,  406,  411,    6,  -33,    0,
    0,    0,    0,    0,  497,    0,    0,    0, -172,   27,
   29,    0,    0,  164,  374,    0,    0,    0,  -83,  229,
  229,  229,   15,   46,    0,    0, -101,  566,   32,   71,
    0,    0,   64,  573,   91,  411, -187,    0,  402,   49,
 -109,   96,   96,   96,    0,    0,    0,    0,    0,    0,
  293,  528,  439,  -96,  -59,    0,   31,  170,  -42,   34,
  333,  414,  -30,    8,   -6,    0,  422,    0,  -17,  -89,
 -101,   24,    0,  -20,   35,  -24,  330,  173,  164,  164,
  221,    0,  307,  310,    0,    0,    0,    0,  313,    0,
  319,    0,  -57,  100,  111,  138, -207,  120,   89,    0,
  436,  442,  450,   11,  229,  151,    0,  536, -231,  265,
  141,    0,  -63,    0,    0,   51,    0,  163,    0,    0,
  377,    0,  388,  178,   76,    0,   33, -101,   39,  471,
    0,    0,    0,    0,    0,    0,    0,    0, -101,    0,
  112,    0,    0,    0,  534, -101,  712,  720,  -12,    0,
    0,    0, -172,  534,    0,  712,  694,  180,  396,    0,
   94,  505,  330,  330,    0,    0,    0,    0,    0,    0,
    0,    0,  136,    0,    0,    0,  534,  712,  720,    0,
  336,  354,  423,  159,    0,    0,    0,    0,    0,    0,
  534, -101,  712,  720,  358,    0,   67,  429,   42,  265,
    0,  364,  265,    0,    0,    0,  402,    0,   50,  433,
    0,    0,    0,   62,  177,    0,  -74,    0,  195,  449,
    0,  453,    0,    0,    0,   70,    0,    0,    0,    0,
   48,    0,    0, -235,  250,  681,  704,  746,  534,  720,
    0,    0,  -74,  195,  462,    0,    0,  258,    0, -101,
  470,    0,    0,    0, -216,    0,    0,    0,    0,    0,
 -101,  477,    0,  515,  -74,    0,  522,    0,    0,    0,
    0,  -57,  479,  487,  510,  519,    0,  523,  -74,  521,
  551,    0,    0,    0,  553,    0,  524,    0,    0,  355,
  -25,    0,    0,    0,  556,    0,    0,    0,    0,  -74,
    0,  534,  350,  720,  578,  362,  -74,    0,    0,    0,
  580,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  572,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  625,   10,
   14,   36,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  572,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  514,  525,
    0,    0,    0, -112,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  531,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -28,    0,   -4,    9,   44,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  572,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   25,   75,   92,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  158,    0,  251,  325,    0,    0,    0,    0,    0,  535,
    0,    0,  537,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  326,  327,
    0,    0,  533,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   86,  145,  254,    0,    0,  545,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  610,  585,    0,  611,    0,    0,  554,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  668, -120,  464,  612,  -52,   81,    0,    0,    0,
  513,  -15,   57,    0,    0,  -70,  237,  520,    0,    0,
    0,  -62,    0,    0,   12,    0,    0,  451,   -5, -117,
    0,  476,    0,    0,    2,  -14,    0,  -32,    0,    0,
    0,    0,  306,
};
final static int YYTABLESIZE=848;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
   79,   73,   26,   28,   27,  221,   67,  140,  223,   10,
  114,    5,  133,   11,  134,  171,  158,   22,   64,   23,
  165,  167,  106,  107,   68,   22,   94,   23,  113,  114,
   22,  280,   23,  281,  170,    9,  131,  141,  194,  293,
  207,   95,  152,  294,  189,   65,   77,   79,  148,  128,
   22,  204,   23,   22,  138,   23,  134,  116,  149,   13,
   14,  187,   60,  156,   94,  129,   84,  163,   85,   20,
  117,  133,   74,  100,   71,   22,   22,   23,   23,   72,
  104,   22,  162,   23,   22,  105,   23,   26,  183,  124,
  121,  231,    1,  103,  133,   76,   80,  233,  202,   81,
  259,  121,   26,  268,  102,    2,  270,   75,  248,   22,
  109,   23,   22,  101,   23,  134,  230,   60,  131,  229,
  250,   77,  110,  106,  113,  163,  139,  143,  145,  193,
  150,  128,  125,  154,   10,  157,  160,  161,   11,   77,
  166,  106,  113,  241,  140,  114,  125,  129,   77,   76,
  106,  113,  237,  146,  108,  236,   77,   79,  274,   25,
    9,  130,   22,  188,   23,   13,   14,  196,  198,  200,
  203,   77,  106,  113,  273,  220,  134,   13,   14,   26,
   28,   27,  159,  288,  164,   77,  267,  106,  113,   10,
   11,  138,  214,  260,  232,  215,  216,  134,  127,  255,
  287,  134,  254,   25,  262,  235,   22,  264,   23,  271,
  163,  131,  238,  115,  125,   16,   17,   18,  126,   22,
  149,   23,  298,   26,   28,   27,   69,  247,  136,  133,
   77,  106,  113,   77,  113,   66,  306,  314,   69,  166,
   25,  315,   87,   22,  190,   23,   13,   14,  164,   13,
   14,  155,   76,  131,  239,  240,   20,  318,  256,   20,
   26,   28,   27,  139,  322,  261,  128,  300,   25,  164,
   99,   22,   21,   23,   13,   14,  147,   13,   14,  201,
  127,  272,  129,   69,  313,  316,   87,   20,   26,   28,
   27,  123,  150,  165,   10,   11,   77,  175,  113,  132,
   13,   14,  137,  168,  121,   68,  118,    7,   13,   14,
   20,    8,   21,   86,  278,  279,  291,  118,    7,  219,
  208,   70,    8,   24,   86,  176,  177,  295,  209,   21,
  219,  208,  134,   13,   14,  137,   13,   14,  147,  209,
   21,  163,  163,   20,  192,  178,  163,  179,  163,  125,
  180,   87,   10,   11,  163,  163,  166,  132,  128,  129,
   13,   14,  246,  163,  163,  122,  130,  126,  184,   25,
   20,  181,   22,  123,   23,    6,    7,  182,  165,  185,
    8,    9,   86,   10,   11,   12,   13,   14,   15,   26,
   28,   27,   16,   17,   18,   19,   20,   22,   21,   23,
  164,  164,   13,   14,  249,  164,  186,  164,  206,  176,
  177,   93,   20,  164,  164,  127,   22,  226,   23,    6,
    7,  225,  164,  164,    8,    9,   86,   10,   11,   12,
   13,   14,   15,   26,   28,   27,   16,   17,   18,   19,
   20,  121,   21,   13,   14,  227,  228,  122,  130,  126,
   63,  244,   87,   22,  245,   23,    6,    7,   10,   11,
  251,    8,    9,  132,   10,   11,   12,   13,   14,   15,
   26,   28,   27,   16,   17,   18,   19,   20,  252,   21,
  253,   22,  257,   23,    6,    7,  258,   89,  263,    8,
    9,  266,   10,   11,   12,   13,   14,   15,   26,   28,
   27,   16,   17,   18,   19,   20,  275,   21,  123,  165,
  165,  276,  111,   22,  165,   23,  165,  112,  282,  289,
  118,    7,  165,  165,  120,    8,  290,   86,  292,  234,
   59,  165,  165,  119,  208,  296,   74,  301,   71,   22,
   73,   23,  209,   72,   74,  302,   71,   22,   73,   23,
  173,   72,   89,   89,   75,  297,   26,   28,   27,   13,
   14,   58,  299,  305,   26,   28,   27,   74,  303,   71,
   22,   89,   23,   74,   72,   71,   22,  304,   23,  307,
   72,  310,  122,  130,  126,    6,    7,   26,   28,   27,
    8,    9,   86,   10,   11,   12,   13,   14,  172,   13,
   14,  142,   16,   17,   18,   19,   20,  104,   22,  308,
   23,  309,  105,  317,  111,   22,  319,   23,  320,  112,
  323,  311,   14,  312,    3,   26,   28,   27,  321,   61,
   31,   20,   26,   28,   27,   90,  173,  173,   11,  211,
   13,   14,   62,  156,   93,  211,   16,   17,   18,    9,
   20,   96,   96,   62,   96,   41,   96,  118,    7,   99,
  122,  100,    8,   55,   56,   57,   61,   40,  158,  157,
  119,   29,   13,   14,   58,  242,   39,   13,   14,   62,
   13,   14,  144,   16,   17,   18,  277,   20,   13,   14,
  153,   88,  265,    0,   61,    0,    0,   96,  174,   98,
   90,   90,   13,   14,  195,   13,   14,   62,   13,   14,
  197,   16,   17,   18,    0,   20,   13,   14,  199,   90,
   74,  284,   71,   22,    0,   23,    0,   72,    0,  211,
    0,  210,  211,    0,  243,  111,   22,  222,   23,    0,
  112,    0,    0,  211,  285,  104,   22,    0,   23,    0,
  105,    0,   68,  104,   22,    0,   23,   98,  105,    0,
   68,  111,   22,    0,   23,   69,  112,    0,   70,   16,
   17,   18,    0,   69,    0,  191,  207,   16,   17,   18,
    0,    0,  205,    0,  174,  174,  286,  111,   22,    0,
   23,    0,  112,   55,   56,   57,    0,    0,    0,    0,
   16,   17,   18,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  210,    0,    0,  210,    0,    0,    0,  122,    0,
    0,    0,    0,    0,    0,  269,    0,    0,   16,   17,
   18,    0,    0,    0,    0,   16,   17,   18,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         15,
   15,   44,   60,   61,   62,  126,   40,   70,  126,    0,
  123,  125,   41,    0,   67,   40,   79,   43,    7,   45,
   41,   84,   38,   38,  256,   43,   25,   45,   44,   44,
   43,  267,   45,  269,   59,    0,   41,   70,  109,  256,
  272,  125,   75,  260,  107,   40,   62,   62,   41,   41,
   43,  114,   45,   43,   70,   45,  109,   46,   74,  267,
  268,  269,    6,   79,   63,   41,   40,   44,   40,  277,
  258,   41,   40,   59,   42,   43,   43,   45,   45,   47,
   42,   43,   59,   45,   43,   47,   45,   44,  103,   41,
   40,   59,  256,   37,  123,   15,  269,   59,  114,  272,
   59,   40,   59,  224,   59,  269,  224,   58,  171,   43,
   40,   45,   43,   33,   45,   41,   41,   61,  123,   44,
  183,  137,   59,  139,  140,   40,   70,   71,   72,   41,
   74,  123,   41,   77,  125,   79,   80,   81,  125,  155,
   84,  157,  158,  159,  207,  258,  256,  123,  164,   69,
  166,  167,   41,   73,  123,   44,  172,  172,  229,   40,
  125,  258,   43,  107,   45,  267,  268,  111,  112,  113,
  114,  187,  188,  189,  227,  125,  229,  267,  268,   60,
   61,   62,  272,  254,   40,  201,  125,  203,  204,  264,
  265,  207,  256,  209,  138,  259,  260,  123,   41,   41,
  253,  254,   44,   40,  210,  149,   43,  213,   45,  225,
  125,  271,  156,  123,  123,  273,  274,  275,  123,   43,
  236,   45,  275,   60,   61,   62,  269,  171,   59,  258,
  246,  247,  248,  249,  250,  269,  289,  300,  269,  183,
   40,  267,  123,   43,  125,   45,  267,  268,  269,  267,
  268,  269,  172,  258,  267,  268,  277,  310,  202,  277,
   60,   61,   62,  207,  317,  209,  258,  282,   40,  125,
  256,   43,  279,   45,  267,  268,  269,  267,  268,  269,
  123,  225,  258,  269,  300,  301,  123,  277,   60,   61,
   62,   41,  236,   40,  264,  265,  312,  125,  314,  269,
  267,  268,  269,  269,   40,  256,  256,  257,  267,  268,
  277,  261,  279,  263,  267,  268,  260,  256,  257,  269,
  270,  272,  261,  123,  263,   89,   90,  271,  278,  279,
  269,  270,  258,  267,  268,  269,  267,  268,  269,  278,
  279,  256,  257,  277,  108,  125,  261,   41,  263,  258,
   41,  123,  264,  265,  269,  270,  300,  269,   53,   54,
  267,  268,  269,  278,  279,   41,   41,   41,  269,   40,
  277,   59,   43,  123,   45,  256,  257,   59,  125,  269,
  261,  262,  263,  264,  265,  266,  267,  268,  269,   60,
   61,   62,  273,  274,  275,  276,  277,   43,  279,   45,
  256,  257,  267,  268,  269,  261,  269,  263,  258,  173,
  174,  271,  277,  269,  270,  258,   43,   41,   45,  256,
  257,  259,  278,  279,  261,  262,  263,  264,  265,  266,
  267,  268,  269,   60,   61,   62,  273,  274,  275,  276,
  277,   40,  279,  267,  268,   58,  269,  123,  123,  123,
   40,  272,  123,   43,   59,   45,  256,  257,  264,  265,
  125,  261,  262,  269,  264,  265,  266,  267,  268,  269,
   60,   61,   62,  273,  274,  275,  276,  277,  125,  279,
   58,   43,  125,   45,  256,  257,   58,   24,  125,  261,
  262,   59,  264,  265,  266,  267,  268,  269,   60,   61,
   62,  273,  274,  275,  276,  277,   58,  279,  258,  256,
  257,   59,   42,   43,  261,   45,  263,   47,  269,   58,
  256,  257,  269,  270,  123,  261,  269,  263,   59,   59,
  125,  278,  279,  269,  270,   59,   40,   59,   42,   43,
   44,   45,  278,   47,   40,   59,   42,   43,   44,   45,
   87,   47,   89,   90,   58,   41,   60,   61,   62,  267,
  268,  269,   41,   41,   60,   61,   62,   40,   59,   42,
   43,  108,   45,   40,   47,   42,   43,   59,   45,   59,
   47,   58,  258,  258,  258,  256,  257,   60,   61,   62,
  261,  262,  263,  264,  265,  266,  267,  268,  269,  267,
  268,  269,  273,  274,  275,  276,  277,   42,   43,   59,
   45,   59,   47,   58,   42,   43,  267,   45,   41,   47,
   41,  267,  268,  269,    0,   60,   61,   62,  267,  256,
   59,  277,   60,   61,   62,   24,  173,  174,  125,  120,
  267,  268,  269,   59,  271,  126,  273,  274,  275,  125,
  277,   42,   43,  123,   45,  123,   47,  256,  257,  125,
   49,  125,  261,  258,  259,  260,  256,  123,   59,   59,
  269,    4,  267,  268,  269,  163,  123,  267,  268,  269,
  267,  268,  269,  273,  274,  275,  236,  277,  267,  268,
  269,   24,  217,   -1,  256,   -1,   -1,   30,   87,   32,
   89,   90,  267,  268,  269,  267,  268,  269,  267,  268,
  269,  273,  274,  275,   -1,  277,  267,  268,  269,  108,
   40,   41,   42,   43,   -1,   45,   -1,   47,   -1,  210,
   -1,  120,  213,   -1,   41,   42,   43,  126,   45,   -1,
   47,   -1,   -1,  224,   41,   42,   43,   -1,   45,   -1,
   47,   -1,  256,   42,   43,   -1,   45,   90,   47,   -1,
  256,   42,   43,   -1,   45,  269,   47,   -1,  272,  273,
  274,  275,   -1,  269,   -1,  108,  272,  273,  274,  275,
   -1,   -1,  115,   -1,  173,  174,   41,   42,   43,   -1,
   45,   -1,   47,  258,  259,  260,   -1,   -1,   -1,   -1,
  273,  274,  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  210,   -1,   -1,  213,   -1,   -1,   -1,  217,   -1,
   -1,   -1,   -1,   -1,   -1,  224,   -1,   -1,  273,  274,
  275,   -1,   -1,   -1,   -1,  273,  274,  275,
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
"HeaderWhen : When Condicion THEN",
"HeaderWhen : Condicion THEN",
"HeaderWhen : When Condicion",
"When : WHEN",
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

//#line 513 "Gramatica.y"

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

public static boolean dentroDeWhen=false;
public static boolean dentroDeFor=false;
public static boolean esperandoBreakcte=false;
public static boolean dentroDeFun=false;
public static String funcionActual;
public static String refEtiqueta = null;
public static String tipoActual = "";
public static String tipoAnterior = "";
public static String marcaWhen= "";
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

	public boolean mismoTipoCtes(String val1, String val2){
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

	public boolean mismoTipoIDCte(String val1, String val2){
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

	public boolean mismoTipoExpID(String val){
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

	public boolean conversionValida(){
		if (tipoActual.equals("I8")){
			tipoAnterior = "F32";
			tipoActual = "F32";
			return true;
		}
		else return false;
	}

	/*public boolean condicionFalsaWhen(){
		String i = marcaWhen.subString(1,marcaWhen.length()-1);
		int inicio = Integer.parse(i);
		int fin = Integer.parse(estructuraActual.getRefTerceto(1).subString(1, estructuraActual.getRefTerceto(1).length()-1));
		int rdo1 = 0;
		int rdo2 = 0;
		for (i = inicio; i < finn ; i++){

		}*/
	
//#line 911 "Parser.java"
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
{ tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "I8", val_peek(0).sval));}
break;
case 22:
//#line 69 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "I8", val_peek(1).sval+val_peek(0).sval));}
break;
case 23:
//#line 70 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito, 269, "constante", "F32", val_peek(0).sval));}
break;
case 24:
//#line 71 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(3).sval+":"+ambito, 269, "constante", "F32", val_peek(1).sval+val_peek(0).sval));}
break;
case 25:
//#line 73 "Gramatica.y"
{AgregarErrorSintactico("Se espera un identificador");}
break;
case 26:
//#line 74 "Gramatica.y"
{AgregarErrorSintactico("Se espera una constante ");}
break;
case 27:
//#line 75 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:' ");}
break;
case 28:
//#line 78 "Gramatica.y"
{estructuraActual.setTipo("I8");}
break;
case 29:
//#line 79 "Gramatica.y"
{estructuraActual.setTipo("F32");}
break;
case 30:
//#line 82 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(2).sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
break;
case 31:
//#line 83 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
break;
case 32:
//#line 84 "Gramatica.y"
{AgregarErrorSintactico("Se espera ',' ");}
break;
case 33:
//#line 87 "Gramatica.y"
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
//#line 95 "Gramatica.y"
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
//#line 103 "Gramatica.y"
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
//#line 112 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 37:
//#line 113 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 38:
//#line 114 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 39:
//#line 115 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 40:
//#line 116 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 41:
//#line 117 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 42:
//#line 120 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(val_peek(0).sval+":"+ambito, 269, "identificador_funcion"));
			 funcionActual=val_peek(0).sval;
			 estructuraActual=new EstructuraTercetos(val_peek(0).sval+":"+ambito);
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 dentroDeFun=true;
			 }
break;
case 43:
//#line 129 "Gramatica.y"
{desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   dentroDeFun=false;
									   }
break;
case 44:
//#line 134 "Gramatica.y"
{AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
break;
case 45:
//#line 135 "Gramatica.y"
{AgregarErrorSintactico("Se espera el header de la funcion");}
break;
case 46:
//#line 136 "Gramatica.y"
{AgregarErrorSintactico("Se espera el retorno de la funcion");}
break;
case 47:
//#line 140 "Gramatica.y"
{Simbolo simbolo= new Simbolo(val_peek(0).sval+":"+ambito+":"+funcionActual,269,"parametro", estructuraActual.getTipo());
					tablaDeSimbolos.add(simbolo);
					parametros.add(simbolo);}
break;
case 48:
//#line 143 "Gramatica.y"
{AgregarErrorSintactico("Se espera tipo de parametro");}
break;
case 55:
//#line 156 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(3).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
								estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 56:
//#line 159 "Gramatica.y"
{if (!mismoTipoIds(val_peek(3).sval, val_peek(1).sval)) 
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
							estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(1).sval, ambito));}
break;
case 57:
//#line 162 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval))
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval);}
break;
case 58:
//#line 165 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(4).sval, val_peek(1).sval))
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
									estructuraActual.crearTerceto(val_peek(3).sval, tablaDeSimbolos.getRefSimbolo(val_peek(4).sval, ambito), val_peek(2).sval + val_peek(1).sval);}
break;
case 59:
//#line 168 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 60:
//#line 170 "Gramatica.y"
{/*estructuraActual.crearTercetoWhen(new Terceto("BF", estructuraActual.getRefTerceto(1)), null);*/}
break;
case 61:
//#line 171 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
//#line 172 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 63:
//#line 175 "Gramatica.y"
{dentroDeWhen=true; marcaWhen = estructuraActual.getRefTerceto(1);}
break;
case 64:
//#line 177 "Gramatica.y"
{/*estructuraActual.completarTercetoWhen(1);
												  if(condicionFalsaWhen)
												       estructuraActual.eliminarTercetosWhen();
													dentroDeWhen=false;*/}
break;
case 65:
//#line 181 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 66:
//#line 185 "Gramatica.y"
{estructuraActual.crearTerceto("OUT", val_peek(1).sval, null);}
break;
case 67:
//#line 186 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 68:
//#line 190 "Gramatica.y"
{
					if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
					estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
					}
break;
case 69:
//#line 195 "Gramatica.y"
{
			if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 		errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
			estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);
			}
break;
case 70:
//#line 200 "Gramatica.y"
{
			if (!mismoTipoExpID(val_peek(0).sval))
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 71:
//#line 204 "Gramatica.y"
{
			if (!mismoTipoExpCte(val_peek(0).sval)) 
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);
			}
break;
case 72:
//#line 209 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval))
						 errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 73:
//#line 212 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 74:
//#line 213 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 75:
//#line 214 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 76:
//#line 215 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 77:
//#line 216 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 78:
//#line 217 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 79:
//#line 218 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 81:
//#line 220 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 82:
//#line 221 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 83:
//#line 222 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 84:
//#line 223 "Gramatica.y"
{String funcion=tablaDeSimbolos.getRefFuncion(val_peek(1).sval, ambito);
								if(funcion!=null)
		 						 	chequearYAsignarParametros(parametrosReales, parametrosFormales.get(funcion));
								estructuraActual.crearTerceto("LABEL"+funcion, null, null);
								}
break;
case 88:
//#line 235 "Gramatica.y"
{parametrosReales.add(val_peek(0).sval);}
break;
case 89:
//#line 236 "Gramatica.y"
{parametrosReales.add(val_peek(1).sval+val_peek(0).sval);}
break;
case 90:
//#line 237 "Gramatica.y"
{parametrosReales.add(tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 91:
//#line 240 "Gramatica.y"
{if (conversionValida()){
												estructuraActual.crearTerceto("tof32", estructuraActual.getRefTerceto(1), null);
											  }else errores_semanticos.add(new ErrorLinea("No se puede realizar la conversion", this.linea.getNumeroLinea()));}
break;
case 92:
//#line 243 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 96:
//#line 252 "Gramatica.y"
{chequearRangoEntero(val_peek(0).sval);}
break;
case 102:
//#line 262 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 105:
//#line 270 "Gramatica.y"
{estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();
			estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 107:
//#line 277 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 108:
//#line 280 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
									 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 109:
//#line 282 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
								  		 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 110:
//#line 285 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 111:
//#line 286 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 112:
//#line 287 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 113:
//#line 290 "Gramatica.y"
{estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
								estructuraActual.addTercetoIf();}
break;
case 114:
//#line 292 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 115:
//#line 293 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 122:
//#line 304 "Gramatica.y"
{if (!mismoTipo()) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(2), estructuraActual.getRefTerceto(1));}
break;
case 123:
//#line 305 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), val_peek(0).sval);}
break;
case 124:
//#line 306 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getRefTerceto(1), val_peek(1).sval + val_peek(0).sval );}
break;
case 125:
//#line 307 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, estructuraActual.getRefTerceto(1));}
break;
case 126:
//#line 308 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, estructuraActual.getRefTerceto(1));}
break;
case 127:
//#line 309 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 128:
//#line 310 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 129:
//#line 311 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 130:
//#line 312 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 131:
//#line 313 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 132:
//#line 314 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval + val_peek(0).sval);}
break;
case 133:
//#line 315 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 134:
//#line 316 "Gramatica.y"
{if (!mismoTipoCtes(val_peek(2).sval, val_peek(0).sval)) errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);}
break;
case 135:
//#line 317 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 136:
//#line 318 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 137:
//#line 319 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 144:
//#line 333 "Gramatica.y"
{estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 refEtiqueta = estructuraActual.getRefTerceto(2);
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 145:
//#line 342 "Gramatica.y"
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
case 146:
//#line 353 "Gramatica.y"
{estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval,ambito));
										}
break;
case 147:
//#line 359 "Gramatica.y"
{estructuraActual.addEtiquetaFor(val_peek(2).sval); estructuraActual.addRefEtiqueta(refEtiqueta);}
break;
case 148:
//#line 363 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), val_peek(0).sval);
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
	 	}
break;
case 149:
//#line 371 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
	 	}
break;
case 150:
//#line 381 "Gramatica.y"
{
					 estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.completarTercetoFor(1);
					 estructuraActual.popIdFor();}
break;
case 151:
//#line 386 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 152:
//#line 390 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
				}
break;
case 153:
//#line 396 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=false;
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
				}
break;
case 154:
//#line 402 "Gramatica.y"
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
case 155:
//#line 413 "Gramatica.y"
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
case 156:
//#line 427 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
								estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 157:
//#line 430 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
									   estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 158:
//#line 433 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);}
break;
case 159:
//#line 436 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval)) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											  estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval+val_peek(0).sval);}
break;
case 160:
//#line 441 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una funcion", this.linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro de una funcion");
					}
					else
						estructuraActual.crearTerceto("=:", ambito, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
				}
break;
case 161:
//#line 449 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else
						estructuraActual.crearTerceto("=:", ambito, val_peek(2).sval);
				}
break;
case 162:
//#line 457 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else
						estructuraActual.crearTerceto("=:", ambito, estructuraActual.getRefTerceto(1));
				}
break;
case 163:
//#line 465 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 164:
//#line 466 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 165:
//#line 467 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 166:
//#line 468 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 168:
//#line 472 "Gramatica.y"
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
//#line 482 "Gramatica.y"
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
//#line 493 "Gramatica.y"
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
//#line 504 "Gramatica.y"
{
				if ((dentroDeFor) && (estructuraActual.existeEtiquetaFor(val_peek(1).sval))){
					estructuraActual.crearTerceto("BI", estructuraActual.getRefEtiqueta(val_peek(1).sval), null);
				}
			  }
break;
//#line 1761 "Parser.java"
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
