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
   23,   23,   24,   10,   10,   21,   21,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   27,   27,   27,   28,   28,   28,
   26,   26,   12,   12,   13,   13,   29,   29,   29,   29,
   31,   31,   32,   32,   33,   34,   34,   19,   19,   19,
   19,   19,   25,   25,   25,   36,   36,   36,   36,   36,
   36,   35,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,   35,   35,   35,   35,   35,   37,   37,   37,
   37,   37,   37,   38,   38,   41,   42,   43,   39,   39,
   44,   44,    3,    3,    3,    3,   40,   40,   40,   40,
   18,   18,   18,   18,   18,   18,   18,   30,   30,   30,
   30,   30,
};
short yylen[] = {                                         2,
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
    1,    1,    1,    9,    8,    3,    2,    2,    3,    3,
    3,    3,    2,    2,    5,    6,    3,    3,    3,    4,
    5,    5,    5,    4,    4,    4,    2,    1,    2,    3,
    4,    4,
};
short yydefred[] = {                                      0,
    0,    5,    0,    0,    4,    0,    0,    0,    0,   28,
   29,   63,   96,   95,    0,  118,  119,  121,    0,    0,
    0,   93,   94,    0,    0,  116,  117,  120,    0,    0,
    0,    0,    0,    0,   14,   16,    0,    0,    0,    0,
   52,   53,    0,    0,    0,    0,    0,   80,    0,    0,
    0,    0,    0,    0,    0,  102,  111,  112,  136,   65,
  137,    0,    0,    0,    0,    0,   42,    0,    0,    0,
    0,    0,    0,    0,    0,  147,   32,    0,   84,    0,
    0,    0,    0,   19,    0,    0,    0,    0,    0,    0,
    0,    0,   51,    0,    0,    2,    6,    7,    8,    0,
   18,    0,   17,    0,    0,    0,    0,    0,    0,    0,
   54,    0,    0,    0,    0,    0,    0,   61,    0,    0,
    0,    0,  103,    0,  115,  135,    0,  153,    0,  148,
  154,  101,    0,   48,    0,    0,    0,   59,    0,    0,
    0,    0,  146,   72,   74,   73,   75,   30,   90,   85,
    0,   88,    0,   68,   69,    0,    0,    0,    0,    0,
   27,   25,   15,    0,    0,   92,    0,    0,    0,    0,
  167,    0,    0,    0,    0,    1,   50,   49,   45,   67,
  113,   13,   12,    0,   82,   83,   81,    0,    0,    0,
   44,    0,    0,    0,    0,   76,   78,   77,   79,   70,
   71,    0,    0,    0,    0,    0,   60,    0,    0,    0,
    0,  168,    0,    0,  110,  105,  108,    0,  109,    0,
    0,  141,  142,  143,    0,    0,   66,    0,   47,    0,
    0,   56,    0,   57,   55,   89,    0,   86,  132,   21,
   23,    0,   20,   91,    0,    0,    0,    0,    0,    0,
    0,   46,   43,    0,    0,    0,  124,   64,    0,  169,
    0,    0,   97,  104,   98,    0,  152,  151,  139,  138,
  140,    0,    0,   38,    0,    0,   58,    0,   22,   24,
  149,  150,    0,    0,    0,    0,    0,   35,    0,    0,
    0,    0,  170,  107,  106,    0,  155,    0,   37,   87,
    0,    0,  161,  162,  163,    0,   34,  172,  171,  156,
    0,    0,    0,    0,    0,    0,    0,    0,   36,  160,
  145,    0,   33,  144,
};
short yydgoto[] = {                                       3,
    4,   98,   30,   31,   32,   33,   34,   35,   83,   36,
   84,   37,   38,   39,   40,  137,   92,   93,   41,   42,
   43,   44,   45,   46,   47,   48,   79,  153,  213,  214,
   49,  124,  218,  219,   50,   51,  225,   52,  170,  284,
   53,   54,   55,  128,
};
short yysindex[] = {                                   -180,
 -100,    0,    0,  202,    0,  490,  366,  -36,  -32,    0,
    0,    0,    0,    0,  506,    0,    0,    0, -258,   15,
   55,    0,    0,  156,  470,    0,    0,    0,  -86,  228,
  228,  228,  -50,   58,    0,    0,   60,  535,  -22,   85,
    0,    0,   75,  547,   45,  366, -167,    0,  -30,  139,
  -56,   87,   87,  -18,   87,    0,    0,    0,    0,    0,
    0, -161,   37,  526,   27,   34,    0,  -13,  240,  -29,
   24, -120,  205,   48,  358,    0,    0,  231,    0,    7,
 -105,   60,   -2,    0,  -19,   71,  -33,  394,  218,  156,
  156,  230,    0,  326,  329,    0,    0,    0,    0,  317,
    0,  322,    0,  426,  133,  135,  136, -224,  121,   13,
    0,  252,  369,  385,   67,  228,  149,    0,  406, -239,
   93,  137,    0,  113,    0,    0,   52,    0,  151,    0,
    0,    0,  373,    0,  378,  147,   18,    0,   28,   60,
  651,  670,    0,    0,    0,    0,    0,    0,    0,    0,
   60,    0,   42,    0,    0,  685,   60,  162,  602,   78,
    0,    0,    0, -258,  685,    0,  162,  558,  166,  381,
    0,  -83,  514,  394,  394,    0,    0,    0,    0,    0,
    0,    0,    0,  174,    0,    0,    0,  685,  162,  602,
    0,  337,  340,  422,  171,    0,    0,    0,    0,    0,
    0,  685,   60,  162,  602,  357,    0,   70,  425,   29,
   93,    0,  383,   93,    0,    0,    0,  -30,    0,   51,
  432,    0,    0,    0,   63,  185,    0,  -73,    0,  245,
  448,    0,  467,    0,    0,    0,  271,    0,    0,    0,
    0,  125,    0,    0,    3,  259,  571,  782,  789,  685,
  602,    0,    0,  -73,  245,  479,    0,    0,  272,    0,
   60,  488,    0,    0,    0, -209,    0,    0,    0,    0,
    0,   60,  493,    0,  519,  -73,    0,  521,    0,    0,
    0,    0,  426,  496,  504,  511,  520,    0,  524,  -73,
  532,  543,    0,    0,    0,  551,    0,  515,    0,    0,
  180,  -24,    0,    0,    0,  525,    0,    0,    0,    0,
  -73,    0,  685,  305,  602,  565,  352,  -73,    0,    0,
    0,  579,    0,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  570,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  631,   31,
   32,   35,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  570,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  523,
  548,    0,    0,    0, -118,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  549,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -38,    0,   -5,   -3,    2,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  570,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   -1,   23,   90,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  157,    0,  323,  327,    0,    0,    0,    0,    0,
  550,    0,    0,  554,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  419,
  420,    0,    0,  553,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   96,  255,  266,    0,    0,  557,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  718,  587,    0,  615,    0,    0,  559,    0,    0,
    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
    0,  600, -121,  599,  593,  -52,   81,    0,    0,    0,
  522,  -15,   57,    0,    0,  -76,  270, -109,    0,    0,
    0,  513,    0,    0,   20,    0,    0,  451, -133, -114,
    0,  474,    0,    0,  -23,  -14,    0,  -34,    0,    0,
    0,    0,    0,  249,
};
#define YYTABLESIZE 836
short yytable[] = {                                      78,
   80,   95,  133,   66,  114,  222,  172,   68,  101,  122,
   81,  212,  224,   82,   74,  136,   69,  212,   22,  130,
   23,  166,  107,  108,    5,  171,   65,  135,  114,  115,
   10,   11,  208,  195,    9,  131,  143,  128,   96,  129,
   95,  164,   13,   14,  188,   26,  294,   78,   80,   22,
  295,   23,   20,  194,   85,  140,  163,  136,  231,  151,
   26,  230,   61,  134,  157,  117,   22,   75,   23,   72,
   22,   22,   23,   23,   73,    1,   75,  263,   72,   22,
  265,   23,  238,   73,  133,  237,  232,  260,    2,  184,
  118,  122,  121,  104,   86,   77,   26,   28,   27,  203,
  109,  212,  122,  269,  212,   13,   14,   59,   76,   22,
  271,   23,   22,  102,   23,  212,  103,  131,   61,  128,
   22,  129,   23,   78,  110,  107,  114,  141,  145,  147,
  125,  152,  122,  111,  155,  164,  158,  161,  162,  114,
   78,  167,  107,  114,  242,  134,   13,   14,  144,   78,
   77,  107,  114,  275,  148,   10,   11,   78,   80,    9,
   25,   13,   14,   22,  189,   23,  160,  116,  197,  199,
  201,  204,   78,  107,  114,  274,  221,  136,  289,  125,
   26,   28,   27,   13,   14,  247,   78,  268,  107,  114,
   10,   11,  140,   20,  261,   25,  233,  127,   22,  126,
   23,  288,  136,  105,   22,  100,   23,  236,  106,  127,
  272,  256,  125,  239,  255,   26,   28,   27,   70,  133,
  164,  151,   22,  299,   23,  119,    7,   22,  248,   23,
    8,   78,  107,  114,   78,  114,   67,  307,  120,   70,
  167,   25,  316,   88,   22,  191,   23,   13,   14,  165,
   10,   11,  131,   77,  128,  134,  129,   20,  319,  257,
   21,   26,   28,   27,  141,  323,  262,   25,  301,  281,
   22,  282,   23,   13,   14,  156,   10,   11,   88,  127,
  134,  134,  273,   20,  132,  314,  317,   26,   28,   27,
   13,   14,  139,  152,  165,   13,   14,   78,  138,  114,
   20,  129,   21,  131,  133,  166,   69,  119,    7,   16,
   17,   18,    8,   22,   87,   23,   70,  292,  119,    7,
  220,  209,   71,    8,   24,   87,   13,   14,  296,  210,
   21,  220,  209,   13,   14,  202,   13,   14,  139,  169,
  210,   21,  176,   20,  240,  241,   20,  125,  119,    7,
   88,  164,  164,    8,  179,   87,  164,  167,  164,  177,
  178,  120,  209,  123,  164,  164,  180,  122,  215,  181,
  210,  216,  217,  164,  164,  182,    6,    7,  193,  165,
  183,    8,    9,   87,   10,   11,   12,   13,   14,   15,
  166,  279,  280,   16,   17,   18,   19,   20,  150,   21,
   22,  185,   23,  186,  187,   64,  207,   94,   22,  226,
   23,    6,    7,  227,  127,  229,    8,    9,   87,   10,
   11,   12,   13,   14,   15,   26,   28,   27,   16,   17,
   18,   19,   20,   25,   21,  228,   22,  245,   23,  246,
   13,   14,  250,  177,  178,  123,  312,   14,  313,  122,
   20,   13,   14,   26,   28,   27,   20,    6,    7,  130,
  126,  252,    8,    9,  253,   10,   11,   12,   13,   14,
   15,   13,   14,  146,   16,   17,   18,   19,   20,  254,
   21,  258,  259,    6,    7,   26,   28,   27,    8,    9,
  267,   10,   11,   12,   13,   14,   15,   13,   14,  154,
   16,   17,   18,   19,   20,  276,   21,  264,   10,   11,
  165,  165,   22,  134,   23,  165,   88,  165,   13,   14,
  196,  166,  166,  165,  165,  277,  166,  283,  166,   26,
   28,   27,  165,  165,  166,  166,  290,   13,   14,  149,
  291,  130,  126,  166,  166,   75,  293,   72,   22,   74,
   23,  297,   73,   75,  302,   72,   22,   74,   23,  298,
   73,  300,  303,   76,  306,   26,   28,   27,   22,  304,
   23,  320,  311,   26,   28,   27,  105,   22,  305,   23,
  123,  106,  318,  142,  122,   26,   28,   27,  112,   22,
  308,   23,  159,  113,   26,   28,   27,  168,  244,  112,
   22,  309,   23,   29,  113,  321,   26,   28,   27,  310,
   75,  285,   72,   22,   60,   23,   91,   73,  322,  324,
  190,   62,   90,   89,   13,   14,  149,  205,   31,   97,
    3,   99,   13,   14,   63,   13,   14,  198,   16,   17,
   18,  123,   20,  112,   22,  157,   23,   11,  113,    6,
    7,   13,   14,  200,    8,    9,   87,   10,   11,   12,
   13,   14,  173,   56,   57,   58,   16,   17,   18,   19,
   20,   62,    9,  158,   99,   41,  130,  126,  100,   40,
  175,   39,   91,   91,  249,  243,  174,  278,   90,   90,
   99,  266,  105,   22,    0,   23,  251,  106,   16,   17,
   18,   91,    0,    0,    0,    0,    0,   90,  192,  234,
    0,  112,   22,  211,   23,  206,  113,    0,    0,  223,
  142,    0,    0,    0,   75,   62,   72,   22,  235,   23,
    0,   73,    0,    0,    0,    0,   13,   14,   63,    0,
   94,    0,   16,   17,   18,    0,   20,   56,   57,   58,
    0,    0,    0,    0,    0,    0,   13,   14,   59,   96,
   96,   69,   96,    0,   96,    0,  175,  175,    0,   69,
    0,    0,  174,  174,   70,    0,  159,   71,   16,   17,
   18,   62,   70,    0,    0,  208,   16,   17,   18,    0,
    0,    0,   13,   14,   63,    0,    0,    0,   16,   17,
   18,    0,   20,  211,    0,    0,  211,   16,   17,   18,
  123,    0,    0,  315,    0,    0,    0,  270,    0,   16,
   17,   18,  286,  105,   22,    0,   23,    0,  106,  287,
  112,   22,    0,   23,    0,  113,
};
short yycheck[] = {                                      15,
   15,   25,   41,   40,  123,  127,   40,   40,   59,   40,
  269,  121,  127,  272,   44,   68,  256,  127,   43,   54,
   45,   41,   38,   38,  125,   59,    7,   41,   44,   44,
    0,    0,  272,  110,    0,   41,   71,   41,  125,   41,
   64,   44,  267,  268,  269,   44,  256,   63,   63,   43,
  260,   45,  277,   41,   40,   71,   59,  110,   41,   75,
   59,   44,    6,   41,   80,   46,   43,   40,   45,   42,
   43,   43,   45,   45,   47,  256,   40,  211,   42,   43,
  214,   45,   41,   47,  123,   44,   59,   59,  269,  104,
  258,   40,  123,   37,   40,   15,   60,   61,   62,  115,
  123,  211,   40,  225,  214,  267,  268,  269,   58,   43,
  225,   45,   43,   33,   45,  225,   59,  123,   62,  123,
   43,  123,   45,  139,   40,  141,  142,   71,   72,   73,
   41,   75,   40,   59,   78,   40,   80,   81,   82,  258,
  156,   85,  158,  159,  160,  123,  267,  268,  269,  165,
   70,  167,  168,  230,   74,  125,  125,  173,  173,  125,
   40,  267,  268,   43,  108,   45,  272,  123,  112,  113,
  114,  115,  188,  189,  190,  228,  125,  230,  255,   41,
   60,   61,   62,  267,  268,  269,  202,  125,  204,  205,
  264,  265,  208,  277,  210,   40,  140,   41,   43,  256,
   45,  254,  255,   42,   43,  256,   45,  151,   47,  123,
  226,   41,  123,  157,   44,   60,   61,   62,  269,  258,
  125,  237,   43,  276,   45,  256,  257,   43,  172,   45,
  261,  247,  248,  249,  250,  251,  269,  290,  269,  269,
  184,   40,  267,  123,   43,  125,   45,  267,  268,  269,
  264,  265,  258,  173,  258,  269,  258,  277,  311,  203,
  279,   60,   61,   62,  208,  318,  210,   40,  283,  267,
   43,  269,   45,  267,  268,  269,  264,  265,  123,  123,
  258,  269,  226,  277,  258,  301,  302,   60,   61,   62,
  267,  268,  269,  237,   40,  267,  268,  313,   59,  315,
  277,   53,  279,   55,  271,   40,  256,  256,  257,  273,
  274,  275,  261,   43,  263,   45,  269,  261,  256,  257,
  269,  270,  272,  261,  123,  263,  267,  268,  272,  278,
  279,  269,  270,  267,  268,  269,  267,  268,  269,  269,
  278,  279,  125,  277,  267,  268,  277,  258,  256,  257,
  123,  256,  257,  261,  125,  263,  261,  301,  263,   90,
   91,  269,  270,   41,  269,  270,   41,   41,  256,   41,
  278,  259,  260,  278,  279,   59,  256,  257,  109,  125,
   59,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  125,  267,  268,  273,  274,  275,  276,  277,   41,  279,
   43,  269,   45,  269,  269,   40,  258,  271,   43,  259,
   45,  256,  257,   41,  258,  269,  261,  262,  263,  264,
  265,  266,  267,  268,  269,   60,   61,   62,  273,  274,
  275,  276,  277,   40,  279,   58,   43,  272,   45,   59,
  267,  268,  269,  174,  175,  123,  267,  268,  269,  123,
  277,  267,  268,   60,   61,   62,  277,  256,  257,   41,
   41,  125,  261,  262,  125,  264,  265,  266,  267,  268,
  269,  267,  268,  269,  273,  274,  275,  276,  277,   58,
  279,  125,   58,  256,  257,   60,   61,   62,  261,  262,
   59,  264,  265,  266,  267,  268,  269,  267,  268,  269,
  273,  274,  275,  276,  277,   58,  279,  125,  264,  265,
  256,  257,   43,  269,   45,  261,  123,  263,  267,  268,
  269,  256,  257,  269,  270,   59,  261,  269,  263,   60,
   61,   62,  278,  279,  269,  270,   58,  267,  268,  269,
  269,  123,  123,  278,  279,   40,   59,   42,   43,   44,
   45,   59,   47,   40,   59,   42,   43,   44,   45,   41,
   47,   41,   59,   58,   41,   60,   61,   62,   43,   59,
   45,  267,   58,   60,   61,   62,   42,   43,   59,   45,
  258,   47,   58,   71,  258,   60,   61,   62,   42,   43,
   59,   45,   80,   47,   60,   61,   62,   85,   41,   42,
   43,   59,   45,    4,   47,   41,   60,   61,   62,   59,
   40,   41,   42,   43,  125,   45,   24,   47,  267,   41,
  108,  256,   24,   24,  267,  268,  269,  115,   59,   30,
    0,   32,  267,  268,  269,  267,  268,  269,  273,  274,
  275,   49,  277,   42,   43,   59,   45,  125,   47,  256,
  257,  267,  268,  269,  261,  262,  263,  264,  265,  266,
  267,  268,  269,  258,  259,  260,  273,  274,  275,  276,
  277,  123,  125,   59,  125,  123,  258,  258,  125,  123,
   88,  123,   90,   91,  172,  164,   88,  237,   90,   91,
   91,  218,   42,   43,   -1,   45,  184,   47,  273,  274,
  275,  109,   -1,   -1,   -1,   -1,   -1,  109,  109,   59,
   -1,   42,   43,  121,   45,  116,   47,   -1,   -1,  127,
  208,   -1,   -1,   -1,   40,  256,   42,   43,   59,   45,
   -1,   47,   -1,   -1,   -1,   -1,  267,  268,  269,   -1,
  271,   -1,  273,  274,  275,   -1,  277,  258,  259,  260,
   -1,   -1,   -1,   -1,   -1,   -1,  267,  268,  269,   42,
   43,  256,   45,   -1,   47,   -1,  174,  175,   -1,  256,
   -1,   -1,  174,  175,  269,   -1,   59,  272,  273,  274,
  275,  256,  269,   -1,   -1,  272,  273,  274,  275,   -1,
   -1,   -1,  267,  268,  269,   -1,   -1,   -1,  273,  274,
  275,   -1,  277,  211,   -1,   -1,  214,  273,  274,  275,
  218,   -1,   -1,  301,   -1,   -1,   -1,  225,   -1,  273,
  274,  275,   41,   42,   43,   -1,   45,   -1,   47,   41,
   42,   43,   -1,   45,   -1,   47,
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
#line 828 "Gramatica.y"

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
public static boolean llamadoFun=false;
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

	
#line 997 "y.tab.c"
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
#line 68 "Gramatica.y"
{ tablaDeSimbolos.add(new Simbolo(yyvsp[-2].sval+":"+ambito, 269, "constante", "I8", yyvsp[0].sval));}
break;
case 22:
#line 69 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-3].sval+":"+ambito, 269, "constante", "I8", yyvsp[-1].sval+yyvsp[0].sval));}
break;
case 23:
#line 70 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-2].sval+":"+ambito, 269, "constante", "F32", yyvsp[0].sval));}
break;
case 24:
#line 71 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-3].sval+":"+ambito, 269, "constante", "F32", yyvsp[-1].sval+yyvsp[0].sval));}
break;
case 25:
#line 73 "Gramatica.y"
{AgregarErrorSintactico("Se espera un identificador");}
break;
case 26:
#line 74 "Gramatica.y"
{AgregarErrorSintactico("Se espera una constante ");}
break;
case 27:
#line 75 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:' ");}
break;
case 28:
#line 78 "Gramatica.y"
{estructuraActual.setTipo("I8");}
break;
case 29:
#line 79 "Gramatica.y"
{estructuraActual.setTipo("F32");}
break;
case 30:
#line 82 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[-2].sval+":"+ambito,269,"variable",estructuraActual.getTipo()));}
break;
case 31:
#line 83 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[0].sval+":"+ambito,269,"variable", estructuraActual.getTipo()));}
break;
case 32:
#line 84 "Gramatica.y"
{AgregarErrorSintactico("Se espera ',' ");}
break;
case 33:
#line 87 "Gramatica.y"
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
#line 95 "Gramatica.y"
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
#line 103 "Gramatica.y"
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
#line 112 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 37:
#line 113 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 38:
#line 114 "Gramatica.y"
{AgregarErrorSintactico("Se espera el identificador de la funcion ");}
break;
case 39:
#line 115 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 40:
#line 116 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 41:
#line 117 "Gramatica.y"
{AgregarErrorSintactico("Se espera el tipo de retorno de la funcion");}
break;
case 42:
#line 120 "Gramatica.y"
{tablaDeSimbolos.add(new Simbolo(yyvsp[0].sval+":"+ambito, 269, "identificador_funcion"));
			 funcionActual=yyvsp[0].sval;
			 estructuraActual=new EstructuraTercetos(yyvsp[0].sval+":"+ambito);
			 listEstructurasSeguimiento.add(estructuraActual);
			 listEstructurasTercetos.add(estructuraActual);
			 dentroDeFun=true;
			 }
break;
case 43:
#line 129 "Gramatica.y"
{desconcatenarAmbito(); 
									   listEstructurasSeguimiento.remove(listEstructurasSeguimiento.size()-1);
									   estructuraActual=listEstructurasSeguimiento.get(listEstructurasSeguimiento.size()-1);
									   dentroDeFun=false;
									   }
break;
case 44:
#line 134 "Gramatica.y"
{AgregarErrorSintactico("Se espera el cuerpo de la funcion");}
break;
case 45:
#line 135 "Gramatica.y"
{AgregarErrorSintactico("Se espera el header de la funcion");}
break;
case 46:
#line 136 "Gramatica.y"
{AgregarErrorSintactico("Se espera el retorno de la funcion");}
break;
case 47:
#line 140 "Gramatica.y"
{Simbolo simbolo= new Simbolo(yyvsp[0].sval+":"+ambito+":"+funcionActual,269,"parametro", estructuraActual.getTipo());
					tablaDeSimbolos.add(simbolo);
					parametros.add(simbolo);}
break;
case 48:
#line 143 "Gramatica.y"
{AgregarErrorSintactico("Se espera tipo de parametro");}
break;
case 55:
#line 156 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito);
								if(tablaDeSimbolos.getUso(lex).equals("constante"))
									errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
								if (!mismoTipoExpID(yyvsp[-3].sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
								estructuraActual.crearTerceto(yyvsp[-2].sval, lex, yyvsp[-1].sval);
								}
break;
case 56:
#line 164 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
							if (!mismoTipoIds(yyvsp[-3].sval, yyvsp[-1].sval)) 
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(yyvsp[-2].sval, lex, tablaDeSimbolos.getRefSimbolo(yyvsp[-1].sval, ambito));}
break;
case 57:
#line 171 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito);
							if(tablaDeSimbolos.getUso(lex).equals("constante"))
								errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
							if (!mismoTipoIDCte(yyvsp[-3].sval, yyvsp[-1].sval))
								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(yyvsp[-2].sval, lex, yyvsp[-1].sval);}
