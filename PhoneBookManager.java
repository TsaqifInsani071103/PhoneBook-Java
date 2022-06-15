
public class PhoneBookManager {
  private ListNode front;  
  private ListNode sorted; 
  private String name; 

  //initialize an empty linked list 
  public PhoneBookManager(String name){
    this.front = null;
    this.name = name; 
    
  } 

  //get the number of nodes in the phone book. 
  public int getPhoneBookSize(){
    int size = 1; //set the size initially to one to account for the front node 
    ListNode current = front; 
    if (front == null){ // if there is no front node, the size is 0
      return 0; 
    }
    while(current.next != null){//keep adding to the size until we reach the end of the list 
      size += 1; 
      current = current.next; 
    }
    return size; //return size 
  } 
  
  //add contact to the end of the linked list 
  public void addContact(String firstName, String lastName, String homeAddress, String phoneNumber){
    String[] newContactInfo = {firstName, lastName, homeAddress, phoneNumber}; 
    if (front == null){ // if linked list is empty, we put the new node as front 
      initializeEmptyList(newContactInfo); 
    }else{
      addToEnd(newContactInfo); //if its not empty, we add the new node to the end 
    }
  }

  public void receiveNode(ListNode newNode){
    if (front == null){
      front = newNode; 
    }else{
      ListNode current = incrementCurrent(); 
      current.next = newNode; 
    }
  }

  //traverse linked list as long as current.next isn't null;
  private ListNode incrementCurrent(){
    ListNode current = front; 
    while(current.next != null){
      current = current.next; 
    }
    return current; 
  } 

  private void initializeEmptyList(String[] newContactInfo){
    front = new ListNode(newContactInfo); //new node as front 
  } 
  //add new node to the end of the linkedlist 
  private void addToEnd(String[] newContactInfo){
    ListNode current = incrementCurrent(); 
    current.next = new ListNode(newContactInfo); //add node to the end of linked list 
  }

  public void addContactToIndex(String firstName, String lastName, 
  String homeAddress, String phoneNumber, int placementIndex){
    //make an array of the contact information 
    String[] newContactInfo = {firstName, lastName, homeAddress, phoneNumber};
    if (placementIndex == 1){ //if index is one, we put to front. 
      addToFront(newContactInfo, placementIndex); 
    }else if (placementIndex > 1 && front != null
    && placementIndex <= getPhoneBookSize()){ // if index is more than one and 
      //linked list isnt empty, we add to desired index
      addToIndex(newContactInfo, placementIndex); 
    }else{ //if linkedlist is empty or the desired index is invalid, we either add to end 
      //or initialize empty list 
      addContact(firstName, lastName, homeAddress, phoneNumber); 
    }
  } 

  //add new node as the front, will set its .next as null if the linked list was initially empty 
  private void addToFront(String[] newContactInfo, int placementIndex){
    ListNode current = front; 
    front = new ListNode(newContactInfo, current); 
    
  }

  //add node to a specific index 
  private void addToIndex(String[] newContactInfo, int placementIndex){
    ListNode current = front; 
    for (int i = 0; i < placementIndex - 2; i++){ 
      current = current.next; 
    } 
    ListNode nodeAfterCurrent = current.next; 
    current.next = new ListNode(newContactInfo, nodeAfterCurrent); //set new node.next as the initial 
    //current node's .next. 
  }

  //remove contact node by name 
  public ListNode removeContactNode(String firstName, String lastName){
    if (front != null){ // if linked list isnt empty,  
      return removeContactNode(firstName, lastName, front); 
    } //remove contactNode by name 
    //do nothing if list is empty 
    return null; 
  }
  //remove contact node by its order number 
  public ListNode removeContactNode(int orderedNumber){
    if (front != null){ //remove if linked list isnt empty
      return removeContactNode(orderedNumber, front); 
    }
    //do nothing if linked list is empty 
    return null; 
  }

  private ListNode removeContactNode(String firstName, String lastName, ListNode current){
    if (currentMatches(front, firstName, lastName)){
      return removeFirstNode(); //if the first node is the one to be deleted, remove first node. 
    }else{
      //if it isnt, we search for the node using the parameters given
      current = searchNode(firstName, lastName);
      ListNode returnNode = removeNodeConfirmed(current); //remove the node if its found
      //no node is deleted if current is returned null 
      return returnNode; 
      
    }
  } 

