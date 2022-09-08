package org.dawan.formations.services;

import org.dawan.formations.dtos.ChangePwdDto;
import org.dawan.formations.dtos.LoginDto;
import org.dawan.formations.dtos.LoginResponseDto;
import org.dawan.formations.dtos.UtilisateurDto;
import org.dawan.formations.models.Utilisateur;
import org.dawan.formations.repositories.UtilisateurRepository;

public interface UtilisateurService extends GenericService<Utilisateur, UtilisateurDto, UtilisateurRepository> {

    UtilisateurDto GETfindByEmail(String email);

    LoginResponseDto POSTcheckLogin(LoginDto login) throws Exception;

    boolean POSTresetPassword(ChangePwdDto changePwdObj) throws Exception;
}
