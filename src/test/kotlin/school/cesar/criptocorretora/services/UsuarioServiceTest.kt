package school.cesar.criptocorretora.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.excecoes.UsuarioInvalidoException
import school.cesar.criptocorretora.repositories.UsuarioRepository
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil
import school.cesar.criptocorretora.validators.UsuarioValidator


class UsuarioServiceTest {

    val usuario = Usuario(123, "70638761476",nome = "Mailson",email = "mailson@souza.com",
        senha = "AssajakaA123", carteira = Carteira())
    val usuarioValidator = UsuarioValidator(cpfUtil = CPFUtil(), emailUtil = EmailUtil(), senhaUtil = SenhaUtil() )
    val usuarioService = UsuarioService(usuarioRepository = UsuarioRepository(), usuarioValidator = usuarioValidator)
    @Test
    fun `procurar por usuario valido existente`(){
        usuarioService.adicionar(usuario = usuario)
        //Assertions.assertTrue(usuarioService.buscarPorId(1).id)
        //assertTrue(usuarioService.buscarPorId(1).id)
        Assertions.assertEquals(usuarioService.buscarPorId(123).id, 123)


    }
    @Test
    fun `procurar por usuario valido inexistente`(){
        usuarioService.adicionar(usuario = usuario)
        //Assertions.assertTrue(usuarioService.buscarPorId(1).id)
        //assertTrue(usuarioService.buscarPorId(1).id)
        assertThrows<UsuarioInvalidoException> {
            usuarioService.buscarPorId(77)
        }.also {
            Assertions.assertEquals("Id Não encontrado", it.message)
        }
        //Assertions.assertEquals(usuarioService.buscarPorId(123).id, 123)


    }


}