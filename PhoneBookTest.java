//Programmers: Tsaqif Insani, Gracia Winata 
//Date: 05/13/2022 
//CS 145
//PhoneBookManager Assignment2
//This program is for us to familiarize ourselves with linked lists. PhoneBookManager.java 
// and ListNode.java are the relevant linked list classes 
//PhoneBookTest.java is just for testing those two classes 
//Notes: 
//     PhoneBookTest.java contains many hard coded test cases, still with a user interface 
//     to make the overall code simple and more focused on the classes relevant to the linked list. 
//     Keep in mind that PhoneBookTest.java is not foolproof for every userInput because its purpose is only 
//     to showcase the functionality of the PhoneBookManager.java class and ListNode.java class 
//     Having said that, PhoneBookManager.java has addContact(), addContactToIndex(), removeContactNode(), 
//     modifyNode(), moveNodeToAnotherBook(), InsertionSortPhoneBook(), toString(), as its methods, 
//     meeting all the requirements. We used insertion sort for the sorting algorithm. We used git as our 
//     version control system, and visual studio code as our IDE. We strived to make the code in 
//     PhoneBookManager.java and ListNode.java as readable and clean to the best of our abilities, 
//     making the functions as small as we could. There are duplicate methods in PhoneBookManager.java 
//     so the user can search for a node either with its first and last name or its order number. 



import java.util.Scanner; 


public class PhoneBookTest{
  public static void main(String[] args){
    PhoneBookManager Seattle = initializeSeattle(); 
    PhoneBookManager Bellingham = initializeBellingham(); 
    while(true){
      menuInstructions(Seattle, Bellingham); 
    }
  }

  public static String showPhoneBookList(){
    return "Seattle, Bellingham"; 
  } 

  //hard code contact into bellingham phonebook manager 
  public static PhoneBookManager initializeBellingham(){
    PhoneBookManager Bellingham = new PhoneBookManager("Bellingham"); 
    Bellingham.addContact("Tsaqif", "Insani", "204 Olivine Lane 98226", "2065819973");
    Bellingham.addContactToIndex("Rangganatha", "Siregar", "204 Olivine Lnae 98226", "20658128391", 1);
    Bellingham.addContactToIndex("Nabil", "Dipo", "204 Olivine Lane 98226", "2061928319827", 3); 
    Bellingham.addContact("Bilal", "Dipo", "204 Olivine Lane 98226", "206102973");  
    return Bellingham; 
  }

  //hard code contact into Seattle phonebook manager 
  public static PhoneBookManager initializeSeattle(){
    PhoneBookManager Seattle = new PhoneBookManager("Seattle"); 
    Seattle.addContact("Abi", "rama", "Seattle 202", "2065819973");
    Seattle.addContactToIndex("Jausha", "Winayla", "Seattle", "20658128391", 1);
    Seattle.addContactToIndex("Mike", "Anugrah", "UW", "2061928319827", 3); 
    Seattle.addContact("Bilal", "Anindya", "UW", "206102973");  
    return Seattle; 
  }

  //print out instructions for the user interface menu 
  public static void menuInstructions(PhoneBookManager Seattle, PhoneBookManager Bellingham){
    System.out.println("Welcome to your phonebook!"); 
    System.out.println("did you want to see (s), or change/add to a phonebook? (c)"); 
    Scanner input = new Scanner(System.in); 
    String userInput = input.nextLine(); 
    System.out.print("\033[H\033[2J");
    checkUserInput(userInput, Seattle, Bellingham); 
  }

  //checks what the user inptuted 
  public static void checkUserInput(String userInput, PhoneBookManager Seattle, PhoneBookManager Bellingham){
    String cleanedUserInput = userInput.trim().toLowerCase();
    switch(cleanedUserInput){
      case "s":
        System.out.println("Here are the phone books available right now: ");
        System.out.println(showPhoneBookList()); 
        //print out the phoneBook if user wants to see 
        seePhoneBook(Seattle, Bellingham);
        break; 
      case "c":
        System.out.println("Here are the phone books available right now: ");
        System.out.println(showPhoneBookList()); 
        //modify phone book if user wants to make changes 
        modifyPhoneBook(Seattle, Bellingham); 
        break;
      default: 
        return; 
    }
  }

