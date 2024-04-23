package Files;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private Scanner sc = new Scanner(System.in);
    private MyFile myfile = new MyFile();
    private ArrayList<String> data = new ArrayList<>();
    public User(){

    }
    public String addContact(ArrayList<String> data){
        if(this.myfile.saveContact(data)){
            return "File Saved Successfully!";
        }
        return "The Contact couldn't be saved";
    }
    public void viewContactList(){
        this.data = this.myfile.viewFiles();
        for(String s : this.data){
            System.out.println(s);
        }
        this.data.clear();
    }
    public void editContact(String phoneNumber){
        int file_number = this.myfile.search(phoneNumber);
        if(file_number > 0 && file_number <= this.myfile.getNo_of_files()){
            System.out.println("Please, fill in the new contact's information.");
            System.out.print("New Name: ");
            this.data.add(this.sc.nextLine());
            String phone_number;
            System.out.println("Please, enter an 11 digit number");
            do{
                System.out.print("New Phone Number: ");
                phone_number = this.sc.nextLine();
            }while(phone_number.length() != 11);
            this.data.add(phoneNumber);
            String email;
            System.out.println("Enter a formal email address containing (@) and (.com)");
            do{
                System.out.print("New Email: ");
                email = this.sc.nextLine();
            }while(!email.contains("@") || !email.contains(".com"));
            this.data.add(email);
            if(this.myfile.editFile(file_number,this.data)){
                System.out.println("The file has been updated successfully!");
            }else{
                System.err.println("Error: The File Couldn't be updated!");
            }

        }else{
            System.out.println("This contact doesn't exist!");
        }
        this.data.clear();
    }
    public void deleteContact(String phoneNumber){
        int file_number = this.myfile.search(phoneNumber),lastFile = this.myfile.getNo_of_files();
        if(file_number <= lastFile && file_number > 0){
            if(this.myfile.deleteFile(file_number,lastFile)){
                System.out.println("File deleted successfully!");
            }else{
                System.out.println("Failed to delete the contact!");
            }
        }else{
            System.out.println("This contact doesn't exist!");
        }
    }
}
