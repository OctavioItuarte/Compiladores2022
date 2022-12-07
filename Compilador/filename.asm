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
b_ee db -2
a_ee db -1
r_ee_aa dd ?
awf_ee dd ?
q_ee_awf dd ?
w_ee dd ?
aa_ee dd ?
holaaaaa db "holaaaaa"
num_ee_aa db ?
num_ee db ?
t_ee db ?
m_ee_aa db ?
d_ee db ?
n_ee db ?
i_ee db ?
num_ee_awf db ?
c_ee db ?
q_ee db ?
@aux1 dd ? 
@conv dd ? 
ID db "ID:" 
msj_exc1 db "Exception: Resultado de suma entre enteros excede el rango permitido" 
msj_exc2 db "Exception: Resultado de suma entre flotantes excede el rango permitido" 
msj_exc3 db "Exception: invocacion a funcion no permitida (invocacion mutua)" 
programa_principal db "programa_principal" 
ee_awf db "ee_awf" 
ee_aa db "ee_aa" 
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
LABEL_ee_awf:mov bl, 120
add bl, 120
jo OVERFLOW_ENTERO
mov num_ee_awf, bl
fld q_ee_awf
fst awf_ee
ret 
LABEL_ee_aa:invoke StdOut, addr holaaaaa
mov bl, 120
mov m_ee_aa, bl
mov bl, num_ee_aa
sub bl, 3
add bl, 1
jo OVERFLOW_ENTERO
add bl, 3
jo OVERFLOW_ENTERO
mov cl, m_ee_aa
add cl, i_ee
jo OVERFLOW_ENTERO
cmp bl, cl
jl LABEL0
mov bl, 120
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
START:
mov cl, 0
mov num_ee, cl
mov cl, 0
mov d_ee, cl
mov cl, 0
mov q_ee, cl
mov cl, 0
mov i_ee, cl
mov cl, 0
mov c_ee, cl
mov cl, 0
mov t_ee, cl
mov cl, 0
mov n_ee, cl
mov cl, a_ee
sub cl, 1
cmp cl, b_ee
je LABEL2
mov cl, t_ee
mov c_ee, cl
LABEL2: mov cl, i_ee
cmp cl, c_ee
jl LABEL3
mov cl, q_ee
mov d_ee, cl
LABEL3: mov cl, n_ee
mov i_ee, cl
LABEL4: mov cl, t_ee
add cl, 1
jo OVERFLOW_ENTERO
cmp i_ee, cl
jl LABEL13
mov cl, i_ee
sub cl, 3
mov i_ee, cl
mov cl, n_ee
mov i_ee, cl
LABEL5: mov cl, t_ee
add cl, 1
jo OVERFLOW_ENTERO
cmp i_ee, cl
jl LABEL9
mov cl, i_ee
sub cl, 3
mov i_ee, cl
jmp LABEL9
mov cl, n_ee
mov i_ee, cl
LABEL6: mov cl, t_ee
add cl, 1
jo OVERFLOW_ENTERO
cmp i_ee, cl
jl LABEL8
mov cl, i_ee
sub cl, 3
mov i_ee, cl
jmp LABEL8
mov cl, i_ee
cmp cl, 3
jl LABEL7
jmp LABEL5
LABEL7: mov cl, -2
mov i_ee, cl
jmp LABEL14
mov cl, d_ee
mov d_ee, cl
jmp LABEL6
LABEL8: jmp LABEL5
LABEL9: mov cl, t_ee
cmp cl, 3
jl LABEL10
mov cl, 1
mov i_ee, cl
mov cl, 5
mov i_ee, cl
jmp LABEL14
jmp LABEL12
LABEL10: mov cl, t_ee
cmp cl, 2
jg LABEL11
mov cl, -5
mov i_ee, cl
LABEL11: jmp LABEL13
LABEL12: jmp LABEL4
LABEL13: mov cl, 1
mov i_ee, cl
LABEL14: mov cl, q_ee
add cl, 5
jo OVERFLOW_ENTERO
mov @conv, ecx
cbw 
fild @conv 
fst w_ee
mov @aux1, ecx 
mov cl, 0
mov num_ee_aa, cl
call LABEL_ee_aa
fld aa_ee
fst w_ee
end_program:END START