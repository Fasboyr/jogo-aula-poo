package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.security.Principal;

import javax.swing.ImageIcon;

public class Estrela extends ElementoGrafico {
    private static int VELOCIDADE = 1;

    public Estrela(int xAleatorio, int yAleatorio) {
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\dust.png");
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
    if (this.getPosicaoEmX() < 0) {
        int y = (int) (Math.random() * 900);
        super.setPosicaoEmX(1600);
        super.setPosicaoEmY(y);
    } else {
        super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
        }
    }
}