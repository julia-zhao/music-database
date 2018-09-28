/* Node<T>.java
 * Julia Zhao and Tasha Xiao
 * May 03 2018
 * Version 1.0.0
 * Template for nodes in DoubleLinkedList
 */
class Node<T> { //T is a placeholder, represents a generic type
  private T item; //item of type T
  private Node<T> next;
  private Node<T> previous;
  
  public Node(T item) { //contructor for the head
    this.item=item;
    this.next=null;
    this.previous=null;
  }
  
  //constructor
  public Node(T item, Node<T> previous, Node<T> next) { 
    this.item=item;
    this.previous=previous;
    this.next=next;
  }
  /* getNext
   * gets the next node
   * @return      the next node
   */
  public Node<T> getNext(){ //gets value of next
    return this.next;
  }
  /* getPrevious
   * gets the previous node
   * @return      the previous node
   */
  public Node<T> getPrevious(){
    return this.previous;
  }
  /* setNext
   * sets the next node
   * @param      the node to be set
   */
  public void setNext(Node<T> next){ //sets the value of next
    this.next = next;
  }
  /* setPrevious
   * sets the previous node
   * @param      the node to be set
   */
  public void setPrevious(Node<T> previous){
    this.previous = previous;
  }
  /* getItem
   * gets the item in the node
   * @return      the item in the node
   */
  public T getItem(){ //gets the value of item
    return this.item;
  }
  /* setItem
   * sets the item in the node
   * @param      the item to be set
   */
  public void setItem(T item){
    this.item=item;
  }
  
}