package br.ifpr.jogo.modelo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.ImageIcon;

@Entity
public class SuperTiro extends ElementoGrafico {
    private static int VELOCIDADE = 30;

   

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


    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

}