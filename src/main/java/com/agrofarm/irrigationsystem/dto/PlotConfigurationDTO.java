package com.agrofarm.irrigationsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlotConfigurationDTO {
    private Long id;
    private Integer idIrrigationType;
    private Integer durationMinutes;
    private Integer slotTime;
    private Float amountWaterLiters;
    private boolean isActive;

}
