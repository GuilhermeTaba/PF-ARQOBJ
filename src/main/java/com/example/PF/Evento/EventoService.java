package com.example.PF.User;

import com.example.PF.Evento.*;
import com.example.PF.exception.EventoAlreadyExists;
import com.example.PF.exception.EventoNotFound;
import com.example.PF.exception.UsuarioNotAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserService userService;

    public ResponseEventoDTO save(SaveEventoDTO saveEventoDTO, Integer userId) {
        if(!userService.isAdmin(userId)){
            throw new UsuarioNotAdmin();
        }
        if (eventoRepository.existsByNome(saveEventoDTO.getNome())) {
            throw new EventoAlreadyExists();
        }

        Evento evento = Evento.toModel(saveEventoDTO);
        evento = eventoRepository.save(evento);

        return ResponseEventoDTO.toDTO(evento);
    }

    public Page<ResponseEventoDTO> list(Pageable pageable){

        return eventoRepository
                .findAll(pageable)
                .map(evento -> ResponseEventoDTO.toDTO(evento));

    }
    public void deleteEvento(String nome, Integer userId){
        if(!userService.isAdmin(userId)){
            throw new UsuarioNotAdmin();
        }
        eventoRepository.deleteByNome(nome);

    }

    public Evento get(String nome) {
        return eventoRepository.findByNome(nome)
                .orElseThrow(() -> new EventoNotFound());
    }
    public ResponseEventoDTO edit(String nome, EditEventoDTO editEventoDTO, Integer userId) {
        if(!userService.isAdmin(userId)){
            throw new UsuarioNotAdmin();
        }
        Evento eventoDB = new Evento();


        eventoDB.setNome(editEventoDTO.getNome());
        eventoDB.setData(editEventoDTO.getData());

        eventoDB = eventoRepository.save(eventoDB);
        return ResponseEventoDTO.toDTO(eventoDB);
    }
}
