package com.isakayabasi.worktypeschedule.repository;


import com.isakayabasi.worktypeschedule.model.Day;
import com.isakayabasi.worktypeschedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends JpaRepository<Day,Long> {
    Day findByPersonDayAndUsers(String personDay, User user);

    boolean existsByPersonDayAndUsers(String personDay, User user);

}
