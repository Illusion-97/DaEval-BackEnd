package org.dawan.formations.controllers.neutralized;

import org.dawan.formations.dtos.*;
import org.dawan.formations.models.Utilisateur;
import org.dawan.formations.services.EmailService;
import org.dawan.formations.services.UtilisateurService;
import org.dawan.formations.tools.TokenSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Utilisateur")
public class UtilisateurController extends GenericController<Utilisateur,UtilisateurDto,UtilisateurService> {

    private final EmailService emailService;

    @Autowired
    public UtilisateurController(UtilisateurService service, EmailService emailService) {
        super(service);
        this.emailService = emailService;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public LoginResponseDto checkLogin(@RequestBody LoginDto loginDto) throws Exception {
        //appel à la méthode du service
        return getService().POSTcheckLogin(loginDto);
    }

    // Mot-de passe oublié, je reçois un email et je vérifie puis j'envoi un lien
    // par email pour réinitialiser le mot de passe
    @PostMapping(value = "/reset-password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetObj) throws Exception {

        UtilisateurDto uDto = getService().GETfindByEmail(resetObj.getEmail());

        if (uDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        else {
            emailService.sendEmailForResetPwd(uDto);
            return ResponseEntity.status(HttpStatus.OK).body("Email sent for update");
        }
    }

    @PostMapping(value = "/change-password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> changePassword(@RequestBody ChangePwdDto changePwdObj) throws Exception {

        boolean isReferencedToken = TokenSaver.tokensByEmail.containsValue(changePwdObj.getToken());

        if (isReferencedToken) {
            // modifier le mot de passe
            boolean resetStatus = getService().POSTresetPassword(changePwdObj);
            if (resetStatus)
                return ResponseEntity.status(HttpStatus.OK).body("Password updated !");
            else
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Operation not acceptable !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid token");
        }
    }
}



