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
invoke ExitProcess, 0 
ret 
OVERFLOW_FLOTANTE: invoke StdOut, addr msj_exc2 
invoke ExitProcess, 0 
ret 
RECURSION_MUTUA: invoke StdOut, addr msj_exc3 
invoke ExitProcess, 0 
ret 
LABEL_ee_awf:mov bl, 120
mov num_ee_awf, bl
mov ah, 02h 
int 21h 
mov ebx, q_ee_awf
mov awf_ee, ebx
mov ah, 02h 
int 21h 
ret 
LABEL_ee_aa:invoke StdOut, addr holaaaaa
mov bl, 120
mov m_ee_aa, bl
mov ah, 02h 
int 21h 
mov bl, num_ee_aa
sub bl, 3
add bl, 1
cmp bl, 128 
jge OVERFLOW_ENTERO
add bl, 3
cmp bl, 128 
jge OVERFLOW_ENTERO
mov cl, m_ee_aa
add cl, i_ee
cmp cl, 128 
jge OVERFLOW_ENTERO
cmp bl, cl
jl LABEL0
mov bl, 120
mov n_ee, bl
mov ah, 02h 
int 21h 
mov ebx, r_ee_aa
mov aa_ee, ebx
mov ah, 02h 
int 21h 
ret 
jmp LABEL1
LABEL0: mov bl, i_ee
mov m_ee_aa, bl
mov ah, 02h 
int 21h 
LABEL1: mov bl, 2
add bl, 1
cmp bl, 128 
jge OVERFLOW_ENTERO
mov aa_ee, ebx
ret 
START:
mov bl, 0
mov num_ee, bl
mov ah, 02h 
int 21h 
mov bl, 0
mov d_ee, bl
mov ah, 02h 
int 21h 
mov bl, 0
mov q_ee, bl
mov ah, 02h 
int 21h 
mov bl, 0
mov i_ee, bl
mov ah, 02h 
int 21h 
mov bl, 0
mov c_ee, bl
mov ah, 02h 
int 21h 
mov bl, 0
mov t_ee, bl
mov ah, 02h 
int 21h 
mov bl, 0
mov n_ee, bl
mov ah, 02h 
int 21h 
mov bl, a_ee
sub bl, 1
cmp bl, b_ee
je LABEL2
mov bl, t_ee
mov c_ee, bl
mov ah, 02h 
int 21h 
LABEL2: mov bl, i_ee
cmp bl, c_ee
jl LABEL3
mov bl, q_ee
mov d_ee, bl
mov ah, 02h 
int 21h 
LABEL3: mov bl, n_ee
mov i_ee, bl
mov ah, 02h 
int 21h 
LABEL4: mov bl, t_ee
add bl, 1
cmp bl, 128 
jge OVERFLOW_ENTERO
cmp i_ee, bl
jl LABEL13
mov bl, i_ee
sub bl, 3
mov i_ee, bl
mov bl, n_ee
mov i_ee, bl
mov ah, 02h 
int 21h 
LABEL5: mov bl, t_ee
add bl, 1
cmp bl, 128 
jge OVERFLOW_ENTERO
cmp i_ee, bl
jl LABEL9
mov bl, i_ee
sub bl, 3
mov i_ee, bl
jmp LABEL9
mov bl, n_ee
mov i_ee, bl
mov ah, 02h 
int 21h 
LABEL6: mov bl, t_ee
add bl, 1
cmp bl, 128 
jge OVERFLOW_ENTERO
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
mov ah, 02h 
int 21h 
jmp LABEL14
mov bl, d_ee
mov d_ee, bl
mov ah, 02h 
int 21h 
jmp LABEL6
LABEL8: jmp LABEL5
LABEL9: mov bl, t_ee
cmp bl, 3
jl LABEL10
mov bl, 1
mov i_ee, bl
mov ah, 02h 
int 21h 
mov bl, 5
mov i_ee, bl
mov ah, 02h 
int 21h 
jmp LABEL14
jmp LABEL12
LABEL10: mov bl, t_ee
cmp bl, 2
jg LABEL11
mov bl, -5
mov i_ee, bl
mov ah, 02h 
int 21h 
LABEL11: jmp LABEL13
LABEL12: jmp LABEL4
LABEL13: mov bl, 1
mov i_ee, bl
mov ah, 02h 
int 21h 
LABEL14: mov bl, q_ee
add bl, 5
cmp bl, 128 
jge OVERFLOW_ENTERO
mov w_ee, ebx
mov bl, 0
mov num_ee_aa, bl
mov ah, 02h 
int 21h
cmp LAST_FUNCTION, programa_principal
je CANCELAR_INVOCACION1
mov AUX_NAME_LAST_FUNCTION, LAST_FUNCTION
mov LAST_FUNCTION, "programa_principal"
call LABEL_ee_aa
mov LAST_FUNCTION, AUX_NAME_LAST_FUNCTION
CANCELAR_INVOCACION1: mov ebx, aa_ee
mov w_ee, ebx
mov ah, 02h 
int 21h 
END START