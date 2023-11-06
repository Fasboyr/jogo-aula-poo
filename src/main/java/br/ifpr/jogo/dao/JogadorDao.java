package br.ifpr.jogo.dao;

import java.util.List;
import br.ifpr.jogo.modelo.Jogador;

public interface JogadorDao {
    public List<Jogador> buscarTodos();
    public Jogador buscarPorId(Integer id);
    public void atualizar(Jogador jogador);
    public void excluir(Jogador jogador);
    public void inserir(Jogador jogador);
}

