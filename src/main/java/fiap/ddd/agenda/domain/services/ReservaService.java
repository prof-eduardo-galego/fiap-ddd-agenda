package fiap.ddd.agenda.domain.services;

import fiap.ddd.agenda.domain.entities.Reserva;
import fiap.ddd.agenda.domain.valueObjects.AgendaId;
import fiap.ddd.agenda.domain.valueObjects.Cidadao;
import fiap.ddd.agenda.domain.valueObjects.ServicoId;

public interface ReservaService {

    Reserva reservar(AgendaId agendaId, ServicoId servicoId, Cidadao cidadao);

}
