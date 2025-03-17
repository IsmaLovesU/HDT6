package com.uvg;

/** 
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * @author: Andres Ismalej 24005
 * @version: 1
 * Ultima modificacion: 16/03/25
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Clase para leer datos de Pokémon desde un archivo CSV
 */
public class PokemonReader {
    
    /**
     * Lee los datos de Pokémon desde un archivo CSV
     * @param filePath ruta del archivo CSV
     * @param pokemonMap mapa donde se guardarán los pokémon
     * @return número de pokémon leídos
     */
    public int lectorCSVPokemon(String ruta, Map<String, Pokemon> mapPokemon) {
        int count = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            // Leer la primera línea (encabezados) y descartarla
            String line = br.readLine();
            
            // Leer los datos
            while ((line = br.readLine()) != null) {
                try {
                    // Dividir la línea por comas
                    String[] data = line.split(",");
                    
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
                            nombre, numeroPokedex, tipo1, tipo2, classificacion,
                            altura, peso, habilidades, generacion, legendario
                        );
                        
                        mapPokemon.put(name, pokemon);
                        count++;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error al procesar la línea: " + line);
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return count;
    }
}