break;
case 58:
#line 178 "Gramatica.y"
{String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[-4].sval, ambito);
									if(tablaDeSimbolos.getUso(lex).equals("constante"))
										errores_semanticos.add(new ErrorLinea("No se permite la asignacion a una constante", this.linea.getNumeroLinea()));
									if (!mismoTipoIDCte(yyvsp[-4].sval, yyvsp[-1].sval))
										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea())); 
									tablaDeSimbolos.setLexema(yyvsp[-2].sval, yyvsp[-1].sval);
									estructuraActual.crearTerceto(yyvsp[-3].sval, lex, yyvsp[-2].sval + yyvsp[-1].sval);}
break;
case 59:
#line 186 "Gramatica.y"
{AgregarErrorSintactico("Se espera '=:'");}
break;
case 60:
#line 188 "Gramatica.y"
{chequearCondicionWhen();
								}
break;
case 61:
#line 191 "Gramatica.y"
{AgregarErrorSintactico("Se espera un if o un when");}
break;
case 62:
#line 192 "Gramatica.y"
{AgregarErrorSintactico("Se espera la palabra reservada then");}
break;
case 63:
#line 195 "Gramatica.y"
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
#line 204 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
												estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
												dentroDeWhen=false; condicionWhenFalse=false;
												}
