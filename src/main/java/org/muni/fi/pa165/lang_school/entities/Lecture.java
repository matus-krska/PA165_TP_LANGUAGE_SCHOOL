package org.muni.fi.pa165.lang_school.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity Lecture represents a lecture of a course. It is stored as an entry of database table T_LECTURE.
 * It contains unique identification ID, time of creation, unique code, topic, lecture time and description
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

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="CREATED_BY")
//    private Lecturer createdBy;

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
}
