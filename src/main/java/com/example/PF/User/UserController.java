package com.example.PF.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseUserDTO saveCurso(  @RequestHeader("X-USER-ID") Integer userId, @RequestBody SaveUserDTO user) {
        return userService.save(user,userId);
    }

    @GetMapping
    public Page<ResponseUserDTO> listUser( Pageable pageable){
        return userService.list(pageable);
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("X-USER-ID")Integer userId, Integer id){
        userService.deleteUser(id,userId);
    }
    @PutMapping("/{cpf}")
    public ResponseUserDTO editCurso(  @RequestHeader("X-USER-ID") Integer userId,@PathVariable Integer cpf, @RequestBody EditUserDTO user) {
        return userService.edit(cpf, user,userId);
    }












}
