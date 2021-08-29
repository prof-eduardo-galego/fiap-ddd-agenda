package fiap.ddd.agenda.infrastructure;

import fiap.ddd.agenda.domain.entities.Reserva;

public interface ReservaRepository {

    void save(Reserva reserva);

}
