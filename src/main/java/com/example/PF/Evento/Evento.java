package com.example.PF.Evento;

import com.example.PF.User.SaveUserDTO;
import com.example.PF.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User usuario;

    public static @NonNull Evento toModel(SaveEventoDTO saveEventoDTO) {
        Evento evento = new Evento();

        evento.setNome(saveEventoDTO.getNome());
        evento.setData(saveEventoDTO.getData());

        return evento;
    }


}
