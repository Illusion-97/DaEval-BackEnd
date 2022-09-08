package org.dawan.formations.services;

import org.dawan.formations.dtos.UtilisateurDto;

public interface EmailService {

    void sendEmailForResetPwd(UtilisateurDto uDto) throws Exception;

}
