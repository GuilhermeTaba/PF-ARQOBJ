package com.example.PF.Evento;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EditEventoDTO {

    private String nome;
    private LocalDateTime data;

}