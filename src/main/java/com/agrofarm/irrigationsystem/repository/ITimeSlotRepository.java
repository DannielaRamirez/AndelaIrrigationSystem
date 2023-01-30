package com.agrofarm.irrigationsystem.repository;

import com.agrofarm.irrigationsystem.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITimeSlotRepository extends JpaRepository<TimeSlot, Long> {
}
