/* MenuGUI.java
 * Julia Zhao and Tasha Xiao
 * May 02 2018
 * Version 1.0.0
 * GUI program for the menu
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuGUI extends JPanel implements ActionListener{
  //declare Gui components
  JFrame frame = new JFrame ("Music Sign Out");
  JButton teacher = new JButton ("Teacher");
  JButton student = new JButton ("Student");
  JButton inventory = new JButton ("Inventory");
  JButton quit = new JButton ("QUIT");
  
  //constructor
  public MenuGUI(){
    //set gui
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800,800);
    JPanel panel = new JPanel (new FlowLayout ());
    JLabel label = new JLabel ("What would you like to do?");
    
    teacher.addActionListener(this);
    student.addActionListener(this);
    inventory.addActionListener(this);
    quit.addActionListener(this);
    
    panel.add(label);
    panel.add(teacher);
    panel.add(student);
    panel.add(inventory);
    panel.add(quit);
    frame.add(panel);
    
    frame.setVisible(true);
  }//end of constructor
  
    /* actionPerformed
   * deals with what happens when you press a button
   * @param e        the action that is performed
   */
  public void actionPerformed(ActionEvent e){
    if (e.getSource()==student){ //student button is clicked, bring up student menu
      new StudentGUI();
      this.setVisible(false);
      frame.dispose();
    }
    else if(e.getSource()==teacher){//bring up teacher menu
      new TeacherGUI();
      this.setVisible(false);
      frame.dispose();
    }
    else if (e.getSource() == inventory){//bring up inventory (as a new window)
      new Inventory();
    }
    
    else {//clicked "QUIT"
      //save, thank you, quit
      MusicResource.printFile(MusicResource.getItems());
      JOptionPane.showMessageDialog(null, "Thank you! The database has been saved!");
      System.exit(0);
    }
    
  }//end of actionPerformed
  
}