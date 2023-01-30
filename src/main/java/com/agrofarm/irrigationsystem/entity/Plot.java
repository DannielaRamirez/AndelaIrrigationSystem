package com.agrofarm.irrigationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "plot" , schema = "gestion", catalog = "dbirrigationsystem")
public class Plot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name= "identifier", nullable = false, unique = true)
    private String identifier;

    @Basic
    @Column(name = "id_region", nullable = false)
    private int idRegion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_configuration",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "plot_id_configuration_fkey")
    )
    private PlotConfiguration plotConfiguration;

    @Basic
    @Column(name = "id_crop", nullable = false)
    private int idCrop;
    @Basic
    @Column(name = "area", nullable = true)
    private Float area;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return id == plot.id && idRegion == plot.idRegion && idCrop == plot.idCrop && Objects.equals(plotConfiguration, plot.plotConfiguration) && Objects.equals(area, plot.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRegion, plotConfiguration, idCrop, area);
    }
}
