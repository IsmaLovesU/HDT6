package com.uvg;

/** 
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * @author: Andres Ismalej 24005
 * @version: 3
 * Ultima modificacion: 16/03/25
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase para leer datos de Pokémon desde un archivo CSV
 */
public class PokemonReader {
    
    /**
     * Lee los datos de Pokémon desde un archivo CSV
     * @param ruta ruta del archivo CSV
     * @param mapPokemon mapa donde se guardarán los pokémon
     * @return número de pokémon leídos
     */
    public int lectorCSVPokemon(String ruta, Map<String, Pokemon> mapPokemon) {
        int count = 0;
        int lineNumber = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            // Leer la primera línea (encabezados) y descartarla
            String line = br.readLine();
            lineNumber++;
            
            // Leer los datos
            while ((line = br.readLine()) != null) {
                lineNumber++;
                try {
                    // Dividir la línea por comas, pero respetando campos entre comillas
                    String[] data = parseCsvLine(line);
                    
                    // Verificar que hay suficientes columnas
                    if (data.length >= 10) {
                        String nombre = data[0].trim();
                        int numeroPokedex = Integer.parseInt(data[1].trim());
                        String tipo1 = data[2].trim();
                        String tipo2 = data[3].trim();
                        String clasificacion = data[4].trim();
                        double altura = Double.parseDouble(data[5].trim());
                        double peso = Double.parseDouble(data[6].trim());
                        String habilidades = data[7].trim();
                        int generacion = Integer.parseInt(data[8].trim());
                        boolean legendario = Boolean.parseBoolean(data[9].trim());
                        
                        // Crear un nuevo Pokémon y agregarlo al mapa
                        Pokemon pokemon = new Pokemon(
                            nombre, numeroPokedex, tipo1, tipo2, clasificacion,
                            altura, peso, habilidades, generacion, legendario
                        );
                        
                        mapPokemon.put(nombre, pokemon);
                        count++;
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar la línea " + lineNumber + ": " + line);
                    System.err.println("Detalles del error: " + e.getMessage());
                    // Continuar con la siguiente línea en lugar de detener el proceso
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return count;
    }
    
    /**
     * Método auxiliar para analizar correctamente una línea CSV
     * @param line línea de texto CSV a analizar
     * @return array con los campos separados
     */
    private String[] parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder currentField = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                // Si ya estamos en un campo entre comillas y encontramos otra comilla
                if (inQuotes) {
                    // Comprobar si la siguiente también es comilla (comillas escapadas)
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        currentField.append('"');
                        i++; // Saltar la siguiente comilla
                    } else {
                        inQuotes = false;
                    }
                } else {
                    inQuotes = true;
                }
            } else if (c == ',' && !inQuotes) {
                // Si encontramos una coma fuera de comillas, finaliza el campo actual
                result.add(currentField.toString());
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }
        
        result.add(currentField.toString());
        
        return result.toArray(new String[0]);
    }
}