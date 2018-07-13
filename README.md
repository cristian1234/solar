# Solar system simulator

Aplicación ejercicio, java que pronostica climas en una galaxia lejana.

## Asumpciones adptada para desarrollar la simulación
* Las órbitas de los 3 planetas son coplanares, o sea pertenecen a un mismo plano.
* La posición inicial en el día 0 de los 3 planetas está alineada con respecto al su sol, formando una recta horizontal, a la derecha del mismo (en el eje positivo x). Las predicciones de los próximos 10 años se calculan en base a esto.
* Se toma como 1 año al tiempo equivalente transcurrido en 365 días. Por ende 10 años serán 3650 días. Se utilziará una granularidad configurable para poder realizar varias simulaciones para un mismo día.


## Modelo:
* SolarSystem: representa el punto de entrada, fachada del sistema
* Planet: representa el movimiento y comportamiento de cada planeta
* Point abstracción geométrica de un punto 2D
* Shape representa a una figura geomètrica 2D y responde preguntas como si es una linea, si es un triangulo, etc, la respuesta de estas preguntas servirán para calcular las condiciones de climas.
* Weather: mantiene los datos de un determinado clima, incluyendo su humedad.
* WeatherDetector: contiene las reglas que evaluan las condiciones del sistema solar necesarias para detectar en un momento dado que clima existe.
* RegisteredDay: asocia un clima con un día dado. Es el resultado de la simulación.


# Implementación

Es una aplicación java 8 la cual puede ejecutarse por linea de comnando, y retorna por consola los periodos de lluvia, sequia, y condiciones de clima ideales que habrá en un periodo de 10 años.
Está strucuturada mediante un proyecto maven.
Posee test unitarios de sus principales componentes, utiliza junit5 y mockito en combinación con mocks de tipo "POJO".
Posee también un test integral que realiza la ejecuciòn completa de la simulación. Sin realizar chequeo de la validez matemática de la misma por cuestiones de complejidad de cálculo.

# Commo compilar

1. Clonar repositorio git
2. Abrir eclipse (recomendado oxigen+ para compatibilidad con junit 5)
3. Importar el repositorio como proyecto maven
