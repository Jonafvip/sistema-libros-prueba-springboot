package com.codewithjona.sistemalibros.entities;

import com.codewithjona.sistemalibros.enums.LiteraryGenre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;



@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends Person {

    @Column(unique = true)
    private String pseudonym;

    private String publisher;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LiteraryGenre literaryGenre;

}
