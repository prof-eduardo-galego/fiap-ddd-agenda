package fiap.ddd.agenda.domain.services;

import fiap.ddd.agenda.domain.entities.Local;
import fiap.ddd.agenda.domain.entities.Orgao;

import java.time.LocalDate;

public interface GerenciamentoAgendaService {

    void criarGradeHorarios(LocalDate data, Orgao orgao, Local local);
}
