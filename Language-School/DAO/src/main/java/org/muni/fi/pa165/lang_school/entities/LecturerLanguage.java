package org.muni.fi.pa165.lang_school.entities;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity represents one language taught by Lecturer. 
 * It is stored as an entry of database table T_LECTURER_LANGUAGE.
 * It contains unique ID, lecturerId, languageTaught and 
 * indication if Lecturer is native speaker. 
 * 
 * @author Simon Hyben, 421112
 */

@Entity
@Table(name="T_LECTURER_LANGUAGE")
public class LecturerLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    
    @Column(name="ID_LECTURER")
    private Long lecturerId;

    @Column(name="LANGUAGE_TAUGHT")
    private String languageTaught;

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
    
    public String getLanguageTaught()
    {
        return this.languageTaught;
    }
    public void setLanguageTaught(String language)
    {
        this.languageTaught = language;
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
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.lecturerId != null ? this.lecturerId.hashCode() : 0);
        hash = 29 * hash + (this.languageTaught != null ? this.languageTaught.hashCode() : 0);
        hash = 29 * hash + (this.nativeSpeaker != null ? this.nativeSpeaker.hashCode() : 0);
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
        if ((this.languageTaught == null) ? (other.languageTaught != null) : !this.languageTaught.equals(other.languageTaught)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.lecturerId != other.lecturerId && (this.lecturerId == null || !this.lecturerId.equals(other.lecturerId))) {
            return false;
        }
        if (this.nativeSpeaker != other.nativeSpeaker && (this.nativeSpeaker == null || !this.nativeSpeaker.equals(other.nativeSpeaker))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LecturerLanguage{" + "id=" + id + ", lecturerId=" + lecturerId + ", languageTaught=" + languageTaught + ", nativeSpeaker=" + nativeSpeaker + '}';
    }
    
    
}
