import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GOLPanel extends JPanel {
    private GOLMatrix matrix;
    private JButton cmdSlower, cmdGo, cmdNext, cmdClear, cmdFaster;
    private JLabel lblGenerations;
    private Timer timer;
    private int interval;
    private ActionListener l;
    private ActionListener c;
    private JButton[][] E;
    private int length;
    private startTimer g;


    public GOLPanel(int worldSize) {


        matrix = new GOLMatrix(worldSize);
        l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < length; j++) {
                        if (e.getSource() == E[i][j]) {
                            if (E[i][j].getBackground() == Color.white) {
                                E[i][j].setBackground(Color.blue);
                            } else {
                                E[i][j].setBackground(Color.white);
                            }
                            matrix.flipCell(i, j);
                            break;
                        }
                    }
                }
            }
        };
        interval = 200;
        length = matrix.getWorld().length;
        cmdClear = new
               JButton("Clear");

        cmdFaster = new

                JButton("Faster");

        cmdNext = new

                JButton("Next");

        cmdGo = new

                JButton("Go");

        cmdSlower = new

                JButton("Slower");

        cmdFaster.setEnabled(false);
        cmdSlower.setEnabled(false);
        cmdNext.setEnabled(true);
        cmdClear.setEnabled(true);
        cmdGo.setEnabled(true);
        lblGenerations = new JLabel("Number of generations: " + matrix.getGenerations());
        setLayout(new BorderLayout());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(length, length));
        E = new JButton[length][length];

        for (
                int i = 0;
                i < length; i++) {
            for (int j = 0; j < length; j++) {
                E[i][j] = new JButton();
                E[i][j].setBackground(Color.white);
                E[i][j].addActionListener(l);
                panel2.add(E[i][j]);
            }
        }

        JPanel p = new JPanel();

        cmdSlower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (interval+20 <= 1000) {
                    interval += 20;
                }
                timer.setDelay(interval);
            }
        });
        cmdFaster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (interval-20 >= 100) {
                    interval -=20;
                }
                timer.setDelay(interval);
            }
        });
        cmdClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix.clearWorld();
                setMatrix();
                lblGenerations.setText("Number of generations: " + matrix.getGenerations());
                repaint();
            }
        });
        cmdNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix.nextGeneration();
                lblGenerations.setText("Number of generations: " + matrix.getGenerations());
                setMatrix();
                repaint();
            }
        });
        g = new startTimer();
        timer = new Timer(interval, g);
        cmdGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmdGo.getText().equals("Go")) {
                    timer.start();
                    cmdGo.setText("Stop");
                    cmdNext.setEnabled(false);
                    cmdClear.setEnabled(false);
                    cmdFaster.setEnabled(true);
                    cmdSlower.setEnabled(true);

                }
                else{
                    timer.stop();
                    cmdGo.setText("Go");
                    cmdFaster.setEnabled(false);
                    cmdSlower.setEnabled(false);
                    cmdNext.setEnabled(true);
                    cmdClear.setEnabled(true);
                }
            }
        });
        p.add(cmdClear);
        p.add(cmdGo);
        p.add(cmdFaster);
        p.add(cmdSlower);
        p.add(cmdNext);
        p.add(lblGenerations);
        add(p, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
    }
    
    private void setMatrix() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix.getWorld()[i][j] == true) {
                    E[i][j].setBackground(Color.BLUE);
                } else E[i][j].setBackground(Color.white);
            }
        }
    }

    private class startTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                        matrix.nextGeneration();
                        lblGenerations.setText("Number of generations: " + matrix.getGenerations());
                        setMatrix();
                        repaint();
            }
        }
    }
