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
awf_ee dd ?
q_ee_awf dd ?
w_ee dd ?
aa_ee dd ?
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
msj_exc1 db "Exception: Resultado de suma entre enteros excede el rango permitido" 
msj_exc2 db "Exception: Resultado de suma entre flotantes excede el rango permitido" 
msj_exc3 db "Exception: invocacion a funcion no permitida (invocacion mutua)" 
NAME_LAST_FUNCTION db ? 
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
LABEL_awf_ee:mov bl, 120
mov num_ee_awf, bl
mov bl, q_ee_awf
mov awf_ee, bl
ret 
LABEL_aa_ee:mov bl, 120
mov m_ee_aa, bl
mov al, num_ee_aa
sub al, 3
mov bl, m_ee_aa
add bl, i_ee
cmp bl, 128 
jge OVERFLOW_ENTERO
cmp al, bl
jl LABEL0
mov bl, 120
mov n_ee, bl
mov bl, 3
mov aa_ee, bl
ret 
jmp LABEL1
LABEL0: mov bl, i_ee
mov m_ee_aa, bl
LABEL1: mov bl, 1
mov aa_ee, bl
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
mov al, a_ee
sub al, 1
cmp al, b_ee
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
LABEL4: mov al, t_ee
add al, 1
cmp al, 128 
jge OVERFLOW_ENTERO
cmp i_ee, al
jl LABEL13
mov al, i_ee
sub al, 3
mov i_ee, al
mov bl, n_ee
mov i_ee, bl
LABEL5: mov al, t_ee
add al, 1
cmp al, 128 
jge OVERFLOW_ENTERO
cmp i_ee, al
jl LABEL9
mov al, i_ee
sub al, 3
mov i_ee, al
jmp LABEL9
mov bl, n_ee
mov i_ee, bl
LABEL6: mov al, t_ee
add al, 1
cmp al, 128 
jge OVERFLOW_ENTERO
cmp i_ee, al
jl LABEL8
mov al, i_ee
sub al, 3
mov i_ee, al
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
mov bl, 5
mov i_ee, bl
LABEL11: jmp LABEL13
LABEL12: jmp LABEL4
LABEL13: mov bl, 1
mov i_ee, bl
LABEL14: mov bl, 0
mov num_ee_aa, bl
call LABEL_aa_ee
mov bl, aa_ee
mov w_ee, bl
END START