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
   31,   31,   32,   32,   32,   33,   34,   34,   19,   19,
   19,   19,   19,   25,   25,   25,   36,   36,   36,   36,
   36,   36,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,   35,   35,   35,   35,   35,   35,   37,   37,
   37,   37,   37,   37,   38,   38,   41,   42,   43,   39,
   39,   44,   44,    3,    3,    3,    3,   40,   40,   40,
   40,   18,   18,   18,   18,   18,   18,   18,   30,   30,
   30,   30,   30,
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
    3,    2,    1,    1,    3,    1,    3,    3,    3,    3,
    3,    2,    2,    3,    2,    2,    1,    1,    1,    1,
    1,    1,    3,    3,    4,    3,    4,    3,    3,    3,
    4,    3,    4,    3,    3,    2,    2,    2,    2,    2,
    2,    1,    1,    1,    9,    8,    3,    2,    2,    3,
    3,    3,    3,    2,    2,    5,    6,    3,    3,    3,
    4,    5,    5,    5,    4,    4,    4,    2,    1,    2,
    3,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    5,    0,    0,    4,    0,    0,    0,    0,   28,
   29,   63,   96,   95,    0,  119,  120,  122,    0,    0,
    0,   93,   94,    0,    0,  117,  118,  121,    0,    0,
    0,    0,    0,    0,   14,   16,    0,    0,    0,    0,
   52,   53,    0,    0,    0,    0,    0,   80,    0,    0,
    0,    0,    0,    0,    0,  102,  112,  113,  137,   65,
  138,    0,    0,    0,    0,    0,   42,    0,    0,    0,
    0,    0,    0,    0,    0,  148,   32,    0,   84,    0,
    0,    0,    0,   19,    0,    0,    0,    0,    0,    0,
    0,    0,   51,    0,    0,    2,    6,    7,    8,    0,
   18,    0,   17,    0,    0,    0,    0,    0,    0,    0,
   54,    0,    0,    0,    0,    0,    0,   61,    0,    0,
    0,    0,    0,    0,  103,  169,  104,    0,  116,  136,
    0,  154,    0,  149,  155,  101,    0,   48,    0,    0,
    0,   59,    0,    0,    0,    0,  147,   72,   74,   73,
   75,   30,   90,   85,    0,   88,    0,   68,   69,    0,
    0,    0,    0,    0,   27,   25,   15,    0,    0,   92,
    0,    0,    0,    0,  168,    0,    0,    0,    0,    1,
   50,   49,   45,   67,  114,   13,   12,    0,   82,   83,
   81,    0,    0,    0,   44,    0,    0,    0,    0,   76,
   78,   77,   79,   70,   71,    0,    0,    0,    0,    0,
   60,    0,    0,  170,    0,    0,    0,    0,    0,  111,
  106,  109,    0,  110,    0,    0,  142,  143,  144,    0,
    0,   66,    0,   47,    0,    0,   56,    0,   57,   55,
   89,    0,   86,  133,   21,   23,    0,   20,   91,    0,
    0,    0,    0,    0,    0,    0,   46,   43,    0,    0,
    0,  125,   64,    0,    0,  171,   97,  105,   98,    0,
  153,  152,  140,  139,  141,    0,    0,   38,    0,    0,
   58,    0,   22,   24,  150,  151,    0,    0,    0,    0,
    0,   35,    0,    0,  173,  172,  108,  107,    0,  156,
    0,   37,   87,    0,    0,  162,  163,  164,    0,   34,
  157,    0,    0,    0,    0,    0,    0,    0,    0,   36,
  161,  146,    0,   33,  145,
};
final static short yydgoto[] = {                          3,
    4,   98,   30,   31,   32,   33,   34,   35,   83,   36,
   84,   37,   38,   39,   40,  141,   92,   93,   41,   42,
   43,   44,   45,   46,   47,   48,   79,  157,  218,  219,
   49,  128,  223,  224,   50,   51,  230,   52,  174,  288,
   53,   54,   55,  132,
};
final static short yysindex[] = {                      -203,
  -90,    0,    0,  169,    0,  336,  -21,  -30,  -24,    0,
    0,    0,    0,    0,  529,    0,    0,    0, -198,   15,
   40,    0,    0,  145,  355,    0,    0,    0,  -82,  217,
  217,  217,  -45,   34,    0,    0, -126,  591,  -18,   83,
    0,    0,   58,  597,   48,  -21,  -83,    0,  194,   95,
  -43,   72,   72,  -59,   72,    0,    0,    0,    0,    0,
    0,  160,   36,  557,  -40,  -48,    0,   77,  196,  -33,
   19,  172,  412,   -2,   45,    0,    0,  483,    0,   16,
  284, -126,  -23,    0,    3,    4,   -8,  409,  136,  145,
  145,  179,    0,  253,  288,    0,    0,    0,    0,  285,
    0,  286,    0,  445,   78,   91,   94,  272,   59,  111,
    0,  522,  527,  547,   90,  217,   73,    0,  454, -209,
  292,  -42,  297,  112,    0,    0,    0,  354,    0,    0,
  265,    0,  106,    0,    0,    0,  313,    0,  314,  120,
   51,    0,   30, -126,  657,  679,    0,    0,    0,    0,
    0,    0,    0,    0, -126,    0,   62,    0,    0,  724,
 -126,   67,  470,   39,    0,    0,    0, -198,  724,    0,
   67,  698,  119,  337,    0,  378,  537,  409,  409,    0,
    0,    0,    0,    0,    0,    0,    0,  438,    0,    0,
    0,  724,   67,  470,    0,  274,  280,  339,  148,    0,
    0,    0,    0,    0,    0,  724, -126,   67,  470,  298,
    0,  110,  178,    0, -126,  394,  297,  333,  297,    0,
    0,    0,  194,    0,   10,  401,    0,    0,    0,  268,
  101,    0,  -78,    0,  299,  404,    0,  406,    0,    0,
    0,  220,    0,    0,    0,    0,  -87,    0,    0, -200,
  206,  714,  731,  741,  724,  470,    0,    0,  -78,  299,
  422,    0,    0,  436,  440,    0,    0,    0,    0, -222,
    0,    0,    0,    0,    0, -126,  450,    0,  462,  -78,
    0,  475,    0,    0,    0,    0,  445,  464,  471,  474,
  477,    0,  501,  -78,    0,    0,    0,    0,  486,    0,
  492,    0,    0,  199,  -39,    0,    0,    0,  499,    0,
    0,  -78,    0,  724,  294,  470,  518,  295,  -78,    0,
    0,    0,  524,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  511,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  578,   12,
   13,   33,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  511,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  458,
  460,    0,    0,    0, -115,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  463,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -36,
    0,    1,   11,  -14,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  511,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   17,   44,   60,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   81,    0,   85,   98,    0,
    0,    0,    0,    0,    0,    0,  467,    0,  468,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  174,  250,    0,    0,  465,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   92,  125,
  241,    0,    0,  484,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  690,  549,    0,  553,    0,    0,  493,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  637, -122,  601,  639,  -61,  444,    0,    0,    0,
  459,  -15,   88,    0,    0,  -84,  -63,  478,    0,    0,
    0,  535,    0,    0,    8,    0,    0,  389, -106,  325,
    0,  414,    0,    0,   -7,  -13,    0,  -34,    0,    0,
    0,    0,    0,   74,
};
final static int YYTABLESIZE=872;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
   22,   80,   23,   22,  134,   23,  140,  115,  227,   66,
   74,   10,   11,  101,   65,   68,  214,   95,   64,  134,
  168,   22,  107,   23,  108,  199,  181,  182,  114,   26,
  115,  176,    9,  297,    5,  167,  147,  298,   26,   28,
   27,  132,   96,  170,   26,  197,   69,   78,  140,   80,
  175,  129,    1,  117,   85,  144,   95,  130,   22,  155,
   23,   22,  212,   23,  161,    2,  285,   76,  286,   75,
   81,   72,   22,   82,   23,   75,   73,   72,   22,   86,
   23,   22,   73,   23,  135,  154,  134,   22,  237,   23,
  188,  236,  103,   61,  235,   26,   28,   27,   25,  207,
  126,   22,  243,   23,  109,  242,  215,  273,  105,   22,
  267,   23,  269,  106,  181,  182,  111,  139,   26,   28,
   27,  128,  110,  132,  104,  124,  133,   78,  135,  107,
  114,  165,   22,  129,   23,  129,   10,   11,  123,  130,
   13,   14,  115,   22,   78,   23,  107,  114,  247,   61,
  279,  198,   22,   78,   23,  107,  114,    9,  145,  149,
  151,   78,  156,   80,  166,  159,  135,  162,  165,  166,
  116,  278,  171,  140,  118,  293,   78,  107,  114,  283,
  284,   88,  126,  195,   25,   10,   11,   22,  261,   23,
   78,  260,  107,  114,  131,  193,  144,  292,  140,  201,
  203,  205,  208,  128,   26,   28,   27,  124,   25,  216,
  100,   22,  130,   23,  131,  276,  165,  136,  302,   21,
  123,  134,  137,   70,   13,   14,  155,  317,   26,   28,
   27,  238,  310,  124,   62,   70,   78,  107,  114,   78,
  114,   22,  241,   23,   67,   13,   14,   63,  244,  166,
  320,   16,   17,   18,  142,   20,   25,  324,  132,   22,
  180,   23,   22,  253,   23,   69,   70,   88,  129,   13,
   14,  169,  173,  304,  130,  171,   26,   28,   27,   20,
  167,   71,   13,   14,  160,   13,   14,  143,  315,  318,
  127,   24,   20,  184,  262,   20,  131,   21,   78,  145,
  114,  135,  265,  183,  124,  245,  246,  124,   16,   17,
   18,   13,   14,  153,    6,    7,  123,  126,  277,    8,
    9,   87,   10,   11,   12,   13,   14,   15,  185,  156,
  211,   16,   17,   18,   19,   20,  124,   21,  128,   88,
   10,   11,  124,  186,  187,  138,  189,  165,  165,  213,
  165,  165,  165,  232,  165,  123,   13,   14,  206,  190,
  165,  165,  191,  299,  231,  167,   20,   13,   14,  165,
  165,  233,  127,  127,   10,   11,   13,   14,  143,  138,
  166,  166,   94,  166,  166,  166,   20,  166,  234,  226,
  250,  171,  272,  166,  166,  251,  259,   22,  257,   23,
    6,    7,  166,  166,  258,    8,    9,   87,   10,   11,
   12,   13,   14,   15,   26,   28,   27,   16,   17,   18,
   19,   20,  263,   21,    6,    7,   13,   14,   59,    8,
    9,  131,   10,   11,   12,   13,   14,   15,   13,   14,
  148,   16,   17,   18,   19,   20,  264,   21,   25,  119,
    7,   22,  266,   23,    8,  229,   87,  268,   77,  271,
   60,  280,  120,  121,  281,  313,   14,  314,   26,   28,
   27,  122,    6,    7,  287,   20,  102,    8,    9,  294,
   10,   11,   12,   13,   14,   15,   13,   14,  153,   16,
   17,   18,   19,   20,  295,   21,  167,  167,  296,  167,
  167,  167,  301,  167,   26,   28,   27,  127,  300,  167,
  167,  112,   22,   77,   23,  303,  113,  152,  167,  167,
  119,    7,  305,  119,    7,    8,  126,   87,    8,  306,
   87,   88,  307,  225,  121,  308,  225,  121,   13,   14,
  192,  309,  122,   21,  311,  122,   21,  127,   20,  312,
   13,   14,  119,    7,  275,  164,  319,    8,  322,   87,
  321,  323,   10,   11,  325,  120,  121,  138,   75,   31,
   72,   22,   74,   23,  122,   73,   75,    3,   72,   22,
   74,   23,   11,   73,    9,   62,   76,   41,   26,   28,
   27,   99,  100,   56,   57,   58,   26,   28,   27,   22,
  126,   23,   13,   14,   59,  146,   40,  158,  126,  220,
   62,  159,  221,  222,  163,   39,   26,   28,   27,  172,
   77,   13,   14,   63,   90,   94,  248,   16,   17,   18,
  282,   20,  105,   22,    0,   23,  270,  106,  112,   22,
   29,   23,  194,  113,   13,   14,  252,    0,    0,  209,
   26,   28,   27,    0,   20,    0,   26,   28,   27,    0,
   89,    0,   91,    0,    6,    7,   97,    0,   99,    8,
    9,   87,   10,   11,   12,   13,   14,  177,   13,   14,
  150,   16,   17,   18,   19,   20,    0,  125,  178,    0,
   90,   90,    0,    0,  126,    0,  126,    0,  105,   22,
  126,   23,    0,  106,   13,   14,  255,  126,    0,   90,
  254,   56,   57,   58,   20,  239,    0,   16,   17,   18,
  112,   22,  256,   23,    0,  113,  179,   99,   91,   91,
    0,   96,   96,    0,   96,    0,   96,  240,  249,  112,
   22,    0,   23,    0,  113,  196,  146,   91,  160,   13,
   14,  158,  210,   75,  289,   72,   22,    0,   23,    0,
   73,  217,    0,   75,    0,   72,   22,    0,   23,  228,
   73,  290,  105,   22,    0,   23,    0,  106,  178,  178,
    0,  291,  112,   22,   69,   23,    0,  113,   13,   14,
  200,    0,   69,   13,   14,  202,    0,   70,    0,    0,
   71,   16,   17,   18,    0,   70,    0,    0,  212,   16,
   17,   18,   62,   13,   14,  204,  179,  179,    0,    0,
    0,    0,    0,   13,   14,   63,    0,    0,    0,   16,
   17,   18,    0,   20,    0,    0,    0,    0,  316,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  217,    0,  217,    0,    0,
    0,  125,    0,   16,   17,   18,    0,    0,  274,   16,
   17,   18,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         15,
   43,   15,   45,   43,   41,   45,   68,  123,  131,   40,
   44,    0,    0,   59,    7,   40,   59,   25,   40,   54,
   44,   43,   38,   45,   38,  110,   90,   91,   44,   44,
   44,   40,    0,  256,  125,   59,   71,  260,   60,   61,
   62,   41,  125,   41,   59,  109,  256,   63,  110,   63,
   59,   41,  256,   46,   40,   71,   64,   41,   43,   75,
   45,   43,  272,   45,   80,  269,  267,   58,  269,   40,
  269,   42,   43,  272,   45,   40,   47,   42,   43,   40,
   45,   43,   47,   45,   41,   41,  123,   43,   59,   45,
  104,   41,   59,    6,   44,   60,   61,   62,   40,  115,
   41,   43,   41,   45,  123,   44,  122,  230,   42,   43,
  217,   45,  219,   47,  178,  179,   59,   41,   60,   61,
   62,   41,   40,  123,   37,   41,   53,  143,   55,  145,
  146,   40,   43,  123,   45,   41,  125,  125,   41,  123,
  267,  268,  258,   43,  160,   45,  162,  163,  164,   62,
  235,   41,   43,  169,   45,  171,  172,  125,   71,   72,
   73,  177,   75,  177,   40,   78,  123,   80,   81,   82,
  123,  233,   85,  235,  258,  260,  192,  193,  194,  267,
  268,  123,  123,  125,   40,  264,  265,   43,   41,   45,
  206,   44,  208,  209,  123,  108,  212,  259,  260,  112,
  113,  114,  115,  123,   60,   61,   62,  123,   40,  122,
  256,   43,  256,   45,   41,  231,  125,  258,  280,  279,
  123,  258,  271,  269,  267,  268,  242,  267,   60,   61,
   62,  144,  294,   40,  256,  269,  252,  253,  254,  255,
  256,   43,  155,   45,  269,  267,  268,  269,  161,  125,
  312,  273,  274,  275,   59,  277,   40,  319,  258,   43,
  125,   45,   43,  176,   45,  256,  269,  123,  258,  267,
  268,  269,  269,  287,  258,  188,   60,   61,   62,  277,
   40,  272,  267,  268,  269,  267,  268,  269,  304,  305,
   41,  123,  277,   41,  207,  277,  123,  279,  314,  212,
  316,  258,  215,  125,   40,  267,  268,   40,  273,  274,
  275,  267,  268,  269,  256,  257,  123,  258,  231,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   41,  242,
  258,  273,  274,  275,  276,  277,   40,  279,  258,  123,
  264,  265,  258,   59,   59,  269,  269,  256,  257,   58,
  259,  260,  261,   41,  263,  258,  267,  268,  269,  269,
  269,  270,  269,  276,  259,  125,  277,  267,  268,  278,
  279,   58,  123,   49,  264,  265,  267,  268,  269,  269,
  256,  257,  271,  259,  260,  261,  277,  263,  269,  125,
  272,  304,  125,  269,  270,   59,   58,   43,  125,   45,
  256,  257,  278,  279,  125,  261,  262,  263,  264,  265,
  266,  267,  268,  269,   60,   61,   62,  273,  274,  275,
  276,  277,  125,  279,  256,  257,  267,  268,  269,  261,
  262,  258,  264,  265,  266,  267,  268,  269,  267,  268,
  269,  273,  274,  275,  276,  277,  269,  279,   40,  256,
  257,   43,   59,   45,  261,  131,  263,  125,   15,   59,
  125,   58,  269,  270,   59,  267,  268,  269,   60,   61,
   62,  278,  256,  257,  269,  277,   33,  261,  262,   58,
  264,  265,  266,  267,  268,  269,  267,  268,  269,  273,
  274,  275,  276,  277,   59,  279,  256,  257,   59,  259,
  260,  261,   41,  263,   60,   61,   62,  258,   59,  269,
  270,   42,   43,   70,   45,   41,   47,   74,  278,  279,
  256,  257,   59,  256,  257,  261,   49,  263,  261,   59,
  263,  123,   59,  269,  270,   59,  269,  270,  267,  268,
  269,   41,  278,  279,   59,  278,  279,  223,  277,   58,
  267,  268,  256,  257,  230,  272,   58,  261,   41,  263,
  267,  267,  264,  265,   41,  269,  270,  269,   40,   59,
   42,   43,   44,   45,  278,   47,   40,    0,   42,   43,
   44,   45,  125,   47,  125,  123,   58,  123,   60,   61,
   62,  125,  125,  258,  259,  260,   60,   61,   62,   43,
  123,   45,  267,  268,  269,   71,  123,   59,  131,  256,
  256,   59,  259,  260,   80,  123,   60,   61,   62,   85,
  177,  267,  268,  269,   24,  271,  168,  273,  274,  275,
  242,  277,   42,   43,   -1,   45,  223,   47,   42,   43,
    4,   45,  108,   47,  267,  268,  269,   -1,   -1,  115,
   60,   61,   62,   -1,  277,   -1,   60,   61,   62,   -1,
   24,   -1,   24,   -1,  256,  257,   30,   -1,   32,  261,
  262,  263,  264,  265,  266,  267,  268,  269,  267,  268,
  269,  273,  274,  275,  276,  277,   -1,   49,   88,   -1,
   90,   91,   -1,   -1,  217,   -1,  219,   -1,   42,   43,
  223,   45,   -1,   47,  267,  268,  269,  230,   -1,  109,
  176,  258,  259,  260,  277,   59,   -1,  273,  274,  275,
   42,   43,  188,   45,   -1,   47,   88,   91,   90,   91,
   -1,   42,   43,   -1,   45,   -1,   47,   59,   41,   42,
   43,   -1,   45,   -1,   47,  109,  212,  109,   59,  267,
  268,  269,  116,   40,   41,   42,   43,   -1,   45,   -1,
   47,  123,   -1,   40,   -1,   42,   43,   -1,   45,  131,
   47,   41,   42,   43,   -1,   45,   -1,   47,  178,  179,
   -1,   41,   42,   43,  256,   45,   -1,   47,  267,  268,
  269,   -1,  256,  267,  268,  269,   -1,  269,   -1,   -1,
  272,  273,  274,  275,   -1,  269,   -1,   -1,  272,  273,
  274,  275,  256,  267,  268,  269,  178,  179,   -1,   -1,
   -1,   -1,   -1,  267,  268,  269,   -1,   -1,   -1,  273,
  274,  275,   -1,  277,   -1,   -1,   -1,   -1,  304,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  217,   -1,  219,   -1,   -1,
   -1,  223,   -1,  273,  274,  275,   -1,   -1,  230,  273,
  274,  275,
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

