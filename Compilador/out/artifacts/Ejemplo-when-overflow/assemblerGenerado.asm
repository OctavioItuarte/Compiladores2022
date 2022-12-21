.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib
includelib \masm32\lib\user32.lib
.data
@varFloat3@3F?38 dd 3.3F+38
b_programa db 2
a_programa db 1
y_programa dd ?
x_programa dd ?
@if_primer_when_$_ db "if primer when - ", 0
@if_segundo_when_$_ db "if segundo when - ", 0
@j_es_igual_a_80_$_ db "j es igual a 80 - ", 0
@segundo_when_$_ db "segundo when - ", 0
@i_es_mayor_a_120_$_ db "i es mayor a 120 - ", 0
@primer_when_$_ db "primer when - ", 0
@x_es_3@3F?38_$_ db "x es 3.3F+38 - ", 0
j_programa db ?
i_programa db ?
@aux1Float dd ? 
@aux1Int db ? 
@conv dd ? 
@auxOpInt db ?
ID db "ID:" 
aux_mem_2bytes dw ? 
stword dw ? 
aux_stword dw ? 
maximoFloat dd 3.40282347F+38 
oldcw dw ?
msj_exc1 db "Exception: Resultado de suma entre enteros excede el rango permitido", 0 
msj_exc2 db "Exception: Resultado de suma entre flotantes excede el rango permitido", 0 
msj_exc3 db "Exception: invocacion a funcion no permitida (invocacion mutua)", 0 
FUNCION_programa_principal db 0 
LAST_FUNCTION db ? 
AUX_NAME_LAST_FUNCTION db ? 
.CODE
OVERFLOW_ENTERO: invoke StdOut, addr msj_exc1 
invoke ExitProcess, 0 
ret 
OVERFLOW_FLOTANTE: invoke StdOut, addr msj_exc2 
invoke ExitProcess, 0 
ret 
RECURSION_MUTUA: invoke StdOut, addr msj_exc3 
invoke ExitProcess, 0 
ret 
START:
finit
fwait
invoke StdOut, addr @primer_when_$_
invoke StdOut, addr @if_primer_when_$_
LABEL0: 
LABEL1: 
mov bl, 80
mov j_programa, bl
mov bl, j_programa
cmp bl, 80
jne LABEL4
invoke StdOut, addr @j_es_igual_a_80_$_
LABEL4: 
mov bl, 120
add bl, j_programa
jo OVERFLOW_ENTERO
mov i_programa, bl
mov bl, i_programa
cmp bl, 120
jle LABEL5
invoke StdOut, addr @i_es_mayor_a_120_$_
LABEL5: 
FINIT 
FWAIT 
fld @varFloat3@3F?38
fstp x_programa
FINIT 
FWAIT 
FINIT 
FWAIT 
fld x_programa
fcomp @varFloat3@3F?38
FSTSW aux_mem_2bytes 
MOV AX , aux_mem_2bytes 
SAHF 
jne LABEL6
invoke StdOut, addr @x_es_3@3F?38_$_
LABEL6: 
FINIT 
FWAIT 
fld x_programa
fld @varFloat3@3F?38
fadd
FNSTSW stword 
and stword, 8
jnz OVERFLOW_FLOTANTE 
FINIT 
FWAIT 
FINIT 
FWAIT 
fstp y_programa
FINIT 
FWAIT 
finit 
fwait
END START