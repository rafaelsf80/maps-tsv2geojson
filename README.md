# tsv2Geojson #
Java standalone program that reads a TSV file and creates a GeoJson file with the same info
Uses Univocity TSV parser library

## Usage
Execution without parameters. Must set input and output filenames inside the code

Expected TSV input format (note X, Y coordinates on columns 3, 4)

   Organizacion	Direccion	Direccion Normalizada	X	Y	S/N
   
   ABD	Paseo de la Direccion, 69, 28039 Madrid	Paseo de la Direccion, 69, 28039 Madrid, Madrid, Spain	-3.708339	40.4605771	S 
   
   Alianza por la Solidaridad	Calle Jaen, 13 28020 Madrid	Calle Jaen, 13, 28020 Madrid, Madrid, Spain	-3.7013099	40.4504681	S
   
   Asociacion Espanola Contra el Cancer (aecc)	Calle Amador de los Rios, 5 28010 Madrid	Calle Amador de los Rios, 5, 28010 Madrid, Madrid, Spain	-3.6914144	40.427129	N
 

## Libraries
Compiled with univocity-parsers-1.3.0.jar

## License
GNU GPL v3