//#line 816 "Gramatica.y"

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

	
//#line 1070 "Parser.java"
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
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
								if(tablaDeSimbolos.getUso(lex).equals("constante"))
									errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
								if (!mismoTipoExpID(val_peek(3).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
								estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval);
								}
break;
case 56:
//#line 164 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
							if (!mismoTipoIds(val_peek(3).sval, val_peek(1).sval)) 
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, lex, tablaDeSimbolos.getRefSimbolo(val_peek(1).sval, ambito));}
break;
case 57:
//#line 171 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
							if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval))
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval);}
break;
case 58:
//#line 178 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(val_peek(4).sval, ambito);
									if(tablaDeSimbolos.getUso(lex).equals("constante"))
										errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
									if (!mismoTipoIDCte(val_peek(4).sval, val_peek(1).sval))
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
									tablaDeSimbolos.setLexema(val_peek(2).sval, val_peek(1).sval);
									estructuraActual.crearTerceto(val_peek(3).sval, lex, val_peek(2).sval + val_peek(1).sval);}
break;
case 59:
//#line 186 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 60:
//#line 188 "Gramatica.y"
{chequearCondicionWhen();
								}
break;
case 61:
//#line 191 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
//#line 192 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 63:
//#line 195 "Gramatica.y"
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
//#line 204 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
												estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
												dentroDeWhen=false; condicionWhenFalse=false;
												}
