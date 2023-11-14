package br.ifpr.jogo.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ifpr.jogo.modelo.Inimigo;
import br.ifpr.jogo.modelo.Nuvem;
import br.ifpr.jogo.modelo.Personagem;

@Entity
@Table(name = "tb_fase")
public class FaseEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idFase;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personagem_id")
    protected Personagem personagem;

    @Column(name = "em_jogo")
    protected boolean emJogo = true;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fase")
    protected List<Inimigo> inimigos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fase")
    protected List<Nuvem> nuvens;

    public FaseEntidade(){
        this.inimigos = new ArrayList<Inimigo>();
        this.nuvens = new ArrayList<Nuvem>();
        
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public boolean isEmJogo() {
        return emJogo;
    }

    public void setEmJogo(boolean emJogo) {
        this.emJogo = emJogo;
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }

    public List<Nuvem> getNuvens() {
        return nuvens;
    }

    public void setNuvens(List<Nuvem> nuvens) {
        this.nuvens = nuvens;
    }

}
