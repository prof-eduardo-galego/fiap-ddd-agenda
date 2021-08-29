package fiap.ddd.agenda.application.controllers;

import fiap.ddd.agenda.application.dto.CidadaoDto;
import fiap.ddd.agenda.application.dto.HorarioDto;
import fiap.ddd.agenda.domain.entities.*;
import fiap.ddd.agenda.domain.services.ReservaService;
import fiap.ddd.agenda.domain.valueObjects.*;
import fiap.ddd.agenda.infrastructure.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AgendaController {

    private final AgendaRepository agendaRepository;
    private final ReservaService reservaService;

    @Autowired
    public AgendaController(AgendaRepository agendaRepository, ReservaService reservaService) {
        this.agendaRepository = agendaRepository;
        this.reservaService = reservaService;
    }

    // Listar todos os orgaos
    @GetMapping("/orgaos")
    public List<Orgao> getAllOrgao() {
        return agendaRepository.listAllOrgao();
    }

    @GetMapping("/orgaos/{idOrgao}/locais")
    public List<Local> getLocalByOrgao(@PathVariable("idOrgao") Integer idOrgao) {
        return agendaRepository.listLocalByOrgao(new OrgaoId(idOrgao));
    }

    @GetMapping("/orgaos/{idOrgao}/locais/{idLocal}/servicos")
    public List<Servico> getServicoByOrgaoLocal(@PathVariable("idOrgao") Integer idOrgao,
                                                @PathVariable("idLocal") Integer idLocal) {
        return agendaRepository.listServicoByOrgao(new OrgaoId(idOrgao));
    }

    @GetMapping("/orgaos/{idOrgao}/locais/{idLocal}/servicos/{idServico}/horarios")
    public List<HorarioDto> getHorariosByOrgaoLocalServico(@PathVariable("idOrgao") Integer idOrgao,
                                                           @PathVariable("idLocal") Integer idLocal,
                                                           @PathVariable("idServico") Integer idServico) {
        List<Agenda> agendas = agendaRepository.listAgendaByLocal(new LocalId(idLocal));
        return agendas.stream()
                .map(agenda -> new HorarioDto(
                        agenda.getHorario().getHorario().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        agenda.getAgendaId().getId().toString()))
                .collect(Collectors.toList());
    }

    @PostMapping("/orgaos/{idOrgao}/locais/{idLocal}/servicos/{idServico}/horarios/{idAgenda}/agendar")
    public ResponseEntity<Reserva> getHorariosByOrgaoLocalServico(@PathVariable("idOrgao") Integer idOrgao,
                                                                  @PathVariable("idLocal") Integer idLocal,
                                                                  @PathVariable("idServico") Integer idServico,
                                                                  @PathVariable("idAgenda") String idAgenda,
                                                                  @RequestBody CidadaoDto cidadaoDto) {
        try {
            Cidadao cidadao = new Cidadao(new NomeCompleto(cidadaoDto.getNomeCompleto()), new CPF(cidadaoDto.getCpf()));
            Reserva reserva = reservaService.reservar(new AgendaId(idAgenda), new ServicoId(idServico), cidadao);
            return ResponseEntity.ok(reserva);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
