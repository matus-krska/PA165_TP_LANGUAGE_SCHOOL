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
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CREATED_BY")
    private Lecturer createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TAUGHT_BY")
    private Lecturer taughtBy;

    @Column(name="CODE")
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

    /**
     * Overrides hashCode of Object
     * @return id as a hash value
     */
    public int hashCode()
    {
        return id.intValue();
    }

    /**
     * Overrides equals of Object
     * @param o any object
     * @return true only if object is isntance of Lecture and their hashes are equal
     */
    public boolean equals(Object o)
    {
        boolean equals = false;
        if(!(o instanceof Lecture))
        {
            return equals;
        }
        if(((Lecture) o).hashCode() == this.hashCode())
        {
            equals = true;
        }
        return equals;
    }

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
}
