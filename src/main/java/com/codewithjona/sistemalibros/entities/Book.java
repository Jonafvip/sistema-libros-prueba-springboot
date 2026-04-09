package com.codewithjona.sistemalibros.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "year_of_publication", nullable = false)
    private Integer yearOfPublication;

    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @Column(nullable = false, unique = true)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    //crea la tabla intermediaria automaticamente en la db
    @ManyToMany
    @JoinTable(
            name = "book_category", //→ El nombre de la tabla intermedia que se crea
            joinColumns = @JoinColumn(name = "book_id"), //→ La FK que apunta a Book (esta clase)
            inverseJoinColumns = @JoinColumn(name = "category_id") //→ La FK que apunta a Category (la otra clase)
    )
    private Set<Category>categories;
}
