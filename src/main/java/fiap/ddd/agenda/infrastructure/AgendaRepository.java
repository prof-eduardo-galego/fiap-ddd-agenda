package fiap.ddd.agenda.infrastructure;

import fiap.ddd.agenda.domain.entities.Agenda;
import fiap.ddd.agenda.domain.entities.Local;
import fiap.ddd.agenda.domain.entities.Orgao;
import fiap.ddd.agenda.domain.entities.Servico;
import fiap.ddd.agenda.domain.valueObjects.AgendaId;
import fiap.ddd.agenda.domain.valueObjects.LocalId;
import fiap.ddd.agenda.domain.valueObjects.OrgaoId;
import fiap.ddd.agenda.domain.valueObjects.ServicoId;

import java.util.List;

public interface AgendaRepository {
    void refresh(Agenda agenda);

    void save(List<Agenda> gradeHorarios);

    void save(Agenda agenda);

    List<Orgao> listAllOrgao();

    List<Local> listLocalByOrgao(OrgaoId orgaoId);

    List<Servico> listServicoByOrgao(OrgaoId orgaoId);

    List<Agenda> listAgendaByLocal(LocalId localId);

    Agenda findAgendaById(AgendaId agendaId);

    Servico findServicoById(ServicoId servicoId);
}