  private ListNode removeContactNode(int orderedNumber, ListNode current){
    if (orderedNumber == 1){ //if first node is to be deleted, 
      //remove first node 
      return removeFirstNode(); 
    }else {
      //search for the node. 
      current = searchNode(orderedNumber);
      ListNode returnNode = removeNodeConfirmed(current); 
      //if current returns null, no node is deleted
      return returnNode; 
    }
  } 
  //make sure the node is found, if so, 
  //do the necessary steps to delete the node from the list
  private ListNode removeNodeConfirmed(ListNode current){
    ListNode toBeDeleted;
    ListNode afterDeletedNode; 
    if (current != null){ 
      toBeDeleted = current.next; 
      afterDeletedNode = toBeDeleted.next; 
      toBeDeleted.next = null; 
      current.next = afterDeletedNode; 
      return toBeDeleted; 
    }
    return null; 
  }
  //checks if the node matches the parameters given 
  private boolean currentMatches(ListNode current, String firstName, String lastName){
    if (current.getFirstName().toLowerCase().equals(firstName.toLowerCase()) 
    && current.getLastName().toLowerCase().equals(lastName.toLowerCase())){
      return true; 
    }
    return false; 
  } 

  //remove the first node of the linked list 
  private ListNode removeFirstNode(){
    ListNode current = front; 
    if (current.next != null){ //if there are more elements 
      //after the first, we just delete the first element 
      //and set the next element as the new front 
      front = current.next; 
      current.next = null; 
      return current; 
    }else{
      front = null; //delete the first element 
      return current; 
    }
  }

  //search for node using name
  private ListNode searchNode(String firstName, String lastName){
    ListNode current = front; 
    while(current.next != null){
      if (currentMatches(current.next, firstName, lastName)){
        return current;  //return the node previous to the wanted node 
      }else{
        current = current.next; //keep searching if havent yet found 
      }
    } 
    return null; //return null if it isnt found 
  }

  //search for node using order number 
  private ListNode searchNode(int orderedNumber){
    if (orderedNumber > 1 && orderedNumber <= getPhoneBookSize()){ 
      //if order number is not pertaining to the front node, 
      //and is within the size of the phonebook,
      //search for the node  
      ListNode current = front; 
      for (int i = 1; i<orderedNumber-1; i++){
        current = current.next; 
      }
      return current; //return the node right before the wanted node. 
    }
    return null; //return null if the order number is invalid. 
  }

  //modify the information of the node using order number 
  public void modifyNode(int orderedNumber, String firstName
  , String lastName, String homeAddress, String phoneNumber){
    if (orderedNumber == 1){ //if we want to change the first node's information, pass front as the argument 
      changeNodeInformation(front, firstName, lastName, homeAddress, phoneNumber); 
    }else{// else, we search for the node 
      ListNode current = searchNode(orderedNumber);//returns the node before the wanted node or returns null if not found 
      nodeChangeConfirmed(current, firstName, lastName, homeAddress, phoneNumber);//ignore if null, change if found 
    }
  } 

  public void modifyNode(String firstNameChanged, String lastNameChanged, String firstName, String lastName, 
  String homeAddress, String phoneNumber){
    if (currentMatches(front, firstNameChanged, lastNameChanged)){//pass front as the argument if we want to modify the first node 
      changeNodeInformation(front, firstName, lastName, homeAddress, phoneNumber);
    }else{//search for the node to be changed, 
      ListNode current = searchNode(firstNameChanged, lastNameChanged);//returns the node before the wanted node or returns null if not found 
      nodeChangeConfirmed(current, firstName, lastName, homeAddress, phoneNumber);//ignore if null, change if found 
    }
  } 
  //make sure, the node is found, and change it if it is. ignore if it wasn't found 
  private void nodeChangeConfirmed(ListNode current, String firstName, String lastName, String homeAddress, String phoneNumber){
    if (current != null){
      ListNode nodeToBeChanged; 
      nodeToBeChanged = current.next; //set node to be changed as the wanted node 
      changeNodeInformation(nodeToBeChanged, firstName, lastName, homeAddress, phoneNumber); 
    }
  }

