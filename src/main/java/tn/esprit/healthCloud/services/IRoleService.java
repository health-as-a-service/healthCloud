package tn.esprit.healthCloud.services;

import tn.esprit.healthCloud.entities.Role;

import java.util.List;

public interface IRoleService {

    Role ajouter (Role role);
    Role modifier (Role role, int id);
    List<Role> afficher ();
    Role afficherById(int id);
    void supprimer (int id);
}
