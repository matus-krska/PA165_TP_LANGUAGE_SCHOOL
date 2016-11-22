package org.muni.fi.pa165.lang_school.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity Course represents a language course. It is stored as an entry of database table T_COURSE.
 * It contains unique ID, name, language, language proficiency, description and list of lectures.
 *
 * @author Petra Kamenickova, 396179
 * @since 1.0
 */

@Entity
@Table(name = "T_COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.language != null ? this.language.hashCode() : 0);
        hash = 83 * hash + (this.language_level != null ? this.language_level.hashCode() : 0);
        hash = 83 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 83 * hash + (this.createdBy != null ? this.createdBy.hashCode() : 0);
        hash = 83 * hash + (this.created != null ? this.created.hashCode() : 0);
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
        final Course other = (Course) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.language == null) ? (other.language != null) : !this.language.equals(other.language)) {
            return false;
        }
        if ((this.language_level == null) ? (other.language_level != null) : !this.language_level.equals(other.language_level)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.createdBy != other.createdBy && (this.createdBy == null || !this.createdBy.equals(other.createdBy))) {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + 
                "id=" + id + 
                ", name=" + name + 
                ", language=" + language + 
                ", language_level=" + language_level + 
                ", description=" + description + 
                ", createdBy=" + createdBy + 
                ", created=" + created + '}';
    }
    
    
}