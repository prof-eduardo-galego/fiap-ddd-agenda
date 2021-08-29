package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class AgendaId {
    @NonNull UUID id;

    public AgendaId() {
        this.id = UUID.randomUUID();
    }

    public AgendaId(String id) {
        this.id = UUID.fromString(id);
    }
}
