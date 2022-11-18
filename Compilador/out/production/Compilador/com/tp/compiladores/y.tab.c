#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "Gramatica.y"
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
#line 20 "y.tab.c"
#define IF 257
#define THEN 258
#define ELSE 259
#define END_IF 260
#define OUT 261
#define FUN 262
#define RETURN 263
#define I8 264
#define F32 265
#define WHEN 266
#define CTE_ENTERA 267
#define CTE_FLOTANTE 268
#define ID 269
#define CONTINUE 270
#define CADENA_MULT 271
#define ASIGNACION 272
#define MAYOR_IGUAL 273
#define MENOR_IGUAL 274
#define DISTINTO 275
#define CONST 276
#define TOF32 277
#define BREAK 278
#define FOR 279
#define YYERRCODE 256
short yylhs[] = {                                        -1,
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
   32,   33,   33,   19,   19,   19,   19,   19,   24,   24,
   24,   35,   35,   35,   35,   35,   35,   34,   34,   34,
   34,   34,   34,   34,   34,   34,   34,   34,   34,   34,
   34,   34,   34,   36,   36,   36,   36,   36,   36,   37,
   40,   40,   38,   38,   41,   41,    3,    3,    3,    3,
    3,   39,   39,   39,   39,   29,   29,   29,   29,   29,
};
short yylen[] = {                                         2,
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
    1,    3,    3,    3,    3,    3,    2,    2,    3,    2,
    2,    1,    1,    1,    1,    1,    1,    3,    3,    4,
    3,    4,    3,    3,    3,    4,    3,    4,    3,    3,
    2,    2,    2,    2,    2,    2,    1,    1,    1,    9,
    3,    3,    3,    3,    3,    3,    2,    7,    8,    7,
    8,    3,    3,    3,    4,    1,    2,    3,    4,    4,
};
short yydefred[] = {                                      0,
    0,    5,    0,    0,    4,    0,    0,    0,    0,   28,
   29,    0,   95,   94,    0,  124,  125,  127,    0,    0,
    0,   92,   93,    0,    0,  122,  123,  126,    0,    0,
    0,    0,    0,    0,   14,   16,    0,    0,    0,    0,
   52,   53,    0,    0,    0,    0,   82,    0,    0,    0,
    0,    0,  108,  117,  118,  142,   64,  143,    0,    0,
    0,    0,    0,   42,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   32,    0,   86,    0,    0,    0,
    0,   19,    0,    0,    0,    0,    0,    0,    0,    0,
   51,    0,    0,    2,    6,    7,    8,    0,   18,    0,
   17,    0,    0,    0,    0,    0,    0,    0,   54,    0,
    0,    0,    0,    0,   61,    0,    0,    0,    0,  109,
  111,    0,  121,  141,    0,  157,    0,  107,    0,   48,
    0,    0,    0,   60,   59,    0,    0,    0,    0,    0,
    0,   74,   76,   75,   77,   30,   69,   87,    0,   67,
    0,  151,   70,   71,    0,    0,    0,    0,    0,   27,
   25,   15,    0,    0,   91,    0,    0,    0,    0,  102,
    0,    0,    0,    0,    1,   50,   49,   45,   66,  119,
   13,   12,    0,   84,   85,   83,    0,    0,    0,   44,
    0,    0,    0,    0,   78,   80,   79,   81,   72,   73,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  166,
    0,    0,  116,    0,  114,  115,    0,    0,  147,  148,
  149,    0,    0,   65,    0,   47,    0,    0,   56,    0,
   57,   55,    0,    0,   68,    0,   88,  138,   21,   23,
    0,   20,   90,    0,    0,    0,    0,    0,    0,    0,
   46,   43,    0,    0,    0,  130,   63,    0,  167,    0,
    0,  103,  110,  104,    0,  156,  155,  145,  144,  146,
    0,   38,    0,    0,   58,    0,    0,    0,   22,   24,
  153,  154,    0,    0,    0,    0,    0,   35,    0,    0,
    0,    0,  168,  113,  112,    0,    0,   37,    0,    0,
   89,    0,    0,   96,   97,   98,    0,   34,  170,  169,
    0,    0,    0,    0,  160,    0,    0,    0,    0,    0,
    0,    0,  158,   36,  161,  165,    0,   33,  159,  150,
};
short yydgoto[] = {                                       3,
    4,   96,   30,   31,   32,   33,   34,   35,   81,   36,
   82,   37,   38,   39,   40,  133,   90,   91,   41,   42,
   43,   44,   45,   46,  151,   47,   77,  211,  212,   48,
  121,  122,  216,   49,   50,  222,   51,  169,  284,   52,
  126,
};
short yysindex[] = {                                   -213,
  -98,    0,    0,  176,    0,  404,  341,   -3,  -37,    0,
    0,  341,    0,    0,  577,    0,    0,    0, -153,    8,
   31,    0,    0,  143,  511,    0,    0,    0,  -84,  227,
  227,  227,  388,   24,    0,    0,  -74,  114,  -29,   61,
    0,    0,   71,  551,   37,  -89,    0,   -1,  137,  -57,
   83,   90,    0,    0,    0,    0,    0,    0,  190,  608,
  542,  -38,    5,    0,   49,  -17,  198,  -30,    6,  298,
  307,  -20,   35,    2,    0,  392,    0,   39, -206,  -74,
  -15,    0,  -16,    9,  -24,  483,  166,  143,  143,  169,
    0,  256,  260,    0,    0,    0,    0,  250,    0,  258,
    0,  367,   42,   46,   62, -209,   72,  105,    0,  398,
  407,  414,   96,  227,    0,  447, -246,  204,   88,    0,
    0, -157,    0,    0,   97,    0,  279,    0,  315,    0,
  304,   99,  126,    0,    0,  644,  -74,  650,  677,  248,
  135,    0,    0,    0,    0,    0,    0,    0,  -74,    0,
  141,    0,    0,    0,  729,  -74,  800,  826,  -22,    0,
    0,    0, -153,  729,    0,  800,  657,  113,  337,    0,
 -182,  585,  483,  483,    0,    0,    0,    0,    0,    0,
    0,    0,  -96,    0,    0,    0,  729,  800,  826,    0,
  273,  296,  365,  157,    0,    0,    0,    0,    0,    0,
  729,  -74,  800,  826,  299,  312,  368,  -39,  204,    0,
  305,  204,    0,   -1,    0,    0,  -46,  372,    0,    0,
    0,  207,  252,    0,  118,    0, -156,  378,    0,  389,
    0,    0,  346,   78,    0,  123,    0,    0,    0,    0,
  146,    0,    0,  -44,  197,  721,  793,  820,  729,  826,
    0,    0,  118, -156,  417,    0,    0,  203,    0,  -74,
  421,    0,    0,    0, -245,    0,    0,    0,    0,    0,
  222,    0,  446,  118,    0,  231,  -74,  457,    0,    0,
    0,    0,  367,  440,  448,  451,  455,    0,  464,  118,
  458,  460,    0,    0,    0,   53,  462,    0,  -74,  473,
    0,  411,   78,    0,    0,    0,  479,    0,    0,    0,
  -74,  482,  118,  491,    0,    0,  729,  271,  826,  285,
  118,  496,    0,    0,    0,    0,  520,    0,    0,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  524,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  570,   13,
   17,   19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  463,    0,  524,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  467,  470,    0,
    0,    0, -118,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -34,    0,    1,   28,  -12,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  524,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   40,   69,   84,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   85,    0,   86,   94,    0,    0,    0,    0,  476,    0,
    0,  480,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  139,  177,
    0,    0,  465,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  255,  290,  321,    0,    0,  484,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  683,  564,    0,  567,    0,
  508,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
    0,    4,  -75,  628,  705,  -63,    7,    0,    0,    0,
  471,   76,   -6,    0,    0,  -99,  390,  583,    0,    0,
    0,  356,    0,   26,  397,    0,    0,  -25,  -70,    0,
    0,  422,    0,   -7,  -14,  516,   23,    0,    0,    0,
  580,
};
#define YYTABLESIZE 928
short yytable[] = {                                      58,
   78,  132,   65,   22,  120,   23,  139,   29,  194,   67,
  294,   74,   10,   72,  295,  171,   11,   93,    9,  259,
   22,   75,   23,  106,  165,  206,    5,   87,  163,  113,
  102,   26,   62,   95,  170,   97,   63,   66,  119,  100,
   94,  137,    1,  162,  132,   78,   26,   83,   22,  219,
   23,  219,   58,   93,  221,    2,  221,   13,   14,  187,
   13,   14,  138,  143,  145,  159,  150,   20,  134,  154,
   84,  157,  160,  161,   75,  148,  166,   22,  146,   23,
  135,   22,  101,   23,   13,   14,  246,  183,  139,  131,
   76,  140,   97,  107,   20,   22,  152,   23,  213,  188,
  108,  214,  215,  196,  198,  200,  203,   10,   11,  140,
  191,   25,  130,  105,   22,   79,   23,  205,   80,  112,
   22,  118,   23,  137,  131,  133,  129,  273,  125,  109,
  230,   26,   28,   27,  128,   76,  119,   10,   22,  120,
   23,   11,  235,    9,  137,  193,  268,  268,  149,  238,
  134,  270,  270,  156,  289,  103,   22,   78,   23,  114,
  104,  272,  135,  132,  247,   22,  228,   23,  115,  227,
   13,   14,  249,   26,   28,   27,  166,  123,   75,  136,
   20,  237,   25,  262,  236,   22,  264,   23,  202,  288,
  132,  140,   13,   14,   86,  256,  190,  255,  124,  138,
  254,  261,   26,   28,   27,  125,  131,  133,  129,   67,
  298,   76,  127,  105,  112,   25,  128,  132,   22,  128,
   23,  218,  281,  139,  282,   69,  308,   13,   14,  150,
   76,   64,  105,  112,  241,   26,   28,   27,   68,   76,
  134,  105,  112,  119,  239,  240,  119,   76,   68,  324,
   13,   14,  164,  292,  116,    7,  135,  328,  137,    8,
   20,  136,   76,  105,  112,   86,   25,  117,  302,   22,
  300,   23,   13,   14,  136,  129,   76,  168,  105,  112,
   21,  137,   20,  260,   21,  134,   26,   28,   27,  312,
  175,  119,  314,  178,   99,  166,  179,  135,   24,  132,
  180,   13,   14,  147,  322,   13,   14,  155,  181,  277,
  184,  149,   10,   11,  185,   20,  182,  130,  119,   13,
   14,   76,  105,  112,   76,  112,  140,    6,    7,  100,
  186,  267,    8,    9,   85,   10,   11,   12,   13,   14,
   15,  131,  133,  129,   16,   17,   18,   19,   20,   86,
   21,  128,  116,    7,   22,  224,   23,    8,   92,   85,
  101,  225,   13,   14,  201,  217,  207,  226,   10,   11,
  233,  311,   20,  130,  208,   21,  271,  318,  320,   99,
   61,   10,   11,   22,  244,   23,   16,   17,   18,   13,
   14,  147,   76,  234,  112,  245,  136,  251,    6,    7,
   26,   28,   27,    8,    9,   85,   10,   11,   12,   13,
   14,   15,  279,  280,  100,   16,   17,   18,   19,   20,
  252,   21,  253,  257,  139,  258,   26,   28,   27,  263,
  266,    6,    7,  158,  132,  274,    8,    9,  167,   10,
   11,   12,   13,   14,   15,  101,   99,  275,   16,   17,
   18,   19,   20,   22,   21,   23,   13,   14,   56,  116,
    7,  189,  116,    7,    8,  283,   85,    8,  204,   85,
  276,  291,  117,  207,  290,  217,  207,  176,  177,  293,
  296,  208,    6,    7,  208,   21,  297,    8,    9,  299,
   10,   11,   12,   13,   14,   15,  192,  301,  303,   16,
   17,   18,   19,   20,  307,   21,  304,  116,    7,  305,
   99,   99,    8,  306,   85,   99,  309,   99,  310,  313,
  217,  207,   25,   99,   99,   22,  248,   23,   57,  208,
   21,  315,   99,   99,  116,    7,  321,  326,  250,    8,
  323,   85,   26,   28,   27,  100,  100,  217,  207,  325,
  100,  327,  100,   22,  329,   23,  208,   21,  100,  100,
  330,  139,  176,  177,   13,   14,  142,  100,  100,    3,
   26,   28,   27,   13,   14,  144,  101,  101,   13,   14,
  136,  101,   31,  101,   22,   62,   23,   41,   20,  101,
  101,   11,  110,   22,    9,   23,   59,  111,  101,  101,
  105,   26,   28,   27,  106,   86,   40,   13,   14,   60,
   26,   28,   27,   16,   17,   18,   73,   20,   70,   22,
   72,   23,  162,   71,   73,  163,   70,   22,   72,   23,
   39,   71,  278,  242,   74,  265,   26,   28,   27,   16,
   17,   18,  223,   98,   26,   28,   27,   73,  141,   70,
   22,   88,   23,    0,   71,    0,   68,  319,   13,   14,
  153,   53,   54,   55,   13,   14,  195,   26,   28,   27,
   13,   14,   56,   13,   14,  197,    0,  316,   14,  317,
   13,   14,  199,   73,    0,   70,   22,   20,   23,    0,
   71,  103,   22,    0,   23,    0,  104,  243,  110,   22,
  210,   23,  229,  111,   53,   54,   55,  210,  231,  210,
    0,    0,    0,  173,    0,   88,   88,    0,  110,   22,
    0,   23,    0,  111,   95,   95,    0,   95,   89,   95,
    0,    0,    0,    0,   88,  232,    0,    0,    6,    7,
    0,  164,    0,    8,    9,   85,   10,   11,   12,   13,
   14,  172,  120,    0,    0,   16,   17,   18,   19,   20,
   73,  285,   70,   22,    0,   23,   59,   71,   73,    0,
   70,   22,    0,   23,    0,   71,    0,   13,   14,   60,
    0,   92,    0,   16,   17,   18,    0,   20,    0,    0,
  174,  210,   89,   89,  210,    0,    0,   59,    0,    0,
  173,  173,    0,    0,  210,  210,    0,    0,   13,   14,
   60,   89,    0,    0,   16,   17,   18,    0,   20,    0,
    0,    0,  209,   16,   17,   18,    0,    0,    0,  220,
    0,  220,   67,  286,  103,   22,    0,   23,    0,  104,
   67,  103,   22,    0,   23,   68,  104,    0,   69,   16,
   17,   18,    0,   68,    0,    0,  206,   16,   17,   18,
  287,  110,   22,    0,   23,    0,  111,  110,   22,    0,
   23,    0,  111,    0,    0,    0,    0,  174,  174,    0,
   16,   17,   18,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  209,    0,    0,  209,    0,  120,    0,
    0,    0,    0,    0,    0,    0,  269,  269,
};
short yycheck[] = {                                       6,
   15,   65,   40,   43,  123,   45,   41,    4,  108,  256,
  256,   58,    0,   44,  260,   40,    0,   25,    0,   59,
   43,   15,   45,   38,   41,  272,  125,   24,   44,   44,
   37,   44,    7,   30,   59,   32,   40,   12,   40,   33,
  125,   41,  256,   59,  108,   60,   59,   40,   43,  125,
   45,  127,   59,   61,  125,  269,  127,  267,  268,  269,
  267,  268,   69,   70,   71,  272,   73,  277,   41,   76,
   40,   78,   79,   80,   68,   41,   83,   43,   72,   45,
   41,   43,   59,   45,  267,  268,  269,  102,  123,   41,
   15,   69,   89,  123,  277,   43,   74,   45,  256,  106,
   40,  259,  260,  110,  111,  112,  113,  264,  265,   41,
  107,   40,  269,   38,   43,  269,   45,  114,  272,   44,
   43,  123,   45,  123,   41,   41,   41,  227,  123,   59,
  137,   60,   61,   62,   41,   60,   40,  125,   43,  258,
   45,  125,  149,  125,   69,   41,  222,  223,   73,  156,
  123,  222,  223,   78,  254,   42,   43,  172,   45,  123,
   47,  225,  123,  227,  171,   43,   41,   45,  258,   44,
  267,  268,  269,   60,   61,   62,  183,   41,  172,   41,
  277,   41,   40,  209,   44,   43,  212,   45,  113,  253,
  254,  123,  267,  268,  123,  202,  125,   41,  256,  206,
   44,  208,   60,   61,   62,  123,  123,  123,  123,  256,
  274,  136,  123,  138,  139,   40,  123,   41,   43,  258,
   45,  125,  267,  258,  269,  272,  290,  267,  268,  236,
  155,  269,  157,  158,  159,   60,   61,   62,  269,  164,
  258,  166,  167,   40,  267,  268,   40,  172,  269,  313,
  267,  268,  269,  260,  256,  257,   59,  321,  258,  261,
  277,  123,  187,  188,  189,  123,   40,  269,  283,   43,
  277,   45,  267,  268,  269,  271,  201,  269,  203,  204,
  279,  206,  277,  208,  279,  258,   60,   61,   62,  296,
  125,   40,  299,  125,   40,  302,   41,  258,  123,  123,
   41,  267,  268,  269,  311,  267,  268,  269,   59,  234,
  269,  236,  264,  265,  269,  277,   59,  269,   40,  267,
  268,  246,  247,  248,  249,  250,  258,  256,  257,   40,
  269,  125,  261,  262,  263,  264,  265,  266,  267,  268,
  269,  258,  258,  258,  273,  274,  275,  276,  277,  123,
  279,  258,  256,  257,   43,   41,   45,  261,  271,  263,
   40,   58,  267,  268,  269,  269,  270,  269,  264,  265,
  123,  296,  277,  269,  278,  279,  125,  302,  303,  125,
   40,  264,  265,   43,  272,   45,  273,  274,  275,  267,
  268,  269,  317,  259,  319,   59,  258,  125,  256,  257,
   60,   61,   62,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  267,  268,  125,  273,  274,  275,  276,  277,
  125,  279,   58,  125,   69,   58,   60,   61,   62,  125,
   59,  256,  257,   78,  258,   58,  261,  262,   83,  264,
  265,  266,  267,  268,  269,  125,   59,   59,  273,  274,
  275,  276,  277,   43,  279,   45,  267,  268,  269,  256,
  257,  106,  256,  257,  261,  269,  263,  261,  113,  263,
  125,  269,  269,  270,   58,  269,  270,   88,   89,   59,
  259,  278,  256,  257,  278,  279,   41,  261,  262,  259,
  264,  265,  266,  267,  268,  269,  107,   41,   59,  273,
  274,  275,  276,  277,   41,  279,   59,  256,  257,   59,
  256,  257,  261,   59,  263,  261,   59,  263,   59,   58,
  269,  270,   40,  269,  270,   43,  171,   45,  125,  278,
  279,   59,  278,  279,  256,  257,   58,  267,  183,  261,
   59,  263,   60,   61,   62,  256,  257,  269,  270,   59,
  261,  267,  263,   43,   59,   45,  278,  279,  269,  270,
   41,  206,  173,  174,  267,  268,  269,  278,  279,    0,
   60,   61,   62,  267,  268,  269,  256,  257,  267,  268,
  269,  261,   59,  263,   43,  123,   45,  123,  277,  269,
  270,  125,   42,   43,  125,   45,  256,   47,  278,  279,
  125,   60,   61,   62,  125,  123,  123,  267,  268,  269,
   60,   61,   62,  273,  274,  275,   40,  277,   42,   43,
   44,   45,   59,   47,   40,   59,   42,   43,   44,   45,
  123,   47,  236,  163,   58,  214,   60,   61,   62,  273,
  274,  275,  127,  256,   60,   61,   62,   40,   69,   42,
   43,   24,   45,   -1,   47,   -1,  269,  302,  267,  268,
  269,  258,  259,  260,  267,  268,  269,   60,   61,   62,
  267,  268,  269,  267,  268,  269,   -1,  267,  268,  269,
  267,  268,  269,   40,   -1,   42,   43,  277,   45,   -1,
   47,   42,   43,   -1,   45,   -1,   47,   41,   42,   43,
  118,   45,   59,   47,  258,  259,  260,  125,   59,  127,
   -1,   -1,   -1,   86,   -1,   88,   89,   -1,   42,   43,
   -1,   45,   -1,   47,   42,   43,   -1,   45,   24,   47,
   -1,   -1,   -1,   -1,  107,   59,   -1,   -1,  256,  257,
   -1,   59,   -1,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   48,   -1,   -1,  273,  274,  275,  276,  277,
   40,   41,   42,   43,   -1,   45,  256,   47,   40,   -1,
   42,   43,   -1,   45,   -1,   47,   -1,  267,  268,  269,
   -1,  271,   -1,  273,  274,  275,   -1,  277,   -1,   -1,
   86,  209,   88,   89,  212,   -1,   -1,  256,   -1,   -1,
  173,  174,   -1,   -1,  222,  223,   -1,   -1,  267,  268,
  269,  107,   -1,   -1,  273,  274,  275,   -1,  277,   -1,
   -1,   -1,  118,  273,  274,  275,   -1,   -1,   -1,  125,
   -1,  127,  256,   41,   42,   43,   -1,   45,   -1,   47,
  256,   42,   43,   -1,   45,  269,   47,   -1,  272,  273,
  274,  275,   -1,  269,   -1,   -1,  272,  273,  274,  275,
   41,   42,   43,   -1,   45,   -1,   47,   42,   43,   -1,
   45,   -1,   47,   -1,   -1,   -1,   -1,  173,  174,   -1,
  273,  274,  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  209,   -1,   -1,  212,   -1,  214,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  222,  223,
};
#define YYFINAL 3
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 279
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,
"':'","';'","'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,
"'}'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,"IF","THEN","ELSE","END_IF","OUT","FUN","RETURN",
"I8","F32","WHEN","CTE_ENTERA","CTE_FLOTANTE","ID","CONTINUE","CADENA_MULT",
"ASIGNACION","MAYOR_IGUAL","MENOR_IGUAL","DISTINTO","CONST","TOF32","BREAK",
"FOR",
};
char *yyrule[] = {
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
"CuerpoElse : ELSE CuerpoIf END_IF",
"CuerpoElse : ELSE CuerpoIf error",
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
"HeaderForID : ID ':' HeaderFor",
"HeaderForID : ID ASIGNACION HeaderFor",
"AsigFor : ID ASIGNACION CTE_ENTERA",
"AsigFor : ID ASIGNACION ID",
"BucleFor : '{' ListSentenciasFor '}'",
"BucleFor : '{' '}' ';'",
"SentenciaControl : HeaderFor BucleFor",
"SentenciaControl : HeaderForID '{' ListSentenciasFor '}' ELSE cte ';'",
"SentenciaControl : HeaderForID '{' ListSentenciasFor '}' ELSE signo cte ';'",
"SentenciaControl : ID ASIGNACION BucleFor ELSE signo cte ';'",
"SentenciaControl : ID ASIGNACION HeaderFor '{' '}' ELSE cte ';'",
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 360 "Gramatica.y"

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
public static String ambito = "";

//-----------------------------------------------------------------------

AnalizadorLexico lexico;

	

	public void addListEstructuraSeguimiento(EstructuraTercetos ET){
		listEstructurasSeguimiento.add(ET);
	}

	public EstructuraTercetos getEstructuraPendiente(){
		return(listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1));
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

#line 730 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 32 "Gramatica.y"
{desconcatenarAmbito();}
break;
case 2:
#line 33 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' ");}
break;
case 3:
#line 34 "Gramatica.y"
{AgregarErrorSintactico("Se esperan '{' '}' ");}
break;
case 4:
#line 35 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 5:
#line 38 "Gramatica.y"
{ambito= yyvsp[0].sval; tablaDeSimbolos.add(new Simbolo(yyvsp[0].sval, 269,"nombre_programa"));
					 estructuraActual=estructuraTercetosPrincipal;
					 listEstructurasSeguimiento.add(estructuraActual);
					 listEstructurasTercetos.add(estructuraActual);
					 }
break;
case 13:
#line 55 "Gramatica.y"
{AgregarErrorSintactico("Se espera identificador");}
break;
case 17:
#line 59 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de la variable");}
break;
case 18:
#line 60 "Gramatica.y"
{AgregarErrorSintactico("Se espera identificador de la variable");}
break;
case 21:
#line 67 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-2].sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);}
break;
case 22:
#line 68 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-3].sval+":"+ambito, 269, "constante", "I8")); estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), yyvsp[-1].sval + yyvsp[0].sval);}
break;
case 23:
#line 69 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-2].sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);}
break;
case 24:
#line 70 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-3].sval+":"+ambito, 269, "constante", "F32")); estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), yyvsp[-1].sval + yyvsp[0].sval);}
break;
case 25:
#line 71 "Gramatica.y"
{AgregarErrorSintactico("Se espera un identificador");}
break;
case 26:
#line 72 "Gramatica.y"
{AgregarErrorSintactico("Se espera una constante ");}
break;
case 27:
#line 73 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:' ");}
break;
case 28:
#line 76 "Gramatica.y"
{estructuraActual.setTipo("I8");}
break;
case 29:
#line 77 "Gramatica.y"
{estructuraActual.setTipo("F32");}
break;
case 30:
#line 80 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-2].sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
break;
case 31:
#line 81 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[0].sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
break;
case 32:
#line 82 "Gramatica.y"
{AgregarErrorSintactico("Se espera ',' ");}
break;
case 33:
#line 85 "Gramatica.y"
{tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo()); }
break;
case 34:
#line 86 "Gramatica.y"
{tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo());}
break;
case 35:
#line 87 "Gramatica.y"
{tablaDeSimbolos.setTipo(ambito, estructuraActual.getTipo());}
break;
case 36:
#line 89 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 37:
#line 90 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 38:
#line 91 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 39:
#line 92 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 40:
#line 93 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 41:
#line 94 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 42:
#line 97 "Gramatica.y"
{estructuraActual=new EstructuraTercetos(yyvsp[0].sval+":"+ambito);
			 ambito= ambito+":"+yyvsp[0].sval; tablaDeSimbolos.add(new Simbolo(ambito, 269, "identificador_funcion"));
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 }
break;
case 43:
#line 104 "Gramatica.y"
{desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   }
break;
case 44:
#line 108 "Gramatica.y"
{AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
break;
case 45:
#line 109 "Gramatica.y"
{AgregarErrorSintactico("Se espera el header de la funcion");}
break;
case 46:
#line 110 "Gramatica.y"
{AgregarErrorSintactico("Se espera el retorno de la funcion");}
break;
case 47:
#line 114 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[0].sval+":"+ambito,269,"parametro", estructuraActual.getTipo()));}
break;
case 48:
#line 115 "Gramatica.y"
{AgregarErrorSintactico("Se espera tipo de parametro");}
break;
case 55:
#line 128 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 56:
#line 129 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), tablaDeSimbolos.getRefSimbolo(yyvsp[-1].sval, ambito));}
break;
case 57:
#line 130 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), yyvsp[-1].sval);}
break;
case 58:
#line 131 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-3].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-4].sval, ambito), yyvsp[-2].sval + yyvsp[-1].sval);}
break;
case 59:
#line 132 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 60:
#line 135 "Gramatica.y"
{ambito= ambito+":"+"when";}
break;
case 61:
#line 136 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
#line 137 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 64:
#line 141 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 66:
#line 146 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 70:
#line 155 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 71:
#line 156 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);}
break;
case 72:
#line 157 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 73:
#line 158 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), yyvsp[0].sval);}
break;
case 74:
#line 159 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 75:
#line 160 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 76:
#line 161 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);}
break;
case 77:
#line 162 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);}
break;
case 78:
#line 163 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 79:
#line 164 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 80:
#line 165 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), yyvsp[0].sval);}
break;
case 81:
#line 166 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), yyvsp[0].sval);}
break;
case 83:
#line 168 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 84:
#line 169 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 85:
#line 170 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 91:
#line 180 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 95:
#line 189 "Gramatica.y"
{chequearRangoEntero(yyvsp[0].sval);}
break;
case 99:
#line 195 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 100:
#line 196 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 101:
#line 197 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 102:
#line 198 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 108:
#line 208 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 111:
#line 216 "Gramatica.y"
{estructuraActual.desapilarYCompletar(2);
						estructuraActual.crearTerceto("BI", null, null);}
break;
case 113:
#line 220 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 114:
#line 223 "Gramatica.y"
{estructuraActual.desapilarYCompletar(1); desconcatenarAmbito();}
break;
case 115:
#line 224 "Gramatica.y"
{estructuraActual.desapilarYCompletar(1); desconcatenarAmbito();}
break;
case 116:
#line 225 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 117:
#line 226 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 118:
#line 227 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 119:
#line 230 "Gramatica.y"
{estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);}
break;
case 120:
#line 231 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 121:
#line 232 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 128:
#line 243 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(2), estructuraActual.getRefTerceto(1));}
break;
case 129:
#line 244 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), yyvsp[0].sval);}
break;
case 130:
#line 245 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-2].sval, estructuraActual.getRefTerceto(1), yyvsp[-1].sval + yyvsp[0].sval );}
break;
case 131:
#line 246 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, estructuraActual.getRefTerceto(1));}
break;
case 132:
#line 247 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-3].sval + yyvsp[-2].sval, estructuraActual.getRefTerceto(1));}
break;
case 133:
#line 248 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getRefTerceto(1), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 134:
#line 249 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 135:
#line 250 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 136:
#line 251 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-3].sval + yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 137:
#line 252 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);}
break;
case 138:
#line 253 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), yyvsp[-1].sval + yyvsp[0].sval);}
break;
case 139:
#line 254 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 140:
#line 255 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);}
break;
case 141:
#line 256 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 142:
#line 257 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 143:
#line 258 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 150:
#line 270 "Gramatica.y"
{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", estructuraActual.getRefTerceto(1), null);
				 estructuraActual.crearTerceto(yyvsp[-2].sval, estructuraActual.getIdFor(), yyvsp[-1].sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 }
break;
case 151:
#line 277 "Gramatica.y"
{estructuraActual.setIdAsigFor}
break;
case 153:
#line 282 "Gramatica.y"
{estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito));
		estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getIdFor(), yyvsp[0].sval);
	 	}
