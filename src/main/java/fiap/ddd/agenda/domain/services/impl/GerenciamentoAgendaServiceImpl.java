package fiap.ddd.agenda.domain.services.impl;

import fiap.ddd.agenda.domain.entities.Agenda;
import fiap.ddd.agenda.domain.entities.Local;
import fiap.ddd.agenda.domain.entities.Orgao;
import fiap.ddd.agenda.domain.services.GerenciamentoAgendaService;
import fiap.ddd.agenda.domain.valueObjects.Horario;
import fiap.ddd.agenda.infrastructure.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GerenciamentoAgendaServiceImpl implements GerenciamentoAgendaService {

    private AgendaRepository agendaRepository;

    @Autowired
    public GerenciamentoAgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public void criarGradeHorarios(LocalDate data, Orgao orgao, Local local) {
        List<Agenda> gradeHorarios = new ArrayList<>();
        for (int d = 0; d <= 3; d++) {
            int id = 1;
            LocalDate date = LocalDate.now().plusDays(d);

            for (int h = 8; h <= 18; h++) {
                LocalDateTime time = date.atTime(h, 0, 0);
                gradeHorarios.add(new Agenda(local, orgao, new Horario(time)));
            }
        }
        agendaRepository.save(gradeHorarios);
    }

}
