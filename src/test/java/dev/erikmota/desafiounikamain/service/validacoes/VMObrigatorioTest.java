package dev.erikmota.desafiounikamain.service.validacoes;

import dev.erikmota.desafiounikamain.models.Monitorador;
import dev.erikmota.desafiounikamain.service.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VMObrigatorioTest {
    @InjectMocks
    private VMObrigatorio validacao;
    @Mock
    private Monitorador m;

    @Test
    @DisplayName("Retornar sucesso por monitorador com data, email, ativo")
    void validarCase1(){
        given(m.getData()).willReturn(LocalDate.of(2024,1,1));
        given(m.getEmail()).willReturn("example@gmail.com");
        given(m.getAtivo()).willReturn(true);

        assertDoesNotThrow(() -> validacao.validar(m));
    }

    @Test
    @DisplayName("Retornar exception por monitorador sem data")
    void validarCase2(){
        given(m.getData()).willReturn(null);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> validacao.validar(m));
        assertEquals("O data é obrigatório!", exception.getMessage());
    }
    @Test
    @DisplayName("Retornar exception por monitorador sem email")
    void validarCase3(){
        given(m.getData()).willReturn(LocalDate.of(2024,1,1));
        given(m.getEmail()).willReturn(null);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> validacao.validar(m));
        assertEquals("O email é obrigatório!", exception.getMessage());
    }

    @Test
    @DisplayName("Retornar exception por monitorador com email inválido")
    void validarCase5(){
        given(m.getData()).willReturn(LocalDate.of(2024,1,1));
        given(m.getEmail()).willReturn("example");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> validacao.validar(m));
        assertEquals("Digite um email válido!", exception.getMessage());
    }
    @Test
    @DisplayName("Retornar exception por monitorador sem ativo")
    void validarCase4(){
        given(m.getData()).willReturn(LocalDate.of(2024,1,1));
        given(m.getEmail()).willReturn("example@gmail.com");
        given(m.getAtivo()).willReturn(null);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> validacao.validar(m));
        assertEquals("O ativo é obrigatório!", exception.getMessage());
    }
}