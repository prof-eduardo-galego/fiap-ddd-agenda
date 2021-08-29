package fiap.ddd.agenda.application.dto;

import lombok.Value;

@Value
public class HorarioDto {

    private String datahoraFormatado;
    private String agendaId;

    public HorarioDto(String datahoraFormatado, String agendaId) {
        this.datahoraFormatado = datahoraFormatado;
        this.agendaId = agendaId;
    }

}
