package org.muni.fi.pa165.lang_school.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity Course represents a language course. It is stored as an entry of database table T_COURSE.
 * It contains unique ID, name, language, langugage proficiency, description and list of lectures.
 *
 * @author Petra Kamenickova, 396179
 * @since 1.0
 */

@Entity
@Table(name = "T_COURSE")
public class Course {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="LANGUAGE")
    private String language;

    @Column(name="LANGUAGE_LEVEL")
    private String language_level;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY")
    private Lecturer createdBy;

    @Column(name = "CREATED")
    private Date created;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="LECTURE_ID")
    private List<Lecture> lectureList;

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (language_level != null ? language_level.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lectureList != null ? lectureList.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!id.equals(course.id)) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (language != null ? !language.equals(course.language) : course.language != null) return false;
        if (language_level != null ? !language_level.equals(course.language_level) : course.language_level != null)
            return false;
        if (description != null ? !description.equals(course.description) : course.description != null) return false;
        if (createdBy != null ? !createdBy.equals(course.createdBy) : course.createdBy != null) return false;
        if (created != null ? !created.equals(course.created) : course.created != null) return false;
        return lectureList != null ? lectureList.equals(course.lectureList) : course.lectureList == null;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", language_level='" + language_level + '\'' +
                '}';
    }

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage_level() {
        return language_level;
    }

    public void setLanguage_level(String language_level) {
        this.language_level = language_level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lecturer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Lecturer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Lecture> getLectureList() {
        return lectureList;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList = lectureList;
    }
}