break;
case 65:
//#line 208 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 66:
//#line 212 "Gramatica.y"
{estructuraActual.crearTerceto("OUT", val_peek(1).sval, null);}
break;
case 67:
//#line 213 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 68:
//#line 217 "Gramatica.y"
{
					if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 231 "Gramatica.y"
{
			if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 		errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			
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
//#line 244 "Gramatica.y"
{
			if (!mismoTipoExpID(val_peek(0).sval))
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
			valores.add(lex);
			
			estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			listOperadores.add(val_peek(1).sval);
			}
break;
case 71:
//#line 256 "Gramatica.y"
{
			if (!mismoTipoExpCte(val_peek(0).sval)) 
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add(val_peek(0).sval);
			listOperadores.add(val_peek(1).sval);
			}
break;
case 72:
//#line 265 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval))
						 errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 278 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
		 				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 290 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 300 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 311 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(val_peek(1).sval);
							}
break;
case 77:
//#line 321 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex= tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(val_peek(1).sval);
							}
break;
case 78:
//#line 331 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add(val_peek(0).sval);
							listOperadores.add(val_peek(1).sval);
							}
break;
case 79:
//#line 339 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add(val_peek(0).sval);
							listOperadores.add(val_peek(1).sval);
							}
break;
case 81:
//#line 350 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 361 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 371 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
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
//#line 382 "Gramatica.y"
{String funcion=tablaDeSimbolos.getRefFuncion(val_peek(1).sval, ambito);
								if(funcion!=null){
		 						 	chequearYAsignarParametros(parametrosReales, parametrosFormales.get(funcion));
									
								}
								estructuraActual.crearTerceto("BI", funcion, null);
								estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);

								yyval.sval=funcion;
								valores.add(funcion);
								parametrosReales.clear();
								}
