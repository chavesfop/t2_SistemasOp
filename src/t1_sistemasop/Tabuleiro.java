/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1_sistemasop;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author chavesfop
 */
public class Tabuleiro extends JPanel {
    private JButton botoes[][];
    private ThreadPonto threads[];
    private int startedThreads;
    private long start, finish;
    private int dificuldade;
    
    public JButton getBotao(int x, int y){
        return this.botoes[x][y];
    }
    
    private int getMsDificuldade(){
        switch(this.dificuldade){
            case 0:
                return 1500;
            case 1:
                return 1000;
            case 2:
                return 800;
            case 3:
                return 666;
        }
        return 300;
    }
    
    Tabuleiro(int dificuldade){
        this.dificuldade = dificuldade;
        setLayout(new GridLayout(10, 10));
        
        botoes = new JButton[10][10];
        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                botoes[x][y] = new JButton();
                botoes[x][y].setName("btn"+x+" "+y);
                botoes[x][y].setText("");
                botoes[x][y].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Object o = e.getSource();
                        JButton b = null;
                        String buttonText = "";

                        if(o instanceof JButton)
                          b = (JButton)o;

                        if(b != null)
                          buttonText = b.getText();
                        
                        if (buttonText == "#"){
                            for (int i = 0; i < 5; i++){
                                if (threads[i].getBotaoAntigo().equals(b)){
                                    threads[i].getBotaoAntigo().setText("");
                                    threads[i].stop();
                                    startedThreads--;
                                    if (startedThreads == 0){
                                        finish = System.currentTimeMillis();
                                        long total = finish - start;
                                        JOptionPane.showMessageDialog(null, String.format("%02d segundos  e %02d milisegundos", total/1000, total%1000));
                                    }
                                }
                            }
                        }
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //nada aqui
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //nada aqui
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //nada aqui
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //nada aqui
                    }
                });
                add(botoes[x][y]);
            }
        }
        
        threads = new ThreadPonto[5];
        for (int i = 0; i<5; i++){
            threads[i]= new ThreadPonto(this.getMsDificuldade(), this);
            threads[i].start();
        }
        this.startedThreads = 5;
        this.start = System.currentTimeMillis();
    }
}
