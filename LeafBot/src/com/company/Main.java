package com.company;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;

public class Main {


    public static int loops = 1000000;
    static JFrame frame = new JFrame();
    static JButton button = new JButton("Start Bot");
    static JLabel label = new JLabel("Loops " + loops);
    static JPanel panel = new JPanel();



    public static void GUIInit(){

    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                BotTimeV3();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (AWTException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    });

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Leaf Bot");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) throws IOException, AWTException, InterruptedException{
    //BotTimeV3();
    GUIInit();
    }
    public static void BotTimeV3() throws IOException, AWTException, InterruptedException {
        Robot robot = new Robot();


        int xCurrentL = 20;
        int xCurrentR = 925;
        int xStart = 20;
        int xEnd = 925;
        int xIncrement = 75;


        int yIncrement = 40;
        int yEnd = 1350;
        int yCurrent = 850;

        boolean humanInterference = false;

        int robotDelayTime = 17;

        robot.mouseMove(452,1225);
        robot.delay(150);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        while (yCurrent < yEnd && !humanInterference) {
            yCurrent = yCurrent + yIncrement;
            while (xCurrentL < xEnd){
                robot.mouseMove(xCurrentL, yCurrent);
                xCurrentL = xCurrentL +xIncrement;
                robot.delay(robotDelayTime);
                if(MouseInfo.getPointerInfo().getLocation().x % 5 > 0 || MouseInfo.getPointerInfo().getLocation().y % 5 > 0){
                    humanInterference = true;
                    System.out.println("Human interference detected, halted script.");
                }
            }
            while (xCurrentR > xStart){
                robot.mouseMove(xCurrentR, yCurrent);
                xCurrentR = xCurrentR -xIncrement;
                robot.delay(robotDelayTime);
                if(MouseInfo.getPointerInfo().getLocation().x % 5 > 0 || MouseInfo.getPointerInfo().getLocation().y % 5 > 0){
                    humanInterference = true;
                    System.out.println("Human interference detected, halted script.");
                }
            }
            xCurrentL = 20;
            xCurrentR = 925;
        }
        while(humanInterference){
            float mousePosx = MouseInfo.getPointerInfo().getLocation().x;
            float mousePosy = MouseInfo.getPointerInfo().getLocation().y;
            Thread.sleep(20000);
            float mousePosxNew = MouseInfo.getPointerInfo().getLocation().x;
            float mousePosyNew = MouseInfo.getPointerInfo().getLocation().y;
            if(mousePosxNew - mousePosx == 0 && mousePosyNew - mousePosy == 0){
                humanInterference = false;
                System.out.println("Human back to sleep now, resuming remaining " +loops +" loops." );
                BotTimeV3();
            }
        }
        loops--;
        System.out.println(loops + " loops remaining.");
        if(loops >0){
            BotTimeV3();
        }
    }
}
