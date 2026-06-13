package com.relatospapel.books.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "editoriales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true) //NO incluyas todos los campos automáticamente en el toString(), Solo incluye los campos que tú marques con @ToString.Include.
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //NO uses todos los campos para equals() y hashCode().Solo usa los campos marcados con @EqualsAndHashCode.Include.
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer idEditorial;

    @Column(name = "nombre_editorial")
    private String nameEditorial;

    @Column(name = "url_editorial")
    private String urlEditorial;

    @Column(name = "estado")
    private Boolean statusEditorial = true;
}
