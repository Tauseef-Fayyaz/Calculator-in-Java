/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scalculator;
import javax.swing.JFrame;



/**
 *
 * @author Tauseef Fayyaz
 */
public class SCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Calculator calculator = new Calculator();
        calculator.setSize(280, 216);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setResizable(false);
        calculator.setVisible(true);
        calculator.setLocation(1050/2, 570/2);
        
    }
    
}
