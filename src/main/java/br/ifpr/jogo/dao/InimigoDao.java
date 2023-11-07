package br.ifpr.jogo.dao;

import java.util.List;
import br.ifpr.jogo.modelo.Inimigo;

public interface InimigoDao {
    public List<Inimigo> buscarTodos();
    public Inimigo buscarPorId(Integer id);
    public void atualizar(Inimigo inimigo);
    public void excluir(Inimigo inimigo);
    public void inserir(Inimigo inimigo);
    
}

