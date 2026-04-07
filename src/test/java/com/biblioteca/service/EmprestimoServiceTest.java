package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoServiceTest {

    private EmprestimoService service = new EmprestimoService();

    @Test
    void deveEmprestarLivroComSucesso() {
        Usuario usuario = new Usuario("João");
        Livro livro = new Livro("Java", "Autor");

        service.emprestarLivro(usuario, livro);

        assertFalse(livro.isDisponivel());
        assertEquals(1, usuario.getQuantidadeLivrosEmprestados());
    }

    @Test
    void naoDeveEmprestarLivroIndisponivel() {
        Usuario usuario = new Usuario("João");
        Livro livro = new Livro("Java", "Autor");

        livro.setDisponivel(false);

        assertThrows(IllegalArgumentException.class, () -> {
            service.emprestarLivro(usuario, livro);
        });
    }

    @Test
    void naoDeveEmprestarUsuarioComMulta() {
        Usuario usuario = new Usuario("João");
        Livro livro = new Livro("Java", "Autor");

        usuario.aplicarMulta(10);

        assertThrows(IllegalArgumentException.class, () -> {
            service.emprestarLivro(usuario, livro);
        });
    }

    @Test
    void naoDeveUltrapassarLimiteDeLivros() {
        Usuario usuario = new Usuario("João");

        usuario.adicionarEmprestimo();
        usuario.adicionarEmprestimo();
        usuario.adicionarEmprestimo();

        Livro livro = new Livro("Java", "Autor");

        assertThrows(IllegalArgumentException.class, () -> {
            service.emprestarLivro(usuario, livro);
        });
    }

    @Test
    void deveDevolverLivroSemAtraso() {
        Usuario usuario = new Usuario("João");
        Livro livro = new Livro("Java", "Autor");

        service.emprestarLivro(usuario, livro);
        service.devolverLivro(usuario, livro, 0);

        assertTrue(livro.isDisponivel());
        assertEquals(0, usuario.getQuantidadeLivrosEmprestados());
        assertFalse(usuario.isPossuiMultaPendente());
    }

    @Test
    void deveAplicarMultaAoDevolverComAtraso() {
        Usuario usuario = new Usuario("João");
        Livro livro = new Livro("Java", "Autor");

        service.emprestarLivro(usuario, livro);
        service.devolverLivro(usuario, livro, 3);

        assertTrue(usuario.isPossuiMultaPendente());
        assertEquals(6.0, usuario.getValorMulta());
    }

    @Test
    void naoDeveDevolverLivroNaoEmprestado() {
        Usuario usuario = new Usuario("João");
        Livro livro = new Livro("Java", "Autor");

        assertThrows(IllegalArgumentException.class, () -> {
            service.devolverLivro(usuario, livro, 0);
        });
    }
}