/* Inventory.java
 * Julia Zhao and Tasha Xiao
 * May 09 2018
 * Version 1.0.0
 * Displays everything currently in the list
 */

import java.awt.event.*; //imports
import javax.swing.*;

public class Inventory extends JPanel{
  
  //declare GUI components
  JFrame frame = new JFrame ("Music Sign Out");
  int index=0;
  JLabel[] info = new JLabel[7];
  JButton delete = new JButton ("Delete item"); //delete the item that the user is on right now
  JTextField searchField = new JTextField ("Enter name of item you'd like to search for");
  
  //constructor
  public Inventory (){
    frame.setSize(400,300);
    //Print to file once the window is closed
    frame.addWindowListener(new WindowAdapter(){ //listens for window closing
      public void windowClosing(java.awt.event.WindowEvent windowEvent){
        MusicResource.printFile(MusicResource.getItems()); //prints to file
      }
    });
    //set GUI components
    JPanel panel = new JPanel();
    JButton back = new JButton ("Back");
    JButton next = new JButton ("Next");
    JButton searchButton = new JButton ("Search");
    
    //add action listeners
    back.addActionListener(new BackListener());
    next.addActionListener(new NextListener());
    searchButton.addActionListener(new SearchListener());
    
    //initialize info
    for (int i=0; i<7; i++){
      info[i]=new JLabel();
    }
    
    //set information when list is not empty
    if (MusicResource.getItems()!=null){
      setInfo(info, index);
    }
    else{ //list is empty
      JOptionPane.showMessageDialog(null, "No items!");
    }
    //add info onto the panel
    for (int i=0; i<7; i++){
      panel.add(info[i]);
    }
    
    //add button and search field
    panel.add(back);
    panel.add(next);
    panel.add(searchField);
    panel.add(searchButton);
    
    //add panel onto frame and set visible
    frame.add(panel);
    frame.setVisible(true);
  }
  
  /* setInfo
   * assigns information onto the label
   * @param info        the text field to be edited
   * @param i           which element of the array (for the information)
   */
  public void setInfo (JLabel[] info, int i){
    info[0].setText("Name: " + MusicResource.getItems().get(i).getName());
    info[1].setText("Number: " + MusicResource.getItems().get(i).getNum());
    if (MusicResource.getItems().get(i).getCondition()==false){ //bad condition
      info[2].setText("Condition: Out to repairs");
    }
    else{ //good condition
      info[2].setText("Condition: Good");
    }
    info[3].setText(MusicResource.getItems().get(i).getDescr());
    if (MusicResource.getItems().get(i).getPerson()==-1){ //no one has taken it out
      info[4].setText("Not taken out");
      info[5].setText("No due date");
    }
    else{
      info[4].setText(Integer.toString(MusicResource.getItems().get(i).getPerson()));
      info[5].setText(MusicResource.getItems().get(i).getDate());
    }
    info[6].setText("Status: " + MusicResource.getItems().get(i).getStatus());
  }
  
  /* search method
   * @param items - list of items to search through
   * @param start - first index
   * @param end - last index
   * @param item - what you're searching for
   * @return int index of what you're searching for
   */
  public int search (DoubleLinkedList<Items> items, int start, int end, String item){
    if (end-start>=0){ //the list should be sorted
      int mid = (end+start)/2; //middle element of the array
      if (items.get(mid).getName().equals(item)){ //base case, you have found the item
        return mid;
      }
      if (items.get(mid).getName().compareTo(item)>0){ //item is in lower half of list
        return search (items, start, mid-1, item); 
      }
      else{
        return search (items, mid+1, end, item); //item is in upper half of list
      }
    }
    //not in the array
    return -1;
  }
  
  //inner classes for action listeners
  class BackListener implements ActionListener{
    /* actionPerformed method
     * Runs if the sign out button is pressed
     * @param event - the action that is performed
     */
    public void actionPerformed(ActionEvent event){
      if (index>0){ //goes back one element in the list
        index=index-1;
        setInfo(info, index); //update the label information
      }
      else if (MusicResource.getItems().size()==1){
        JOptionPane.showMessageDialog(null, "There is only one item!");
      }
      else{
        JOptionPane.showMessageDialog(null, "No more items!");
      }
    }
  }
  
  //for the next button
  class NextListener implements ActionListener{
    /* actionPerformed method
     * Runs if the sign out button is pressed
     * @param event - the action that is performed
     */
    public void actionPerformed(ActionEvent event){
      if (index<MusicResource.getItems().size()-1){
        index=index+1; //goes forward one element in the list
        setInfo(info, index); //update the text field's information
      }
      else if (MusicResource.getItems().size()==1){ 
        JOptionPane.showMessageDialog(null, "There is only one item!");
      }
      else{
        JOptionPane.showMessageDialog(null, "No more items!");
      }
    }
  }
  
  //search then display
  class SearchListener implements ActionListener{
    /* actionPerformed method
     * Runs if the sign out button is pressed
     * @param event - the action that is performed
     */
    public void actionPerformed(ActionEvent event){
      //search for the item
      index=search (MusicResource.getItems(), 0, MusicResource.getItems().size(), searchField.getText());
      if (index>=0){ //item was found
        setInfo(info,index);
      }
      else{
        JOptionPane.showMessageDialog(null, "Item not found!");
      }
    }
  }
}