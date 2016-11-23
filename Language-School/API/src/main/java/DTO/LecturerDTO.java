package DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO representation of entity Lecturer
 * @author Simon Hyben, 421112
 * @since 1.0
 */
public class LecturerDTO
{
    private Long id;
    private List<LectureDTO> lessons = new ArrayList<>();
    private String name;
    private String surname;
    private List<LecturerLanguageDTO> taughtLanguages = new ArrayList<>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<LectureDTO> getLessons()    
    {
        return lessons;
    }

    public void setLessons(List<LectureDTO> lessons)
    {
        this.lessons = lessons;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String description)
    {
        this.surname = surname;
    }

    public List<LecturerLanguageDTO> getTaughtLanguages()
    {
        return taughtLanguages;
    }

    public void setTaughtLanguages(List<LecturerLanguageDTO> taughtLanguages)
    {
        this.taughtLanguages = taughtLanguages;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LecturerDTO that = (LecturerDTO) o;

        if (!getName().equals(that.getName())) return false;
        if (!getSurname().equals(that.getSurname())) return false;
        if (!getId().equals(that.getId())) return false;
        return true;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.surname);
        return hash;
    }
    
    @Override
    public String toString() 
    {
        return "LecturerDTO{" + "id=" + id + ", name=" + name + ", surname=" + surname + '}';
    }
}

