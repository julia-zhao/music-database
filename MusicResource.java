/* MusicResource.java
 * Julia Zhao and Tasha Xiao
 * May 06 2018
 * Version 1.0.0
 * Where information/methods(not gui) are stored
 * RUN PROGRAM HERE
 */

//import java.time.LocalDate;
//import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Scanner;

public class MusicResource {
  private static DoubleLinkedList<Items> list;
  private static DoubleLinkedList<Person> students;
  
  /* printFile
   * prints the list of items to .txt file
   * @param DoubleLinkedList<Items> array       the list to be printed
   */
  public static void printFile(DoubleLinkedList<Items> array){
    DoubleLinkedList.sortAlpha(array);
    try{
      PrintWriter printOut = new PrintWriter ("Items.txt");
      for (int i=0; i<array.size(); i++){
        printOut.print(array.get(i).getName()+" ");
        printOut.print(array.get(i).getCondition() + " ");
        printOut.print(array.get(i).getNum() + " ");
        printOut.print(array.get(i).getStatus() + " ");
        if (array.get(i).getPerson()!= -1){
          printOut.print(array.get(i).getPerson() + " ");
          printOut.print(array.get(i).getDate() + " ");
        }
        printOut.print("*" + array.get(i).getDescr());
        printOut.println();
      }
      printOut.close();
    }
    catch (Exception e){
      System.out.println ("Error 3");
    }
  }//end of printFile
  
  /* signIn
   * assigns a student and due date to an item currently in the list
   * @param DoubleLinkedList<Items> list       the list to be looked through
   */
//  public static void signIn(DoubleLinkedList<Items> list){
//    Scanner input = new Scanner(System.in);
//    System.out.println("What do you want to sign in?");
//    String temp = input.nextLine();
//    Items tempTemp = checkItem(temp, list);
//    //checks if item is found
//    if (tempTemp == null){
//      System.out.println("Not found.");
//    }
//    else {
//      tempTemp.setPerson(-1);
//      tempTemp.setDate(null);
//    }
//  }//end of sign in
  
  /* signOut
   * removes the student from the list
   * @param DoubleLinkedList<Items> list       the list to be looked through
   //   */
//  public static void signOut(DoubleLinkedList<Person> students, DoubleLinkedList<Items> list){
//    Scanner input = new Scanner(System.in);
//    System.out.println("What do you want to sign out?");
//    String temp = input.nextLine();
//    Items tempTemp = checkItem(temp, list);
//    //checks if item is found
//    if (tempTemp == null){
//      System.out.println("Not found.");
//    }
//    else {
//      //checks if item is out on repairs
//      if (tempTemp.getCondition() == false){
//        System.out.println("This is currently out to repairs.");
//      }
//      else{
//        System.out.println("Enter your student id.");
//        temp = input.nextLine();
//        
//        //checks if item is already signed out by a different user
//        if (tempTemp.getPerson() != -1 && tempTemp.getPerson() != Integer.parseInt(temp)){
//          System.out.println("This is already signed out by someone else.");
//        }
//        else{
//          Person tempStu = checkStudent(Integer.parseInt(temp),students);
//          if (tempStu == null){
//            System.out.println("Student not found.");
//          }
//          else {
//            tempTemp.setPerson(tempStu.getNum());
//            //finds current date
//            String tempDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
//            //increases day by 1
//            tempTemp.setDate(LocalDate.parse(tempDate).plusDays(1).toString());
//          }
//        }
//      }
//    }
//  }//end of sign out
  
  /* readStudents
   * read in students from txt file
   * @return      a double linked list of students (under the class Person)
   */
  public static DoubleLinkedList<Person> readStudents (){
    DoubleLinkedList<Person> students = new DoubleLinkedList<Person>();
    try{
      Scanner read = new Scanner (new File ("students.txt"));
      while (read.hasNextLine()){
        Person newPerson = new Person();
        newPerson.setNum(read.nextInt());
        newPerson.setName(read.nextLine());
        students.add(newPerson);
      }
      read.close();
    }
    catch(Exception e){
      System.out.println("Error 2");
    }
    return students;
  }//end of readStudents
  
  /* readItems
   * read in items from txt file
   * @return      a double linked list of items
   */
  public static DoubleLinkedList<Items> readItems(){
    DoubleLinkedList<Items> list = new DoubleLinkedList<Items>();
    try {
      File file = new File("Items.txt");
      Scanner fileInput = new Scanner(file);
      
      while (fileInput.hasNext()){
        String temp = fileInput.nextLine();
        Scanner stringInput = new Scanner(temp);
        
        //fill in the commonly shared features
        Items tempItem = new Items(stringInput.next(), stringInput.next(),
                                   stringInput.next(), stringInput.next());
        String tempTemp = stringInput.next();
        //if the item has a student
        if (tempTemp.charAt(0) != '*'){
          tempItem.setPerson(Integer.parseInt(tempTemp));
          
          tempItem.setDate(stringInput.next());
        }
        //set description
        tempItem.setDescr(temp.substring(temp.indexOf('*')+1));       
        list.add(tempItem);
        stringInput.close();
      }
      fileInput.close();
    }
    catch(Exception E){
      System.out.println("Error 1");
    }
    return list;
  }//end of readItems
  
  /* checkStudent
   * finds a student in the list based on their student number
   * @return      the item in the list, or null if not found
   */
  public static Person checkStudent (int num, DoubleLinkedList<Person> students){
    int index=0;
    //goes through the entire list
    while (index < students.size()){
      if (num==students.get(index).getNum()){
        //found the student
        return students.get(index);
      }
      index++;
    }
    //if student is not found
    return null;
  }//end of checkStudent
  
  /* checkItem
   * finds an item in the list based on the name of the item
   * @return      an item in the list if found, null if not found
   */
  public static Items checkItem (String input, DoubleLinkedList<Items> list){
    for (int i = 0; i<list.size(); i++){
      if (input.equals(list.get(i).getName())){
        return list.get(i);
      }
    }
    return null;
  }//end of checkItem
  
  /* getItems
   * gets the list of items
   * @return      the list of items
   */
  public static DoubleLinkedList<Items> getItems(){
    return list;
  }
  
  /* getStudents
   * gets the list of students
   * @return      the list of students
   */
  public static DoubleLinkedList<Person> getStudents(){
    return students;
  }
  
  //main method
  public static void main(String[] args) { 
    
//read in the files----------------------------------
    list = readItems();
    students = readStudents();
    for (int i = 0; i< list.size(); i++){
      Items temp = list.get(i);
      temp.display(0);
      System.out.println();
    }
    
    for (int i=0; i<students.size(); i++){
      System.out.println (students.get(i).getNum());
    }
    
    //start the gui
    new MenuGUI();
  }//end of main
}//end of MusicResource.java