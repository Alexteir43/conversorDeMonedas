Código Java con funcionalidad de conversor de monedas con valores en tiempo real haciendo uso y consumo de api.
Al iniciar, en el entorno del usuario se le aparecerán dos opciones enumeradas ; la primera (1) es realizar una nueva conversion y la segunda opción (2) será la de salir.
Cuando el usuario escribe el número 1, se le pregunta qué moneda es la que quiere convertir ej (USD), después de le pregunta la cantidad que desea convertir de esa moneda. Por último, se pregunta nuevamente la moneda que será el destino de esa conversión.
Una vez teniendo esos datos, el programa hace la conversión consumiendo la api de cambios, mostrandole al usuario al final de la operación el valor de la moneda destino, la cantidad y la moneda origen en específico y el resultado de la operación.

En total se dispone de 35 monedas a convertir la aplicación sin límite de intentos hasta que el usuario decida salir. 
Si los datos introducidos como por ejemplo la sigla o clave de la moneda no son válidos, esto se detecta automáticamente y se le pide ingresar nuevamente un dato válido o disponible.
