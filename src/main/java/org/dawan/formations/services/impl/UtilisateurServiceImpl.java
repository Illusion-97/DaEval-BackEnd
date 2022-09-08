package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.*;
import org.dawan.formations.models.Etudiant;
import org.dawan.formations.models.STATUT_UTILISATEUR;
import org.dawan.formations.models.Utilisateur;
import org.dawan.formations.repositories.UtilisateurRepository;
import org.dawan.formations.services.UtilisateurService;
import org.dawan.formations.tools.DtoTools;
import org.dawan.formations.tools.HashTools;
import org.dawan.formations.tools.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class UtilisateurServiceImpl extends GenericServiceImpl<Utilisateur, UtilisateurDto, UtilisateurRepository> implements UtilisateurService {

    private final TokenTools tokenTools;
    private final EtudiantServiceImpl etudiantService;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository repo, TokenTools tokenTools, EtudiantServiceImpl etudiantService) {
        super(repo, Utilisateur.class, UtilisateurDto.class);
        this.tokenTools = tokenTools;
        this.etudiantService = etudiantService;
    }

    public List<UtilisateurDto> POSTFilteredByPage(UtilisateurDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getNom(), obj.getPrenom(), obj.getEmail(), obj.getStatut(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(UtilisateurDto obj) {
        return getRepo().getCount(obj.getNom(), obj.getPrenom(), obj.getEmail(), obj.getStatut());
    }

    @Override
    public UtilisateurDto POSTSave(UtilisateurDto obj) {
        Utilisateur u = DtoTools.convert(obj, Utilisateur.class);
        Etudiant e = null;
        try {
            if (u.getId() == null || u.getId() == 0) { // insertion
                u.setPassword(HashTools.hashSHA512(u.getPassword()));
                if (u.getStatut().equals(STATUT_UTILISATEUR.ETUDIANT)) {
                    e = new Etudiant();
                }
            } else { // modif
                UtilisateurDto userInDb = GETById(u.getId());
                if (!userInDb.getPassword().contentEquals(u.getPassword())) {
                    u.setPassword(HashTools.hashSHA512(u.getPassword()));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        u = repo.saveAndFlush(u);
        if (e != null) {
            e.setUtilisateur(u);
            e.setPromotions(new ArrayList<>());
            etudiantService.POSTSave(DtoTools.convert(e, EtudiantDto.class));
        }
        return getDto(u);
    }

    @Override
    public UtilisateurDto GETfindByEmail(String email) {
        return getDto(getRepo().findByEmail(email));
    }

    @Override
    public LoginResponseDto POSTcheckLogin(LoginDto login) throws Exception {
        Utilisateur user = getRepo().findByEmail(login.getEmail());
        if (user != null && user.getPassword().equals(HashTools.hashSHA512(login.getPassword()))) {
            return new LoginResponseDto(DtoTools.convert(user, SimpleUtilisateurDto.class), tokenTools.generateToken(user));
        } else {
            throw new Exception("Invalid credential");
        }
    }

    @Override
    public boolean POSTresetPassword(ChangePwdDto changePwdObj) throws Exception {
        boolean expired = tokenTools.isTokenExpired(changePwdObj.getToken());
        if (expired)
            throw new Exception("Error : Expired token, ask for reset again !");

        String newPassword = HashTools.hashSHA512(changePwdObj.getPassword());

        //récupérer l'utilisateur par email
        String email = tokenTools.getEmailFromToken(changePwdObj.getToken());
        Utilisateur u = getRepo().findByEmail(email);

        if (u != null) {
            String currentPwd = u.getPassword();

            if (newPassword.equals(currentPwd))
                throw new Exception("Error : updating with the same old password !");

            u.setPassword(newPassword);
            repo.saveAndFlush(u);
            return true;
        }
        return false;
    }
}
