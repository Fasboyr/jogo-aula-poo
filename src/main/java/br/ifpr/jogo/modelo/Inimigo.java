package br.ifpr.jogo.modelo;


import javax.persistence.Entity;
import javax.swing.ImageIcon;

@Entity
public class Inimigo extends ElementoGrafico {
    private static int VELOCIDADE = 2;

    public Inimigo(int xAleatorio, int yAleatorio) {
        this.posicaoEmX = xAleatorio;
        this.posicaoEmY = yAleatorio;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/meteoro.png"));
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }


    public void atualizar() {
    this.posicaoEmX = this.posicaoEmX - VELOCIDADE;
    }

    
}

