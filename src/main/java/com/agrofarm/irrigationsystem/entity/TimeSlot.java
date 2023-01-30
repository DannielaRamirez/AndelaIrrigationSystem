package com.agrofarm.irrigationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "time_slot", schema = "gestion", catalog = "dbirrigationsystem")
public class TimeSlot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "id_plot", nullable = false)
    private Long idPlot;
    @Basic
    @Column(name = "time_start", nullable = true)
    private LocalDateTime timeStart;
    @Basic
    @Column(name = "time_end", nullable = true)
    private LocalDateTime timeEnd;
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return id == timeSlot.id && idPlot == timeSlot.idPlot && status == timeSlot.status && Objects.equals(timeStart, timeSlot.timeStart) && Objects.equals(timeEnd, timeSlot.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPlot, timeStart, timeEnd, status);
    }
}
