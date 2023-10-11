package br.ifpr.jogo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.ImageIcon;

@Entity
public class Tiro extends ElementoGrafico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTiro;


    private static int VELOCIDADE = 20;

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


    public Integer getIdTiro() {
        return this.idTiro;
    }

    public void setIdTiro(Integer idTiro) {
        this.idTiro = idTiro;
    }
    

}
