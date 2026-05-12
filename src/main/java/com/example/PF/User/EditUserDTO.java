package com.example.PF.User;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class EditUserDTO {
    private String nome;
    private Integer cpf;

    public static @NonNull ResponseUserDTO toDTO(User user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setNome(user.getNome());
        responseUserDTO.setCpf(user.getCpf());
        responseUserDTO.setPapel(user.getPapel());
        return responseUserDTO;
    }
}
