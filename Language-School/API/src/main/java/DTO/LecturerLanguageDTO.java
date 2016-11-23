package DTO;

import java.util.Objects;

/**
 * DTO representation of entity LecturerLanguage
 * @author Simon Hyben, 421112
 * @since 1.0
 */
public class LecturerLanguageDTO
{
    private Long id;
    private Long lecturerId;
    private String languageTaught;
    private Boolean nativeSpeaker;    

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getLecturerId()    
    {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId)
    {
        this.lecturerId = lecturerId;
    }

    public String getLanguageTaught()
    {
        return languageTaught;
    }

    public void setLanguageTaught(String languageTaught)
    {
        this.languageTaught = languageTaught;
    }

    public Boolean getNativeSpeaker()
    {
        return nativeSpeaker;
    }

    public void setNativeSpeaker(Boolean nativeSpeaker)
    {
        this.nativeSpeaker = nativeSpeaker;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LecturerLanguageDTO that = (LecturerLanguageDTO) o;

        if (getLecturerId() != getLecturerId()) return false;
        if (!getLanguageTaught().equals(that.getLanguageTaught())) return false;
        if (getNativeSpeaker() != getNativeSpeaker()) return false;
        if (!getId().equals(that.getId())) return false;
        return true;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.lecturerId);
        hash = 13 * hash + Objects.hashCode(this.languageTaught);
        hash = 13 * hash + Objects.hashCode(this.nativeSpeaker);
        return hash;
    }
    
    @Override
    public String toString() 
    {
        return "LecturerLanguageDTO{" + "id=" + id + ", lecturerId=" + lecturerId + ", "
                + "languageTaught=" + languageTaught + "nativeSpeaker=" + nativeSpeaker  + '}';
    }
}
