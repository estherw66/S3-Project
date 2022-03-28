package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.models.Employee;
import com.fontys.S3ITProject.models.Person;
import com.fontys.S3ITProject.persistence.AccountRepository;
import com.fontys.S3ITProject.persistence.Impl.AccountRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

//    private AccountRepositoryImpl fakeDataBase;
//
//    public LoginController(AccountRepositoryImpl fakeDataBase) { this.fakeDataBase = fakeDataBase; }
//
//
//
//    @GetMapping("/e.wolfs@goldskye.com/password")
//    public ResponseEntity<Person> getPerson(){
//        List<Person> users = fakeDataBase.getAllUsers();
//
//        for (Person person : users){
//            if (person.getEmail() == "e.wolfs@goldskye.com"){
//                return ResponseEntity.ok().body(person);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }
//
//        return null;
//    }
//
//    @RequestMapping(value = "/?username={username}&?password={password}", method = RequestMethod.GET)
//    public ResponseEntity<Person> checkLogin(@PathVariable String username, String password){
//        Person user = fakeDataBase.checkLogin(username, password);
//
//        if (user != null){
//            return ResponseEntity.ok().body(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Person>> getAllUsers(){
//        List<Person> users = fakeDataBase.getAllUsers();
//
//        if (users != null){
//            return ResponseEntity.ok().body(users);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
