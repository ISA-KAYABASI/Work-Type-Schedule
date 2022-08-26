package com.workTypeCalendar.model;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


//@Component
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "users" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private boolean isActive = true;
//    ****************************************************************
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_days",
                joinColumns = { @JoinColumn(name = "user_id")},
                inverseJoinColumns = { @JoinColumn(name = "day_id")})
    @ToString.Exclude
    private Set<Day> days = new HashSet<>();

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_CALENDAR_TABLE",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//
//            inverseJoinColumns = @JoinColumn(
//                    name = "userCalendar_id", referencedColumnName = "id"))
//    private Set<UserCalendar> userCalendars;
//   ***************************************
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles_",
            joinColumns = @JoinColumn(
                    name = "user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles ;

    public User(String firstName, String lastName, String email, String password,boolean isActive, Collection<Role> roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
