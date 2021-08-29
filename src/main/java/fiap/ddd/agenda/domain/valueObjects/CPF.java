package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class CPF {
    @NonNull String numero;

    public CPF(String numero) {
        if (numero.isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio");
        }

        final String somenteNumeros = numero.replaceAll("[^0-9]", "");
        if (somenteNumeros.length() != 11) {
            throw new IllegalArgumentException("Numero de caracteres invalidos para CPF");
        }

        // TODO adicionar validacao de digitos

        this.numero = somenteNumeros;
    }
}
