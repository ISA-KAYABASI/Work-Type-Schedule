package com.workTypeCalendar.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "days")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personDay;
    private WorkType chooseWorkType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "days")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    public Day(String name, WorkType chooseWorkType) {
        this.personDay = name;
        this.chooseWorkType = chooseWorkType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Day day = (Day) o;
        return id != null && Objects.equals(id, day.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

