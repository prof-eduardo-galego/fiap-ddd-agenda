package fiap.ddd.agenda.infrastructure.impl;

import fiap.ddd.agenda.domain.entities.Agenda;
import fiap.ddd.agenda.domain.entities.Local;
import fiap.ddd.agenda.domain.entities.Orgao;
import fiap.ddd.agenda.domain.entities.Servico;
import fiap.ddd.agenda.domain.valueObjects.*;
import fiap.ddd.agenda.infrastructure.AgendaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AgendaRepositoryFakeImpl implements AgendaRepository {

    private static final List<Orgao> ORGAO_LIST = new ArrayList<>();
    private static final List<Local> LOCAL_LIST = new ArrayList<>();
    private static final List<Servico> SERVICO_LIST = new ArrayList<>();
    private static final List<Agenda> AGENDA_LIST = new ArrayList<>();

    static {
        final Orgao iirgd = new Orgao(new OrgaoId(1), new DescricaoCurta("IIRGD"));
        ORGAO_LIST.add(iirgd);

        final Orgao detran = new Orgao(new OrgaoId(2), new DescricaoCurta("DETRAN"));
        ORGAO_LIST.add(detran);

        final Local local = new Local(new LocalId(1), new DescricaoCurta("Poupatempo Luz"), iirgd);
        LOCAL_LIST.add(local);
        LOCAL_LIST.add(new Local(new LocalId(2), new DescricaoCurta("Poupatempo Santo Amaro"), iirgd));
        LOCAL_LIST.add(new Local(new LocalId(3), new DescricaoCurta("Poupatempo Itaquera"), iirgd));
        LOCAL_LIST.add(new Local(new LocalId(4), new DescricaoCurta("Detran Armenia"), detran));
        LOCAL_LIST.add(new Local(new LocalId(5), new DescricaoCurta("Detran Villa Lobos"), detran));

        SERVICO_LIST.add(new Servico(new ServicoId(1), new DescricaoCurta("Emissao RG"), iirgd));
        SERVICO_LIST.add(new Servico(new ServicoId(2), new DescricaoCurta("Emissao de AAC"), iirgd));
        SERVICO_LIST.add(new Servico(new ServicoId(3), new DescricaoCurta("Renovacao de CNH"), detran));

        AGENDA_LIST.add(new Agenda(local, local.getOrgao(), new Horario(LocalDateTime.now().plus(10, ChronoUnit.MINUTES))));
    }

    public List<Orgao> listAllOrgao() {
        return ORGAO_LIST;
    }

    public List<Local> listAllLocal() {
        return LOCAL_LIST;
    }

    public List<Local> listLocalByOrgao(final OrgaoId orgaoId) {
        return LOCAL_LIST.stream()
                .filter(local -> local.getOrgao().getOrgaoId().equals(orgaoId))
                .collect(Collectors.toList());
    }

    public List<Servico> listAllServico() {
        return SERVICO_LIST;
    }

    public List<Servico> listServicoByOrgao(final OrgaoId orgaoId) {
        return SERVICO_LIST.stream()
                .filter(servico -> servico.getOrgao().getOrgaoId().equals(orgaoId))
                .collect(Collectors.toList());
    }

    public List<Agenda> listAllAgenda() {
        return AGENDA_LIST;
    }

    public List<Agenda> listAgendaByLocal(final LocalId localId) {
        return AGENDA_LIST.stream()
                .filter(agenda -> agenda.getAgendaStatus().equals(AgendaStatusEnum.VAGO)
                        && agenda.getLocal().getLocalId().equals(localId))
                .collect(Collectors.toList());
    }

    public Agenda findAgendaById(final AgendaId agendaId) {
        return AGENDA_LIST.stream()
                .filter(agenda -> agenda.getAgendaId().equals(agendaId))
                .findFirst().orElse(null);
    }

    @Override
    public Servico findServicoById(ServicoId servicoId) {
        return SERVICO_LIST.stream()
                .filter(servico -> servico.getServicoId().equals(servicoId))
                .findFirst().orElse(null);
    }

    @Override
    public void refresh(Agenda agenda) {
        // RELOAD DATA FROM DATABASE
    }

    @Override
    public void save(List<Agenda> gradeHorarios) {
        for (Agenda a : gradeHorarios) {
            upsert(a);
        }
    }

    @Override
    public void save(Agenda agenda) {
        upsert(agenda);
    }

    private void upsert(Agenda agenda) {
        final Agenda found = this.findAgendaById(agenda.getAgendaId());
        if (found != null) {
            AGENDA_LIST.remove(found);
        }
        AGENDA_LIST.add(agenda);
    }

}
