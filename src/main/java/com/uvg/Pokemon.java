package src;

/** 
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * @author: Andres Ismalej 24005
 * @version: 2
 * Ultima modificacion: 16/03/25
*/

import java.util.Objects;

/**
 * Clase que representa un Pokemon con sus atributos. 
*/
public class Pokemon {
    private String nombre;
    private int numeroPokedex;
    private String tipo1;
    private String tipo2;
    private String clasificacion;
    private double altura;
    private double peso;
    private String habilidades;
    private int generacion;
    private boolean legendario;

      // Constructor del Pokemon
    public Pokemon(String nombre, int numeroPokedex, String tipo1, String tipo2, String clasificacion, 
                double altura, double peso, String habilidades, int generacion, boolean legendario) {
        this.nombre = nombre;
        this.numeroPokedex = numeroPokedex;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.clasificacion = clasificacion;
        this.altura = altura;
        this.peso = peso;
        this.habilidades = habilidades;
        this.generacion = generacion;
        this.legendario = legendario;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public int getGeneracion() {
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public boolean isLegendario() {
        return legendario;
    }

    public void setLegendario(boolean legendario) {
        this.legendario = legendario;
    }

    /**
     * Compara el nombre de 2 Pokemonn para evitar coincidencias
     * @return true si coinciden y false en caso contrario
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon pokemon = (Pokemon) obj;
        return name.equals(pokemon.nombre);
    }

    /**
     * Calcula el codigo Hash del Pokemon segun su nombre. 
     * @return Codigo Hash
    */
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    /**
     * Devuelve en forma de texto los atributos de un Pokemon.
     * @return Una cadena que contiene toda la información del Pokémon en formato legible
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Número de Pokédex: ").append(numeroPokedex).append("\n");
        sb.append("Tipo Primario: ").append(tipo1).append("\n");
        sb.append("Tipo Secundario: ").append(tipo2 != null && !tipo2.isEmpty() ? tipo2 : "N/A").append("\n");
        sb.append("Clasificación: ").append(clasificacion).append("\n");
        sb.append("Altura (m): ").append(altura).append("\n");
        sb.append("Peso (kg): ").append(peso).append("\n");
        sb.append("Habilidades: ").append(habilidades).append("\n");
        sb.append("Generación: ").append(generacion).append("\n");
        sb.append("Legendario: ").append(legendario ? "Sí" : "No").append("\n");
        return sb.toString();
    }
}

