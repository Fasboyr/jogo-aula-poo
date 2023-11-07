package br.ifpr.jogo.servico;

import br.ifpr.jogo.modelo.FaseUm;
import br.ifpr.jogo.dao.FaseUmDao;
import br.ifpr.jogo.dao.impl.FaseUmDaoImpl;

import java.util.List;

public class FaseUmServico {

    private static FaseUmDao dao = new FaseUmDaoImpl();

    public static List<FaseUm> buscarTodos() {
        return dao.buscarTodos();
    }

    public static FaseUm buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(FaseUm faseUm) {
        dao.inserir(faseUm);
    }

    public static void atualizar(FaseUm faseUm) {
        dao.atualizar(faseUm);
    }

    public static void excluir(FaseUm faseUm) {
        dao.excluir(faseUm);
    }
    
}
