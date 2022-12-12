.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib
.data
a_ee db 1
b_ee db 3
c_ee db 20
aa_ee db ?
@aux1 dd ? 
@conv dd ? 
ID db "ID:" 
msj_exc1 db "Exception: Resultado de suma entre enteros excede el rango permitido" 
msj_exc2 db "Exception: Resultado de suma entre flotantes excede el rango permitido" 
msj_exc3 db "Exception: invocacion a funcion no permitida (invocacion mutua)" 
programa_principal db 0 
LAST_FUNCTION db ? 
AUX_NAME_LAST_FUNCTION db ? 
.CODE
OVERFLOW_ENTERO: invoke StdOut, addr msj_exc1 
jmp end_program
invoke ExitProcess, 0 
ret 
OVERFLOW_FLOTANTE: invoke StdOut, addr msj_exc2 
jmp end_program
invoke ExitProcess, 0 
ret 
RECURSION_MUTUA: invoke StdOut, addr msj_exc3 
jmp end_program
invoke ExitProcess, 0 
ret 
START:
mov bl, 20
mov aa_ee, bl
mov bl, 2
mov aa_ee, bl
LABEL0: LABEL1: end_program:END START