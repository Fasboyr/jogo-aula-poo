package br.ifpr.jogo.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.SuperTiroDao;
import br.ifpr.jogo.modelo.SuperTiro;

public class SuperTiroDaoImpl implements SuperTiroDao {

    private Session sessao;

    public SuperTiroDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<SuperTiro> buscarTodos() {
        Query<SuperTiro> query = this.sessao.createQuery("from SuperTiro",
    SuperTiro.class);
        List<SuperTiro> superTiros = query.getResultList();
        return superTiros;
    }

    @Override
    public SuperTiro buscarPorId(Integer id) {
        return this.sessao.find(SuperTiro.class, id);
    }

    @Override
    public void inserir(SuperTiro superTiro) {
        try {
            sessao.beginTransaction();
            sessao.persist(superTiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(SuperTiro superTiro) {
    try {
        sessao.beginTransaction();
        sessao.merge(superTiro);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    public void excluir(SuperTiro superTiro) {
    try {
        sessao.beginTransaction();
        sessao.remove(superTiro);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

}