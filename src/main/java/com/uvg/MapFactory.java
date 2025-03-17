package com.uvg;

/** 
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * @author: Andres Ismalej 24005
 * @version: 1
 * Ultima modificacion: 12/03/25
*/

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Clase que implementa el patron factory para los diferentes Maps.
*/
public class MapFactory {
    // Constantes para los tipos de mapa
    public static final int HASH_MAP = 1;
    public static final int TREE_MAP = 2;
    public static final int LINKED_HASH_MAP = 3;
     
    /**
    * Crea un mapa del tipo especificado
    * @param mapType el tipo de mapa a crear (1=HashMap, 2=TreeMap, 3=LinkedHashMap)
    * @return un mapa del tipo especificado
    */
    public static Map<String, Pokemon> crearMap(int mapType) {
        switch (mapType) {
            case HASH_MAP:
                return new HashMap<>();
            case TREE_MAP:
                return new TreeMap<>();
            case LINKED_HASH_MAP:
                return new LinkedHashMap<>();
            default:
                 // Por defecto un HashMap
                return new HashMap<>();
        }
    }
     
    /**
    * Muestra las opciones de mapas disponibles
    */
    public static void mostrarOpcionesMap() {
        System.out.println("Seleccione el tipo de mapa a utilizar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
    }
     
    /**
    * Obtiene el nombre del tipo de mapa
    * @param mapType el tipo de mapa
    * @return el nombre del tipo de mapa
    */
    public static String obtenerNombreMap(int mapType) {
        switch (mapType) {
            case HASH_MAP:
                return "HashMap";
            case TREE_MAP:
                return "TreeMap";
            case LINKED_HASH_MAP:
                return "LinkedHashMap";
            default:
                return "Desconocido";
        }
    }
}
