package com.agrofarm.irrigationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTO {
    private Long id;
    private Long idPlot;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Integer status;

}
