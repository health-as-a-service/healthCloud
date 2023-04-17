package tn.esprit.healthCloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.healthCloud.entities.User;

import tn.esprit.healthCloud.services.IUserService;

import java.util.List;
@RestController
@AllArgsConstructor
public class UserController {
    IUserService iUserService;


    @PostMapping("/User")
    User addAdmin(@RequestBody User user){
        return iUserService.ajouter(user);
    }
    @PutMapping("/User/{id}")
    User modifierAdmin(@RequestBody User user, @PathVariable long id){
        return iUserService.modifier(user,id);
    }
    @GetMapping("/User")
    List<User> afficherAdmin(){
        return iUserService.afficher();
    }
    @GetMapping("/User/{id}")
    User afficherAdminById(@PathVariable long id){
        return iUserService.afficherById(id);
    }
    @DeleteMapping("/User/{id}")
    void supprimeradmin(@PathVariable long id){
        iUserService.supprimer(id);
    }

    @DeleteMapping("/User/block/{id}")
    void blockerAdmin(@PathVariable long id ){
        iUserService.block(id);
    }
    @DeleteMapping("/User/deblock/{id}")
    void deblockerAdmin(@PathVariable long id ){
        iUserService.deblock(id);
    }

}
