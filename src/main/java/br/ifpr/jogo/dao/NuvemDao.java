package br.ifpr.jogo.dao;

import java.util.List;
import br.ifpr.jogo.modelo.Nuvem;

public interface NuvemDao {
    public List<Nuvem> buscarTodos();
    public Nuvem buscarPorId(Integer id);
    public void atualizar(Nuvem nuvem);
    public void excluir(Nuvem nuvem);
    public void inserir(Nuvem nuvem);
}

