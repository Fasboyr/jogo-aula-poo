package br.ifpr.jogo.dao.impl;

import java.math.BigInteger;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.FaseUmDao;
import br.ifpr.jogo.modelo.FaseUm;


public class FaseUmDaoImpl implements FaseUmDao {

    private Session sessao;

    public FaseUmDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<FaseUm> buscarTodos() {
        Query<FaseUm> query = this.sessao.createQuery("from FaseUm",
    FaseUm.class);
        List<FaseUm> faseUm = query.getResultList();
        return faseUm;
    }

    @Override
    public FaseUm buscarPorId(Integer id) {
        return this.sessao.find(FaseUm.class, id);
    }

    @Override
    public void inserir(FaseUm faseUm) {
        try {
            sessao.beginTransaction();
            sessao.persist(faseUm);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(FaseUm faseUm) {
    try {
        sessao.beginTransaction();
        sessao.merge(faseUm);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    public void excluir(FaseUm faseUm) {
    try {
        sessao.beginTransaction();
        sessao.remove(faseUm);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
 
    @Override
    public boolean verificarDadosExistem() {
        try {
            Query<?> query = this.sessao.createSQLQuery("SELECT 1 FROM faseum LIMIT 1");
            return query.uniqueResult() != null;
        } catch (Exception e) {
            System.err.println("Erro ao verificar a existência de dados: ");
            e.printStackTrace();
            return false;
        }
    }

}