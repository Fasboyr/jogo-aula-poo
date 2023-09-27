package br.ifpr.jogo.modelo;



import javax.swing.ImageIcon;

import br.ifpr.jogo.principal.principal;

public class Nuvem extends ElementoGrafico {
    private static int VELOCIDADE = 1;

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
}