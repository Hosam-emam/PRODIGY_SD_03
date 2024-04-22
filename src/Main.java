import Files.MyFile;
import Files.User;
import Files.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> promptData(){
        ArrayList<String> data = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Contact's Name: ");
        data.add(scan.nextLine());
        System.out.print("His Phone Number: ");
        data.add(scan.nextLine());
        System.out.print("His Email: ");
        data.add(scan.nextLine());
        return data;
    }
    public static void main(String[] args) {
        User user = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlease, Choose which operation you wish to perform.");
        int option;
        do {
            System.out.print("(1) -> Add Contact, (2) -> View Contact List, (3) -> Edit Contact, (4) -> Delete Contact, (Any Other Number) -> exit: ");
            try{
                option = Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Please, stick with the required input!");
                option = 1;
                continue;
            }
            switch (option) {
                case 1: {
                    ArrayList<String> data = promptData();
                    System.out.println(user.addContact(data));
                    break;
                }
                case 2: {
                    user.viewContactList();
                    break;
                }
                case 3: {
                    System.out.print("Enter the contacts Phone Number: ");
                    user.editContact(sc.nextLine());
                    break;
                }
                case 4: {
                    System.out.print("Enter the contacts Phone Number: ");
                    user.deleteContact(sc.nextLine());
                    break;
                }
                default:{
                    System.out.println("Have a nice day!");
                }
            }
        }while(option > 0 && option < 5);
    }
}