import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static MyThread myThread1;
    private static MyThread myThread2;
    public static void main(String [] args){

        JFrame frame;
        JPanel panel;
        JLabel label;
        JSlider slider;
        JSpinner spinner1, spinner2;
        SpinnerModel priority1, priority2;
        JButton button1, button2, button3, button4, button5;

        frame = new JFrame("Lab1");
        panel = new JPanel();
        label = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        button1.setText("START");
        button1.setBounds(150, 300, 80, 80);

        button2.setText("START1");
        button2.setBounds(300, 200, 100, 50);

        button3.setText("START2");
        button3.setBounds(400, 200, 100, 50);

        button4.setText("STOP1");
        button4.setBounds(300, 300, 100, 50);

        button5.setText("STOP2");
        button5.setBounds(400, 300, 100, 50);


        priority1 = new SpinnerNumberModel(1, 0, 10, 1);
        spinner1 = new JSpinner(priority1);
        spinner1.setBounds(100, 200, 50, 30);


        priority2 = new SpinnerNumberModel(1, 0, 10, 1);
        spinner2 = new JSpinner(priority2);
        spinner2.setBounds(200, 200, 50, 30);

        slider = new JSlider(0, 100, 50);
        slider.setPreferredSize(new Dimension(400, 200));

        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
        slider.setPaintTrack(true);

        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myThread1 = new MyThread(slider,1);
                myThread2 = new MyThread(slider,-1);
               myThread1.start();
               myThread2.start();
                spinner1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        System.out.println("Value -" + ((JSpinner)e.getSource()).getValue());
                        int pr = (int) ((JSpinner)e.getSource()).getValue();
                        myThread1.setPriority(pr);
                    }
                });
                spinner2.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        System.out.println("Value2 -" + ((JSpinner)e.getSource()).getValue());
                        int pr = (int) ((JSpinner)e.getSource()).getValue();
                        myThread2.setPriority(pr);
                    }
                });
            }
        });

        MySemaphore mySemaphore = new MySemaphore();

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button5.setEnabled(false);
                button3.setEnabled(true);
                button4.setEnabled(true);
                if(!mySemaphore.isOccupied()){
                    myThread1 = new MyThread(slider,1);
                    myThread1.start();
                    mySemaphore.setOccupied();
                }
                else{
                    JOptionPane.showMessageDialog(null, "semaphore is occupied");
                }

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button4.setEnabled(false);
                button2.setEnabled(true);
                button5.setEnabled(true);
                if(!mySemaphore.isOccupied()){
                    myThread2 = new MyThread(slider,-1);
                    myThread2.start();
                    mySemaphore.setOccupied();
                }
                else{
                    JOptionPane.showMessageDialog(null, "semaphore is occupied");
                }


            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myThread1.setNotActive();
                mySemaphore.release();

            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myThread2.setNotActive();
                mySemaphore.release();


            }
        });


        panel.add(slider);
        panel.add(label);
        frame.add(spinner1);
        frame.add(spinner2);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(panel);


        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
