package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    UserRepository userRepository;
    PasswordEncoder encoder;

    @Override
    public User ajouter(User user) {


        encoder.encode(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User modifier(User user, long id) {
        userRepository.findById(id).orElse(null);
        return userRepository.save(user);
    }

    @Override
    public List<User> afficher() {
        return userRepository.findAll();
    }

    @Override
    public User afficherById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void block(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatut(false);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Utilisateur non valide avec l'id" + id);
        }
    }

    @Override
    public void deblock(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatut(true);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Utilisateur non valide avec l'id " + id);
        }
    }
    }

