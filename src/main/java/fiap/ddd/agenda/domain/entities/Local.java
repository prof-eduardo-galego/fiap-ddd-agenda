package fiap.ddd.agenda.domain.entities;

import fiap.ddd.agenda.domain.valueObjects.DescricaoCurta;
import fiap.ddd.agenda.domain.valueObjects.LocalId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "localId")
public class Local {

    @NonNull
    private LocalId localId;
    @NonNull
    private DescricaoCurta descricao;
    @NonNull
    private Orgao orgao;

    public Local(LocalId localId, DescricaoCurta descricao, Orgao orgao) {
        this.localId = localId;
        this.descricao = descricao;
        this.orgao = orgao;
    }
}
