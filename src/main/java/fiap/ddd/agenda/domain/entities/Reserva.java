package fiap.ddd.agenda.domain.entities;

import fiap.ddd.agenda.domain.valueObjects.Cidadao;
import fiap.ddd.agenda.domain.valueObjects.ReservaId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "reservaId")
public class Reserva {

    @NonNull
    private ReservaId reservaId;
    @NonNull
    private Agenda agenda;
    @NonNull
    private Servico servico;
    @NonNull
    private Cidadao cidadao;

    public Reserva(Agenda agenda, Servico servico, Cidadao cidadao) {
        this.reservaId = new ReservaId();
        this.agenda = agenda;
        this.servico = servico;
        this.cidadao = cidadao;
    }

}
