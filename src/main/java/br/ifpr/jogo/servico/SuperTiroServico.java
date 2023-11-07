package br.ifpr.jogo.servico;

import br.ifpr.jogo.modelo.SuperTiro;
import br.ifpr.jogo.dao.SuperTiroDao;
import br.ifpr.jogo.dao.impl.SuperTiroDaoImpl;

import java.util.List;


public class SuperTiroServico {
    private static SuperTiroDao dao = new SuperTiroDaoImpl();
    
    public static List<SuperTiro> buscarTodos() {
        return dao.buscarTodos();
    }

    public static SuperTiro buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(SuperTiro superTiro) {
        dao.inserir(superTiro);
    }

    public static void atualizar(SuperTiro superTiro) {
        dao.atualizar(superTiro);
    }

    public static void excluir(SuperTiro superTiro) {
        dao.excluir(superTiro);
    }
}