break;
case 65:
#line 208 "Gramatica.y"
{AgregarErrorSintactico("Se espera ';'");}
break;
case 66:
#line 212 "Gramatica.y"
{estructuraActual.crearTerceto("OUT", yyvsp[-1].sval, null);}
break;
case 67:
#line 213 "Gramatica.y"
{AgregarErrorSintactico("Se espera un out");}
break;
case 68:
#line 217 "Gramatica.y"
{
					if (!mismoTipoIds(yyvsp[-2].sval, yyvsp[0].sval)) 
						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
					
					estructuraActual.crearTerceto(yyvsp[-1].sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					estructuraActual.addTercetoWhen();
					listOperadores.add(yyvsp[-1].sval);
					valores.add(lex1);
					valores.add(lex2);
					}
break;
case 69:
#line 231 "Gramatica.y"
{
			if (!mismoTipoIDCte(yyvsp[-2].sval, yyvsp[0].sval)) 
		 		errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			
			String lex1 =tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
			estructuraActual.crearTerceto(yyvsp[-1].sval, lex1, yyvsp[0].sval);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add(lex1);
			valores.add(yyvsp[0].sval);
			listOperadores.add(yyvsp[-1].sval);
			}
break;
case 70:
#line 244 "Gramatica.y"
{
			if (!mismoTipoExpID(yyvsp[0].sval))
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
			valores.add(lex);
			
			estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			listOperadores.add(yyvsp[-1].sval);
			}
break;
case 71:
#line 256 "Gramatica.y"
{
			if (!mismoTipoExpCte(yyvsp[0].sval)) 
				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
			estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);
			yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
			estructuraActual.addTercetoWhen();
			valores.add(yyvsp[0].sval);
			listOperadores.add(yyvsp[-1].sval);
			}
