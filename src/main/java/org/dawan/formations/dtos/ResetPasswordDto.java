package org.dawan.formations.dtos;

public class ResetPasswordDto {

    private String email;

    public ResetPasswordDto(String email) {
        super();
        this.email = email;
    }

    public ResetPasswordDto() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
