package com.agrofarm.irrigationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crop" , schema = "gestion", catalog = "dbirrigationsystem")
public class Crop {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "crop")
    private String cropName;

    @Basic
    @Column(name = "water_m3_by_area")
    private Float waterM3ByArea;

}
