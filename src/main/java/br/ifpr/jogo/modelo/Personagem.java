package br.ifpr.jogo.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;


import br.ifpr.jogo.principal.principal;

@Entity
//@Table(nome = "tb_personagem")
public class Personagem extends ElementoGrafico {
    private static final int POSIOCAO_INICIAL_EM_X = 100;
    private static final int POSIOCAO_INICIAL_EM_y = 100;


    @Column(name = "descolamento_em_x")
    private int deslocamentoEmX;

    @Column(name = "descolamento_em_y")
    private int deslocamentoEmY;

    @Column(name = "pontuacao")
    private int pontuacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_personagem")
    private List<Tiro> tiros;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_personagem")
    private List<SuperTiro> superTiros;

    @Column(name = "vida")
    private int vida = 3 ;

    @Column(name = "velocidade_de_deslocamento")
    private int velocidadeDeDeslocamento;


    
    public Personagem(){
        this.posicaoEmX = POSIOCAO_INICIAL_EM_X;
        this.posicaoEmY = POSIOCAO_INICIAL_EM_y;
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
    }


    public Personagem(int velocidadeDeDeslocamento){
             this.posicaoEmX = POSIOCAO_INICIAL_EM_X;
             this.posicaoEmY = POSIOCAO_INICIAL_EM_y;
             this.tiros = new ArrayList<Tiro>();
             this.superTiros = new ArrayList<SuperTiro>();
             this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;

    }


    public void verificarBorda() {
        if (super.getPosicaoEmX() < 0) {
            super.setPosicaoEmX(0);
        } else if (super.getPosicaoEmX() + super.getLarguraImagem() > principal.LARGURA_JANELA) {
            int limiteEmX = principal.LARGURA_JANELA - super.getLarguraImagem(); 
            super.setPosicaoEmX(limiteEmX);
        }

        if (super.getPosicaoEmY() < 0) {
            super.setPosicaoEmY(0);
        } else if (super.getPosicaoEmY() + super.getAlturaImagem() > (principal.ALTURA_JANELA - 40)) {
            int limiteEmY = (principal.ALTURA_JANELA - 40) - super.getAlturaImagem();
            super.setPosicaoEmY(limiteEmY);
        }
    }


    public void carregar(){
        ImageIcon carregando = new ImageIcon(getClass().getResource("/nave.png"));
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

    public void superAtirar() {
        int frenteDaNave = this.posicaoEmX + this.larguraImagem;
        int meioDaNave = this.posicaoEmY + (this.alturaImagem / 2);
        SuperTiro superTiro = new SuperTiro(frenteDaNave, meioDaNave);
        this.superTiros.add(superTiro);
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
    public int getVelocidadeDeDeslocamento() {
        return this.velocidadeDeDeslocamento;
    }

    public void setVelocidadeDeDeslocamento(int velocidadeDeDeslocamento) {
        this.velocidadeDeDeslocamento = velocidadeDeDeslocamento;
    }

    public List<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }

    public List<SuperTiro> getSuperTiros() {
        return this.superTiros;
    }

    public void setSuperTiros(List<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }
   
    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }


    public static int getPosiocaoInicialEmX() {
        return POSIOCAO_INICIAL_EM_X;
    }


    public static int getPosiocaoInicialEmY() {
        return POSIOCAO_INICIAL_EM_y;
    }
 
    
}
