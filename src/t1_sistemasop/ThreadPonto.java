/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1_sistemasop;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JButton;

/**
 *
 * @author chavesfop
 */
public class ThreadPonto implements Runnable {
    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;
    private JButton botaoAntigo;
    private Tabuleiro tabuleiro;
    
    public JButton getBotaoAntigo(){
        return this.botaoAntigo;
    }
    
    public ThreadPonto(int sleepMs, Tabuleiro tabuleiro){
        this.interval = sleepMs;
        this.tabuleiro = tabuleiro;
    }
    
    public void start(){
        worker = new Thread(this);
        worker.start();
    }
    
    public void stop(){
        running.set(false);
    }
    
    @Override
    public void run() {
        int x, y;
        this.running.set(true);
        while(running.get()){
            if (this.botaoAntigo instanceof JButton){
                this.botaoAntigo.setText("");
            }
            Random ran = new Random();
            x = ran.nextInt(10);
            y = ran.nextInt(10);

            while (tabuleiro.getBotao(x, y).getText().equals("#")){
                x = ran.nextInt(10);
                y = ran.nextInt(10);
            }
            tabuleiro.getBotao(x, y).setText("#");
            this.botaoAntigo = tabuleiro.getBotao(x, y);
            try{
                Thread.sleep(this.interval);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            
        }
    }
    
}
