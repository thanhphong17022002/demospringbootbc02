
package data;

import java.util.Date;
import java.util.Scanner;
 
public class MyToys {
     Scanner sc = new Scanner(System.in);
    public String getString(String str){   
        System.out.print(str);
        String n = sc.nextLine();
        return n;
    }
  
    public double getAnDouble(String msg){
     double p;
        do {            
            try {
                System.out.print(msg);
                p = Double.parseDouble(sc.nextLine());
                return p;
            } catch (Exception e) {
                System.out.println("INPUT AGAIN !!! ");
            }
        } while (true);
    }
    
    
    
    public int getAnInt(String msg){
        int p;
        do {      
            try {
                System.out.print(msg);
                p = Integer.parseInt(sc.nextLine());
                return p;
            } catch (Exception e) {
                System.out.println("INPUT AGAIN !!!");
            }
            
        } while (true);
    }
    
  
}
