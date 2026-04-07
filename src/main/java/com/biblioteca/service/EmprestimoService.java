package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

public class EmprestimoService {

    private static final int LIMITE_LIVROS_EMPRESTADOS = 3;
    private static final double MULTA_POR_DIA_ATRASO = 2.0;

    public void emprestarLivro(Usuario usuario, Livro livro) {

        if (!livro.isDisponivel()) {
            throw new IllegalArgumentException("O livro não está disponível para empréstimo.");
        }

        if (usuario.isPossuiMultaPendente()) {
            throw new IllegalArgumentException("O usuário possui multa pendente.");
        }

        if (usuario.getQuantidadeLivrosEmprestados() >= LIMITE_LIVROS_EMPRESTADOS) {
            throw new IllegalArgumentException("O usuário já atingiu o limite de livros emprestados.");
        }

        livro.setDisponivel(false);
        usuario.adicionarEmprestimo();
    }

    public void devolverLivro(Usuario usuario, Livro livro, int diasAtraso) {

        if (livro.isDisponivel()) {
            throw new IllegalArgumentException("Não é possível devolver um livro que não está emprestado.");
        }

        livro.setDisponivel(true);
        usuario.removerEmprestimo();

        if (diasAtraso > 0) {
            double valorMulta = diasAtraso * MULTA_POR_DIA_ATRASO;
            usuario.aplicarMulta(valorMulta);
        }
    }
}