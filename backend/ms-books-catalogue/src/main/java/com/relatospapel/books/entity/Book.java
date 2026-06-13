package com.relatospapel.books.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDate;

@Entity
@Table(name = "libro",
        uniqueConstraints = {@UniqueConstraint(name = "uk_libro_isbn", columnNames = "isbn")
}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idBook;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "fecha_publicacion")
    private LocalDate datePublication;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "cantidad", nullable = false)
    private int stock = 0;

    @Lob
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String description;

    @Column(name = "valoracion", nullable = false)
    private int assessment;

    @Column(name = "visibilidad", nullable = false)
    private Boolean visibility = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_editorial" , foreignKey = @ForeignKey(name = "fk_libro_editorial"))
    private Editorial editorial;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_autor" , foreignKey = @ForeignKey(name = "fk_libro_autor"))
    private Author author;

}
