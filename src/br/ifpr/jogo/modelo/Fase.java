package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fase extends JPanel {
    private Image  fundo;
    private Personagem personagem;

    public Fase(){
        ImageIcon carregando = new ImageIcon("recursos\\espaço.jpg");
        this.fundo = carregando.getImage();

        this.personagem = new Personagem();
        this.personagem.carregar();

    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(this.fundo, null, null);
        graficos.drawImage(this.personagem.getImagem(), null, null);

    }
}