  private void changeNodeInformation(ListNode nodeToChange, String firstName, String lastName, String homeAddress, String phoneNumber){
    nodeToChange.changeFirstName(firstName); //change node's first name 
    nodeToChange.changeLastName(lastName); //change node's last name 
    nodeToChange.changeAddress(homeAddress);// change node's homeAddress 
    nodeToChange.changePhoneNumber(phoneNumber);//change node's phone number 
  }

  //method to remove node and add to another book object using firstname and last name 
  public void moveNodeToAnotherBook(String firstName, String lastName, PhoneBookManager phoneBook){
    ListNode movedNode = removeContactNode(firstName, lastName); //remove node 
    if (movedNode != null){
      phoneBook.receiveNode(movedNode);// if successfully removed existing node, 
      //phoneBook object receives node 
    }
  } 

  //method to remove a node and add to another book using its order number 
  public void moveNodeToAnotherBook(int orderedNumber, PhoneBookManager phoneBook){
    ListNode movedNode = removeContactNode(orderedNumber); 
    if (movedNode != null){
      phoneBook.receiveNode(movedNode);
    }
  } 

  //sort the phoneBook list 
  public void insertionSortPhoneBook(){
    sorted = null; //initialize sorted as null 
    ListNode current = front; 
    while(current != null){
      ListNode next = current.next; 
      sortedInsert(current); 
      current = next; 
    }
    if (sorted != null){
      front = sorted; 
    }
  } 

  //insert the node to the appropriate index. 
  private void sortedInsert(ListNode newnode){
    //if sorted is empty and the new node isn't bigger than the current sorted value 
    if (sorted == null || !compareValues(sorted, newnode)){
      newnode.next = sorted; //insert newnode before sorted 
      sorted = newnode; //head becomes new node 
    }else{
      ListNode current = sorted; //if new node is bigger 
      while(current.next != null && compareValues(current.next, newnode)){
        current = current.next; //while next nodes are smaller, traverse 
      }
      newnode.next = current.next; //insert new node between current and current.next 
      current.next = newnode; 
    }

  } 


  //we want to check if newnode is bigger than current.next 
  private boolean compareValues(ListNode currentNode, ListNode newnode){
    String[] newNodeArray = newnode.getLastName().split(""); 
    String[] currentNodeArray = currentNode.getLastName().split("");
    int smallerArray = checkSmallerArray(newNodeArray, currentNodeArray);  
    for (int i=0; i < smallerArray; i++){
      int newNodeChar = charToNumber(newNodeArray[i]); 
      int currentNodeChar = charToNumber(currentNodeArray[i]); 
      if (newNodeChar > currentNodeChar){
        return true; 
      }else if (newNodeChar == currentNodeChar){
        continue; 
      }else{
        return false; 
      }
    }
    return false; 
  } 

  //check which array is smaller than the other 
  private int checkSmallerArray(String[] newNodeArray, String[] currentNodeArray){
    if (newNodeArray.length > currentNodeArray.length){
      return currentNodeArray.length; 
    }else{
      return newNodeArray.length; 
    }
  }

  //return a number for a specific letter 
  private int charToNumber(String character){
    String characterLowerString = character.toLowerCase(); 
    switch(characterLowerString){
      case "a": 
        return 1; 
      case "b":
        return 2;
      case "c":
        return 3; 
      case "d":
        return 4; 
      case "e":
        return 5;
      case "f":
        return 6;
      case "g":
        return 7;
      case "h":
        return 8;
      case "i":
        return 9;
      case "j":
        return 10;
      case "k":
        return 11;
      case "l":
        return 12;
      case "m":
        return 13;
      case "n":
        return 14;
      case "o":
        return 15;
      case "p":
        return 16;
      case "q":
        return 17;
      case "r":
        return 18;
      case "s":
        return 19;
      case "t":
        return 20;
      case "u":
        return 21;
      case "v":
        return 22;
      case "w":
        return 23;
      case "x":
        return 24;
      case "y":
        return 25;
      case "z":
        return 26;  
    } 
    return 0; 
  } 

  //retrieve object's name 
  public String getName(){
    return this.name; 
  } 

  //display each node in the linked list. 
  public String toString(){
    ListNode current = front; 
    String displayPhoneBook = "";
    if (current != null){
      for (int i = 0; i< getPhoneBookSize(); i++){
        displayPhoneBook += (i+1) + ") " + current; 
        displayPhoneBook += "\n";   
        current = current.next; 
      }
    } else{
      return "empty"; 
    }
    return displayPhoneBook; 
  }
}