break;
case 72:
#line 265 "Gramatica.y"
{if (!mismoTipoIds(yyvsp[-2].sval, yyvsp[0].sval))
						 errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
					
					estructuraActual.crearTerceto(yyvsp[-1].sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add(yyvsp[-1].sval);
					}
break;
case 73:
#line 278 "Gramatica.y"
{if (!mismoTipoIds(yyvsp[-2].sval, yyvsp[0].sval)) 
		 				errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
					String lex1 =tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
					String lex2 =tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
					
					estructuraActual.crearTerceto(yyvsp[-1].sval, lex1, lex2);
					yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
					valores.add(lex1);
					valores.add(lex2);
					estructuraActual.addTercetoWhen();
					listOperadores.add(yyvsp[-1].sval);
					}
break;
case 74:
#line 290 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[-2].sval, yyvsp[0].sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex= tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
							estructuraActual.crearTerceto(yyvsp[-1].sval, lex, yyvsp[0].sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							valores.add(yyvsp[0].sval);
							estructuraActual.addTercetoWhen();
							listOperadores.add(yyvsp[-1].sval);
							}
break;
case 75:
#line 300 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[-2].sval, yyvsp[0].sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex= tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
						estructuraActual.crearTerceto(yyvsp[-1].sval, lex, yyvsp[0].sval);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(yyvsp[0].sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(yyvsp[-1].sval);
						}
break;
case 76:
#line 311 "Gramatica.y"
{if (!mismoTipoExpID(yyvsp[0].sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
							estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(yyvsp[-1].sval);
							}
break;
case 77:
#line 321 "Gramatica.y"
{if (!mismoTipoExpID(yyvsp[0].sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							String lex= tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
							estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							valores.add(lex);
							estructuraActual.addTercetoWhen();
							listOperadores.add(yyvsp[-1].sval);
							}
break;
case 78:
#line 331 "Gramatica.y"
{if (!mismoTipoExpCte(yyvsp[0].sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add(yyvsp[0].sval);
							listOperadores.add(yyvsp[-1].sval);
							}
break;
case 79:
#line 339 "Gramatica.y"
{if (!mismoTipoExpCte(yyvsp[0].sval)) 
		 						errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
							estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);
							yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
							estructuraActual.addTercetoWhen();
							valores.add(yyvsp[0].sval);
							listOperadores.add(yyvsp[-1].sval);
							}
break;
case 81:
#line 350 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[0].sval, yyvsp[-2].sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
						estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(yyvsp[-2].sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(yyvsp[-1].sval);
						}
break;
case 82:
#line 361 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[0].sval, yyvsp[-2].sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
						estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(yyvsp[-2].sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(yyvsp[-1].sval);
						}
break;
case 83:
#line 371 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[0].sval, yyvsp[-2].sval)) 
		 					errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
						String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
						estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
						yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
						valores.add(lex);
						valores.add(yyvsp[-2].sval);
						estructuraActual.addTercetoWhen();
						listOperadores.add(yyvsp[-1].sval);
						}
break;
case 84:
#line 382 "Gramatica.y"
{String funcion=tablaDeSimbolos.getRefFuncion(yyvsp[-1].sval, ambito);
								if(funcion!=null){
		 						 	chequearYAsignarParametros(parametrosReales, parametrosFormales.get(funcion));
									llamadoFun=true;
								}
								estructuraActual.crearTerceto("LABEL"+funcion, null, null);
								yyval.sval=funcion;
								valores.add(funcion);
								parametrosReales.clear();
								}
break;
case 88:
#line 399 "Gramatica.y"
{parametrosReales.add(yyvsp[0].sval);}
break;
case 89:
#line 400 "Gramatica.y"
{parametrosReales.add(yyvsp[-1].sval+yyvsp[0].sval);}
break;
case 90:
#line 401 "Gramatica.y"
{parametrosReales.add(tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));}
break;
case 91:
#line 404 "Gramatica.y"
{if (conversionValida()){
												estructuraActual.crearTerceto("tof32", yyvsp[-1].sval, null);
											  }
											  	else errores_semanticos.add(new ErrorLinea("No se puede realizar la conversion", this.linea.getNumeroLinea()));
											  yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
break;
case 92:
#line 410 "Gramatica.y"
{AgregarErrorSintactico("Se espera expresion");}
break;
case 96:
#line 419 "Gramatica.y"
{chequearRangoEntero(yyvsp[0].sval);}
break;
case 102:
#line 429 "Gramatica.y"
{yyerror("falta cerrar parentesis");}
break;
case 105:
#line 437 "Gramatica.y"
{estructuraActual.completarTercetoIf(2);
			estructuraActual.crearTerceto("BI", null, null);
			estructuraActual.addTercetoIf();
			estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 107:
#line 444 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 108:
#line 447 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
									 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 109:
#line 449 "Gramatica.y"
{estructuraActual.completarTercetoIf(1);
								  		 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);}
break;
case 110:
#line 452 "Gramatica.y"
{yyerror("falta palabra reservada end_if");}
break;
case 111:
#line 453 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 112:
#line 454 "Gramatica.y"
{AgregarErrorSintactico("Se espera '{' '}' ");}
break;
case 113:
#line 457 "Gramatica.y"
{estructuraActual.crearTerceto("BF", yyvsp[-1].sval, null);
								estructuraActual.addTercetoIf();
								estructuraActual.addTercetoWhen();
								}
break;
case 114:
#line 461 "Gramatica.y"
{AgregarErrorSintactico("Falta cerrar parentesis");}
break;
case 115:
#line 462 "Gramatica.y"
{AgregarErrorSintactico("Falta abrir parentesis");}
break;
case 116:
#line 465 "Gramatica.y"
{listOperadores=listOperadores2; comparador=yyvsp[0].sval; valores=valores2;}
break;
case 117:
#line 466 "Gramatica.y"
{listOperadores=listOperadores2; comparador=yyvsp[0].sval; valores=valores2;}
break;
case 118:
#line 467 "Gramatica.y"
{listOperadores=listOperadores2; comparador=yyvsp[0].sval;valores=valores2;}
break;
case 119:
#line 468 "Gramatica.y"
{listOperadores=listOperadores2; comparador=yyvsp[0].sval;valores=valores2;}
break;
case 120:
#line 469 "Gramatica.y"
{listOperadores=listOperadores2; comparador=yyvsp[0].sval;valores=valores2;}
break;
case 121:
#line 470 "Gramatica.y"
{listOperadores=listOperadores2; comparador=yyvsp[0].sval;valores=valores2;}
break;
case 122:
#line 473 "Gramatica.y"
{if (!mismoTipo()) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);
												estructuraActual.addTercetoWhen();
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											
											}
break;
case 123:
#line 481 "Gramatica.y"
{if (!mismoTipoExpCte(yyvsp[0].sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										
											estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);
											estructuraActual.addTercetoWhen();
											valores2.add(yyvsp[0].sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										
										}
break;
case 124:
#line 490 "Gramatica.y"
{if (!mismoTipoExpCte(yyvsp[0].sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(yyvsp[-2].sval, yyvsp[-3].sval, yyvsp[-1].sval + yyvsp[0].sval );
												
												estructuraActual.addTercetoWhen();
												valores2.add(yyvsp[-1].sval+yyvsp[0].sval);
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema(yyvsp[-1].sval, yyvsp[0].sval);
											}
break;
case 125:
#line 500 "Gramatica.y"
{if (!mismoTipoExpCte(yyvsp[-2].sval)) 
		   									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   								
											estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);
											estructuraActual.addTercetoWhen();
											valores1.add(yyvsp[-2].sval);
											yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 126:
#line 508 "Gramatica.y"
{if (!mismoTipoExpCte(yyvsp[-2].sval)) 
		   										errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											
												estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-3].sval + yyvsp[-2].sval, yyvsp[0].sval);
												estructuraActual.addTercetoWhen();
												valores1.add(yyvsp[-3].sval+yyvsp[-2].sval);
												yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
												tablaDeSimbolos.setLexema(yyvsp[-3].sval, yyvsp[-2].sval);
											}
break;
case 127:
#line 517 "Gramatica.y"
{if (!mismoTipoExpID(yyvsp[0].sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   								String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
										estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
										estructuraActual.addTercetoWhen();
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 128:
#line 525 "Gramatica.y"
{if (!mismoTipoExpID(yyvsp[-2].sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

										String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
										estructuraActual.crearTerceto(yyvsp[-1].sval, lex, yyvsp[0].sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									}
break;
case 129:
#line 534 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[0].sval, yyvsp[-2].sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
									estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, lex);
									estructuraActual.addTercetoWhen();
									valores1.add(yyvsp[-2].sval);
									valores2.add(lex);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 130:
#line 544 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[0].sval, yyvsp[-2].sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
										estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-3].sval + yyvsp[-2].sval, lex);
										estructuraActual.addTercetoWhen();
										valores1.add(yyvsp[-3].sval +yyvsp[-2].sval);
										valores2.add(lex);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(yyvsp[-3].sval, yyvsp[-2].sval);
									}
break;
case 131:
#line 555 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[-2].sval, yyvsp[0].sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));

									String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
									estructuraActual.crearTerceto(yyvsp[-1].sval, lex, yyvsp[0].sval);
									estructuraActual.addTercetoWhen();
									valores1.add(yyvsp[0].sval);
									valores2.add(lex);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 132:
#line 565 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[-3].sval, yyvsp[0].sval)) 
		   								errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							
										String lex=tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito);
										estructuraActual.crearTerceto(yyvsp[-2].sval, lex, yyvsp[-1].sval + yyvsp[0].sval);
										estructuraActual.addTercetoWhen();
										valores1.add(lex);
										valores2.add(yyvsp[-1].sval+yyvsp[0].sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										tablaDeSimbolos.setLexema(yyvsp[-1].sval, yyvsp[0].sval);
									}
break;
case 133:
#line 576 "Gramatica.y"
{if (!mismoTipoIds(yyvsp[-2].sval, yyvsp[0].sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   						
									String lex1=tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito);
									String lex2=tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito);
									estructuraActual.crearTerceto(yyvsp[-1].sval, lex1, lex2);
									estructuraActual.addTercetoWhen();
									
									valores1.add(lex1);
									valores2.add(lex2);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 134:
#line 588 "Gramatica.y"
{if (!mismoTipoCtes(yyvsp[-2].sval, yyvsp[0].sval)) 
		   							errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		   							estructuraActual.crearTerceto(yyvsp[-1].sval, yyvsp[-2].sval, yyvsp[0].sval);
									estructuraActual.addTercetoWhen();
									valores1.add(yyvsp[-2].sval);
									valores2.add(yyvsp[0].sval);
									yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 135:
#line 596 "Gramatica.y"
{yyerror("falta expresion en la comparacion");}
break;
case 136:
#line 597 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 137:
#line 598 "Gramatica.y"
{yyerror("falta comparador");}
break;
case 144:
#line 612 "Gramatica.y"
{estructuraActual.crearTerceto("BF", yyvsp[-4].sval, null);
				 refEtiqueta = yyvsp[-6].sval; /*estructuraActual.getRefTerceto(2);*/
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto(yyvsp[-2].sval, estructuraActual.getIdFor(), yyvsp[-1].sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 145:
#line 621 "Gramatica.y"
{estructuraActual.addNumCondicionFor();
				 estructuraActual.crearTerceto("BF", yyvsp[-3].sval, null);
				 refEtiqueta = yyvsp[-5].sval; /*estructuraActual.getRefTerceto(2);*/
				 estructuraActual.addTercetoFor();
				 estructuraActual.crearTerceto("+", estructuraActual.getIdFor(), yyvsp[-1].sval);
				 estructuraActual.crearTerceto("=:", estructuraActual.getIdFor(), estructuraActual.getRefTerceto(1));
				 estructuraActual.crearListTercetoBreak();
				 dentroDeFor=true;
				 }
break;
case 146:
#line 632 "Gramatica.y"
{estructuraActual.crearListTercetoBreakCte();
										esperandoBreakcte=true;
										estructuraActual.addIdAsigFor(tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval,ambito));
										}
break;
case 147:
#line 638 "Gramatica.y"
{estructuraActual.addEtiquetaFor(yyvsp[-1].sval);}
break;
case 148:
#line 641 "Gramatica.y"
{estructuraActual.addRefEtiqueta(refEtiqueta);}
break;
case 149:
#line 645 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[-2].sval, yyvsp[0].sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito));
		estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getIdFor(), yyvsp[0].sval);
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 150:
#line 654 "Gramatica.y"
{if (!mismoTipoIds(yyvsp[-2].sval, yyvsp[0].sval)) 
			errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
		estructuraActual.addIdFor(tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito));
		estructuraActual.crearTerceto(yyvsp[-1].sval, estructuraActual.getIdFor(), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));
		estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
		estructuraActual.addNumCondicionFor();
		yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
	 	}
break;
case 151:
#line 665 "Gramatica.y"
{estructuraActual.completarTercetoFor(1);
					 estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(), null, null);
					 estructuraActual.crearTerceto("BI", "[" + estructuraActual.getNumeroTercetoCondicionFor() + "]", null);
					 estructuraActual.popIdFor();}
break;
case 152:
#line 670 "Gramatica.y"
{AgregarErrorSintactico("Se esperan sentencias ejecutables");}
break;
case 153:
#line 674 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);

					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=!estructuraActual.vaciaListTercetoBreak();

					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
				}
break;
case 154:
#line 682 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);

					estructuraActual.borrarListTercetosBreak();
					dentroDeFor=!estructuraActual.vaciaListTercetoBreak();

					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
				}
break;
case 155:
#line 690 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-1].sval);
					estructuraActual.completarTercetosBreakCte(1);

					estructuraActual.borrarListTercetosBreak();
					estructuraActual.borrarListTercetosBreakCte();
					dentroDeFor=!estructuraActual.vaciaListTercetoBreak();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
					}
break;
case 156:
#line 704 "Gramatica.y"
{
					estructuraActual.completarTercetosBreak(1);
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-2].sval + yyvsp[-1].sval);
					estructuraActual.completarTercetosBreakCte(1);

					estructuraActual.borrarListTercetosBreak();
					estructuraActual.borrarListTercetosBreakCte();
					dentroDeFor=!estructuraActual.vaciaListTercetoBreak();
					esperandoBreakcte=!estructuraActual.vaciaListTercetoBreakCte();

					estructuraActual.borrarIdAsigFor();
					estructuraActual.crearTerceto("LABEL"+estructuraActual.cantTercetos(),null,null);
					tablaDeSimbolos.setLexema(yyvsp[-2].sval, yyvsp[-1].sval);
				}
break;
case 157:
#line 721 "Gramatica.y"
{if (!mismoTipoIds(yyvsp[-2].sval, yyvsp[0].sval)) 
									errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
								estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), tablaDeSimbolos.getRefSimbolo(yyvsp[0].sval, ambito));
								yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
								}
