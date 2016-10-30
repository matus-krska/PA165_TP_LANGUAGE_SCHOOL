package org.muni.fi.pa165.lang_school.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Simon Hyben, 421112
 */
public class LecturerLanguage {
    @Id
    @Column(name="ID")
    private Long id;
    
    @Column(name="ID_LECTURER")
    private Long lecturerId;

    @Column(name="LANGUAGE")
    private String language;

    @Column(name="NATIVE")
    private Boolean nativeSpeaker;
    
    public Long getId() 
    {
        return this.id;
    }
    
    public void setId(Long id) 
    {
        this.id = id;
    }
    
    public Long getLecturerId() 
    {
        return this.lecturerId;
    }
    
    public void setLecturerId(Long id) 
    {
        this.lecturerId = id;
    }
    
    public String getLanguage()
    {
        return this.language;
    }
    public void setLanguage(String language)
    {
        this.language = language;
    }
    
    public Boolean getNative()
    {
        return this.nativeSpeaker;
    }
    
    public void setNative(Boolean nativeSpeaker)
    {
        this.nativeSpeaker = nativeSpeaker;
    }
    
    @Override
    public int hashCode() {
        int hash = 101;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final LecturerLanguage other = (LecturerLanguage) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LecturerLanguage{" + "id=" + id + ", lecturerId=" + lecturerId + ", language=" + language + ", nativeSpeaker=" + nativeSpeaker + '}';
    }
}
