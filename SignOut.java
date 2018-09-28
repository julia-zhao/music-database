/* SignOut.java
 * Julia Zhao and Tasha Xiao
 * May 02 2018 
 * Version 1.0.0
 * Interface to sign out or add object
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

public class SignOut extends JPanel{
  JFrame frame = new JFrame ("Music Sign Out");
  JTabbedPane tabbedPane = new JTabbedPane();
  JTextField studentNum1 = new JTextField("Student number");
  JTextField studentNum2 = new JTextField("Student number");
  JTextField studentNum3 = new JTextField("Student number");
  JTextField instrument = new JTextField("Instrument");
  JTextField instrNum = new JTextField("Instrument Number");
  JTextField sheetMusic = new JTextField ("Sheet Music");
  JTextField sheetNum = new JTextField("Sheet Number");
  JTextField equipment = new JTextField ("Equipment");
  JTextField equipNum = new JTextField("Equipment Number");
  JCheckBox condition1 = new JCheckBox("Good condition?");
  JCheckBox condition2 = new JCheckBox("Good condition?");
  JCheckBox condition3 = new JCheckBox("Good condition?");
  
  public SignOut(){ //constructor
    frame.setSize(400,300);
    //Print to file once the window is closed
    frame.addWindowListener(new WindowAdapter(){
      public void windowClosing(java.awt.event.WindowEvent windowEvent){
        MusicResource.printFile(MusicResource.getItems());
      }
    });
    JPanel instrumentPanel = new JPanel();
    JPanel sheetPanel = new JPanel();
    JPanel equipmentPanel = new JPanel();
    
    JButton signOut1 = new JButton ("Sign Out");
    JButton signOut2 = new JButton ("Sign Out");
    JButton signOut3 = new JButton ("Sign Out");
    
    signOut1.addActionListener(new SignOutListener());
    signOut2.addActionListener(new SignOutListener());
    signOut3.addActionListener(new SignOutListener());
    
    instrumentPanel.add(studentNum1);
    instrumentPanel.add(instrument);
    instrumentPanel.add(instrNum);
    instrumentPanel.add(condition1);
    instrumentPanel.add(signOut1);

    sheetPanel.add(studentNum2);
    sheetPanel.add(sheetMusic);
    sheetPanel.add(sheetNum);
    sheetPanel.add(condition2);
    sheetPanel.add(signOut2);
    
    equipmentPanel.add(studentNum3);
    equipmentPanel.add(equipment);
    equipmentPanel.add(equipNum);
    equipmentPanel.add(condition3);
    equipmentPanel.add(signOut3);
    
    tabbedPane.addTab("Instrument", instrumentPanel);
    tabbedPane.addTab("Sheet Music", sheetPanel);
    tabbedPane.addTab("Equipment", equipmentPanel);
    
    frame.add(tabbedPane); 
    frame.setVisible(true);
  } 
  class SignOutListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      String name="";
      String idNumber="";
      //get the information from the field
      if (tabbedPane.getSelectedIndex()==0){
        name=instrument.getText();
        idNumber = studentNum1.getText();
        
      }
      else if (tabbedPane.getSelectedIndex()==1){
        name = sheetMusic.getText();
        idNumber = studentNum2.getText();
      }
      else{ //last tab
        name = equipment.getText();
        idNumber = studentNum3.getText();
      }
      //check if item is found
      Items item = MusicResource.checkItem(name, MusicResource.getItems());
      if (item==null){
        JOptionPane.showMessageDialog(null, "Item not found in database!");
      }
      else{
        //check if item is out on repairs
        if (item.getCondition()==false){
          JOptionPane.showMessageDialog(null, "Out to repairs. Cannot be signed out.");
        }
        else{
          if (isInt(idNumber)==true){
            //check if item is already signed out by a different user
            if (item.getPerson() !=-1 && item.getPerson()!=Integer.parseInt(idNumber)){
              JOptionPane.showMessageDialog(null, "Already signed out by someone else!");
            }
            else{
              Person student = MusicResource.checkStudent(Integer.parseInt(idNumber), MusicResource.getStudents());
              if (student==null){
                JOptionPane.showMessageDialog(null, "Sorry, your student number isn't in the database!");
              }
              else{
                item.setPerson(student.getNum());
                //finds current date
                String tempDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
                //increases day by 1
                item.setDate(LocalDate.parse(tempDate).plusDays(1).toString());
                JOptionPane.showMessageDialog(null, "Successfully signed out!");
              }
            }
          }
          else{
            JOptionPane.showMessageDialog(null, "Please make sure the student number only has numbers!");
          }
        }
      }
    }
  }
  
  public boolean isInt(String str){
    boolean valid = true;
    int i=0;
    //check if the instrument number is valid (no letters)
    do{
      if (!Character.isDigit(str.charAt(i))){
        valid=false;
      }
      i++;
    }while (valid==true && i<str.length());
    return valid;
  }
  
  public static void main(String [] args){
    new SignOut();
  }
}