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
@varFloat1@2F5 dd 1.2F5
@varFloat0@0 dd 0.0
c_program db 1
b_program db 5
aux_program dd ?
@a_es_1_$_ db "a es 1 - ", 0
@jj_no_es_3_$_ db "jj no es 3 - ", 0
@salida_del_for_2_$_ db "salida del for 2 - ", 0
@Se_hizo__la_conversion_$_ db "Se hizo  la conversion - ", 0
@i_es_mayor_a_120_$_ db "i es mayor a 120 - ", 0
@dentro_de_for_2_$_ db "dentro de for 2 - ", 0
@mult_no_es_25_$_ db "mult no es 25 - ", 0
@j_es_igual_a_80_$_ db "j es igual a 80 - ", 0
@mult_es_25_$_ db "mult es 25 - ", 0
@counter_es_0_$_ db "counter es 0 - ", 0
@a_es_2_$_ db "a es 2 - ", 0
mult_program db ?
multiplicacion_program db ?
j_program_multiplicacion db ?
i_program db ?
jj_program db ?
j_program db ?
counter_program db ?
a_program db ?
i_program_multiplicacion db ?
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
FUNCION_multiplicacion_program db 0 
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
LABEL_multiplicacion_program:mov al, i_program_multiplicacion
imul j_program_multiplicacion
mov multiplicacion_program, al
ret 
START:
finit
fwait
mov bl, 5
mov i_program_multiplicacion, bl
mov bl, 5
mov j_program_multiplicacion, bl
cmp FUNCION_multiplicacion_program, 1
je RECURSION_MUTUA 
mov FUNCION_programa_principal, 1
call LABEL_multiplicacion_program
mov FUNCION_programa_principal, 0
mov bl, multiplicacion_program
mov mult_program, bl
mov bl, mult_program
cmp bl, 25
je LABEL0
invoke StdOut, addr @mult_no_es_25_$_
jmp LABEL1
LABEL0: 
invoke StdOut, addr @mult_es_25_$_
LABEL1: 
mov bl, 0
mov counter_program, bl
mov bl, 0
mov i_program, bl
LABEL2: 
mov bl, i_program
cmp bl, 3
jge LABEL6
mov bl, i_program
add bl, 1
jo OVERFLOW_ENTERO
mov i_program, bl
mov bl, 0
mov j_program, bl
LABEL3: 
mov bl, j_program
cmp bl, 3
jge LABEL5
mov bl, j_program
add bl, 1
jo OVERFLOW_ENTERO
mov j_program, bl
invoke StdOut, addr @dentro_de_for_2_$_
mov bl, counter_program
cmp bl, 0
jne LABEL4
invoke StdOut, addr @counter_es_0_$_
jmp LABEL3
LABEL4: 
invoke StdOut, addr @salida_del_for_2_$_
mov bl, counter_program
add bl, 1
jo OVERFLOW_ENTERO
mov counter_program, bl
jmp LABEL3
LABEL5: 
mov bl, 2
mov a_program, bl
jmp LABEL7
jmp LABEL2
LABEL6: 
mov bl, 1
mov a_program, bl
LABEL7: 
mov bl, a_program
cmp bl, 1
jne LABEL8
invoke StdOut, addr @a_es_1_$_
LABEL8: 
mov bl, a_program
cmp bl, 2
jne LABEL9
invoke StdOut, addr @a_es_2_$_
LABEL9: 
fld @varFloat0@0
fstp aux_program
mov bl, b_program
add bl, 1
jo OVERFLOW_ENTERO
cmp c_program, bl
jge LABEL11
mov bl, c_program
cmp bl, b_program
jge LABEL10
mov bl, c_program
add bl, b_program
jo OVERFLOW_ENTERO
mov @conv, ebx
cbw 
fild @conv 
fstp aux_program
invoke StdOut, addr @Se_hizo__la_conversion_$_
LABEL10: 
LABEL11: 
mov bl, 4
mov jj_program, bl
mov bl, jj_program
cmp bl, 3
jne LABEL12
FINIT 
FWAIT 
fld aux_program
fld @varFloat1@2F5
fadd
FNSTSW stword 
and stword, 8
jnz OVERFLOW_FLOTANTE 
fstp aux_program
jmp LABEL13
LABEL12: 
invoke StdOut, addr @jj_no_es_3_$_
mov bl, jj_program
add bl, 6
jo OVERFLOW_ENTERO
mov @auxOpInt, 2
mov al, bl
imul @auxOpInt
mov @auxOpInt, 3
mov al, al
idiv @auxOpInt
mov jj_program, al
LABEL13: 
mov bl, 80
mov j_program, bl
mov bl, j_program
cmp bl, 80
jne LABEL14
invoke StdOut, addr @j_es_igual_a_80_$_
LABEL14: 
mov bl, 120
add bl, j_program
jo OVERFLOW_ENTERO
mov i_program, bl
mov bl, i_program
cmp bl, 120
jle LABEL15
invoke StdOut, addr @i_es_mayor_a_120_$_
LABEL15: 
finit 
fwait
END START