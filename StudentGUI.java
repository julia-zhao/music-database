/* StudentGUI.java
 * Julia Zhao and Tasha Xiao
 * May 09 2018 
 * Version 1.0.0
 * GUI for student menu
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentGUI extends JPanel{
  //create gui components
  JFrame frame = new JFrame ("Music Sign Out");
  public StudentGUI(){
    //create/set more gui components
    frame.setSize(800,800);
    
    JPanel panel = new JPanel();
    JLabel question = new JLabel ("What would you like to do?");
    JButton back = new JButton("Go back");
    JButton signOut = new JButton ("Sign Out");
    JButton signIn = new JButton ("Sign In");
    JButton recent = new JButton ("View recent actions");
    
    signOut.addActionListener(new SignOutListener());
    signIn.addActionListener (new SignInListener());
    recent.addActionListener(new RecentListener());
    back.addActionListener(new BackListener());
    
    panel.add(question);
    panel.add(back);
    panel.add(signOut);
    panel.add(signIn);
    panel.add(recent);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setVisible (true);
  }
  
  /* BackListener 
   * Occurs when back button is pressed
   * exits and opens the menu
   */
  class BackListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      frame.setVisible(false);
      frame.dispose();
      new MenuGUI();
    }
  }//end of BackListener
  
  /* SignOutListener 
   * Occurs when signOut button is pressed
   * opens the signOut menu
   */
  class SignOutListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      new SignOut();
    }
  }
  /* SignInListener 
   * Occurs when signIn button is pressed
   * opens the signIn menu
   */
  class SignInListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      new SignIn();
      frame.setVisible(false);
      frame.dispose();
    }
  }
  /* RecentListener 
   * Occurs when recent button is pressed
   * opens the recently signed out student list
   */
  class RecentListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      new RecentStudent();
    }
  }
}//end of StudentGUI.java