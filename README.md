# Proyecto de Compilador - Diseño de Compiladores

Consiste en la implementación completa de un compilador para un lenguaje imperativo simple, incluyendo análisis léxico, sintáctico, generación de código intermedio y traducción a código ejecutable.

---

## Objetivo

Construir un compilador capaz de:
- Analizar léxica y sintácticamente un programa fuente.
- Detectar errores léxicos y sintácticos.
- Generar código intermedio mediante **tercetos**.
- Traducir a instrucciones ejecutables compatibles con la arquitectura Pentium **80x86 con coprocesador 80x87**.

---

## Etapas de desarrollo

El compilador fue desarrollado en fases:

1. **Análisis léxico**:
   - Implementado en Java.
   - Reconocimiento de tokens: identificadores, constantes (`int`, `float`, `string`), símbolos atómicos, palabras clave.
   - Verificación de restricciones (longitud de identificadores, valores válidos de enteros, etc.).
   - Detección de errores léxicos con cancelación del proceso si es necesario.

2. **Análisis sintáctico**:
   - Utilización de [**BYACC/J**](https://byaccj.sourceforge.net) (Berkeley Yacc for Java).
   - Análisis ascendente LALR(1).
   - Manejo de estructuras como: 
     - sentencias ejecutables y declarativas
     - estructuras de control: `if`, `for`, `break`, `continue`
     - llamadas a funciones y pasaje de parámetros
     - funciones anidadas y manejo de **ámbitos estáticos**

3. **Generación de código intermedio**:
   - Mediante **tercetos**.
   - Representación intermedia de instrucciones, operaciones y control de flujo.

4. **Generación de código ejecutable**:
   - Traducción de tercetos a instrucciones ejecutables.
   - Generación de instrucciones de bajo nivel y manejo de registros.

---

## Características del lenguaje fuente

- **Tipos de datos**: `int`, `float`, `string`
- **Constantes**: numéricas (enteras y flotantes), literales de texto
- **Sentencias de control**: 
  - `if`, `for`, `break`, `continue`
- **Funciones**:
  - Definición y anidamiento
  - Ámbitos estáticos
  - Pasaje de parámetros
- **Errores**:
  - Detección temprana de errores léxicos y sintácticos
  - Mensajes claros de error
  - Cancelación del proceso de compilación ante errores

---

## Arquitectura destino

- **Procesador objetivo**: arquitectura Pentium **80x86**
- **Coprocesador**: **80x87**
  - Permite manejo extendido de operaciones en punto flotante.
  - Agrega 8 registros de 80 bits para operaciones numéricas.
  - Utilizado como coprocesador en paralelo con el 80x86.
  - [Documentacion del co-procesador](https://www.website.masmforum.com/tutorials/fptute/)
- Link [x86 Instruction Set Reference](https://c9x.me/x86/)

---

## Tecnologías utilizadas

- **Java**: lenguaje de implementación
- **BYACC/J**: parser generator (LALR(1), ascendente)
- **ASCII**: definición de tokens de símbolos
- **Estructuras auxiliares**:
  - Tabla de símbolos
  - Generador de tercetos
  - Manejador de errores
