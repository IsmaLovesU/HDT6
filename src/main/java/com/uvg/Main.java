package src;

/** 
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * @author: Andres Ismalej 24005
 * @version: 1
 * Ultima modificacion: 16/03/25
*/

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase principal para la aplicación de Pokémon
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Seleccionar el tipo de mapa
        MapFactory.mostrarOpcionesMap();
        System.out.print("Ingrese su opción: ");
        int mapType = leerOpcionEntero(scanner, 1, 3);
        
        // Crear el mapa usando el Factory
        Map<String, Pokemon> pokemonMap = MapFactory.crearMap(mapType);
        System.out.println("Usando " + MapFactory.obtenerNombreMap(mapType) + " para almacenar los Pokémon.");
        
        // Leer el archivo CSV
        System.out.print("Ingrese la ruta del archivo CSV de Pokémon: ");
        String filePath = scanner.nextLine();
        
        PokemonReader reader = new PokemonReader();
        int count = reader.lectorCSVPokemon(filePath, pokemonMap);
        
        if (count == 0) {
            System.out.println("No se pudieron cargar datos de Pokémon. Verifique el archivo y vuelva a intentarlo.");
            return;
        }
        
        System.out.println("Se cargaron " + count + " Pokémon correctamente.");
        
        // Crear el controlador de Pokémon
        Controlador controlador = new Controlador(pokemonMap);
        
        // Menú principal
        boolean exit = false;
        while (!exit) {
            mostrarMenu();
            int option = leerOpcionEntero(scanner, 0, 5);
            
            switch (option) {
                case 1:
                    agregarPokemonAColeccion(scanner, controlador);
                    break;
                case 2:
                    mostrarInfoPokemon(scanner, controlador);
                    break;
                case 3:
                    mostrarPokemonsUsuarioPorTipo(controlador);
                    break;
                case 4:
                    mostrarTodosPokemonsPorTipo(controlador);
                    break;
                case 5:
                    buscarPokemonPorHabilidad(scanner, controlador);
                    break;
                case 0:
                    exit = true;
                    System.out.println("¡Hasta luego, Entrenador Pokémon!");
                    break;
            }
            
            if (!exit) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menú principal
    */
    private static void mostrarMenu() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Agregar un Pokémon a tu colección");
        System.out.println("2. Mostrar los datos de un Pokémon");
        System.out.println("3. Mostrar tu colección ordenada por tipo");
        System.out.println("4. Mostrar todos los Pokémon ordenados por tipo");
        System.out.println("5. Buscar Pokémon por habilidad");
        System.out.println("0. Salir");
        System.out.print("Ingrese su opción: ");
    }
    
    /**
     * Lee una opción numérica dentro de un rango
    */
    private static int leerOpcionEntero(Scanner scanner, int min, int max) {
        int option = -1;
        
        while (option < min || option > max) {
            try {
                option = Integer.parseInt(scanner.nextLine());
                
                if (option < min || option > max) {
                    System.out.print("Opción inválida. Ingrese un número entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un número válido: ");
            }
        }
        
        return option;
    }
    
    /**
     * Agrega un Pokémon a la colección del usuario
    */
    private static void agregarPokemonAColeccion(Scanner scanner, Controlador controlador) {
        System.out.print("Ingrese el nombre del Pokémon que desea agregar: ");
        String nombre = scanner.nextLine().trim();
        
        String resultado = controlador.agregarPokemonColeccion(nombre);
        System.out.println(resultado);
    }
    
    /**
     * Muestra la información de un Pokémon
    */
    private static void mostrarInfoPokemon(Scanner scanner, Controlador controlador) {
        System.out.print("Ingrese el nombre del Pokémon: ");
        String nombre = scanner.nextLine().trim();
        
        String info = controlador.obtenerPokemonInfo(nombre);
        System.out.println(info);
    }
    
    /**
     * Muestra la colección del usuario ordenada por tipo
    */
    private static void mostrarPokemonsUsuarioPorTipo(Controlador controlador) {
        List<Map.Entry<String, String>> pokemonsPorTipo = controlador.obtenerPokemonsTipo();
        
        if (pokemonsPorTipo.isEmpty()) {
            System.out.println("Tu colección está vacía.");
            return;
        }
        
        System.out.println("Tu colección de Pokémon ordenada por tipo:");
        System.out.println("------------------------------------------");
        System.out.printf("%-20s | %-15s%n", "Nombre", "Tipo Primario");
        System.out.println("------------------------------------------");
        
        for (Map.Entry<String, String> entry : pokemonsPorTipo) {
            System.out.printf("%-20s | %-15s%n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("------------------------------------------");
        System.out.println("Total en tu colección: " + controlador.getTamanoColeccionUsuario());
    }
    
    /**
     * Muestra todos los Pokémon ordenados por tipo
    */
    private static void mostrarTodosPokemonsPorTipo(Controlador controlador) {
        List<Map.Entry<String, String>> pokemonsPorTipo = controlador.pokemonsPorTipo();
        
        System.out.println("Todos los Pokémon ordenados por tipo:");
        System.out.println("------------------------------------------");
        System.out.printf("%-20s | %-15s%n", "Nombre", "Tipo Primario");
        System.out.println("------------------------------------------");
        
        for (Map.Entry<String, String> entry : pokemonsPorTipo) {
            System.out.printf("%-20s | %-15s%n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("------------------------------------------");
        System.out.println("Total de Pokémon: " + controlador.getCantidadTotalPokemon());
    }
    
    /**
     * Busca Pokémon por habilidad
    */
    private static void buscarPokemonPorHabilidad(Scanner scanner, Controlador controlador) {
        System.out.print("Ingrese la habilidad a buscar: ");
        String habilidad = scanner.nextLine().trim();
        
        List<String> listaPokemon = controlador.buscarPorHabilidad(habilidad);
        
        if (listaPokemon.isEmpty()) {
            System.out.println("No se encontraron Pokémon con la habilidad '" + habilidad + "'.");
            return;
        }
        
        System.out.println("Pokémon con la habilidad '" + habilidad + "':");
        System.out.println("------------------------------------------");
        
        for (String nombre : listaPokemon) {
            System.out.println(nombre);
        }
        
        System.out.println("------------------------------------------");
        System.out.println("Total encontrados: " + listaPokemon.size());
    }
}
