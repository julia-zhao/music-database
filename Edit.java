/* Edit.java
 * Julia Zhao and Tasha Xiao
 * May 09 2018
 * Version 1.0.0
 * GUI for editing an item information
 */
import java.awt.event.*;
import javax.swing.*;

public class Edit extends JPanel implements ActionListener{
  //add GUI components
  JFrame frame2 = new JFrame("Edit");
  JPanel panel2 = new JPanel();
  JButton ok = new JButton("OK");
  JButton cancel = new JButton("Cancel");
  Items tempItem = new Items();
  
  JLabel nameLab = new JLabel("Name: ");
  JLabel name = new JLabel();
  JLabel descrLab = new JLabel("Description: ");
  JTextField descr = new JTextField(10);
  JLabel conditionLab = new JLabel();
  String[] condStrings = { "OK", "Not OK"};
  JComboBox condition = new JComboBox(condStrings);
  JLabel numLab = new JLabel("Number: ");
  JTextField num = new JTextField(10);
  String[] statStrings = { "Sheet_music", "Instrument", "Other"};
  JComboBox status = new JComboBox(statStrings);
  
  //constructor
  public Edit(){
    
    //set GUI components + prepare list
    DoubleLinkedList<Items> list = MusicResource.getItems();
    
    frame2.setVisible(false);
    frame2.setSize(500,500);
    
    //ask for item name
    String instName = JOptionPane.showInputDialog(frame2, "What item?");
    
    //send alert if not found
    if (instName != null){
      tempItem = MusicResource.checkItem(instName, list);
      if (tempItem == null){
        JOptionPane.showMessageDialog(null, "Not found");
      }
      //when an item is found, set all the current information
      else {
        JLabel statusLab = new JLabel("Status: (current = " + tempItem.getStatus() + ")");
        name = new JLabel(tempItem.getName());
        descr = new JTextField(tempItem.getDescr(), 10);
        if(tempItem.getCondition() == true){
          conditionLab = new JLabel("Condition: (current = OK)");
        }
        else {
          conditionLab = new JLabel("Condition: (current = Not OK)");
        }
        num = new JTextField(tempItem.getNum(), 10);
        
        ok.addActionListener(this);
        cancel.addActionListener(this);
        
        //add components
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.add(nameLab);
        panel2.add(name);
        panel2.add(descrLab);
        panel2.add(descr);
        panel2.add(conditionLab);
        panel2.add(condition);
        panel2.add(numLab);
        panel2.add(num);
        panel2.add(statusLab);
        panel2.add(status);
        panel2.add(ok);
        panel2.add(cancel);
        
        frame2.add(panel2);
        frame2.setVisible(true);
      }
      
    }
  }//end of constructor
  
  /* actionPerformed
   * deals with what happens when you press a button(confirm/cancel)
   * @param e        the action that is performed
   */
  public void actionPerformed(ActionEvent e){
    if (e.getSource()== ok){
      //check if num is numeric
      try  
      {  
        Integer.parseInt(num.getText()); //goes to catch if num is invalid
        
        //set the information of the item
        tempItem.setDescr(descr.getText());
        String cond = (String)condition.getSelectedItem();
        if (cond.equals("OK")){
          tempItem.setCondition(true);
        }
        else if (cond.equals("Not OK")){
          tempItem.setCondition(false);
        }
        tempItem.setNum(num.getText());

        tempItem.setStatus((String)status.getSelectedItem());
        
        //removes frame
        this.setVisible(false);
        frame2.dispose();
      }  
      //if num is not a number
      catch(NumberFormatException nfe)  
      {  
        JOptionPane.showMessageDialog(null, "Number must be numeric!");
      }
    }
    //when cancel button is pressed
    else{
      this.setVisible(false);
      frame2.dispose();
    } 
  }//end of actionPerformed
}//end of Edit.java