break;
case 158:
#line 727 "Gramatica.y"
{if (!mismoTipoExpID(yyvsp[-2].sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
									   estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);
									   yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
									   }
break;
case 159:
#line 733 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[-2].sval, yyvsp[0].sval)) 
											errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
										estructuraActual.crearTerceto(yyvsp[-1].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito), yyvsp[0].sval);
										yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
										}
break;
case 160:
#line 739 "Gramatica.y"
{if (!mismoTipoIDCte(yyvsp[-3].sval, yyvsp[-1].sval)) 
												errores_semanticos.add(new ErrorLinea("Tipos incompartibles", this.linea.getNumeroLinea()));
											  tablaDeSimbolos.setLexema(yyvsp[-1].sval, yyvsp[0].sval);
											  estructuraActual.crearTerceto(yyvsp[-2].sval, tablaDeSimbolos.getRefSimbolo(yyvsp[-3].sval, ambito), yyvsp[-1].sval+yyvsp[0].sval);
											  yyval.sval="["+String.valueOf(estructuraActual.cantTercetos()-1)+"]";
											  }
break;
case 161:
#line 747 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No se encuentra dentro de una funcion", this.linea.getNumeroLinea()));
						System.out.println("No se encuentra dentro de una funcion");
					}
					else
						if (llamadoFun){
							estructuraActual.crearTerceto("BI",null, null);
						}
						estructuraActual.crearTerceto("=:", ambito, tablaDeSimbolos.getRefSimbolo(yyvsp[-2].sval, ambito));
				}
