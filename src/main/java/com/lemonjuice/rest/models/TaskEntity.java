package com.lemonjuice.rest.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "task")
public class TaskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
