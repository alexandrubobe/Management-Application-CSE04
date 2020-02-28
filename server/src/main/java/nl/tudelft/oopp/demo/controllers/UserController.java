package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("test")
    @ResponseBody
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @GetMapping("identifyMe/{credentials}")
    @ResponseBody
    public boolean authenticate(@PathVariable String credentials) {
        String[] userInputs = credentials.split(":");
        String givenUsername = userInputs[0];
        String givenPassword = userInputs[1];
        //query DB to see if a user w/givenUsername and password exists
        List<User> users = userRepository.findByUserNameAndUserPassword(givenUsername, givenPassword);
        for (User u: users) {
            System.out.println(u.toString());
        }
        if (users.size() == 0) {
            return false;
        }
        return true;
    }
}
