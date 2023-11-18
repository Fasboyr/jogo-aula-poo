package br.ifpr.jogo.modelo;

import javax.persistence.Entity;
import javax.swing.ImageIcon;

@Entity
public class SuperTiro extends ElementoGrafico {
    private static int VELOCIDADE = 30;

   

    public SuperTiro(){}

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


   

}