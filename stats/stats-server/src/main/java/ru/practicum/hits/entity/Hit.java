package ru.practicum.hits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hits")
public class Hit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String app;

    @Column(nullable = false, unique = true)
    private String uri;

    @Column(nullable = false, unique = true)
    private String ip;

    @Column(nullable = false, unique = true)
    private LocalDateTime moment;
}
