package br.ifpr.jogo.servico;

import br.ifpr.jogo.entidade.FaseEntidade;
import br.ifpr.jogo.dao.FaseEntidadeDao;
import br.ifpr.jogo.dao.impl.FaseEntidadeDaoImpl;


import java.util.List;

public class FaseUmServico {

    private static FaseEntidadeDao dao = new FaseEntidadeDaoImpl();

    public static List<FaseEntidade> buscarTodos() {
        return dao.buscarTodos();
    }

    public static FaseEntidade buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(FaseEntidade faseEntidade) {
        dao.inserir(faseEntidade);
    }

    public static void atualizar(FaseEntidade faseEntidade) {
        dao.atualizar(faseEntidade);
    }

    public static void excluir(FaseEntidade faseEntidade) {
        dao.excluir(faseEntidade);
    }
 
    public static boolean verificarDadosExistem(){
        return dao.verificarDadosExistem();
    }

    
}
