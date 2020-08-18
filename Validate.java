
/**This program creates a graphical user interface(GUI)login screen with java swing and
 * validates users input according to the already stored data in th program. It checks caps lock
 * the lenght of the inout password and response accordingly when the login button is clicked.
 * Animates to to reject incorrect password and display warning labels.
 * Source code written by Awunjia Serge Atabong in Buea-cameroon
 * on the 18/08/2020, contact me directly at awujiaa2018@gmail.com whatsApp: +237 651565843.
 * This is a free and open source software(FOSS), feel free to modify accordingly and use.
 */


package com.ASAtech;

//Library imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

//Declaring Validate class
public class Validate extends JFrame {

    //Declaring private fields
    private String secret = "40405";  //Password to be entered(greater than 5characters), modify to you taste
    private Font font = new Font("Serif", Font.PLAIN + Font.BOLD, 16);
    public JLabel name, warnings, caps;
    private JLabel status;
    private JPasswordField pass;
    private JCheckBox showPass;
    private JButton login;

    //Calling the constructor
    public Validate() throws AWTException {
        super("Authentication");
        createUI();
        AddingEvents();
        capsChecker();
    }

    //Declaring the createUI method
    public void createUI(){
        getContentPane().setBackground(Color.PINK);
        name = new JLabel("WELCOME BACK ASA");
        name.setFont(font);
        name.setBounds(170, 70, 300, 30);
        add(name);
        warnings = new JLabel("", JLabel.CENTER);
        warnings.setForeground(Color.red);
        warnings.setBounds(150, 130, 200, 30);
        add(warnings);
        pass = new JPasswordField();
        pass.setBounds(160, 180, 200, 30);
        add(pass);
        showPass = new JCheckBox("Show Password");
        showPass.setBounds(195, 220, 120, 30);
        add(showPass);
        login = new JButton("Login");
        login.setBounds(220, 280, 80, 30);
        add(login);
        caps = new JLabel();
        caps.setBounds(220, 400, 300, 30);
        add(caps);
        status = new JLabel();
        status.setBounds(240, 300, 200, 30);
        add(status);

    }

    //Creating event handler methods
    public void AddingEvents(){
        LoginButtonHandler handler = new LoginButtonHandler();
        showPass.addActionListener(handler);
        login.addActionListener(handler);
    }

    //Declaring the shakeAnimation method
    public void shakeAnimation(){
        final Point POINT = pass.getLocation();
        final int DELAY = 7;  //Delay for how long the password field wil shake, modify to you taste
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 30; i++){
                    try{
                        shakePasswordField(new Point(POINT.x + 7, POINT.y));  //7 is value of displacement to the right for the password field
                        Thread.sleep(DELAY);
                        shakePasswordField(POINT);
                        Thread.sleep(DELAY);
                        shakePasswordField(new Point(POINT.x - 7, POINT.y));  //-7 is value of displacement to the left for the password field
                        Thread.sleep(DELAY);
                        shakePasswordField(POINT);
                        Thread.sleep(DELAY);
                    }
                    catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    //Calling the shakePasswordField method
    public void shakePasswordField(Point p){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                pass.setLocation(p);
            }
        });
    }

    //Method to constantly check caps lock position(value)
    public void capsChecker(){
        Thread dread = new Thread(){
            public void run(){
                for(;;){
                    if(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)){
                        caps.setText("CapsLock ON");
                    }
                    else{
                        caps.setText("");
                    }
                    try{
                        sleep(100);
                    }
                    catch (InterruptedException ex){
                        Logger.getLogger(Validate.class.getName());
                    }
                }
            }
        }; dread.start();
    }

    //Method for action listener for the password field
    private class LoginButtonHandler implements ActionListener {
        char[] temp = pass.getPassword();
        String password = new String(temp);

        @Override
        public void actionPerformed(ActionEvent e) {

            String password = pass.getText();

            if(e.getSource() == showPass){
                if(showPass.isSelected()){
                    pass.setEchoChar((char) 0);
                }
                else{
                    pass.setEchoChar('*');
                }
            }

            if(e.getSource() == login){
                if(password.isEmpty()){
                    warnings.setText("This field is required!");
                    shakeAnimation();
                }
                else if(password.length() < 4){
                    warnings.setText("Password too short!");
                    shakeAnimation();
                }
                else if(password.equals(secret)){
                    openNewFrame();
                    killAuthenticate();
                }
                else{
                    warnings.setText("Incorrect Password");
                    shakeAnimation();
                    pass.setText("");

                }
            }
        }
    }

    //Method to open new frame when the password is correct
    public void openNewFrame(){
        MainFrame commandPanel = new MainFrame();
        commandPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        commandPanel.setSize(1000, 700);
        commandPanel.setLocationRelativeTo(null);
        commandPanel.setVisible(true);
    }

    //Method to kill the login when the new frame is opening
    public void killAuthenticate(){
        super.dispose();
    }
}
