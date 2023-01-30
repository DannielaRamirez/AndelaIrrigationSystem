package com.agrofarm.irrigationsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plot_configuration", schema = "gestion", catalog = "dbirrigationsystem")
public class PlotConfiguration {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @JoinColumn(name = "id_irrigation_type", referencedColumnName = "irrigation_type")
    private Integer idIrrigationType;
    @Basic
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
    @Basic
    @Column(name = "slot_time")
    private Integer slotTime;
    @Basic
    @Column(name = "amount_water_liters")
    private Float amountWaterLiters;

    @OneToOne(mappedBy = "plotConfiguration")
    private Plot plot;

    @Basic
    @Column(name = "is_active")
    private boolean isActive;
}
