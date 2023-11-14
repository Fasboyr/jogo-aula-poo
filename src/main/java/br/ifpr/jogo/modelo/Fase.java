package br.ifpr.jogo.modelo;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.swing.JPanel;
import javax.swing.Timer;

//import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Fase extends JPanel implements KeyListener, ActionListener{
    public static final int DELAY = 5;
    public static final int VELOCIDADE_DE_DESLOCAMENTO = 7;
    public static final int QTDE_DE_INIMIGOS = 50;
    public static final int QTDE_DE_NUVENS = 30;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idFase;

    @Transient
    protected Image fundo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personagem_id")
    protected Personagem personagem;

    @Transient
    protected Timer timer;

    

    @Column(name = "em_jogo")
    protected boolean emJogo = true;

    @OneToMany(mappedBy = "fase", cascade = CascadeType.ALL)
    protected List<Nuvem> nuvens;



    public Fase() {
        setFocusable(true); 
        setDoubleBuffered(true); 
        addKeyListener(this); 
    }

    public void desenhaPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + personagem.getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }

    public void desenhaVida(Graphics2D graficos) {
        String textoVida = "VIDA: " + personagem.getVida();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoVida, 20, 50);
    }

    public abstract void inicializaInimigos();

    public abstract void verificarColisoes();

    public abstract void inicializaElementosGraficosAdicionais();

   
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void actionPerformed(ActionEvent e);


    public Integer getIdFase() {
        return this.idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Image getFundo() {
        return this.fundo;
    }

    public void setFundo(Image fundo) {
        this.fundo = fundo;
    }

    public Personagem getPersonagem() {
        return this.personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isEmJogo() {
        return this.emJogo;
    }

    public boolean getEmJogo() {
        return this.emJogo;
    }

    public void setEmJogo(boolean emJogo) {
        this.emJogo = emJogo;
    }

    public List<Nuvem> getNuvens() {
        return this.nuvens;
    }

    public void setNuvens(List<Nuvem> nuvens) {
        this.nuvens = nuvens;
    }
    

}

