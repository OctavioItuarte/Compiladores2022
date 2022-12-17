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
@varFloat1@0 dd 1.0
@varFloat2@0 dd 2.0
b_ee dd ?
a_ee dd ?
@afuera_$_ db "afuera - ", 0
@a_es_1@0_$_ db "a es 1.0 - ", 0
@putooo_$_ db "putooo - ", 0
@bien_la_multiplicacion_$_ db "bien la multiplicacion - ", 0
@a_es_2@0_$_ db "a es 2.0 - ", 0
@qbslcdll_$_ db "qbslcdll - ", 0
i_ee_multiplicacion db ?
multiplicacion_ee db ?
w_ee db ?
j_ee_multiplicacion db ?
q_ee db ?
@aux1Float dd ? 
@aux1Int db ? 
@conv dd ? 
@auxOpInt db ?
ID db "ID:" 
aux_mem_2bytes dw ? 
stword dw ? 
msj_exc1 db "Exception: Resultado de suma entre enteros excede el rango permitido", 0 
msj_exc2 db "Exception: Resultado de suma entre flotantes excede el rango permitido", 0 
msj_exc3 db "Exception: invocacion a funcion no permitida (invocacion mutua)", 0 
FUNCION_programa_principal db 0 
FUNCION_multiplicacion_ee db 0 
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
LABEL_multiplicacion_ee:mov al, i_ee_multiplicacion
imul j_ee_multiplicacion
mov multiplicacion_ee, al
ret 
START:
finit
fld @varFloat1@0
fst a_ee
fld @varFloat1@0
fst b_ee
mov bl, 1
mov q_ee, bl
mov bl, 2
mov w_ee, bl
mov bl, q_ee
mov i_ee_multiplicacion, bl
mov bl, w_ee
mov j_ee_multiplicacion, bl
cmp FUNCION_multiplicacion_ee, 1
je RECURSION_MUTUA 
mov FUNCION_programa_principal, 1
call LABEL_multiplicacion_ee
mov FUNCION_programa_principal, 0
mov bl, multiplicacion_ee
cmp bl, 2
jne LABEL0
invoke StdOut, addr @bien_la_multiplicacion_$_
LABEL0: 
fld a_ee
fcomp @varFloat1@0
FSTSW aux_mem_2bytes 
MOV AX , aux_mem_2bytes 
SAHF 
jne LABEL1
invoke StdOut, addr @a_es_1@0_$_
fld a_ee
fadd b_ee
fstsw stword
fwait 
and ax, 8h 
cmp ax, 8h 
jnz OVERFLOW_FLOTANTE
fst a_ee
LABEL1: 
fld a_ee
fcomp @varFloat2@0
FSTSW aux_mem_2bytes 
MOV AX , aux_mem_2bytes 
SAHF 
jne LABEL2
invoke StdOut, addr @a_es_2@0_$_
fld a_ee
fadd b_ee
fstsw stword
fwait 
and ax, 8h 
cmp ax, 8h 
jnz OVERFLOW_FLOTANTE
fst a_ee
LABEL2: 
invoke StdOut, addr @afuera_$_
fld a_ee
fadd a_ee
fstsw stword
fwait 
and ax, 8h 
cmp ax, 8h 
jnz OVERFLOW_FLOTANTE
fst a_ee
fld @varFloat3@3F?38
fst a_ee
invoke StdOut, addr @qbslcdll_$_
fld a_ee
fadd a_ee
fstsw stword
fwait 
and ax, 8h 
cmp ax, 8h 
jnz OVERFLOW_FLOTANTE
fst a_ee
invoke StdOut, addr @putooo_$_
finit 
END START