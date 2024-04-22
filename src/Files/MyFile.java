package Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFile {
    private ArrayList<String> data = new ArrayList<>();
    private File file;
    private FileWriter fw;
    private Scanner fr;
    MyFile(){

    }
    public boolean saveContact(ArrayList<String> data){
        this.file = new File("Contacts/Contact " + (getNo_of_files()+1)+ ".txt");
        FileWriter fw;
        try{
            fw = new FileWriter(this.file,true);
            for(String i : data){
                fw.write(i+"\n");
            }
            fw.close();
            return true;
        }catch(java.io.IOException e){
            return false;
        }
    }
    public ArrayList<String> viewFiles(){
        this.data.add("The number of Contacts you have: " + getNo_of_files() + "\n==================");

        String Directory = "Contacts/";
        this.file = new File(Directory);
        File[] files = this.file.listFiles();
        if(files == null){
            System.err.println("Error: The specified path is not a directory.");
        }else{
            for(File i : files){
                try{
                    this.fr = new Scanner(i);
                    while(this.fr.hasNext()){
                        this.data.add(this.fr.nextLine());
                    }
                    this.data.add("==================");
                    this.fr.close();
                }catch(java.io.FileNotFoundException e){
                    System.err.println("Error: The file is not found!");
                }
            }
        }
        return this.data;
    }
    public int search(String number){

        String dir = "Contacts/";
        this.file = new File(dir);
        File[] files = this.file.listFiles();
        if(files == null){
            System.out.println("Error: The specified path is not a directory.");
            return -1;
        }else{
            for(File i : files){
                try{
                    this.fr = new Scanner(i);
                    this.fr.nextLine();
                    String phone = fr.nextLine();
                    if(phone.equals(number)){
                        this.fr.close();
                        String f = i.toString();
                        f = f.replace("Contacts\\Contact ","");
                        f = f.replace(".txt" ,"");
                        return Integer.parseInt(f);
                    }
                }catch(java.io.FileNotFoundException e){
                    System.err.println("Error: the file is not found!");
                }
            }
        }
        this.fr.close();
        return -2;
    }
    public boolean editFile(int file_num,ArrayList<String> newdata){
        this.file = new File("Contacts\\Contact " + file_num +".txt");
        try{
            this.fw = new FileWriter(this.file,false);
            for(String data : newdata){
                this.fw.write(data+ "\n");
            }
           this.fw.close();
        }catch(java.io.IOException e){
            System.err.println("Error: Couldn't update the file");
            return false;
        }
        return true;
    }

    public boolean deleteFile(int num,int lastFileNum){
        if(lastFileNum == 1){
            this.file = new File("Contacts\\Contact 1.txt");
            this.file.delete();
            return true;
        }
        else{
            for(int i = num; i < lastFileNum; i++){
                this.file = new File("Contacts\\Contact " + i + ".txt");
                File nextFile = new File("Contacts\\Contact " + (i+1) + ".txt");
                try{
                    this.fr = new Scanner(nextFile);
                    while(this.fr.hasNextLine()){
                        this.data.add(this.fr.nextLine());
                    }
                    this.fr.close();
                }catch(java.io.FileNotFoundException e){
                    return false;
                }
                    try {
                        this.fw = new FileWriter(this.file,false);
                        for(String s : this.data){
                            fw.write(s+"\n");
                        }
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("Error: Couldn't transfer data between files");;
                        return false;
                    }
                    this.data.clear();
            }
            this.file = new File("Contacts\\Contact " +lastFileNum + ".txt");
            this.file.delete();
            return true;
        }
    }
    public int getNo_of_files(){
        String myDirectoryPath = "Contacts/"; // Replace with the actual directory path
        File dir = new File(myDirectoryPath);
        File[] directoryListing = dir.listFiles();
        int cnt = 0;
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with each file (e.g., print the file name)
                cnt++;
            }
        } else {
            // Handle the case where dir is not a valid directory
            System.err.println("Error: The specified path is not a directory.");
        }
        return cnt;
    }
}
