package kz.asumc.dto;

public class LoginResponseDto {
    private String token;
    private String userType;
    private String role;

    public LoginResponseDto() {}

    public LoginResponseDto(String token, String userType, String role) {
        this.token = token;
        this.userType = userType;
        this.role = role;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
