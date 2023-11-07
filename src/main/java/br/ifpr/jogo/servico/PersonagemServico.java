package br.ifpr.jogo.servico;

import br.ifpr.jogo.modelo.Personagem;
import br.ifpr.jogo.dao.PersonagemDao;
import br.ifpr.jogo.dao.impl.PersonagemDaoImpl;

import java.util.List;


public class PersonagemServico {
    private static PersonagemDao dao = new PersonagemDaoImpl();
    
    public static List<Personagem> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Personagem buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Personagem personagem) {
        dao.inserir(personagem);
    }

    public static void atualizar(Personagem personagem) {
        dao.atualizar(personagem);
    }

    public static void excluir(Personagem personagem) {
        dao.excluir(personagem);
    }
}