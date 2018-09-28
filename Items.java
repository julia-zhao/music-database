/* Items.java
 * Julia Zhao and Tasha Xiao
 * May 03 2018
 * Version 1.0.0
 * Template for insturments, equipment and sheet music
 */

public class Items {
  
  //initial variable declaration
  private boolean condition = true;
  private String name = "";
  private String descr = "";
  private String num = "";
  private String status = "";
  
  private int stu = -1;
  private String dueDate = "";
  
  /********************************************************* the constructors ***********************************************/
  //default constructor
  public Items() { 
  }
  
  public Items(String name, String condition, String num, String status){
    this.condition = Boolean.parseBoolean(condition);
    this.name = name;
    this.num = num;
    this.status = status;
  }
  
  /* display
   * Prints information about the item onto console
   * @param a        the mode it is in - 0 = displays item regardless
   *                                     1 = display only if it's signed out
   *                                     2 = display only if it's signed out by a specific student
   */
  public void display(int a){
    //displays all items
    if (a == 0){
      System.out.println("Name: " + this.name);
      System.out.println("Num: " + this.num);
      System.out.println("Condition: " + this.condition);
      System.out.println("Description: "+ this.descr);
      System.out.println("Status: " + this.status);
      if(this.stu != -1){
        System.out.println("Student: " + this.stu);
        System.out.println("Due date: " + this.dueDate);
      }
    }
    //doesn't display an item if it isn't signed out by a student
    else if (a == 1){
      if (dueDate.equals("") == false){
        System.out.println("Name: " + this.name);
        System.out.println("Num: " + this.num);
        System.out.println("Condition: " + this.condition);
        System.out.println("Description: "+ this.descr);
        System.out.println("Status: " + this.status);
        if(this.stu != -1){
          System.out.println("Student: " + this.stu);
          System.out.println("Due date: " + this.dueDate);
        }
      }
    }
    //doesn't display an item if it isn't signed out by a SPECIFIC student
    else{
      if (a == this.stu){
        System.out.println("Name: " + this.name);
        System.out.println("Num: " + this.num);
        System.out.println("Condition: " + this.condition);
        System.out.println("Description: "+ this.descr);
        System.out.println("Status: " + this.status);
        if(this.stu != -1){
          System.out.println("Student: " + this.stu);
          System.out.println("Due date: " + this.dueDate);
        }
      }
    }
  }//end of display
  /********************************************************* the get methods ***********************************************/
  /* getCondition
   * returns the condition of the item
   * @return        the condition of the item
   */
  public boolean getCondition(){
    return this.condition;
  }
  
  /* getName
   * returns the name of the item (unique to each item)
   * @return        the name of the item
   */
  public String getName(){
    return this.name;
  }
  
  /* getDescr
   * returns the description of the item 
   * @return        the description of the item
   */
  public String getDescr(){
    return this.descr;
  }
  /* getNum
   * returns the number of the item 
   * @return        the number of the item
   */
  public String getNum(){
    return this.num;
  }
  /* getStatus
   * returns the status ("Instrument, Sheet_music, Others") of the item 
   * @return        the description of the item
   */
  public String getStatus(){
    return this.status;
  }
  
  /* getPerson
   * returns the student number of the signed-out item 
   * @return        the student number of the signed-out item
   */
  public int getPerson(){
    return this.stu;
  }
  /* getDate
   * returns the due date of the signed-out item 
   * @return        the due date of the signed-out item 
   */
  public String getDate(){
    return this.dueDate;
  }
  /********************************************************* the set methods ***********************************************/
  /* setName
   * sets the name of the item
   * @param        the name to be set
   */
  public void setName(String name){
    this.name = name;
  }
  /* setCondition
   * sets the condition of the item
   * @param        the condition to be set
   */
  public void setCondition(boolean cond){
    this.condition = cond;
  }
  
  /* setNum
   * sets a number to the item
   * @param        the number to be set
   */
  public void setNum(String num){
    this.num = num;
  }
  /* setStatus
   * sets the status of the item
   * @param        the status to be set
   */
  public void setStatus(String status){
    this.status = status;
  }
  /* setPerson
   * sets a student number to the item
   * @param        the student number to be set
   */
  public void setPerson(int student){
    this.stu = student;
  }
  /* setDescr
   * sets the description of the item
   * @param        the description to be set
   */
  public void setDescr(String descr){
    this.descr = descr;
  }
  /* setDate
   * sets the due date of the item
   * @param        the date to be set
   */
  public void setDate(String date){
    this.dueDate = date;
  }
  
}//end of Items.java