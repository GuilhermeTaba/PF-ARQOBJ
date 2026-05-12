package com.example.PF.Evento;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SaveEventoDTO {

    private String nome;
    private LocalDateTime data;

}