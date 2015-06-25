package com.rafaelsf80.java.phd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

/**
 * TsvToGeojson
 * <p>
 * This program reads a TSV file, and creates a GeoJson file with the info
 * Uses Univocity TSV parser library
 * </p>
 * <p> Expected TSV input format (note X, Y coordinates on columns 3, 4)
 * Organizacion	Direccion	Direccion Normalizada	X	Y	S/N
 * ABD	Paseo de la Direccion, 69, 28039 Madrid	Paseo de la Direccion, 69, 28039 Madrid, Madrid, Spain	-3.708339	40.4605771	S 
 * Alianza por la Solidaridad	Calle Jaen, 13 28020 Madrid	Calle Jaen, 13, 28020 Madrid, Madrid, Spain	-3.7013099	40.4504681	S
 * Asociacion Espanola Contra el Cancer (aecc)	Calle Amador de los Rios, 5 28010 Madrid	Calle Amador de los Rios, 5, 28010 Madrid, Madrid, Spain	-3.6914144	40.427129	N
 *
 * @return Geojson file
 * @author rafaelsanchez
 *
 */

public class TsvToGeojson {
	
	static final String fullPath = "maps_with_coords.tsv";
	static final String outputFile = "geoJson.tsv";

	public static void main(String[] args) {
		
		try {	

			// Parse TSV file
			System.out.println("Reading and parsing file: "+fullPath);

			// TSV parser is a library that reads a file and can parse TSV automatically
			TsvParserSettings settings = new TsvParserSettings();
			
			//the file used in the example uses '\n' as the line separator sequence.
			//the line separator sequence is defined here to ensure systems such as MacOS and Windows
			//are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
			settings.getFormat().setLineSeparator("\n");

			// creates a TSV parser
			TsvParser parserOLTFAP = new TsvParser(settings);	    

			// parses all rows in one go. Each row at allRows.get(i)
			FileReader reader = new FileReader(fullPath);
			BufferedReader buffReader = new BufferedReader(reader);
			List<String[]> allRows = parserOLTFAP.parseAll(buffReader);
			
			// creates json string
			String geoJson = "{ \"type\":\"FeatureCollection\",\"features\":[";		    
			for (int i=1; i<allRows.size(); i++) {		    	
				geoJson += "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[" + 
						(allRows.get(i))[3] + ", " + (allRows.get(i))[4] + "]},\"properties\":{\"valor\":\"" + 
						"____VALOR_____" + "\",\"direccion\":\"" + "____DIRECCION______"  + "\"}}";

				if (i<(allRows.size()-1)) {
					geoJson += ",";
				}
			}
			geoJson += "]}";

			System.out.println(geoJson);
			
			// writes to file
			System.out.println("Writing to file: "+outputFile);
			FileWriter outputWriter = new FileWriter(outputFile, false);
			outputWriter.write(geoJson);
			outputWriter.close();
			
		}
		catch (Exception e) {
			System.out.println("Reading error: "+e.toString());
			e.printStackTrace();
		}
	}
}
