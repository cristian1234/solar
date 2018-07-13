Asumpciones
Las �rbitas de los 3 planetas son coplanares, o sea pertenecen a un mismo plano
La posici�n inicial en el d�a 0 de los 3 planetas est� alineada con respecto al sol formando una recta horizontal, a la derecha del sol (en el eje positivo x). Las predicciones de los pr�ximos 10 a�os se calcular�n en base a esto.
Se toma como 1 a�o al tiempo equivalente transcurrido en 365 d�as. Por ende 10 a�os ser�n 3650 d�as.

Planteo de la soluci�n:
Esta simulaci�n por conveniencia se hace 1 sola vez al inicio registrando en una colecci�n todos sus datos para luego poder consultar los mismos. La granularidad de la simulaci�n ser� por d�a hasta completar la cantidad de d�as que abarcan 10 a�os esto ser�a 365*10=3650 d�as, y el coeficiente de error para determinar la posici�n de los planetas es 1000 metros. O sea se va a considerar que los planetas est�n alineados aunque alguno tenga una desviaci�n en la recta que forman de hasta 1km. Se calcular� con el producto cruzado de los segmentos, si da menor o igual a 1000 mts se considera que est�n alineados. Esto mismo se utiliza para saber si el sol est� alineado o no.
El c�lculo del per�metro se calcular� sumando los segmentos formados por la posici�n de cada planeta, y la verificaci�n de si el sol est� dentro del per�metro se hace verificando si el producto escalar entre el �ngulo que forma el sol y 2 planetas tiene el mismo signo para las tres combinaciones (sol-planeta1-planeta2, sol-planeta1-planeta3, sol-planeta2-planeta3) 

Clases del Modelo:
SolarSystem representa el punto de entrada, fachada del sistema
Planet representa el movimiento y comportamiento de cada planeta
Point abstracci�n geom�trica de un punto 2D
Shape representa a una figura geom�trica 2D y responde preguntas como si es una linea, si es un triangulo, etc, la respuesta de estas preguntas servir�n para calcular las condiciones de climas.
Weather mantiene mantiene los datos de un determinado clima, incluyendo su humedad.
WeatherDetector contiene las reglas que evaluaran las condiciones del sistema solar necesarias para detectar en un momento dado que clima existe.
RegisteredDay: asocia un clima con un d�a dado. Es el resultado de la simulaci�n.
