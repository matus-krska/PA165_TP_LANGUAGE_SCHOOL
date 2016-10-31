package org.muni.fi.pa165.lang_school.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity student represents a student of a course. It is stored as an entry of database table T_STUDENT.
 * It contains unique ID, name, surname and list of enrolled lectures. 
 *
 * @author Richard Zan, 396380
 * @since 1.0
 */

@Entity
@Table(name="T_STUDENT")
public class Student {
    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="LECTURE_ID")
    private List<Lecture> lectures;

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

    public List<Lecture> getLecture() {
        return lectures;
    }

    public void setLecture(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Student other = (Student) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", surname=" + surname + '}';
    }
}
