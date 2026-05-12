package com.example.PF.Evento;

import com.example.PF.Evento.EditEventoDTO;

import com.example.PF.Evento.ResponseEventoDTO;
import com.example.PF.Evento.SaveEventoDTO;
import com.example.PF.Evento.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEventoDTO saveEvento(  @RequestHeader("X-USER-ID") Integer userId,@RequestBody SaveEventoDTO saveEventoDTO) {
        return eventoService.save(saveEventoDTO,userId);
    }

    @GetMapping
    public Page<ResponseEventoDTO> listEvento(  Pageable pageable){
        return eventoService.list(pageable);
    }

    @DeleteMapping
    public void deleteEvento(  @RequestHeader("X-USER-ID") Integer userId,String nome){
        eventoService.deleteEvento(nome,userId);
    }
    @PutMapping("/{nome}")
    public ResponseEventoDTO editEvento(  @RequestHeader("X-USER-ID") Integer userId,@PathVariable String nome, @RequestBody EditEventoDTO evento) {
        return eventoService.edit(nome, evento,userId);
    }




}
