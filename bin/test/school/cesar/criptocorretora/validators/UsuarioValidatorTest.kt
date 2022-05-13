package school.cesar.criptocorretora.validators

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil


class UsuarioValidatorTest {
    @Test
    fun `deve lancar excecao quando nome for vazio`(){
        val usuario = Usuario(123, "70639761476",nome = "",email = "mailson@souza.com",senha = "12345678",
            carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O nome deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando cpf for vazio`(){
        val usuario = Usuario(123, "",nome = "Mailson",email = "mailson@souza.com",senha = "12345678",
            carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O cpf deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando e-mail for vazio`(){
        val usuario = Usuario(123, "12345678910",nome = "Mailson",email = "",senha = "12345678",
            carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O e-mail deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando a senha for vazia`(){
        val usuario = Usuario(123, "12345678910",nome = "Mailson",email = "mailson@souza.com",senha = "",
            carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O senha deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando nome tiver seu tamanho superior a 200 caracteres`(){
        val usuario = Usuario(123, "70639761476",
            nome = "O goleiro Mailson lamentou a queda do Sport nas quartas de final do Campeonato Pernambucano, " +
                    "na noite desta quarta-feira, ao ser derrotado nos pênaltis para o Salgueiro na Ilha do Retiro " +
                    "- após empata em 2 a 2 no tempo normal. No entanto, ele afirmou que o resultado não impacta " +
                    "o clube para a estreia na Série B do Brasileiro, sábado, contra o Sampaio Corrêa, " +
                    "também em casa",email = "mailson@souza.com",senha = "12345678", carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O campo nome deve ter menos de 200 caracteres", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando cpf tiver tamanho diferente de 11 digitos`(){
        val usuario = Usuario(123, "123",nome = "Mailson",email = "mailson@souza.com",senha = "12345678",
            carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O campo cpf deve ter 11 caracteres numericos", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando a senha não tiver tamanho entre 8 e 15 caracteres`(){
        val usuario = Usuario(123, "12345678910",nome = "Mailson",email = "mailson@souza.com",
            senha = "12", carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O campo confirmação senha deve ter entre 8 e 15 caracteres", it.message)
        }
    }
    @Test
    fun `deve lancar excecao quando a senha não tiver tamanho maior 15 caracteres`(){
        val usuario = Usuario(123, "12345678910",nome = "Mailson",email = "mailson@souza.com",
            senha = "12345678910234567", carteira = Carteira());
        assertThrows<RuntimeException> {
            UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil()).valida(usuario)
        }.also {
            assertEquals("O campo confirmação senha deve ter entre 8 e 15 caracteres", it.message)
        }
    }
}