package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.UtilisateurDto;
import org.dawan.formations.services.EmailService;
import org.dawan.formations.tools.JwtTokenUtil;
import org.dawan.formations.tools.TokenSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private JavaMailSender emailSender;

    @Value("${frontapp.url}")
    private String frontAppUrl;

    @Override
    public void sendEmailForResetPwd(UtilisateurDto uDto) throws Exception {
        //générer un token
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", uDto.getId());
        claims.put("user_fullName", uDto.getNom() + " " + uDto.getPrenom());
        claims.put("user_role", uDto.getStatut().toString());

        String token = jwtTokenUtil.doGenerateToken(claims, uDto.getEmail());
        TokenSaver.tokensByEmail.put(uDto.getEmail(), token);

        //envoyer le token par email
        String resetLink = "<a href=\"" + frontAppUrl + "/#/fr/reset-password?token=" + token + "\">Réinitialiser</a>";

        MimeMessage msg = emailSender.createMimeMessage();
        msg.setRecipients(Message.RecipientType.TO, uDto.getEmail());
        msg.setSubject("Réinitialisation du mot de passe");

        msg.setText("Bonjour " + uDto.getPrenom() + ","
                + "<br /> Ce message vous a été envoyé car vous avez oublié votre mot de passe.<br />"
                + "Pour le réinitialiser, cliquez sur ce lien : " + resetLink, "UTF-8", "html");
        emailSender.send(msg);
    }

}
