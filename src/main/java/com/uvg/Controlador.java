package com.uvg;

/** 
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * @author: Andres Ismalej 24005
 * @version: 1
 * Ultima modificacion: 16/03/25
*/

import java.util.*;

/**
 * Clase que maneja las operaciones con los Pokémon
*/
public class Controlador {
    
    // Mapa con todos los pokémon disponibles
    private Map<String, Pokemon> todosPokemons;
    
    // Set con los nombres de los pokémon en la colección del usuario
    private Set<String> coleccionUsuario;
    
    /**
     * Constructor
     * @param mapPokemon mapa con todos los pokémon
    */
    public Controlador(Map<String, Pokemon> mapPokemon) {
        this.todosPokemons = mapPokemon;
        // Usamos HashSet para la colección del usuario
        this.coleccionUsuario = new HashSet<>();
    }
    
    /**
     * Agrega un Pokémon a la colección del usuario
     * @param nombrePokemon nombre del Pokémon
     * @return mensaje de resultado
    */
    public String agregarPokemonColeccion(String nombrePokemon) {
        // Verificar si el Pokémon existe
        if (!todosPokemons.containsKey(nombrePokemon)) {
            return "Error: El Pokémon: " + nombrePokemon + ", no existe en los datos.";
        }
        
        // Verificar si ya está en la colección
        if (coleccionUsuario.contains(nombrePokemon)) {
            return "El Pokémon: " + nombrePokemon + ", ya está en tu colección.";
        }
        
        // Agregar a la colección
        coleccionUsuario.add(nombrePokemon);
        return "¡Pokémon: " + nombrePokemon + ", agregado a tu colección!";
    }
    
    /**
     * Obtiene la información de un Pokémon
     * @param nombrePokemon nombre del Pokémon
     * @return información del Pokémon
    */
    public String obtenerPokemonInfo(String nombrePokemon) {
        Pokemon pokemon = todosPokemons.get(nombrePokemon);
        if (pokemon == null) {
            return "Error: El Pokémon: " + nombrePokemon + ", no existe en los datos.";
        }
        return pokemon.toString();
    }
    
    /**
     * Obtiene los pokémon del usuario ordenados por tipo
     * @return lista de pares (nombre, tipo1)
    */
    public List<Map.Entry<String, String>> obtenerPokemonsTipo() {
        List<Map.Entry<String, String>> result = new ArrayList<>();
        
        // Agregar cada pokémon del usuario a la lista
        for (String nombre : coleccionUsuario) {
            Pokemon pokemon = todosPokemons.get(nombre);
            if (pokemon != null) {
                // Crear un par (nombre, tipo1)
                result.add(new AbstractMap.SimpleEntry<>(nombre, pokemon.getTipo1()));
            }
        }
        
        // Ordenar la lista por tipo1
        Collections.sort(result, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        
        return result;
    }
    
    /**
     * Obtiene todos los pokémon ordenados por tipo
     * @return lista de pares (nombre, tipo1)
    */
    public List<Map.Entry<String, String>> pokemonsPorTipo() {
        List<Map.Entry<String, String>> result = new ArrayList<>();
        
        // Agregar cada pokémon a la lista
        for (Map.Entry<String, Pokemon> entry : todosPokemons.entrySet()) {
            String nombre = entry.getKey();
            String tipo1 = entry.getValue().getTipo1();
            result.add(new AbstractMap.SimpleEntry<>(nombre, tipo1));
        }
        
        // Ordenar la lista por tipo1
        Collections.sort(result, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        
        return result;
    }
    
    /**
     * Busca pokémon por habilidad
     * @param habilidad habilidad a buscar
     * @return lista de nombres de pokémon con esa habilidad
    */
    public List<String> buscarPorHabilidad(String habilidad) {
        List<String> result = new ArrayList<>();
        
        // Buscar pokémon con la habilidad
        for (Map.Entry<String, Pokemon> entry : todosPokemons.entrySet()) {
            Pokemon pokemon = entry.getValue();
            // Verificar si las habilidades contienen la buscada (ignorando mayúsculas/minúsculas)
            if (pokemon.getHabilidades().toLowerCase().contains(habilidad.toLowerCase())) {
                result.add(entry.getKey());
            }
        }
        
        return result;
    }
    
    /**
     * Obtiene la cantidad de pokémon en la colección del usuario
     * @return cantidad de pokémon
    */
    public int getTamanoColeccionUsuario() {
        return coleccionUsuario.size();
    }
    
    /**
     * Obtiene la cantidad total de pokémon
     * @return cantidad total de pokémon
    */
    public int getCantidadTotalPokemon() {
        return todosPokemons.size();
    }
}