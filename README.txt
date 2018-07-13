Asumpciones
Las órbitas de los 3 planetas son coplanares, o sea pertenecen a un mismo plano
La posición inicial en el día 0 de los 3 planetas está alineada con respecto al sol formando una recta horizontal, a la derecha del sol (en el eje positivo x). Las predicciones de los próximos 10 años se calcularán en base a esto.
Se toma como 1 año al tiempo equivalente transcurrido en 365 días. Por ende 10 años serán 3650 días.

Planteo de la solución:
Esta simulación por conveniencia se hace 1 sola vez al inicio registrando en una colección todos sus datos para luego poder consultar los mismos. La granularidad de la simulación será por día hasta completar la cantidad de días que abarcan 10 años esto sería 365*10=3650 días, y el coeficiente de error para determinar la posición de los planetas es 1000 metros. O sea se va a considerar que los planetas están alineados aunque alguno tenga una desviación en la recta que forman de hasta 1km. Se calculará con el producto cruzado de los segmentos, si da menor o igual a 1000 mts se considera que están alineados. Esto mismo se utiliza para saber si el sol está alineado o no.
El cálculo del perímetro se calculará sumando los segmentos formados por la posición de cada planeta, y la verificación de si el sol está dentro del perímetro se hace verificando si el producto escalar entre el ángulo que forma el sol y 2 planetas tiene el mismo signo para las tres combinaciones (sol-planeta1-planeta2, sol-planeta1-planeta3, sol-planeta2-planeta3) 

Clases del Modelo:
SolarSystem representa el punto de entrada, fachada del sistema
Planet representa el movimiento y comportamiento de cada planeta
Point abstracción geométrica de un punto 2D
Shape representa a una figura geomètrica 2D y responde preguntas como si es una linea, si es un triangulo, etc, la respuesta de estas preguntas serviràn para calcular las condiciones de climas.
Weather mantiene mantiene los datos de un determinado clima, incluyendo su humedad.
WeatherDetector contiene las reglas que evaluaran las condiciones del sistema solar necesarias para detectar en un momento dado que clima existe.
RegisteredDay: asocia un clima con un día dado. Es el resultado de la simulación.
