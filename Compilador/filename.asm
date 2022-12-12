.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib
.data
@varFloat3@0 dd 3.0
@varFloat$3@0 dd -3.0
u_ee_aa db 100
b_ee db -2
a_ee db -1
bb_ee dd ?
r_ee_aa dd ?
f_ee dd ?
x_ee_aa_cc dd ?
w_ee dd ?
aa_ee dd ?
g_ee_aa dd ?
Impresion_desde_funcion_bb db "Impresion desde funcion bb"
Impresion_desde_funcion_aa db "Impresion desde funcion aa"
num_ee_aa db ?
num_ee db ?
t_ee db ?
m_ee_aa db ?
d_ee db ?
n_ee db ?
i_ee db ?
num_ee_bb db ?
cc_ee_aa db ?
c_ee db ?
q_ee db ?
@aux1Float dd ? 
@aux1Int db ? 
@conv dd ? 
@auxOpInt db ?
ID db "ID:" 
msj_exc1 db "Exception: Resultado de suma entre enteros excede el rango permitido" 
msj_exc2 db "Exception: Resultado de suma entre flotantes excede el rango permitido" 
msj_exc3 db "Exception: invocacion a funcion no permitida (invocacion mutua)" 
FUNCION_programa_principal db 0 
FUNCION_bb_ee db 0 
FUNCION_aa_ee db 0 
FUNCION_cc_ee_aa db 0 
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
LABEL_bb_ee:mov bl, 120
add bl, 120
jo OVERFLOW_ENTERO
mov num_ee_bb, bl
invoke StdOut, addr Impresion_desde_funcion_bb
fld @varFloat$3@0
fst bb_ee
ret 
LABEL_aa_ee:invoke StdOut, addr Impresion_desde_funcion_aa
fld g_ee_aa
fst x_ee_aa_cc
cmp FUNCION_cc_ee_aa, 1
je RECURSION_MUTUA 
mov FUNCION_aa_ee, 1
call LABEL_cc_ee_aa
mov FUNCION_aa_ee, 0
mov bl, cc_ee_aa
mov m_ee_aa, bl
mov @auxOpInt, 3
mov al, num_ee_aa
idiv @auxOpInt
mov @auxOpInt, 1
mov al, al
idiv @auxOpInt
mov al, m_ee_aa
idiv i_ee
mov @auxOpInt, 1
mov al, al
idiv @auxOpInt
cmp al, al
jl LABEL0
mov bl, 120
add bl, 120
jo OVERFLOW_ENTERO
mov n_ee, bl
fld r_ee_aa
fst aa_ee
ret 
jmp LABEL1
LABEL0: mov bl, i_ee
mov m_ee_aa, bl
LABEL1: mov bl, 2
add bl, 1
jo OVERFLOW_ENTERO
mov @conv, ebx
cbw 
fild @conv 
fst aa_ee
ret 
LABEL_cc_ee_aa:mov bl, 45
mov cc_ee_aa, bl
ret 
START:
mov bl, 0
mov num_ee, bl
mov bl, 0
mov d_ee, bl
mov bl, 0
mov q_ee, bl
mov bl, 0
mov i_ee, bl
mov bl, 0
mov c_ee, bl
mov bl, 0
mov t_ee, bl
mov bl, 0
mov n_ee, bl
mov bl, a_ee
sub bl, 1
cmp bl, b_ee
je LABEL2
mov bl, t_ee
mov c_ee, bl
LABEL2: mov bl, i_ee
cmp bl, c_ee
jl LABEL3
mov bl, q_ee
mov d_ee, bl
LABEL3: mov bl, n_ee
mov i_ee, bl
LABEL4: mov bl, t_ee
add bl, 1
jo OVERFLOW_ENTERO
cmp i_ee, bl
jl LABEL13
mov bl, i_ee
sub bl, 3
mov i_ee, bl
mov bl, n_ee
mov i_ee, bl
LABEL5: mov bl, t_ee
add bl, 1
jo OVERFLOW_ENTERO
cmp i_ee, bl
jl LABEL9
mov bl, i_ee
sub bl, 3
mov i_ee, bl
jmp LABEL9
mov bl, n_ee
mov i_ee, bl
LABEL6: mov bl, t_ee
add bl, 1
jo OVERFLOW_ENTERO
cmp i_ee, bl
jl LABEL8
mov bl, i_ee
sub bl, 3
mov i_ee, bl
jmp LABEL8
mov bl, i_ee
cmp bl, 3
jl LABEL7
jmp LABEL5
LABEL7: mov bl, -2
mov i_ee, bl
jmp LABEL14
mov bl, d_ee
mov d_ee, bl
jmp LABEL6
LABEL8: jmp LABEL5
LABEL9: mov bl, t_ee
cmp bl, 3
jl LABEL10
mov bl, 1
mov i_ee, bl
mov bl, 5
mov i_ee, bl
jmp LABEL14
jmp LABEL12
LABEL10: mov bl, t_ee
cmp bl, 2
jg LABEL11
mov bl, -5
mov i_ee, bl
LABEL11: jmp LABEL13
LABEL12: jmp LABEL4
LABEL13: mov bl, 1
mov i_ee, bl
LABEL14: mov @auxOpInt, 5
mov al, q_ee
idiv @auxOpInt
add al, 1
jo OVERFLOW_ENTERO
mov @conv, eax
cbw 
fild @conv 
fst w_ee
mov bl, 0
mov num_ee_aa, bl
cmp FUNCION_aa_ee, 1
je RECURSION_MUTUA 
mov FUNCION_programa_principal, 1
call LABEL_aa_ee
mov FUNCION_programa_principal, 0
fld aa_ee
fst w_ee
end_program:END START