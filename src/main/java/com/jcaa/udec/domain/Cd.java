package com.jcaa.udec.domain;

public class Cd {
    private String id;
    private String titulo;
    private String artista;
    private double precio;

    public Cd(String id, String titulo, String artista, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.precio = precio;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
