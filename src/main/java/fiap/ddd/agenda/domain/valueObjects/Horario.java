package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Horario {
    @NonNull LocalDateTime horario;

    public Horario(LocalDateTime horario) {
        if (horario.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Nao e possivel criar agendas retroativas");
        }
        this.horario = horario;
    }
}
