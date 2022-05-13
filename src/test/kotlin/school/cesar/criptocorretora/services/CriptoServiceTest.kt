package school.cesar.criptocorretora.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.builders.CriptoBuilder
import school.cesar.criptocorretora.excecoes.CriptoInvalidaException
import school.cesar.criptocorretora.repositories.CriptoRepository
import school.cesar.criptocorretora.validators.CriptoValidador
import java.math.BigDecimal

class CriptoServiceTest {
    val criptoRepositoryTeste = CriptoRepository()
    val criptoBuilderTeste = CriptoBuilder()
    val criptoValidadorTeste = CriptoValidador()

    val criptoService = CriptoService(criptoBuilderTeste , criptoValidadorTeste, criptoRepositoryTeste)

    @Test
    fun `Erro ao buscar por uma cripto nao existente`(){
        criptoService.add("Cardano", BigDecimal(4.3))
        assertThrows<CriptoInvalidaException> {
            criptoService.buscarPorId(1)
        }.also {
            Assertions.assertEquals("Id não existente", it.message)
        }

    }





}