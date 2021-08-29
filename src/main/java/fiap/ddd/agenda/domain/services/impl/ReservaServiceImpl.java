package fiap.ddd.agenda.domain.services.impl;

import fiap.ddd.agenda.domain.entities.Agenda;
import fiap.ddd.agenda.domain.entities.Reserva;
import fiap.ddd.agenda.domain.entities.Servico;
import fiap.ddd.agenda.domain.services.ReservaService;
import fiap.ddd.agenda.domain.valueObjects.AgendaId;
import fiap.ddd.agenda.domain.valueObjects.Cidadao;
import fiap.ddd.agenda.domain.valueObjects.ServicoId;
import fiap.ddd.agenda.infrastructure.AgendaRepository;
import fiap.ddd.agenda.infrastructure.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    private AgendaRepository agendaRepository;
    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(AgendaRepository agendaRepository, ReservaRepository reservaRepository) {
        this.agendaRepository = agendaRepository;
        this.reservaRepository = reservaRepository;
    }

    public Reserva reservar(AgendaId agendaId, ServicoId servicoId, Cidadao cidadao) {
        if (agendaId == null) {
            throw new IllegalArgumentException("Agenda nao pode ser vazio");
        }
        if (servicoId == null) {
            throw new IllegalArgumentException("Servico nao pode ser vazio");
        }
        if (cidadao == null) {
            throw new IllegalArgumentException("Cidadao nao pode ser vazio");
        }

        final Servico servico = agendaRepository.findServicoById(servicoId);
        if (servico == null) {
            throw new IllegalArgumentException("Servico nao encontrado");
        }

        final Agenda agenda = agendaRepository.findAgendaById(agendaId);
        if (agenda == null) {
            throw new IllegalArgumentException("Agenda nao encontrada");
        }

        agenda.reservar();
        agendaRepository.save(agenda);

        final Reserva reserva = new Reserva(agenda, servico, cidadao);
        reservaRepository.save(reserva);

        return reserva;
    }

}
