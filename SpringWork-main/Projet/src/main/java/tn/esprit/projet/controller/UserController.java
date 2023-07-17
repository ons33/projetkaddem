package tn.esprit.projet.controller;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.springjwt.models.Role;
import tn.esprit.projet.springjwt.models.User;
import tn.esprit.projet.springjwt.repository.UserRepository;

import java.util.List;



@AllArgsConstructor
@RestController

@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired

    private UserRepository userRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/getall")
    public List<User> GetUser(){

        return  userRepository.findAll();
    }
    @PostMapping("/user/addetude")
    public void addUser(@RequestBody User E){

        userRepository.save(E);
    }

    @PutMapping("/user/put/{id}")
  //update user with role

    public void updateUser(@PathVariable("id") Long id, @RequestBody User E){
//update role with user



User u = new User();
        E.setId(id);
        E.setRoles(u.getRoles());
        userRepository.save(E);
    }
    @DeleteMapping("/user/del/{id}")
    public void deleteEtud(@PathVariable("id") Long id){

        userRepository.deleteById(id);
    }

    @GetMapping("/user/find/{id}")
    public User findEtd(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }
}