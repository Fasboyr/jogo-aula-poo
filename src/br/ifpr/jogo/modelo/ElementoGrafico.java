package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class ElementoGrafico {
    protected int posicaoEmX;
    protected int posicaoEmY;
    protected Image imagem;
    protected int larguraImagem;
    protected int alturaImagem;
    private boolean ehVisivel = true;

    public abstract void carregar();

    public abstract void atualizar();

    public Rectangle getRectangle(){
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    public int getPosicaoEmX() {
        return this.posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return this.posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public Image getImagem() {
        return this.imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLarguraImagem() {
        return this.larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return this.alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }


    public boolean isEhVisivel() {
        return this.ehVisivel;
    }

    public boolean getEhVisivel() {
        return this.ehVisivel;
    }

    public void setEhVisivel(boolean ehVisivel) {
        this.ehVisivel = ehVisivel;
    }

}
