package toystopinventorymanagementsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Scanner;  

/**
 *
 * @author Fahad Satti
 */
public class ToyStopInventoryManagementSystem {
    ToyStopService tsService = new ToyStopService();
    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        System.out.println("Loading Data Please wait...");  

        tsims.loadData(tsims);

        //load previous data
        //tsims.loadData();
        //tsims.loadData();
        
        //tsims.showMenu();
        tsims.showMenu(tsims);
        //tsims.printAll();
        tsims.saveData();
    }

    private void loadData(ToyStopInventoryManagementSystem tsims) throws IOException, ClassNotFoundException {
        
        try{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("data.txt"));  
        tsService=(ToyStopService)in.readObject();  
        System.out.println("Local Data Loaded");  
  
        in.close();}
        catch(IOException x){}
        catch(ClassNotFoundException x){
            tsims.init();
            System.out.println("Local Data Not found Initializing with random data");  

        }
        finally{
            if (tsService.stores.size() <= 0 || tsService.employees.size() <= 0){
                tsims.init();
                System.out.println("Local Data Not found Initializing with random data"); 
            }
        }

        
    }
    
    private void saveData() throws FileNotFoundException, IOException {
        
        FileOutputStream fout=new FileOutputStream("data.txt");  
        ObjectOutputStream out=new ObjectOutputStream(fout);  
  
        out.writeObject(tsService);  
        out.flush();  
        System.out.println("success");  
    }

    private void showMenu(ToyStopInventoryManagementSystem tsims) {
        int choice = 1;
        while(choice != 0){
            System.out.println("Welcome to Toy Stop Inventory Management System");
            System.out.println("Enter 1 to show all data");
            System.out.println("Enter 2 to add a new Store");
            System.out.println("Enter 3 to add a new Employee");
            System.out.println("Enter 4 to add a new Toy");
            System.out.println("Enter 0 to save state");
        
            Scanner sc=new Scanner(System.in); 
        
            choice=sc.nextInt();
         
            if(choice == 1){
                tsims.printAll();
            }else if(choice == 2){
            
            tsService.addStore();
            System.out.println("Store Created");  

            }else if(choice == 3){
                 tsService.addEmployee();
                             System.out.println("Emplyee Registered");  

            }else if(choice == 4){
             tsService.addToy();
             System.out.println("Toy Inserted"); 
            
            }else if(choice == 0){
                System.out.println("Saving Data Please wait");  
            }
         
        }
    }

    private void printAll() {
        System.out.println(this.tsService.stores);
    }
    
}
