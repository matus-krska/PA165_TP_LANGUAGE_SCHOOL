package org.muni.fi.pa165.lang_school.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity Lecture represents a lecture of a course. It is stored as an entry of database table T_LECTURE.
 * It contains unique identification ID, time of creation, unique code, topic, lecture time and description
 * It is created by Lecturer and also taught by a Lecturer (it doesn't have to be the same person)
 * Lecture belongs to a Course
 * Arbitrary number of students can enroll in it.
 *
 * @author Matus Krska, 410073
 * @since 1.0
 */

@Entity
@Table(name="T_LECTURE")
public class Lecture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CREATED_BY")
    private Lecturer createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TAUGHT_BY")
    private Lecturer taughtBy;

    @Column(name="CODE", nullable = false)
    private String code;

    @Column(name="TOPIC")
    private String topic;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CREATED")
    private Date created;

    @Column(name="LECTURE_TIME")
    private Date lectureTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COURSE_ID")
    private Course course;

    @ManyToMany
    @JoinTable(
            name="STUDENT_LECTURE",
            joinColumns=@JoinColumn(name="LECTURE_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="STUDENT_ID", referencedColumnName="ID"))
    private List<Student> students;

    

    public Lecturer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Lecturer createdBy) {
        this.createdBy = createdBy;
    }

    public Lecturer getTaughtBy() {
        return taughtBy;
    }

    public void setTaughtBy(Lecturer taughtBy) {
        this.taughtBy = taughtBy;
    }

//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLectureTime() {
        return lectureTime;
    }

    public void setLectureTime(Date lectureTime) {
        this.lectureTime = lectureTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.createdBy != null ? this.createdBy.hashCode() : 0);
        hash = 89 * hash + (this.taughtBy != null ? this.taughtBy.hashCode() : 0);
        hash = 89 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 89 * hash + (this.topic != null ? this.topic.hashCode() : 0);
        hash = 89 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 89 * hash + (this.created != null ? this.created.hashCode() : 0);
        hash = 89 * hash + (this.lectureTime != null ? this.lectureTime.hashCode() : 0);
        hash = 89 * hash + (this.course != null ? this.course.hashCode() : 0);
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
        final Lecture other = (Lecture) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        if ((this.topic == null) ? (other.topic != null) : !this.topic.equals(other.topic)) {
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
        if (this.taughtBy != other.taughtBy && (this.taughtBy == null || !this.taughtBy.equals(other.taughtBy))) {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created))) {
            return false;
        }
        if (this.lectureTime != other.lectureTime && (this.lectureTime == null || !this.lectureTime.equals(other.lectureTime))) {
            return false;
        }
        if (this.course != other.course && (this.course == null || !this.course.equals(other.course))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lecture{" + "id=" + id + 
                ", createdBy=" + createdBy + 
                ", taughtBy=" + taughtBy + 
                ", code=" + code + 
                ", topic=" + topic + 
                ", description=" + description + 
                ", created=" + created + 
                ", lectureTime=" + lectureTime + 
                ", course=" + course + 
                ", students=" + students + '}';
    }
    
    
}
