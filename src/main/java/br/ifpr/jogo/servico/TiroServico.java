package br.ifpr.jogo.servico;

import br.ifpr.jogo.modelo.Tiro;
import br.ifpr.jogo.dao.TiroDao;
import br.ifpr.jogo.dao.impl.TiroDaoImpl;

import java.util.List;


public class TiroServico {
    private static TiroDao dao = new TiroDaoImpl();
    
    public static List<Tiro> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Tiro buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Tiro tiro) {
        dao.inserir(tiro);
    }

    public static void atualizar(Tiro tiro) {
        dao.atualizar(tiro);
    }

    public static void excluir(Tiro tiro) {
        dao.excluir(tiro);
    }
}