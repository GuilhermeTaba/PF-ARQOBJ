package com.example.PF.User;

import com.example.PF.Evento.Evento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveUserDTO {

    private String nome;
    private Integer cpf;
    private Papel papel;

}
