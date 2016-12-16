package REST.Mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({ "passwordHash"})
public class StudentDTOMixin {
    
}

