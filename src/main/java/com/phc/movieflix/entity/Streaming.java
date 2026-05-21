package com.phc.movieflix.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_streaming")
@Getter
@Setter
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    public Streaming() {
    }

    public Streaming(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
