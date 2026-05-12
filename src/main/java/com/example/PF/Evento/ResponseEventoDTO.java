package com.example.PF.Evento;

import com.example.PF.User.ResponseUserDTO;
import com.example.PF.User.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ResponseEventoDTO {

    private String nome;
    private LocalDateTime data;

    public static @NonNull ResponseEventoDTO toDTO(Evento evento) {
        ResponseEventoDTO responseEventoDTO = new ResponseEventoDTO();
        responseEventoDTO.setNome(evento.getNome());

        responseEventoDTO.setData(evento.getData());
        return responseEventoDTO;
    }
}
