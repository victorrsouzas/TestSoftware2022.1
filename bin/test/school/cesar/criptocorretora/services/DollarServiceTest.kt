package school.cesar.criptocorretora.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DollarServiceTest {
    @Test
    fun `testar valor do dollar`() {
        var dollarServiceTest = DollarService()
        Assertions.assertTrue(
            BigDecimal(4) <= dollarServiceTest.pegarValorDollarAtual()
                    || BigDecimal(5) >= dollarServiceTest.pegarValorDollarAtual()
        )
    }
}