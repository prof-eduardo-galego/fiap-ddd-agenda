package fiap.ddd.agenda.domain;

import fiap.ddd.agenda.domain.entities.*;
import fiap.ddd.agenda.domain.valueObjects.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DomainEntitiesTest {

    @Test
    public void successCreateOrgao() {
        // Given

        // When
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));

        // Then
        assertThat(orgao).isNotNull();
        assertThat(orgao.getOrgaoId()).isNotNull();
        assertThat(orgao.getOrgaoId().getId()).isNotNull();
        assertThat(orgao.getOrgaoId().getId()).isEqualTo(1);
        assertThat(orgao.getDescricao()).isNotNull();
        assertThat(orgao.getDescricao().getDescricao()).isEqualTo("Detran");
    }

    @Test
    public void successCreateLocal() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));

        // When
        final Local local = new Local(new LocalId(10), new DescricaoCurta("Poupatempo Santo Amaro"), orgao);

        // Then
        assertThat(local).isNotNull();
        assertThat(local.getLocalId()).isNotNull();
        assertThat(local.getLocalId().getId()).isNotNull();
        assertThat(local.getLocalId().getId()).isEqualTo(10);
        assertThat(local.getDescricao()).isNotNull();
        assertThat(local.getDescricao().getDescricao()).isEqualTo("Poupatempo Santo Amaro");
        assertThat(local.getOrgao()).isEqualTo(orgao);
    }

    @Test
    public void successCreateService() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));

        // When
        final Servico servico = new Servico(new ServicoId(100), new DescricaoCurta("Renovacao de CNH"), orgao);

        // Then
        assertThat(servico).isNotNull();
        assertThat(servico.getServicoId()).isNotNull();
        assertThat(servico.getServicoId().getId()).isNotNull();
        assertThat(servico.getServicoId().getId()).isEqualTo(100);
        assertThat(servico.getDescricao()).isNotNull();
        assertThat(servico.getDescricao().getDescricao()).isEqualTo("Renovacao de CNH");
        assertThat(servico.getOrgao()).isEqualTo(orgao);
    }

    @Test
    public void successCreateCidadao() {
        // Given
        final NomeCompleto nomeCompleto = new NomeCompleto("Nome completo");
        final CPF cpf = new CPF("231.254.589-66");

        // When
        final Cidadao cidadao = new Cidadao(nomeCompleto, cpf);

        // Then
        assertThat(cidadao).isNotNull();
        assertThat(cidadao.getNomeCompleto()).isNotNull();
        assertThat(cidadao.getNomeCompleto().getNomeCompleto()).isEqualTo("Nome completo");
        assertThat(cidadao.getCpf()).isNotNull();
        assertThat(cidadao.getCpf().getNumero()).isEqualTo("23125458966");
    }

    @Test
    public void successCreateAgenda() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));
        final Local local = new Local(new LocalId(10), new DescricaoCurta("Poupatempo Santo Amaro"), orgao);
        final Horario horario = new Horario(LocalDateTime.now().plus(1, ChronoUnit.DAYS));

        // When
        final Agenda agenda = new Agenda(local, orgao, horario);

        // Then
        assertThat(agenda).isNotNull();
        assertThat(agenda.getAgendaId()).isNotNull();
        assertThat(agenda.getOrgao()).isNotNull();
        assertThat(agenda.getOrgao()).isEqualTo(orgao);
        assertThat(agenda.getLocal()).isNotNull();
        assertThat(agenda.getLocal()).isEqualTo(local);
        assertThat(agenda.getAgendaStatus()).isEqualTo(AgendaStatusEnum.VAGO);
    }

    @Test
    public void successCreateReserva() {
        // Given
        final Orgao orgao = new Orgao(new OrgaoId(1), new DescricaoCurta("Detran"));
        final Local local = new Local(new LocalId(10), new DescricaoCurta("Poupatempo Santo Amaro"), orgao);
        final Horario horario = new Horario(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        final Agenda agenda = new Agenda(local, orgao, horario);
        final Servico servico = new Servico(new ServicoId(100), new DescricaoCurta("Renovacao de CNH"), orgao);
        final NomeCompleto nomeCompleto = new NomeCompleto("Nome completo");
        final CPF cpf = new CPF("231.254.589-66");
        final Cidadao cidadao = new Cidadao(nomeCompleto, cpf);

        // When
        final Reserva reserva = new Reserva(agenda, servico, cidadao);

        // Then
        assertThat(reserva).isNotNull();
        assertThat(reserva.getReservaId()).isNotNull();
        assertThat(reserva.getCidadao()).isNotNull();
    }
}