break;
case 154:
#line 286 "Gramatica.y"
{estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito));
		estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));
	 	}
break;
case 155:
#line 292 "Gramatica.y"
{estructuraActual.desapilarYCompletar(2);
					 estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.popIdFor();}
break;
case 156:
#line 296 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 158:
#line 302 "Gramatica.y"
{
					
					estructuraActual.
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-1].sval);

					estructuraActual.completarTercetosBreakCte(1);
					}
break;
case 159:
#line 310 "Gramatica.y"
{
					
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-2].sval + yyvsp[-1].sval);

					estructuraActual.completarTercetosBreakCte(1);
				}
break;
case 160:
#line 317 "Gramatica.y"
{

					estructuraActual.crearTerceto(yyvsp[-5].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-6].sval, ambito), yyvsp[-2].sval+yyvsp[-1].sval);
				}
break;
case 161:
#line 322 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 162:
#line 326 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 163:
#line 327 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), estructuraActual.getRefTerceto(1));}
break;
case 164:
#line 328 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);}
break;
case 165:
#line 329 "Gramatica.y"
{estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), yyvsp[-1].sval+yyvsp[0].sval);}
break;
case 168:
#line 334 "Gramatica.y"
{
				if(!estructuraActual.estaVacia()){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-1].sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				else
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion"), this.linea.getNumeroLinea());
					System.out.println("No existe ID para la asignacion");
				}
				}
break;
case 169:
#line 344 "Gramatica.y"
{
				if(!estructuraActual.estaVacia()){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-2].sval + yyvsp[-1].sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion"), this.linea.getNumeroLinea());
					System.out.println("No existe ID para la asignacion");
			  }
			  }
break;
#line 1365 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
