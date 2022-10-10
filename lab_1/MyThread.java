import javax.swing.*;

public class MyThread extends Thread {

    private int step;
    private JSlider slider;
    private boolean isActive;


    MyThread(JSlider slider, int step) {
        this.slider = slider;
        this.step = step;

    }

    public void setNotActive(){
        isActive = false;
    }

    @Override
    public void run() {
        isActive = true;
        while (isActive) {
            synchronized (slider) {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                    slider.setValue(slider.getValue() + step);
                    if (slider.getValue() == 90 || slider.getValue() == 10) return;

            }
        }
    }

}

