package gui;

import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Login_SignUp {
    public static void main(String[] args) {
        Login loginForm = new Login();
        loginForm.setVisible(true);
        loginForm.setLocationRelativeTo(null);
        loginForm.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }       
}
