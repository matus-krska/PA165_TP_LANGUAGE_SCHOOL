package DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Simon Hyben, 421112
 *
 */
public class UserCreateDTO {

    private Long id;

    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") // Minimum 8 characters at least 1 Alphabet and 1 Number
    private String password;

    @NotNull
    private String userRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserCreateDTO))
            return false;
        UserCreateDTO other = (UserCreateDTO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.getEmail()))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.getPassword()))
            return false;
        return true;
    }

}
