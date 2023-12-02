package sptech.safemoney.dominio;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.safemoney.repositorio.ObjetivoRepository;
import sptech.safemoney.repositorio.UsuarioRepository;
import sptech.safemoney.servico.ObjetivoService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ObjetivoTest {
    @Autowired
    private ObjetivoService objetivoService;

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("setValorInvestido() deve lançar exceção caso o valor investido seja maior que o valor final")
    void depositar() {
        Objetivo objetivo = new Objetivo();

        // Preenchendo os campos
        objetivo.setId(1);
        objetivo.setNome("Exemplo de Objetivo");
        objetivo.setUrlImagem("https://exemplo.com/imagem.jpg");
        objetivo.setDataInicio(LocalDate.of(2023, 1, 1));
        objetivo.setDataTermino(LocalDate.of(2023, 12, 31));
        objetivo.setConcluida(0);
        objetivo.setValorInvestido(5000.0);
        objetivo.setValorFinal(1000.0);


        // Criando e configurando um objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setId(1);

        // Configurar campos do usuário, se necessário
        objetivo.setFkUsuario(usuario);
        assertThrows(ResponseStatusException.class,
                () -> objetivoService.depositar(objetivo, 3001.0));
    }
    @Test
    @DisplayName("Verifica se o objetivo foi concluido após o valor investido for igual ao valor final")
    void concluido() {

        Objetivo objetivo = new Objetivo();
        objetivo.setNome("Exemplo de Objetivo");
        objetivo.setUrlImagem("https://exemplo.com/imagem.jpg");
        objetivo.setDataInicio(LocalDate.of(2023, 1, 1));
        objetivo.setDataTermino(LocalDate.of(2023, 12, 31));
        objetivo.setConcluida(0);
        objetivo.setValorFinal(3000.0);
        objetivo.setValorInvestido(0.0);
        objetivoService.depositar(objetivo, 3000.0);


        assertEquals(1, objetivo.getConcluida());
    }


    @Test
    @DisplayName("Verifica se o objetivo foi concluido após o valor investido for igual ao valor final")
    void novoObjetivo() {

        Objetivo objetivo = new Objetivo();
        objetivo.setNome("Exemplo de Objetivo");
        objetivo.setUrlImagem("https://exemplo.com/imagem.jpg");
        objetivo.setDataInicio(LocalDate.of(2023, 12, 1));
        objetivo.setDataTermino(LocalDate.of(2023, 1, 31));
        objetivo.setConcluida(0);
        objetivo.setValorFinal(3000.0);
        objetivo.setValorInvestido(0.0);


        assertThrows(ResponseStatusException.class,
                () -> objetivoService.verificaSave(objetivo));
    }

    @Test
    @DisplayName("verifica se os valores de valorFinal e valorInvestido são válidos")
    void verificaValores() {

        Objetivo objetivo = new Objetivo();
        objetivo.setNome("Exemplo de Objetivo");
        objetivo.setUrlImagem("https://exemplo.com/imagem.jpg");
        objetivo.setDataInicio(LocalDate.of(2023, 12, 1));
        objetivo.setDataTermino(LocalDate.of(2023, 1, 31));
        objetivo.setConcluida(0);
        objetivo.setValorFinal(null);
        objetivo.setValorInvestido(-2220.0);


        assertThrows(ConstraintViolationException.class,
                () -> objetivoService.verificaValores(objetivo));
    }

}