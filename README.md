# DataGeneratorBD
Este programa se encarga de generar datos para ser introducidos en una base de datos relacional.


#Instrucciones de ejecución
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
#Esquema SQL

Persona(id_persona[PK],nombre,direccion,telefono)
Produccion(id_produccion[PK],fecha_estreno,censura,nombre,categoria,ref_director[FK])
Locacion(id_direccion,direccion_locacion)
grabadaEn(ref_produccion[FK],ref_locacion[FK])
Pelicula(id_produccion[PK],ref_pelicula[FK])
Serie(ref_produccion[FK])
Secuela(id_secuela[PK],ref_pelicula[FK])
Temporada(id_temporada[PK],nro_capitulos, ref_serie[FK])
Director(id_persona[PK],nombre,direccion,telefono,ref_produccion[FK])
Manager(id_persona[PK],nombre,direccion,telefono,snn,fecha_nacimiento)
Actor(id_persona[PK],nombre,direccion,telefono,ref_manager[FK])
Doble(id_persona[PK],nombre,direccion,telefono,especialidad)
Extra(id_persona[PK],nombre,direccion,telefono,tiempo)
Normal(id_persona[PK],nombre,direccion,telefono,papel)
aCargo(ref_produccion[FK],ref_director[FK])
trabajan(ref_persona[FK],ref_produccion[FK])
Bodega(id_bodega[PK],direccion_bodega)
abastece(ref_serie[FK],ref_bodega[FK])
Efecto_especial(id_bodega[PK],direccionbodega,elemento)
Ropa(id_bodega[PK],direccionbodega,vestuario)
Maquinaria(id_bodega[PK],direccionbodega,implementacion)
Insumo(id_bodega[PK],direccionbodega,artefacto)

