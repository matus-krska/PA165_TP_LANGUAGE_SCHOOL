package org.muni.fi.pa165.lang_school.entities;

import javax.persistence.*;
import java.util.List;
/**
 * Entity Lecturer represents a lecturer of a course. It is stored as an entry of database table T_LECTURER.
 * It contains unique identification ID, name, surname and map of languages taught by lecturer. 
 * Map also contains information whether or not lecturer is native speaker of certain taught language.
 *
 * @author Simon Hyben, 421112
 * @since 1.0
 */

@Entity
@Table(name="T_LECTURER")
public class Lecturer
{
    @Id
    @Column(name="ID")
    private Long id;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="LECTURE_ID")
    private List<Lecture> lessons;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="LECTURER_LANGUAGE_ID")
    private List<LecturerLanguage> taughtLanguages; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<LecturerLanguage> getTaughtLanguages() {
        return taughtLanguages;
    }

    public void setTaughtLanguages(List<LecturerLanguage> taughtLanguages) {
        this.taughtLanguages = taughtLanguages;
    }
    
    public List<Lecture> getLessons() {
        return this.lessons;
    }
    
    public void setLessons(List<Lecture> lessons) {
        this.lessons = lessons;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lecturer other = (Lecturer) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lecturer{" + "id=" + id + ", name=" + name + ", surname=" + surname + '}';
    }
}

