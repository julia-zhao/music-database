/* DoubleLinkedList<E>.java
 * Julia Zhao and Tasha Xiao
 * May 03 2018
 * Version 1.0.0
 * Program for the double linked list
 */

class DoubleLinkedList<E> { 
  private Node<E> head;//first item in the list
  private Node <E> tail;//last item in the list
   
  /* add
   * adds an item to the list
   * @param item      the item to be added
   */
  public void add(E item) {
    //no items in the list
    if (head==null) { //creates a new head with value item, next node is null
      head=new Node<E>(item);
      tail=head; //reference points to head
      return;
    }
    
    Node<E> tempNode=tail;
    tempNode.setNext(new Node<E>(item, tempNode, null));
    tail=tempNode.getNext();
    return;
  }//end of add
  
  /* get
   * gets an item at an indicated index value
   * @param index      the index of the item to be retrieved
   * @return           the item at the indicated index
   */
  public E get(int index) {
    Node<E> tempNode = head;
    for (int i=0; i<index; i++){
      tempNode=tempNode.getNext();   
    }
    return tempNode.getItem();
  }//end of get
  
  /* set
   * sets an item to an indicated index value
   * @param item      the item to be replaced
   * @param index     the index in the list the item is to be added to
   */
  public void set(int index, E item) { 
    Node<E> tempNode = head;
    for (int i=0; i<index; i++){
      tempNode=tempNode.getNext();   
    }
    tempNode.setItem(item);
  }
  
  /* indexOf
   * finds the index of an indicated item
   * @param item      the item to be found
   * @return          the index of the item
   */
  public int indexOf(E item) {
    Node<E> tempNode = head;
    int counter=0;
    while (tempNode!=null){
      //compare the values
      if (tempNode.getItem().equals(item)){
        return counter;
      }
      tempNode=tempNode.getNext(); 
      counter++;
    }
    return counter;
  }//end of indexOf
  
  /* remove
   * removes an item from the list
   * @param item      the item to be removed
   */
  public E remove(int index) {
    int counter=0;
    if (index==0){ //if you need to remove the first item
      head=new Node<E>(head.getNext().getItem(), head, head.getNext().getNext());
    }
    Node <E> tempNode=head;
    while (tempNode!=null){
      //compare the values
      if ((counter+1)==index){ //if the next item is what you want to remove
        System.out.println ("Removing: " + tempNode.getNext().getItem());
        if (tempNode.getNext().getNext()!=null){
          tempNode.setNext(tempNode.getNext().getNext());
        }                                
        else{ //last item in the list
          tempNode.setNext(null);
        }
      }
      tempNode=tempNode.getNext(); 
      counter++;
    }
    return null;
  }//end of remove
  
  /* clear
   * empties the list
   */
  public void clear() {
    head=null;
  }//end of clear
  
  /* size
   * finds the amount of items to the list
   * @return        the size of the list
   */
  public int size() {
    Node <E> tempNode=head;
    int counter=0;
    while (tempNode!=null){
      counter++;
      tempNode=tempNode.getNext();
    }
    return counter;
  }//end of size
  
  /* sortAlpha
   * sorts a list alphabetically from A to Z(insertion sort)
   * @param a     the list to be sorted
   */
  public static void sortAlpha(DoubleLinkedList<Items> a){
    for (int i=1; i<a.size(); i++){ //starts at 2nd element
      int index=i-1;
      Items tempItem = a.get(i);
      String item = tempItem.getName(); //pivot element
      
      while (index>=0 && ((a.get(index)).getName()).compareTo(item)>0){
        a.set(index+1, a.get(index)); //move the element one position down
        index--;
      }
      
      a.set(index+1,tempItem); //insert item in proper position
    }
  }//end of sortAlpha
  
   /* sortChrono
   * sorts a list chronologically from most recent to least recent(insertion sort)
   * @param a     the list to be sorted
   */
  public static void sortChrono(DoubleLinkedList<Items> a){
    for (int i=1; i<a.size(); i++){ //starts at 2nd element
      int index=i-1;
      Items tempItem = a.get(i); //pivot element
      String item = tempItem.getDate();
      
      while (index>=0 && ((a.get(index)).getDate()).compareTo(item)<0){
        a.set(index+1, a.get(index)); //move the element one position down
        index--;
      }
      
      a.set(index+1,tempItem); //insert item in proper position
    }
  }//end of sortChrono
}//end of DoubleLinkedList.java