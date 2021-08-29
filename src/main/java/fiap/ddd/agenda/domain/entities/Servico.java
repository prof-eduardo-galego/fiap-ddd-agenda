package fiap.ddd.agenda.domain.entities;

import fiap.ddd.agenda.domain.valueObjects.DescricaoCurta;
import fiap.ddd.agenda.domain.valueObjects.ServicoId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "servicoId")
public class Servico {

    @NonNull
    private ServicoId servicoId;
    @NonNull
    private DescricaoCurta descricao;
    @NonNull
    private Orgao orgao;

    public Servico(ServicoId servicoId, DescricaoCurta descricao, Orgao orgao) {
        this.servicoId = servicoId;
        this.descricao = descricao;
        this.orgao = orgao;
    }
}
