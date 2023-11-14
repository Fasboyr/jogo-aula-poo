package br.ifpr.jogo.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.ImageIcon;

@Entity
public class Tiro extends ElementoGrafico {
    
    private static int VELOCIDADE = 20;


   

    public Tiro(){}

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY - 60 ;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/tiro.png"));
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX + VELOCIDADE;
    }


 

    
}