  //ask which phoneBook the user wants to see 
  public static void seePhoneBook(PhoneBookManager Seattle, PhoneBookManager Bellingham){
    System.out.println("Please enter the name of the phonebook: ");
    System.out.println(checkIfBellinghamOrSeattle(Seattle, Bellingham)); 
  } 

  //check if its either Bellingham or Seattle, if not, return null 
  public static PhoneBookManager checkIfBellinghamOrSeattle(PhoneBookManager Seattle, PhoneBookManager Bellingham){
    Scanner input = new Scanner(System.in);
    String userInput = input.nextLine();
    if(userInput.trim().toLowerCase().equals("bellingham")){
      return Bellingham; 
    }else if(userInput.trim().toLowerCase().equals("seattle")){
      return Seattle; 
    }else{
      System.out.println("no such phonebook exists, only -> (Seattle, Bellingham)");
    }
    return null; 
  }


  //if user wants to modify, ask for the phoneBook object they want to modify
  public static void modifyPhoneBook(PhoneBookManager Seattle, PhoneBookManager Bellingham){
    System.out.println("Please enter the name of the PhoneBook you want to modify");
    PhoneBookManager bookObject = checkIfBellinghamOrSeattle(Seattle, Bellingham); 
    System.out.println(bookObject);
    processChangeBook(bookObject, Seattle, Bellingham);
  } 

  public static void processChangeBook(PhoneBookManager bookObject, PhoneBookManager Seattle, PhoneBookManager Bellingham){
    Scanner input = new Scanner(System.in);
    boolean continueAsk = true; 
    while(continueAsk){
      System.out.println("Here are the things you can do to modify your phonebook: ");
      System.out.println("\t Add a contact to the end of the phoneBook (a)");
      System.out.println("\t Add a contact to an index of the phoneBook (ai)");
      System.out.println("\t remove a contact from a phone book (r)");
      System.out.println("\t modify a contact (m)");
      System.out.println("\t sort the phone book (s)"); 
      System.out.println("\t move a contact to another phone book (mc)"); 
      System.out.println("\t type in (stop) once you're done modifying"); 

      System.out.println("your choice: ");
      String userInput = input.nextLine(); 
      continueAsk = processUserInput(userInput, bookObject, Seattle, Bellingham); 

    } 
  }

  public static boolean processUserInput(String userInput, PhoneBookManager bookObject, PhoneBookManager Seattle, PhoneBookManager Bellingham){
    switch(userInput){
      case "a":
        addContact(bookObject); 
        return true; 
      case "ai":
        addContactToIndex(bookObject); 
        return true; 
      case "r":
        removeContact(bookObject); 
        return true; 
      case "m":
        modifyContact(bookObject); 
        return true; 
      case "s":
        sortPhoneBook(bookObject); 
        return true; 
      case "mc":
        moveContactNode(bookObject, Seattle, Bellingham);
        return true; 
      case "stop":
        return false; 
      default: 
        return true; 
    }
  }

  //add contact to the bookObject 
  public static void addContact(PhoneBookManager bookObject){
    Scanner input = new Scanner(System.in); 
    System.out.println("You've chosen to add a contact:");
    System.out.println("Please enter the new contact's first name: ");
    String firstName = input.next(); 
    System.out.println("Please enter the new contact's last name: ");
    String lastName = input.next(); 
    input.nextLine(); 
    System.out.println("Please enter the new contact's home address: ");
    String homeAddress = input.nextLine(); 
    System.out.println("Please enter the new contact's phone number: ");
    String phoneNumber = input.nextLine(); 
    bookObject.addContact(firstName, lastName, homeAddress, phoneNumber);
    System.out.println("Contact added!"); 
    System.out.println(bookObject); 
  }

  //add contact to a specified index 
  public static void addContactToIndex(PhoneBookManager bookObject){
    Scanner input = new Scanner(System.in); 
    System.out.println("You've chosen to add a contact to a specific order number:");
    System.out.println("Please enter the new contact's first name: ");
    String firstName = input.next(); 
    System.out.println("Please enter the new contact's last name: ");
    String lastName = input.next(); 
    input.nextLine(); 
    System.out.println("Please enter the new contact's home address: ");
    String homeAddress = input.nextLine(); 
    System.out.println("Please enter the new contact's phone number: ");
    String phoneNumber = input.nextLine(); 
    System.out.println("Please which nth row you want to put your contact in:");
    int placementIndex = input.nextInt(); 
    bookObject.addContactToIndex(firstName, lastName, homeAddress, phoneNumber, placementIndex);
    System.out.println("Contact added!"); 
    System.out.println(bookObject); 
  }

