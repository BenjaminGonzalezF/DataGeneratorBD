# DataGeneratorBD


Ejecutar java -jar source-code.jar N


Donde N corresponde a la cantidad de personas a generar, las personas se distribuiran entre:
*Actores que pueden ser: Doble - Extra - Normal
*Manager
*Director

Se generarán 130 producciones que se repartiran entre peliculas o serie.

Cada produccion tiene acargo un director,esta relacion se genera en acargo.csv
Cada persona trabaja en una produccion, esta relacion se genera en trabaja.csv
Cada actor tiene su manager esta relacion va incluida en el atributo ref_manager de cada actor.


Archivos de salida:
        -actores.csv
	-acargo.csv
	-actoresDoble.csv
	-actoresExtra.csv
	-actoresNormales.csv
	-directores.csv
	-managers.csv
	-peliculas.csv
	-personas.csv
	-producciones.csv
	-series.csv
	-trabajan.csv