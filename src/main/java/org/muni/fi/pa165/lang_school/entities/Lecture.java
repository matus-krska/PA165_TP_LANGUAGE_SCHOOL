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
}
