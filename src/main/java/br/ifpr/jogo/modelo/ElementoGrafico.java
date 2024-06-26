package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementoGrafico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idElemento_Grafico;

    @Column(name = "posicao_em_x")
    protected int posicaoEmX;

    @Column(name = "posicao_em_y")
    protected int posicaoEmY;

    @Column(name = "Largura_Imagem")
    protected int larguraImagem;

    @Column(name = "Altura_Imagem")
    protected int alturaImagem;

    @Transient
    protected Image imagem;

    @Column(name = "eh_visivel")
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

    public Integer getIdElemento_Grafico() {
        return idElemento_Grafico;
    }

    public void setIdElemento_Grafico(Integer idElemento_Grafico) {
        this.idElemento_Grafico = idElemento_Grafico;
    }


    

}
