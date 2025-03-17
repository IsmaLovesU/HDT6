package com.uvg;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pruebas unitarias específicas para la clase Controlador
 */
public class ControladorTest {
    
    private Controlador controlador;
    private Map<String, Pokemon> mapaTest;
    
    /**
     * Configura los datos para las pruebas
     */
    @Before
    public void setUp() {
        // Crear un mapa de prueba
        mapaTest = new HashMap<>();
        
        // Agregar algunos pokémon de prueba con diferentes habilidades y tipos
        mapaTest.put("Pikachu", new Pokemon("Pikachu", 25, "Electric", "", "Mouse Pokémon", 
                                          0.4, 6.0, "Static,Lightning Rod", 1, false));
        
        mapaTest.put("Bulbasaur", new Pokemon("Bulbasaur", 1, "Grass", "Poison", "Seed Pokémon", 
                                            0.7, 6.9, "Overgrow,Chlorophyll", 1, false));
        
        mapaTest.put("Charizard", new Pokemon("Charizard", 6, "Fire", "Flying", "Flame Pokémon", 
                                            1.7, 90.5, "Blaze,Solar Power", 1, false));
                                            
        mapaTest.put("Squirtle", new Pokemon("Squirtle", 7, "Water", "", "Tiny Turtle Pokémon", 
                                           0.5, 9.0, "Torrent,Rain Dish", 1, false));
                                           
        mapaTest.put("Jigglypuff", new Pokemon("Jigglypuff", 39, "Normal", "Fairy", "Balloon Pokémon", 
                                             0.5, 5.5, "Cute Charm,Competitive", 1, false));
                                             
        mapaTest.put("Gengar", new Pokemon("Gengar", 94, "Ghost", "Poison", "Shadow Pokémon", 
                                         1.5, 40.5, "Cursed Body", 1, false));
        
        // Crear el controlador con el mapa de prueba
        controlador = new Controlador(mapaTest);
    }
    
    /**
     * Prueba exhaustiva de búsqueda de Pokémon por habilidad
     */
    @Test
    public void testBuscarPorHabilidadExhaustivo() {
        // Caso 1: Búsqueda de habilidad que varios Pokémon comparten (parcialmente)
        List<String> resultadoCute = controlador.buscarPorHabilidad("Cute");
        assertEquals("Debería encontrar 1 Pokémon con 'Cute' en su habilidad", 1, resultadoCute.size());
        assertTrue("El Pokémon encontrado debería ser Jigglypuff", resultadoCute.contains("Jigglypuff"));
        
        // Caso 2: Búsqueda de habilidad que un solo Pokémon tiene
        List<String> resultadoCursed = controlador.buscarPorHabilidad("Cursed Body");
        assertEquals("Debería encontrar 1 Pokémon con 'Cursed Body'", 1, resultadoCursed.size());
        assertTrue("El Pokémon encontrado debería ser Gengar", resultadoCursed.contains("Gengar"));
        
        // Caso 3: Búsqueda de habilidad con mayúsculas/minúsculas mixtas
        List<String> resultadoTorrent = controlador.buscarPorHabilidad("tOrReNt");
        assertEquals("Debería encontrar 1 Pokémon con 'torrent' ignorando mayúsculas/minúsculas", 
                    1, resultadoTorrent.size());
        assertTrue("El Pokémon encontrado debería ser Squirtle", resultadoTorrent.contains("Squirtle"));
        
        // Caso 4: Búsqueda de habilidad que no existe
        List<String> resultadoInexistente = controlador.buscarPorHabilidad("Levitate");
        assertEquals("No debería encontrar ningún Pokémon con 'Levitate'", 0, resultadoInexistente.size());
        
        // Caso 5: Búsqueda con string vacío
        List<String> resultadoVacio = controlador.buscarPorHabilidad("");
        assertEquals("Debería encontrar todos los Pokémon con string vacío", 6, resultadoVacio.size());
    }
    
    /**
     * Prueba exhaustiva de la gestión de la colección de usuario
     */
    @Test
    public void testGestionColeccionUsuario() {
        // Verificar que la colección inicia vacía
        assertEquals("La colección debería iniciar vacía", 0, controlador.getTamanoColeccionUsuario());
        
        // Caso 1: Agregar un Pokémon válido
        String resultado1 = controlador.agregarPokemonColeccion("Pikachu");
        assertTrue("Debería mostrar mensaje de éxito", resultado1.contains("agregado a tu colección"));
        assertEquals("Debería haber 1 Pokémon en la colección", 1, controlador.getTamanoColeccionUsuario());
        
        // Caso 2: Agregar un segundo Pokémon válido
        String resultado2 = controlador.agregarPokemonColeccion("Charizard");
        assertTrue("Debería mostrar mensaje de éxito", resultado2.contains("agregado a tu colección"));
        assertEquals("Debería haber 2 Pokémon en la colección", 2, controlador.getTamanoColeccionUsuario());
        
        // Caso 3: Intentar agregar un Pokémon duplicado
        String resultado3 = controlador.agregarPokemonColeccion("Pikachu");
        assertTrue("Debería indicar que ya existe", resultado3.contains("ya está en tu colección"));
        assertEquals("Debería seguir habiendo 2 Pokémon en la colección", 2, controlador.getTamanoColeccionUsuario());
        
        // Caso 4: Intentar agregar un Pokémon que no existe
        String resultado4 = controlador.agregarPokemonColeccion("Mewtwo");
        assertTrue("Debería mostrar mensaje de error", resultado4.contains("Error"));
        assertEquals("Debería seguir habiendo 2 Pokémon en la colección", 2, controlador.getTamanoColeccionUsuario());
        
        // Caso 5: Verificar los tipos de Pokémon en la colección
        List<Map.Entry<String, String>> pokemonsPorTipo = controlador.obtenerPokemonsTipo();
        assertEquals("Debería haber 2 Pokémon en la lista ordenada", 2, pokemonsPorTipo.size());
        
        // La lista debería estar ordenada por tipo: Electric, Fire
        assertEquals("El primer tipo debería ser Electric", "Electric", pokemonsPorTipo.get(0).getValue());
        assertEquals("El primer Pokémon debería ser Pikachu", "Pikachu", pokemonsPorTipo.get(0).getKey());
        assertEquals("El segundo tipo debería ser Fire", "Fire", pokemonsPorTipo.get(1).getValue());
        assertEquals("El segundo Pokémon debería ser Charizard", "Charizard", pokemonsPorTipo.get(1).getKey());
    }
}
