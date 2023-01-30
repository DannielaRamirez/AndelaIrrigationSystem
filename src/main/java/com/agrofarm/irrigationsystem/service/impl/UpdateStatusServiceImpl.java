package com.agrofarm.irrigationsystem.service.impl;

import com.agrofarm.irrigationsystem.entity.TimeSlot;
import com.agrofarm.irrigationsystem.service.ITimeSlotService;
import com.agrofarm.irrigationsystem.service.IUpsateStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@AllArgsConstructor
@Service
public class UpdateStatusServiceImpl implements IUpsateStatusService {
    ITimeSlotService iTimeSlotService;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Override
    public void updateStatus(Long plotId) {
            LocalDateTime localDate = LocalDateTime.now();
            log.info("Start Irrigation :: Execution Time - {}", dateTimeFormatter.format(localDate));
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setIdPlot(plotId);
            timeSlot.setStatus(1);
            timeSlot.setTimeStart(localDate);
            iTimeSlotService.createSlot(timeSlot);
        }
}