break;
case 88:
//#line 401 "Gramatica.y"
{parametrosReales.add(val_peek(0).sval);}
break;
case 89:
//#line 402 "Gramatica.y"
{parametrosReales.add(val_peek(1).sval+val_peek(0).sval);}
break;
case 90:
//#line 403 "Gramatica.y"
{parametrosReales.add(tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));}
break;
case 91:
//#line 406 "Gramatica.y"
{if (conversionValida()){
												estructuraActual.crearTerceto("tof32", val_peek(1).sval, null);
											  }
											  	else errores_semanticos.add(new ErrorLinea("No se puede realizar la conversion", this.linea.getNumeroLinea()));
											  yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
break;
case 92:
//#line 412 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 96:
//#line 421 "Gramatica.y"
{chequearRangoEntero(val_peek(0).sval);}
break;
case 102:
//#line 431 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 106:
//#line 440 "Gramatica.y"
{estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();
			estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 108:
//#line 447 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 109:
//#line 450 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
									 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 110:
//#line 452 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
								  		 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 111:
//#line 455 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 112:
//#line 456 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 113:
//#line 457 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 114:
//#line 460 "Gramatica.y"
{estructuraActual.crearTerceto("BF", val_peek(1).sval, null);
								estructuraActual.addTercetoIf();
								estructuraActual.addTercetoWhen();
								}
break;
case 115:
//#line 464 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 116:
//#line 465 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 117:
//#line 468 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval; valores=valores2;}
break;
case 118:
//#line 469 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval; valores=valores2;}
break;
case 119:
//#line 470 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 120:
//#line 471 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 121:
//#line 472 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 122:
//#line 473 "Gramatica.y"
{listOperadores=listOperadores2; comparador=val_peek(0).sval;valores=valores2;}
break;
case 123:
//#line 476 "Gramatica.y"
{if (!mismoTipo()) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
												estructuraActual.addTercetoWhen();
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											
											}
break;
case 124:
//#line 484 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										
											estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
											estructuraActual.addTercetoWhen();
											valores2.add(val_peek(0).sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										
										}
break;
case 125:
//#line 493 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(0).sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(val_peek(2).sval, val_peek(3).sval, val_peek(1).sval + val_peek(0).sval );
												
												estructuraActual.addTercetoWhen();
												valores2.add(val_peek(1).sval+val_peek(0).sval);
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
											}
break;
case 126:
//#line 503 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   								
											estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
											estructuraActual.addTercetoWhen();
											valores1.add(val_peek(2).sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 127:
//#line 511 "Gramatica.y"
{if (!mismoTipoExpCte(val_peek(2).sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, val_peek(0).sval);
												estructuraActual.addTercetoWhen();
												valores1.add(val_peek(3).sval+val_peek(2).sval);
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema(val_peek(3).sval, val_peek(2).sval);
											}
break;
case 128:
//#line 520 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(0).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   								String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
										estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
										estructuraActual.addTercetoWhen();
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 129:
//#line 528 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

										String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
										estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 130:
//#line 537 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
									estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, lex);
									estructuraActual.addTercetoWhen();
									valores1.add(val_peek(2).sval);
									valores2.add(lex);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 131:
//#line 547 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(0).sval, val_peek(2).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
										estructuraActual.crearTerceto(val_peek(1).sval, val_peek(3).sval + val_peek(2).sval, lex);
										estructuraActual.addTercetoWhen();
										valores1.add(val_peek(3).sval +val_peek(2).sval);
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(val_peek(3).sval, val_peek(2).sval);
									}
break;
case 132:
//#line 558 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
									estructuraActual.crearTerceto(val_peek(1).sval, lex, val_peek(0).sval);
									estructuraActual.addTercetoWhen();
									valores1.add(val_peek(0).sval);
									valores2.add(lex);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 133:
//#line 568 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(0).sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito);
										estructuraActual.crearTerceto(val_peek(2).sval, lex, val_peek(1).sval + val_peek(0).sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										valores2.add(val_peek(1).sval+val_peek(0).sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
									}
break;
case 134:
//#line 579 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   						
									String lex1=tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito);
									String lex2=tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito);
									estructuraActual.crearTerceto(val_peek(1).sval, lex1, lex2);
									estructuraActual.addTercetoWhen();
									
									valores1.add(lex1);
									valores2.add(lex2);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 135:
