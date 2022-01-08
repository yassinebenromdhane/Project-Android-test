package com.example.myapplication.Model;

public class Article {
    String id;
    String nom;
    String prix;


    public Article(String id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prix='" + prix + '\'' +
                '}';
    }
}