  //remove contact with either first name and last name or its order number 
  public static void removeContact(PhoneBookManager bookObject){
    Scanner input = new Scanner(System.in);
    System.out.println("You've chosen to remove a contact");
    System.out.println("Please enter either the contact's row number or the contact's full name with a space between the first and last name"); 
    String userInput = input.nextLine(); 
    if (isNumber(userInput)){
      bookObject.removeContactNode(Integer.parseInt(userInput));
    }else{
      String[] firstNameLastName = userInput.split(" ", 2); 
      bookObject.removeContactNode(firstNameLastName[0], firstNameLastName[1]); 
    }
    System.out.println("contact removed!"); 
    System.out.println(bookObject); 
  }  

  //modify a specified node 
  public static void modifyContact(PhoneBookManager bookObject){
    Scanner input = new Scanner(System.in);
    System.out.println("You've chosen to modify a contact");
    System.out.println("Please enter either the contact's row number or the contact's full name with a space between the first and last name"); 
    String userInput = input.nextLine(); 
    System.out.println("type 'no' or 'none' if you don't want to modify a specific field when asked");
    System.out.println("Please enter the contact's new first name: ");
    String firstName = input.next(); 
    System.out.println("Please enter the new contact's new last name: ");
    String lastName = input.next(); 
    input.nextLine(); 
    System.out.println("Please enter the new contact's new home address: ");
    String homeAddress = input.nextLine(); 
    System.out.println("Please enter the new contact's new phone number: ");
    String phoneNumber = input.nextLine(); 
    if (isNumber(userInput)){
      bookObject.modifyNode(Integer.parseInt(userInput), firstName, lastName, homeAddress, phoneNumber);
    }else{
      String[] firstNameLastName = userInput.split(" ", 2); 
      bookObject.modifyNode(firstNameLastName[0], firstNameLastName[1], firstName, lastName, homeAddress, phoneNumber); 
    }
    System.out.println("contact modified!"); 
    System.out.println(bookObject); 
  }  

  //sort the phone book 
  public static void sortPhoneBook(PhoneBookManager bookObject){
    System.out.println("You've chosen to sort your phoneBook");
    System.out.println("phone book sorted!");
    bookObject.insertionSortPhoneBook();
    System.out.println(bookObject); 
  }

  //move a node from one phonebook to another; 
  public static void moveContactNode(PhoneBookManager bookObject, PhoneBookManager Seattle, PhoneBookManager Bellingham){
    Scanner input = new Scanner(System.in); 
    System.out.println("You've chosen to move a contact");
    System.out.println("Please enter either the contact's row number or the contact's full name with a space between the first and last name"); 
    String userInput = input.nextLine(); 

      if (isNumber(userInput)){; 
        if (bookObject == Bellingham){
          bookObject.moveNodeToAnotherBook(Integer.parseInt(userInput), Seattle);
        }else if (bookObject == Seattle){
          bookObject.moveNodeToAnotherBook(Integer.parseInt(userInput), Bellingham);
        }
      }else{
        if (bookObject == Bellingham){
          String[] firstNameLastName = userInput.split(" ", 2); 
          bookObject.moveNodeToAnotherBook(firstNameLastName[0], firstNameLastName[1], Seattle);
        }else if(bookObject == Seattle){
          String[] firstNameLastName = userInput.split(" ", 2); 
          bookObject.moveNodeToAnotherBook(firstNameLastName[0], firstNameLastName[1], Bellingham);
        }
      }
      System.out.println("Contact moved!");
      System.out.println(bookObject); 
      System.out.println("==============================="); 
      if (bookObject == Bellingham){
        System.out.println(Seattle);  
      }else if(bookObject == Seattle){
        System.out.println(Bellingham);  
      }
    

  }

  public static boolean checkIfPhoneBookExists(String userInput){
    if(userInput.trim().toLowerCase().equals("bellingham")){
      return true; 
    }else if(userInput.trim().toLowerCase().equals("seattle")){
      return true; 
    }else{
      System.out.println("no such phonebook exists, only -> (Seattle, Bellingham)");
    }
    return false; 
  } 

  public static boolean isNumber(String userInput){
    try{
      int number = Integer.parseInt(userInput); 
      return true; 
    }catch(Exception e){
      return false; 
    }
  }
}