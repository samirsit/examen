package com.examen.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "clients")
public class Client {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;


}
