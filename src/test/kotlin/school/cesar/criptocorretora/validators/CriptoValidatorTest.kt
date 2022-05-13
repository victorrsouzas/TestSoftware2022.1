package school.cesar.criptocorretora.validators
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Cripto
import java.math.BigDecimal

class CriptoValidatorTest {

    @Test
    fun `deve lancar excecao quando nome for vazio`(){
        val user = Cripto(id = -100, nome = "", cotacaoAtualEmDollar =  BigDecimal("0.01") );
        assertThrows<RuntimeException> {
            CriptoValidador().valida(user)
        }.also {
            assertEquals("O campo nome deve ser preenchido", it.message)
        }
    }



}