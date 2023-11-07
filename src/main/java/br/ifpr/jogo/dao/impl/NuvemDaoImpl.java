package br.ifpr.jogo.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.NuvemDao;
import br.ifpr.jogo.modelo.Nuvem;

public class NuvemDaoImpl implements NuvemDao {

    private Session sessao;

    public NuvemDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Nuvem> buscarTodos() {
        Query<Nuvem> query = this.sessao.createQuery("from Nuvem",
    Nuvem.class);
        List<Nuvem> nuvens = query.getResultList();
        return nuvens;
    }

    @Override
    public Nuvem buscarPorId(Integer id) {
        return this.sessao.find(Nuvem.class, id);
    }

    @Override
    public void inserir(Nuvem nuvem) {
        try {
            sessao.beginTransaction();
            sessao.persist(nuvem);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Nuvem nuvem) {
    try {
        sessao.beginTransaction();
        sessao.merge(nuvem);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    public void excluir(Nuvem nuvem) {
    try {
        sessao.beginTransaction();
        sessao.remove(nuvem);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

}