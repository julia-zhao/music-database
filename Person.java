/* Person.java
 * Julia Zhao and Tasha Xiao
 * May 03 2018
 * Version 1.0.0
 * Template for a person
 */
public class Person {
  
  private boolean status;//whether the person is a student or a teacher
  private String name = "";//the name of the person
  private int personNum;//their student number
  
  //default constructor
  public Person (){
  }
  
  public Person(String name, int num) {
    this.name = name;
    this.personNum = num;
  }
  public Person(String num) {
    this.personNum = Integer.parseInt(num);
  }
  /* getName
   * gets the person's name
   * @return      the person's name
   */
  public String getName(){
    return this.name;
  }
  /* getNum
   * gets the person's number (student/teacher number)
   * @return      the person's number
   */
  public int getNum(){
    return this.personNum;
  }
  /* getNum
   * sets the person's number
   * @param      the number to be set
   */
  public void setNum (int num){
    personNum = num;
  }
  /* setName
   * sets the person's name
   * @param      the person's name
   */
  public void setName(String name){
    this.name=name;
  }
}//end of Person.java