package school.cesar.criptocorretora.services

import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import java.math.BigDecimal

class CarteiraServiceTest {
    private val usuarioServiceMock = mockk<UsuarioService>()
    private val criptoServiceMock = mockk<CriptoService>()
    private val carteiraService = CarteiraService(usuarioServiceMock, criptoServiceMock)
    @Test
    fun `deve comprar uma cripto inexistente na carteira`() {
        val cripto = Cripto(957, "Cardano", BigDecimal(2022))
        val usuario = Usuario(123, "70638761476", "Mailson", "mailson@souza.com",
            "AssajakaA", Carteira())

        every { usuarioServiceMock.buscarPorId(123) } returns usuario
        every { criptoServiceMock.buscarPorId(957) } returns cripto

        carteiraService.comprar(123, 957, BigDecimal(2022))

        assertEquals(BigDecimal(1), usuario.carteira.cripto[cripto])
    }

    @Test
    fun `deve consultar valores agrupados`() {
        val usuario = Usuario(123, "70638761476", "Mailson", "mailson@souza.com",
            "AssajakaA", Carteira())
        every { usuarioServiceMock.buscarPorId(123).carteira.cripto } returns usuario.carteira.cripto

        assertNotNull(carteiraService.consultarValoresAgrupados(123))
    }
}