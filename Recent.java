/* Recent.java
 * Julia Zhao and Tasha Xiao
 * May 09 2018
 * Version 1.0.0
 * Shows all the recent sign outs (gui)
 */
import java.text.SimpleDateFormat;
import javax.swing.*;

public class Recent {
  public Recent(){
    //create and add in the gui components
    JFrame frame = new JFrame ("Music Sign Out");
    frame.setSize(300,400);
    JPanel panel = new JPanel ();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    JLabel label = new JLabel ("\tRecent Sign Outs:");
    DoubleLinkedList<Items> list = MusicResource.getItems();
    DoubleLinkedList.sortChrono(list);
    
    JLabel overdue = new JLabel("----Overdue:----");
    JLabel signOuts[] = new JLabel[list.size()];
    
    //add in the appropriate labels for each signed out item
    for(int i = 0; i<list.size(); i++){
      if (list.get(i).getPerson() != -1){
        signOuts[i] = new JLabel("\t" + list.get(i).getName() + 
                                 "\t Due: " + list.get(i).getDate() + 
                                 "\t Name: " + list.get(i).getPerson());
      }
    }
    
    panel.add(label);
    
    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    int j = 0;
    
    //add in the items that aren't overdue
    while (j<list.size() && (signOuts[j] != null) && list.get(j).getDate().compareTo(currentDate)>=0){
      panel.add(signOuts[j]);
      j++;
    }
    
    //add overdue label
    panel.add(overdue);
    
    //add the overdue items
    while (j<list.size() && (signOuts[j] != null) && list.get(j).getPerson() != -1){
      panel.add(signOuts[j]);
      j++;
      System.out.println(j);
    }
    
    frame.add(panel);
    frame.setVisible(true);
    
  }
}//end of Recent.java