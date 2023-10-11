package br.ifpr.jogo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.ImageIcon;

@Entity
public class SuperTiro extends ElementoGrafico {
    private static int VELOCIDADE = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSuper_Tiro;

    @ManyToOne
    @JoinColumn(name = "fk_personagem")
    private Personagem personagem;

    public SuperTiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY - 45 ;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/supertiro.png"));
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
    this.posicaoEmX = this.posicaoEmX + VELOCIDADE;
    }


    public Integer getIdSuper_Tiro() {
        return this.idSuper_Tiro;
    }

    public void setIdSuper_Tiro(Integer idSuper_Tiro) {
        this.idSuper_Tiro = idSuper_Tiro;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

}