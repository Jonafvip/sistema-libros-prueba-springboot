package com.codewithjona.sistemalibros.entities;

import com.codewithjona.sistemalibros.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType categoryType;

    @Column(nullable = false)
    private Double assessment;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;

    @ManyToMany(mappedBy = "categories")
    private Set<DvD> dvds;
}
