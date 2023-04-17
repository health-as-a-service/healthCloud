package tn.esprit.healthCloud.services;


import tn.esprit.healthCloud.entities.User;

import java.util.List;

public interface IUserService {
    User ajouter (User user);
    User modifier (User user, long id);
    List<User> afficher ();
    User afficherById(long id);
    void supprimer (long id);
    void block (long id);
    void deblock (long id);
}
