package com.isakayabasi.worktypeschedule.service.impl;



import com.isakayabasi.worktypeschedule.repository.DayRepository;
import com.isakayabasi.worktypeschedule.service.DayService;
import org.springframework.stereotype.Service;


@Service
public class DayServiceImpl implements DayService {

    private DayRepository dayRepository;

    public DayServiceImpl(DayRepository dayRepository) {
        super();
        this.dayRepository = dayRepository;
    }


}