//#line 591 "Gramatica.y"
{if (!mismoTipoCtes(val_peek(2).sval, val_peek(0).sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							estructuraActual.crearTerceto(val_peek(1).sval, val_peek(2).sval, val_peek(0).sval);
									estructuraActual.addTercetoWhen();
									valores1.add(val_peek(2).sval);
									valores2.add(val_peek(0).sval);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 136:
//#line 599 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 137:
//#line 600 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 138:
//#line 601 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 145:
//#line 615 "Gramatica.y"
{estructuraActual.crearTerceto("BF", val_peek(4).sval, null);
				 refEtiqueta = val_peek(6).sval;
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto(val_peek(2).sval, estructuraActual.getIdFor(), val_peek(1).sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 146:
//#line 624 "Gramatica.y"
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
case 147:
//#line 635 "Gramatica.y"
{estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval,ambito));
										}
break;
case 148:
//#line 641 "Gramatica.y"
{estructuraActual.addEtiquetaFor(val_peek(1).sval);}
break;
case 149:
//#line 644 "Gramatica.y"
{estructuraActual.addRefEtiqueta(refEtiqueta);}
break;
case 150:
//#line 648 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), val_peek(0).sval);
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 151:
//#line 657 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
		estructuraActual.crearTerceto(val_peek(1).sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 152:
//#line 668 "Gramatica.y"
{estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.completarTercetoFor(1);
					 estructuraActual.completarTercetosBreak(1);
					 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
					 estructuraActual.popIdFor();

					 estructuraActual.borrarListTercetosBreak();
					 dentroDeFor=!estructuraActual.vaciaListTercetoBreak();
					 }
break;
case 153:
//#line 678 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 155:
//#line 683 "Gramatica.y"
{estructuraActual.eliminarEtiqueta();}
break;
case 156:
//#line 684 "Gramatica.y"
{
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);

					estructuraActual.borrarListTercetosBreakCte();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					}
break;
case 157:
//#line 695 "Gramatica.y"
{
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(2).sval + val_peek(1).sval);
					estructuraActual.completarTercetosBreakCte(1);
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);

					estructuraActual.borrarListTercetosBreakCte();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					tablaDeSimbolos.setLexema(val_peek(2).sval, val_peek(1).sval);
				}
break;
case 158:
//#line 709 "Gramatica.y"
{if (!mismoTipoIds(val_peek(2).sval, val_peek(0).sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
								estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(0).sval, ambito));
								yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 159:
//#line 715 "Gramatica.y"
{if (!mismoTipoExpID(val_peek(2).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
									   estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);
									   yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									   }
break;
case 160:
//#line 721 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(2).sval, val_peek(0).sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										estructuraActual.crearTerceto(val_peek(1).sval, tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito), val_peek(0).sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 161:
//#line 727 "Gramatica.y"
{if (!mismoTipoIDCte(val_peek(3).sval, val_peek(1).sval)) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											  tablaDeSimbolos.setLexema(val_peek(1).sval, val_peek(0).sval);
											  estructuraActual.crearTerceto(val_peek(2).sval, tablaDeSimbolos.getRefSimbolo(val_peek(3).sval, ambito), val_peek(1).sval+val_peek(0).sval);
											  yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
break;
case 162:
//#line 735 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una funcion", this.linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefFuncion(funcionActual, ambito), tablaDeSimbolos.getRefSimbolo(val_peek(2).sval, ambito));
						estructuraActual.crearTerceto("BI",null, null);
					}	
						
				}
break;
case 163:
//#line 746 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro del cuerpo de una funcion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefFuncion(funcionActual, ambito), val_peek(2).sval);
						estructuraActual.crearTerceto("BI",null, null);
					}	
				}
