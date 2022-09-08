package org.dawan.formations.dtos;

public class LoginResponseDto {

    private SimpleUtilisateurDto simpleUserDto;
    private String token;

    public LoginResponseDto(SimpleUtilisateurDto simpleUserDto, String token) {
        this.simpleUserDto = simpleUserDto;
        this.token = token;
    }

    public LoginResponseDto() {
        super();
    }

    public SimpleUtilisateurDto getSimpleUserDto() {
        return simpleUserDto;
    }

    public void setSimpleUserDto(SimpleUtilisateurDto simpleUserDto) {
        this.simpleUserDto = simpleUserDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
