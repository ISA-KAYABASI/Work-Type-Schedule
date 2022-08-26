package com.workTypeCalendar.repository;

import com.workTypeCalendar.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends JpaRepository<Day,Long> {
    Day findByPersonDay(String personDay);

}
