/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scalculator;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;


        
        
        
        
/**
 *
 * @author Tauseef Fayyaz
 */
public class Calculator extends JFrame  {

private JTextField screen;

private Font font;
private JButton[] buttons;
private JPanel buttonPanel;
private String expression;
private String result;
private Keypad keypad;
private String operator;


private static final String[] buttonNames ={"CE","x2","x3","Back","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};

public Calculator()
{
    super("SCalculator");
    
    //Setting Layout and Icon
    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("calculator.png")));
    setLayout(new FlowLayout());
    getContentPane().setBackground(Color.white);
    
    //Setting Screen Text Varianles
    operator="0";
    expression="0";
    result="0";
    
   
    //Setting and adding a Screen
    font = new Font("Ariel",Font.BOLD,14);
    screen = new JTextField();
    screen.setColumns(22);
    screen.setFont(font);
    screen.setDisabledTextColor(Color.BLACK);
    screen.setEditable(false);
    screen.setBackground(Color.white);
    screen.setHorizontalAlignment(JTextField.RIGHT);
    screen.setText(result);
    add(screen);
    
    //Setting up a JPanel for holding buttons in Grid Layout
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(5,4,5,5));
    buttonPanel.setBackground(Color.white);
   
    //Setting up buttons and adding their listeners and adding them to JPanel
    buttons = new JButton[buttonNames.length];
    keypad = new Keypad();
    for(int i=0;i<buttonNames.length;i++)
    {
        buttons[i]= new JButton(buttonNames[i]);
        buttons[i].setBackground(Color.white);
        buttonPanel.add(buttons[i]);
        buttons[i].addKeyListener(keypad);
        buttons[i].addMouseListener(keypad);
    }
    //Adding panel to JFrame
    add(buttonPanel);
}

public void reset()
{
     result="0";
     expression="0";
     operator="0";
     screen.setText(result);
}

private class Keypad extends MouseAdapter  implements KeyListener
{
    //KeyListeners
 public void keyPressed(KeyEvent event){
 
     String button= KeyEvent.getKeyText(event.getKeyCode());
               switch(button)
              {
                  case "Equals":
                      if(operator!="0")
                      {
                          result= String.valueOf(perfomOperationAndGetResult(result,expression,operator));
                          screen.setText(result);
                      }
                      break;
                  case "Plus":
                  case "Minus":
                  case "*":
                  case "x2":
                  case "x3":
                  case "Slash":
                      operator=button;
                      if(result!="0")
                      {
                          result= String.valueOf(perfomOperationAndGetResult(result,expression,operator));
                          screen.setText(result);
                      }
                      else
                          result=expression;
                      expression="0";
                      break;
                  case "Period":
                      if(!expression.contains("."))
                      {
                           expression+=".";
                           screen.setText(expression);     
                      }
                      break;
                  case "C":
                      reset();
                      break;
                  case "Backspace":
                      if(operator!="0" && result!="0" && expression=="0")
                      {
                           reset();
                      }
                      else if(expression.length()-1>0)
                      { expression=expression.substring(0,expression.length()-1 );
                        screen.setText(expression);
                      }
                      else
                      { 
                          expression="0";
                          screen.setText(expression);
                      }  
                      break;
                      //erase the last character
                  case "0":
                  case "1":
                  case "2":
                  case "3":
                  case "4":
                  case "5":
                  case "6":
                  case "7":
                  case "8":
                  case "9":
                      if(result=="0" && expression=="0")
                      {
                          expression=button;
                          screen.setText(expression);
                      }
                      else if(expression=="0")
                      {
                          expression=button;
                          screen.setText(expression);
                      }
                      else
                      {
                          if(expression.length()<32)
                          {
                              expression+=button;
                              screen.setText(expression);
                          }
                          else
                          {
                               JOptionPane.showMessageDialog(Calculator.this, "Max digit limit exceeded!",
                               "Warning!",JOptionPane.WARNING_MESSAGE);
                          }
                      }
                      break;
   }
          
  
 }   
 public void keyReleased(KeyEvent event){}
 public void keyTyped(KeyEvent event){
    
 }
  
 
 //MouseListener
  public void mouseClicked(MouseEvent event)
  {
      for(int i=0;i<buttonNames.length;i++)
      {
          if(event.getSource()==buttons[i])
          {
              switch(buttons[i].getText())
              {
                  case "=":
                      if(operator!="0")
                      {
                          result= String.valueOf(perfomOperationAndGetResult(result,expression,operator));
                          screen.setText(result);
                      }
                      break;
                  case "+":
                  case "-":
                  case "*":
                  case "x2":
                  case "x3":
                  case "/":
                      operator=buttons[i].getText();
                      if(result!="0")
                      {
                          result= String.valueOf(perfomOperationAndGetResult(result,expression,operator));
                          screen.setText(result);
                      }
                      else
                          result=expression;
                      expression="0";
                      break;
                  case ".":
                      if(!expression.contains("."))
                      {
                           expression+=".";
                           screen.setText(expression);     
                      }
                      break;
                  case "CE":
                      reset();
                      break;
                  case "Back":
                      if(operator!="0" && result!="0" && expression=="0")
                      {
                           reset();
                      }
                      else if(expression.length()-1>0)
                      { expression=expression.substring(0,expression.length()-1 );
                        screen.setText(expression);
                      }
                      else
                      { 
                          expression="0";
                          screen.setText(expression);
                      }  
                      break;
                      //erase the last character
                  default:
                      if(result=="0" && expression=="0")
                      {
                          expression=buttons[i].getText();
                          screen.setText(expression);
                      }
                      else if(expression=="0")
                      {
                          expression=buttons[i].getText();
                          screen.setText(expression);
                      }
                      else
                      {
                          if(expression.length()<32)
                          {
                              expression+=buttons[i].getText();
                              screen.setText(expression);
                          }
                          else
                          {
                               JOptionPane.showMessageDialog(Calculator.this, "Max digit limit exceeded!",
                               "Warning!",JOptionPane.WARNING_MESSAGE);
                          }
                      }  
              }
          }
      }
  }
  
  
  
  
  //Calculations
  public double perfomOperationAndGetResult(String a,String b, String operator)
 {
   
     double number1 = Double.parseDouble(a);
     double number2 = Double.parseDouble(b);
     double result=0;
     
     if(operator.equals("+"))
     {
         result = number1+number2;
         return result;
     }
     else if(operator.equals("-"))
     {
         return result=number1-number2;
     }
     else if(operator=="*")
         return result=number1*number2;
     else if(operator=="/")
     {
         if(number2!=0)
         {
             return result= number1/number2;
         }
         else
         {
             JOptionPane.showMessageDialog(Calculator.this, "Division by Zero is not possible!",
             "Math Error",JOptionPane.ERROR_MESSAGE);
             reset();
             
         }
     }
     else if(operator=="x2")
     {
         return result= number1*number1;
     }
     else if(operator=="x3")
     {
         return result = number1*number1*number1;
     }
         return result;
 }
}}
