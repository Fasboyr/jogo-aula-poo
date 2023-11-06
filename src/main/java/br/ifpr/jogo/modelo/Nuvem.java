package br.ifpr.jogo.modelo;



import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.ImageIcon;
import br.ifpr.jogo.principal.principal;

@Entity
public class Nuvem extends ElementoGrafico {
    private static int VELOCIDADE = 1;

    @ManyToOne
    @JoinColumn(name = "fk_fase")
    private Fase fase;


    public Nuvem(int xAleatorio, int yAleatorio) {
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/DustCloud 1.png"));
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
    if (this.getPosicaoEmX() < 0) {
        int y = (int) (Math.random() * principal.ALTURA_JANELA);
        super.setPosicaoEmX(principal.LARGURA_JANELA);
        super.setPosicaoEmY(y);
    } else {
        super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
        }
    }

   

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

}