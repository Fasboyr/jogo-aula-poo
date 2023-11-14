package br.ifpr.jogo.dao;

import java.util.List;
import br.ifpr.jogo.entidade.FaseEntidade;

public interface FaseEntidadeDao {
    public List<FaseEntidade> buscarTodos();
    public FaseEntidade buscarPorId(Integer id);
    public void atualizar(FaseEntidade faseEntidade);
    public void excluir(FaseEntidade faseEntidade);
    public void inserir(FaseEntidade faseEntidade);
    public boolean verificarDadosExistem();
    
}
