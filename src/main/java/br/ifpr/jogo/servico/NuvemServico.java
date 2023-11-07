package br.ifpr.jogo.servico;

import br.ifpr.jogo.modelo.Nuvem;
import br.ifpr.jogo.dao.NuvemDao;
import br.ifpr.jogo.dao.impl.NuvemDaoImpl;

import java.util.List;


public class NuvemServico {
    private static NuvemDao dao = new NuvemDaoImpl();
    
    public static List<Nuvem> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Nuvem buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Nuvem nuvem) {
        dao.inserir(nuvem);
    }

    public static void atualizar(Nuvem nuvem) {
        dao.atualizar(nuvem);
    }

    public static void excluir(Nuvem nuvem) {
        dao.excluir(nuvem);
    }
}