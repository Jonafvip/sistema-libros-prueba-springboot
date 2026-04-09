package com.codewithjona.sistemalibros.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dvds")
public class DvD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String duration;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "dvd_category",
            joinColumns = @JoinColumn(name = "dvd_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

}
