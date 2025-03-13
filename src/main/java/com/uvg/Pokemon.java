package com.uvg;

/** 
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * @author: Andres Ismalej 24005
 * @version: 1
 * Ultima modificacion: 12/03/25
*/

import java.util.Objects;

/**
 * Clase que representa un Pokemon con sus atributos. 
*/
public class Pokemon {
    private String name;
    private int pokedexNumber;
    private String type1;
    private String type2;
    private String classification;
    private double height;
    private double weight;
    private String abilities;
    private int generation;
    private boolean lengendary;

    // Constructor del Pokemon
    public Pokemon(String name, int pokedexNumber, String type1, String type2, String classification,
                  double height, double weight, String abilities, int generation, boolean legendary) {
        this.name = name;
        this.pokedexNumber = pokedexNumber;
        this.type1 = type1;
        this.type2 = type2;
        this.classification = classification;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.generation = generation;
        this.legendary = legendary;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

    /**
     * Compara el nombre de 2 Pokemons.
     * @return true si coinciden y false en caso contrario
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon pokemon = (Pokemon) obj;
        return name.equals(name, pokemon.name);
    }

    /**
     * Calcula el codigo Hash del Pokemon segun su nombre. 
     * @return Codigo Hash
    */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Devuelve en forma de texto los atributos de un Pokemon.
     * @return Una cadena que contiene toda la información del Pokémon en formato legible
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(name).append("\n");
        sb.append("Número de Pokédex: ").append(pokedexNumber).append("\n");
        sb.append("Tipo Primario: ").append(type1).append("\n");
        sb.append("Tipo Secundario: ").append(type2 != null && !type2.isEmpty() ? type2 : "N/A").append("\n");
        sb.append("Clasificación: ").append(classification).append("\n");
        sb.append("Altura (m): ").append(height).append("\n");
        sb.append("Peso (kg): ").append(weight).append("\n");
        sb.append("Habilidades: ").append(abilities).append("\n");
        sb.append("Generación: ").append(generation).append("\n");
        sb.append("Legendario: ").append(legendary ? "Sí" : "No").append("\n");
        return sb.toString();
    }
}

