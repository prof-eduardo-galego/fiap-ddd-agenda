package fiap.ddd.agenda.infrastructure.impl;

import fiap.ddd.agenda.domain.entities.Reserva;
import fiap.ddd.agenda.infrastructure.ReservaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaRepositoryFakeImpl implements ReservaRepository {

    private static final List<Reserva> RESERVA_LIST = new ArrayList<>();

    @Override
    public void save(Reserva reserva) {
        RESERVA_LIST.add(reserva);
    }
}
