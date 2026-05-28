package auth_service.services;

import auth_service.models.UserEntity;
import auth_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        // On vérifie si le nom d'utilisateur existe déjà
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Erreur : Ce nom d'utilisateur est déjà utilisé !");
        }
        
        // Plus tard, on viendra crypter le mot de passe ici avant de sauvegarder
        
        // On enregistre en base de données
        return userRepository.save(user);
    }
}