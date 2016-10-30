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