break;
case 164:
//#line 756 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else{
						estructuraActual.crearTerceto("=:", tablaDeSimbolos.getRefFuncion(funcionActual, ambito), val_peek(2).sval);
						estructuraActual.crearTerceto("BI",null, null);
					}	
				}
break;
case 165:
//#line 766 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 166:
//#line 767 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 167:
//#line 768 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 168:
//#line 769 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 170:
//#line 773 "Gramatica.y"
{
				if(dentroDeFor){
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreak();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una sentencia de control", this.linea.getNumeroLinea()));
					System.out.println("No se encuentra dentro de una sentencia de control");
				}
			  }
break;
case 171:
//#line 783 "Gramatica.y"
{
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(1).sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se espera el retorno de una sentencia de control", this.linea.getNumeroLinea()));
					System.out.println("No se espera el retorno de una sentencia de control");
				}
				}
break;
case 172:
//#line 794 "Gramatica.y"
{
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), val_peek(2).sval + val_peek(1).sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se espera el retorno de una sentencia de control", this.linea.getNumeroLinea()));
					System.out.println("No se espera el retorno de una sentencia de control");
			  	}
			  }
break;
case 173:
//#line 805 "Gramatica.y"
{
				if (estructuraActual.existeEtiquetaFor(val_peek(1).sval)){
					estructuraActual.crearTerceto("BI", estructuraActual.getRefEtiqueta(val_peek(1).sval), null);
				}
				else
					errores_semanticos.add(new ErrorLinea("No existe la etiqueta de salto", linea.getNumeroLinea()));
			  }
break;
//#line 2224 "Parser.java"
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
