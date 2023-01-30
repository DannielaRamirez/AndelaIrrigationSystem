package com.agrofarm.irrigationsystem.service.impl;

import com.agrofarm.irrigationsystem.entity.TimeSlot;
import com.agrofarm.irrigationsystem.repository.ITimeSlotRepository;
import com.agrofarm.irrigationsystem.service.ITimeSlotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimeSlotServiceImpl implements ITimeSlotService {

    private ITimeSlotRepository iTimeSlotRepository;
    @Override
    public void createSlot(TimeSlot timeSlot) {
        iTimeSlotRepository.save(timeSlot);
    }
}
