package br.ifpr.jogo.servico;

import br.ifpr.jogo.modelo.Inimigo;
import br.ifpr.jogo.dao.InimigoDao;
import br.ifpr.jogo.dao.impl.InimigoDaoImpl;

import java.util.List;

public class InimigoServico {

    private static InimigoDao dao = new InimigoDaoImpl();

    public static List<Inimigo> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Inimigo buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Inimigo inimigo) {
        dao.inserir(inimigo);
    }

    public static void atualizar(Inimigo inimigo) {
        dao.atualizar(inimigo);
    }

    public static void excluir(Inimigo inimigo) {
        dao.excluir(inimigo);
    }
    
}
