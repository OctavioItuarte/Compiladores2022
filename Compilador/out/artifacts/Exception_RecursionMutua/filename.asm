.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib
.data
u_ee_aa db 100
bb_ee dd ?
r_ee_aa dd ?
flotante_ee dd ?
a_ee_aa_cc dd ?
x_ee_aa_cc dd ?
w_ee dd ?
aa_ee dd ?
g_ee_aa dd ?
num_ee_aa db ?
num_ee db ?
m_ee_aa db ?
b_ee db ?
d_ee db ?
f_ee db ?
num_ee_bb db ?
cc_ee_aa db ?
a_ee db ?
c_ee db ?
e_ee db ?
@aux1 dd ? 
@conv dd ? 
ID db "ID:" 
msj_exc1 db "Exception: Resultado de suma entre enteros excede el rango permitido" 
msj_exc2 db "Exception: Resultado de suma entre flotantes excede el rango permitido" 
msj_exc3 db "Exception: invocacion a funcion no permitida (invocacion mutua)" 
programa_principal db 0 
ee_bb db 0 
ee_aa db 0 
ee_aa_cc db 0 
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
LABEL_ee_bb:mov bl, 120
add bl, 120
jo OVERFLOW_ENTERO
mov num_ee_bb, bl
fld flotante_ee
fst bb_ee
ret 
LABEL_ee_aa:fld g_ee_aa
fst x_ee_aa_cc
cmp ee_aa_cc, 1
je RECURSION_MUTUA 
mov ee_aa, 1
call LABEL_ee_aa_cc
mov ee_aa, 0
mov bl, cc_ee_aa
mov m_ee_aa, bl
mov bl, num_ee_aa
sub bl, 3
mov al, m_ee_aa
idiv b_ee
cmp bl, al
jl LABEL0
mov bl, 120
mov m_ee_aa, bl
fld r_ee_aa
fst aa_ee
ret 
jmp LABEL1
LABEL0: mov bl, b_ee
mov m_ee_aa, bl
LABEL1: mov bl, 2
add bl, 1
jo OVERFLOW_ENTERO
mov @conv, ebx
cbw 
fild @conv 
fst aa_ee
ret 
LABEL_ee_aa_cc:mov cl, 3
mov num_ee_aa, cl
cmp ee_aa, 1
je RECURSION_MUTUA 
mov ee_aa_cc, 1
call LABEL_ee_aa
mov ee_aa_cc, 0
fld aa_ee
fst a_ee_aa_cc
mov cl, 45
mov cc_ee_aa, cl
ret 
START:
mov cl, 0
mov num_ee, cl
mov cl, 0
mov a_ee, cl
mov cl, 0
mov b_ee, cl
mov cl, 0
mov c_ee, cl
mov cl, 0
mov d_ee, cl
mov cl, 0
mov e_ee, cl
mov cl, 0
mov f_ee, cl
mov cl, a_ee
add cl, 5
jo OVERFLOW_ENTERO
mov @conv, ecx
cbw 
fild @conv 
fst w_ee
mov @aux1, ecx 
mov cl, 0
mov num_ee_aa, cl
cmp ee_aa, 1
je RECURSION_MUTUA 
mov programa_principal, 1
call LABEL_ee_aa
mov programa_principal, 0
fld aa_ee
fst w_ee
end_program:END START