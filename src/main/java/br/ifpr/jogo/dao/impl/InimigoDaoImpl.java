package br.ifpr.jogo.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.InimigoDao;
import br.ifpr.jogo.modelo.Inimigo;


public class InimigoDaoImpl implements InimigoDao {

    private Session sessao;

    public InimigoDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Inimigo> buscarTodos() {
        Query<Inimigo> query = this.sessao.createQuery("from Inimigo",
    Inimigo.class);
        List<Inimigo> inimigos = query.getResultList();
        return inimigos;
    }

    @Override
    public Inimigo buscarPorId(Integer id) {
        return this.sessao.find(Inimigo.class, id);
    }

    @Override
    public void inserir(Inimigo inimigo) {
        try {
            sessao.beginTransaction();
            sessao.persist(inimigo);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Inimigo inimigo) {
    try {
        sessao.beginTransaction();
        sessao.merge(inimigo);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    public void excluir(Inimigo inimigo) {
    try {
        sessao.beginTransaction();
        sessao.remove(inimigo);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
}
