package DTO;

import java.util.List;

/**
 * DTO represantation of entity Course
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class CourseDTO
{
    private Long id;
    private String name;
    private String language;
    private String language_level;
    private String description;
    private List<LectureDTO> lectures;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseDTO courseDTO = (CourseDTO) o;

        if (getName() != null ? !getName().equals(courseDTO.getName()) : courseDTO.getName() != null) return false;
        if (getLanguage() != null ? !getLanguage().equals(courseDTO.getLanguage()) : courseDTO.getLanguage() != null)
            return false;
        if (getLanguage_level() != null ? !getLanguage_level().equals(courseDTO.getLanguage_level()) : courseDTO.getLanguage_level() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(courseDTO.getDescription()) : courseDTO.getDescription() != null)
            return false;
        return getLectures() != null ? getLectures().equals(courseDTO.getLectures()) : courseDTO.getLectures() == null;

    }

    @Override
    public int hashCode()
    {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
        result = 31 * result + (getLanguage_level() != null ? getLanguage_level().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getLectures() != null ? getLectures().hashCode() : 0);
        return result;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getLanguage_level()
    {
        return language_level;
    }

    public void setLanguage_level(String language_level)
    {
        this.language_level = language_level;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<LectureDTO> getLectures()
    {
        return lectures;
    }

    public void setLectures(List<LectureDTO> lectures)
    {
        this.lectures = lectures;
    }
}
