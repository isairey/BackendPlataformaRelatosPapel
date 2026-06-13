package com.relatospapel.books.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer idAuthor;

    @Column(name = "nombre_autor")
    private String nameAuthor;

    @Column(name = "apellido_autor")
    private String lastNameAuthor;

    @Column(name = "biografia")
    private String biografia;

    @Column(name = "estado")
    private Boolean statusAuthor = true;

}
