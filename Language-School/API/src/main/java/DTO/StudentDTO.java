package DTO;

import java.util.List;
import java.util.Objects;

/**
 * Student DTO representation
 * 
 * @author Richard Zan, 396380
 * @since 1.0
 */
public class StudentDTO {
    
    private Long id;

    private String name;
    private String surname;

    private List<LectureDTO> lectures;
    
    private byte[] passwordHash;
    private byte[] passwordSalt;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<LectureDTO> getLecture() {
        return lectures;
    }

    public void setLecture(List<LectureDTO> lectures) {
        this.lectures = lectures;
    }
    
    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.surname);
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
        final StudentDTO other = (StudentDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", lectures=" + lectures + '}';
    }
    
    
}
