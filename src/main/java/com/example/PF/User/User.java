package com.example.PF.User;

import com.example.PF.Evento.Evento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private Integer cpf;
    @Column(nullable = false)
    private Papel papel;
    @OneToMany(mappedBy = "user")
    private List<Evento> eventos;


    public static @NonNull User toModel(SaveUserDTO saveUserDTO) {
        User user = new User();

        user.setNome(saveUserDTO.getNome());
        user.setCpf(saveUserDTO.getCpf());
        user.setPapel(saveUserDTO.getPapel());
        return user;
    }


}
