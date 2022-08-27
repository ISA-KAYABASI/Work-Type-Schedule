package com.isakayabasi.worktypeschedule.service.impl;


import com.isakayabasi.worktypeschedule.model.Day;
import com.isakayabasi.worktypeschedule.repository.DayRepository;
import com.isakayabasi.worktypeschedule.service.DayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayServiceImpl implements DayService {

    private DayRepository dayRepository;

    public DayServiceImpl(DayRepository dayRepository) {
        super();
        this.dayRepository = dayRepository;
    }

    @Override
    public Day saveDay(Day day) {
        return dayRepository.save(day);
    }

    @Override
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }


}
