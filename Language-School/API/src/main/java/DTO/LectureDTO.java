package DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO represantation of entity Lecture
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class LectureDTO
{
    private Long id;
    private LecturerDTO taughtBy;
    private String code;
    private String topic;
    private String description;
    private String lectureTime;
    private CourseDTO course;
    private List<StudentDTO> students = new ArrayList<>();

    public Long getId()
    {
        return id;
    }

    public LecturerDTO getTaughtBy()
    {
        return taughtBy;
    }

    public void setTaughtBy(LecturerDTO taughtBy)
    {
        this.taughtBy = taughtBy;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLectureTime()
    {
        return lectureTime;
    }

    public void setLectureTime(String lectureTime)
    {
        this.lectureTime = lectureTime;
    }

    public CourseDTO getCourse()
    {
        return course;
    }

    public void setCourse(CourseDTO course)
    {
        this.course = course;
    }

    public List<StudentDTO> getStudents()
    {
        return students;
    }

    public void setStudents(List<StudentDTO> students)
    {
        this.students = students;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LectureDTO that = (LectureDTO) o;

        if (getTaughtBy() != null ? !getTaughtBy().equals(that.getTaughtBy()) : that.getTaughtBy() != null)
            return false;
        if (!getCode().equals(that.getCode())) return false;
        if (getTopic() != null ? !getTopic().equals(that.getTopic()) : that.getTopic() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getLectureTime() != null ? !getLectureTime().equals(that.getLectureTime()) : that.getLectureTime() != null)
            return false;
        if (getCourse() != null ? !getCourse().equals(that.getCourse()) : that.getCourse() != null) return false;
        return getStudents() != null ? getStudents().equals(that.getStudents()) : that.getStudents() == null;

    }

    @Override
    public int hashCode()
    {
        int result = getTaughtBy() != null ? getTaughtBy().hashCode() : 0;
        result = 31 * result + getCode().hashCode();
        result = 31 * result + (getTopic() != null ? getTopic().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getLectureTime() != null ? getLectureTime().hashCode() : 0);
        result = 31 * result + (getCourse() != null ? getCourse().hashCode() : 0);
        result = 31 * result + (getStudents() != null ? getStudents().hashCode() : 0);
        return result;
    }
}
