package br.ifpr.jogo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.FaseEntidadeDao;
import br.ifpr.jogo.entidade.FaseEntidade;

public class FaseEntidadeDaoImpl implements FaseEntidadeDao {

    private Session sessao;

    public FaseEntidadeDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<FaseEntidade> buscarTodos() {
        Query<FaseEntidade> query = this.sessao.createQuery("from FaseEntidade",
                FaseEntidade.class);
        List<FaseEntidade> faseEntidade = query.getResultList();
        return faseEntidade;
    }

    @Override
    public FaseEntidade buscarPorId(Integer id) {
        return this.sessao.find(FaseEntidade.class, id);
    }

    @Override
    public void inserir(FaseEntidade faseEntidade) {
        try {
            sessao.beginTransaction();
            sessao.persist(faseEntidade);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(FaseEntidade faseEntidade) {
        try {
            sessao.beginTransaction();
            sessao.merge(faseEntidade);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(FaseEntidade faseEntidade) {
        try {
            sessao.beginTransaction();
            sessao.remove(faseEntidade);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verificarDadosExistem() {
        try {
            Query<?> query = this.sessao.createSQLQuery("SELECT 1 FROM tb_fase LIMIT 1");
            return query.uniqueResult() != null;
        } catch (Exception e) {
            System.err.println("Erro ao verificar a existência de dados: ");
            e.printStackTrace();
            return false;
        }
    }

}