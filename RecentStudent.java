/* Recent.java
 * Julia Zhao and Tasha Xiao
 * May 09 2018
 * Version 1.0.0
 * Shows the recent sign outs for a specific student(gui)
 */
import java.text.SimpleDateFormat;
import javax.swing.*;

public class RecentStudent {
  
  //constructor
  public RecentStudent(){
    
    //create gui components
    JFrame frame = new JFrame ("Music Sign Out");
    frame.setSize(300,400);
    JFrame frame2 = new JFrame();
    JPanel panel = new JPanel ();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    JLabel label = new JLabel ("Recent Sign Outs:");
    JLabel overdue = new JLabel("----Overdue:---");

    String stuNum = JOptionPane.showInputDialog(frame2, "What's your student number?");

    if (stuNum != null){
      try {
    //finds the items according to student number
    DoubleLinkedList<Items> list = MusicResource.getItems();
    //initialize
    JLabel signOuts[];
    if (list != null){
    DoubleLinkedList.sortChrono(list);
    
    signOuts = new JLabel[list.size()];
    
    for(int i = 0; i<list.size(); i++){
      if (list.get(i).getPerson() == Integer.parseInt(stuNum)){
      signOuts[i] = new JLabel(list.get(i).getName() + 
                               "\t Due: " + list.get(i).getDate() + 
                               "\t Name: " + list.get(i).getPerson());
      }
      }
    }
    else{
      signOuts = new JLabel[0];
    }
    
    //add in the labels
    panel.add(label);
    
    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    int j = 0;
    
    //not overdue items
    while (j<list.size() && (signOuts[j] != null) && list.get(j).getDate().compareTo(currentDate)>=0){
      panel.add(signOuts[j]);
      j++;
    }
    //overdue label
    panel.add(overdue);
    
    //overdue items
    while (j<list.size() && (signOuts[j] != null) && list.get(j).getPerson() != -1){
      panel.add(signOuts[j]);
      j++;
      System.out.println(j);
    }

    frame.add(panel);
    frame.setVisible(true);
    }
    
    catch(Exception E){
      JOptionPane.showMessageDialog(null, "Invalid input");
    }
    
  }
  }
}//end of RecentStudent.java