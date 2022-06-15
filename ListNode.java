public class ListNode {
  private String firstName; 
  private String lastName; 
  private String homeAddress;
  private String phoneNumber; 
  protected ListNode next; 

  public ListNode(String[] newContactInfo, ListNode nextNode){
    this.firstName = newContactInfo[0]; 
    this.lastName = newContactInfo[1];
    this.homeAddress = newContactInfo[2];
    this.phoneNumber = newContactInfo[3];
    this.next = nextNode; 
  } 

  public ListNode(String[] newContactInfo){
    this.firstName = newContactInfo[0]; 
    this.lastName = newContactInfo[1];
    this.homeAddress = newContactInfo[2];
    this.phoneNumber = newContactInfo[3];
    this.next = null; 
  } 

  public String getLastName(){
    return this.lastName; 
  } 

  protected void changeLastName(String lastName){
    if(!lastName.toLowerCase().startsWith("n")){
      this.lastName = lastName; 
    }
  }

  public String getFirstName(){
    return this.firstName; 
  }

  protected void changeFirstName(String firstName){
    if(!firstName.toLowerCase().startsWith("n")){
      this.firstName = firstName; 
    }
  } 

  public String getPhoneNumber(){
    return this.phoneNumber; 
  } 

  public String getHomeAddress(){
    return this.homeAddress; 
  } 

  protected void changePhoneNumber(String phoneNumber){
    if(!phoneNumber.toLowerCase().startsWith("n")){
      this.phoneNumber = phoneNumber; 
    }
  }

  protected void changeAddress(String homeAddress){
    if(!homeAddress.toLowerCase().startsWith("n")){
      this.homeAddress = homeAddress; 
    }
  }

  public String toString(){
    String nodeInformation = String.format("%-15s, %-15s, %-30s, %-10s", lastName, firstName, homeAddress, phoneNumber);
    return nodeInformation; 
  }
}
