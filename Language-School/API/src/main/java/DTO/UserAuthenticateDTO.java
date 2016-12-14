package DTO;

/**
 * @author Simon Hyben, 421112
 *
 */
public class UserAuthenticateDTO {

    private Long userId;
    private String password;

    public UserAuthenticateDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
