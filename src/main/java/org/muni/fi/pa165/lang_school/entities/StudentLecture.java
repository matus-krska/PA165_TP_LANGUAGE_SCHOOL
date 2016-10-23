package org.muni.fi.pa165.lang_school.entities;

import javax.persistence.*;

/**
 * Join table for Student and Lecture
 * Arbitrary number of students can enroll into arbitrary number of lectures
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class StudentLecture
{
    @Id
    @Column(name="ID")
    private Long id;

//    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name="ID_STUDENT")
//    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_LECTURE")
    private Lecture lecture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
