package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoEmX;
    private int posicaoEmY;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private int velocidadeDeDeslocamento;
    private ArrayList<Tiro> tiros;

    private static final int POSIOCAO_INICIAL_EM_X = 100;
    private static final int POSIOCAO_INICIAL_EM_y = 100;

    public Personagem(){
        this.posicaoEmX = POSIOCAO_INICIAL_EM_X;
        this.posicaoEmY = POSIOCAO_INICIAL_EM_y;
        this.tiros = new ArrayList<Tiro>();
    }


    public Personagem(int velocidadeDeDeslocamento){
             this.posicaoEmX = POSIOCAO_INICIAL_EM_X;
             this.posicaoEmY = POSIOCAO_INICIAL_EM_y;
             this.tiros = new ArrayList<Tiro>();
             this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;

    }

    public void carregar(){
        ImageIcon carregando = new ImageIcon("recursos\\naveHorizontal.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
        
        }
    public void atualizar(){
        this.posicaoEmX = this.posicaoEmX + this.deslocamentoEmX;
        this.posicaoEmY = this.posicaoEmY + this.deslocamentoEmY;
    }


    public void atirar() {
        int frenteDaNave = this.posicaoEmX + this.larguraImagem;
        int meioDaNave = this.posicaoEmY + (this.alturaImagem / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
    }

     public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -this.velocidadeDeDeslocamento;
            break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = this.velocidadeDeDeslocamento;
            break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = this.velocidadeDeDeslocamento;
            break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -this.velocidadeDeDeslocamento;
            break;

            case KeyEvent.VK_W:
                this.deslocamentoEmY = -this.velocidadeDeDeslocamento;
            break;
            case KeyEvent.VK_S:
                this.deslocamentoEmY = this.velocidadeDeDeslocamento;
            break;
            case KeyEvent.VK_D:
                this.deslocamentoEmX = this.velocidadeDeDeslocamento;
            break;
            case KeyEvent.VK_A:
                this.deslocamentoEmX = -this.velocidadeDeDeslocamento;
            break;
        
            default:
                break;
        }   

    }

     public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = 0;
            break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = 0;
            break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = 0;
            break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = 0;
            break;

            case KeyEvent.VK_W:
                this.deslocamentoEmY = 0;
            break;
            case KeyEvent.VK_S:
                this.deslocamentoEmY = 0;
            break;
            case KeyEvent.VK_D:
                this.deslocamentoEmX = 0;
            break;
            case KeyEvent.VK_A:
                this.deslocamentoEmX = 0;
            break;
            default:
                break;
        }
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

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
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


    public int getVelocidadeDeDeslocamento() {
        return this.velocidadeDeDeslocamento;
    }

    public void setVelocidadeDeDeslocamento(int velocidadeDeDeslocamento) {
        this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

}
