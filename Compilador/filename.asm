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
num_ee_aa db ?
num_ee db ?
t_ee db ?
m_ee_aa db ?
d_ee db ?
n_ee db ?
i_ee db ?
awf_ee db ?
num_ee_awf db ?
q_ee_awf db ?
w_ee db ?
aa_ee db ?
c_ee db ?
q_ee db ?
.CODE
OVERFLOWENTERO: cmp ebp, 128 
je FIN_EXCEPCION_OVERFLOW_ENTERO 
invoke MessageBox, NULL, addr msj_exc1, addr msj_exc1, MB_OK 
invoke ExitProcess, 0 
FIN_EXCEPCION_OVERFLOW_ENTERO: 
ret 
OVERFLOWFLOTANTE: ret 
RECURSIONMUTUA: ret 
LABEL_awf_ee:mov bl, 0
mov num_ee_awf, bl
mov bl, q_ee_awf
mov awf_ee, bl
ret 
LABEL_aa_ee:mov al, num_ee_aa
sub al, 3
mov bl, m_ee_aa
add bl, i_ee
cmp al, bl
jl LABEL0
mov bl, -1
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
mov cl, a_ee
sub cl, 1
cmp cl, b_ee
je LABEL2
mov bl, t_ee
mov c_ee, bl
LABEL2: mov ebx, i_ee
cmp ebx, c_ee
jl LABEL3
mov bl, q_ee
mov d_ee, bl
LABEL3: mov bl, n_ee
mov i_ee, bl
LABEL4: mov dl, t_ee
add dl, 1
cmp dl, i_ee
jl LABEL13
mov null, i_ee
sub null, 3
mov i_ee, null
mov bl, n_ee
mov i_ee, bl
LABEL5: mov null, t_ee
add null, 1
cmp null, i_ee
jl LABEL9
mov null, i_ee
sub null, 3
mov i_ee, null
jmp LABEL9
mov bl, n_ee
mov i_ee, bl
LABEL6: mov null, t_ee
add null, 1
cmp null, i_ee
jl LABEL8
mov null, i_ee
sub null, 3
mov i_ee, null
jmp LABEL8
mov ebx, i_ee
cmp ebx, 3
jl LABEL7
jmp LABEL5
LABEL7: mov bl, -2
mov i_ee, bl
jmp LABEL14
mov bl, d_ee
mov d_ee, bl
jmp LABEL6
LABEL8: jmp LABEL5
LABEL9: mov ebx, t_ee
cmp ebx, 3
jl LABEL10
mov bl, 1
mov i_ee, bl
mov bl, 5
mov i_ee, bl
jmp LABEL14
jmp LABEL12
LABEL10: mov ebx, t_ee
cmp ebx, 2
jg LABEL11
mov bl, 5
mov i_ee, bl
LABEL11: jmp LABEL13
LABEL12: jmp LABEL4
LABEL13: mov bl, 1
mov i_ee, bl
LABEL14: mov bl, num_ee_aa
mov i_ee, bl
call LABEL_aa_ee
mov bl, aa_ee
mov w_ee, bl
END START