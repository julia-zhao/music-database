/* SignIn.java
 * Julia Zhao and Tasha Xiao
 * May 06 2018
 * Version 1.0.0
 * The GUI interface for a student who wants to sign an object in
 */
import java.awt.event.*;//imports
import javax.swing.*;

public class SignIn {
  JFrame frame = new JFrame("Music Sign Out");
  JTextField question = new JTextField ("What would you like to sign in?");
  
  public SignIn(){ //constructor
    frame.setSize (400,300);
    //Print to file once the window is closed
    frame.addWindowListener(new WindowAdapter(){ //listener for the window, overrides default method
      public void windowClosing(java.awt.event.WindowEvent windowEvent){
        MusicResource.printFile(MusicResource.getItems());
      }
    });
    JPanel panel = new JPanel();
    
    JButton ok = new JButton ("OK");
    JButton back = new JButton ("Back");
    
    //add action listeners
    ok.addActionListener(new OkListener());
    back.addActionListener(new BackListener());
    
    //add buttons to panel, panel to frame, set visible
    panel.add(question);
    panel.add(ok);
    panel.add(back);
    frame.add(panel);
    frame.setVisible (true);
  }
  
  //inner class, for ok button
  class OkListener implements ActionListener{
    /* actionPerformed method
     * Runs if the sign out button is pressed
     * @param event - the action that is performed
     */
    public void actionPerformed(ActionEvent event){
      String name = question.getText(); //get information from text fields
      Items item = MusicResource.checkItem(name, MusicResource.getItems());
      if (item == null){ //item not found
        JOptionPane.showMessageDialog(null, "This is not in the inventory!");
      }
      else{
        if (item.getPerson()==-1){ //person not found
          JOptionPane.showMessageDialog(null, "This has not been signed out!");
        }
        else{ //person and item are both ok
          item.setPerson(-1); //clear it from the item
          item.setDate(null);    
          JOptionPane.showMessageDialog(null, "The database has been updated!");
          
        }
      }
    }
  }
  
  //inner class, for back button
  class BackListener implements ActionListener{
    /* actionPerformed method
     * Runs if the sign out button is pressed
     * @param event - the action that is performed
     */
    public void actionPerformed(ActionEvent event){
      frame.dispose();
      new StudentGUI();
    }
  }
}