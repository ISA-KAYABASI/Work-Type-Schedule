package com.workTypeCalendar.service;


import com.workTypeCalendar.model.Day;

import java.util.List;

public interface DayService {

    Day saveDay(Day day);

    List<Day> getAllDays();

}
