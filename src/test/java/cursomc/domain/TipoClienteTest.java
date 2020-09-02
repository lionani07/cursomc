package cursomc.domain;

import cursomc.domain.enums.TipoCliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TipoClienteTest {

    @Test
    public void mustReturnTipoClienteWhenIsValid() {
        // arrange
        final var codigo = 1;

        // act
        final var tipoCliente = TipoCliente.getByCodigo(codigo);

        // assert
        assertEquals(TipoCliente.PESSOAFISICA, tipoCliente);
    }

    @Test
    public void mustThrowExceptionWhenCodigoInvalid() {
        // arrange
        final var codigo = 3;

        // act and assert
        assertThrows(IllegalArgumentException.class, ()-> TipoCliente.getByCodigo(codigo));
    }

}