break;
case 162:
#line 758 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else
						if (llamadoFun){
							estructuraActual.crearTerceto("BI", null, null);
						}
						estructuraActual.crearTerceto("=:", ambito, yyvsp[-2].sval);
				}
break;
case 163:
#line 769 "Gramatica.y"
{
					if(!dentroDeFun){
						errores_semanticos.add(new ErrorLinea("No existe ID para la asignacion", this.linea.getNumeroLinea()));
						System.out.println("No existe ID para la asignacion");
					}
					else
						if (llamadoFun){
							estructuraActual.crearTerceto("BI", null, null);
						}
						estructuraActual.crearTerceto("=:", ambito, yyvsp[-2].sval);
				}
break;
case 164:
#line 780 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 165:
#line 781 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 166:
#line 782 "Gramatica.y"
{AgregarErrorSintactico("Falta ;");}
break;
case 167:
#line 783 "Gramatica.y"
{AgregarErrorSintactico("Falta expresion de retorno");}
break;
case 169:
#line 787 "Gramatica.y"
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
case 170:
#line 797 "Gramatica.y"
{
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-1].sval);
					estructuraActual.crearTerceto("BI", null, null);
					estructuraActual.guardarTercetoBreakCte();
				}
				else{
					errores_semanticos.add(new ErrorLinea("No se espera el retorno de una sentencia de control", this.linea.getNumeroLinea()));
					System.out.println("No se espera el retorno de una sentencia de control");
				}
				}
break;
case 171:
#line 808 "Gramatica.y"
{
				if((dentroDeFor) && (esperandoBreakcte)){
					estructuraActual.crearTerceto("=:", estructuraActual.getIdAsigFor(), yyvsp[-2].sval + yyvsp[-1].sval);
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
#line 819 "Gramatica.y"
{
				if ((dentroDeFor) && (estructuraActual.existeEtiquetaFor(yyvsp[-1].sval))){
					estructuraActual.crearTerceto("BI", estructuraActual.getRefEtiqueta(yyvsp[-1].sval), null);
				}
			  }
break;
#line 2159 "y.tab.c"
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
