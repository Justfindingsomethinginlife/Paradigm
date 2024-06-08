/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project1_6681012;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Reimbursement
{
    private static String fileName = "reimbursements.txt", path="src/main/java/Project1/";
    
    private static final int s_limit_travel, s_limit_mobile, c_limit_travel, c_limit_mobile;    
    
    public static void testvalue()
    {
        
    }
    
    public static int getSalary_Travel_Limit()     {return s_limit_travel;}
    public static int getSalary_Mobile_Limit()     {return s_limit_mobile;}
    public static int getCommision_Travel_Limit()  {return c_limit_travel;}
    public static int getCommision_Mobile_Limit()  {return c_limit_mobile;}
    
    static 
    {   
        Boolean file_failure = false;
        Scanner key = new Scanner(System.in);
        int [] temp = new int[4];
        
        while (!file_failure)
        {
            try (
                Scanner fscan = new Scanner(new File("src/main/java/Project1_6681012/" + fileName));
            ){  
                System.out.println("Read from " + new File("src/main/java/Project1_6681012/" + fileName).getPath());
                fscan.nextLine();
                file_failure = true;
                String l = fscan.nextLine();
                String [] col = l.split(",");
                if(col[0].trim().equals("c"))
                {
                    temp[0] = Integer.parseInt(col[1].trim());
                    temp[1] = Integer.parseInt(col[2].trim());
                    l=fscan.nextLine();
                    col = l.split(",");
                    temp[2] = Integer.parseInt(col[1].trim());
                    temp[3] = Integer.parseInt(col[2].trim());
                }
                else if(col[0].trim().equals("s"))
                {
                    temp[2] = Integer.parseInt(col[1].trim());
                    temp[3] = Integer.parseInt(col[2].trim());
                    l=fscan.nextLine();
                    col = l.split(",");
                    temp[0] = Integer.parseInt(col[1].trim());
                    temp[1] = Integer.parseInt(col[2].trim());
                }                
            }
            catch (FileNotFoundException e) 
            {
                System.out.print(e + " --> ");
                System.out.println("New file name = ");
                fileName = key.next();
                System.out.println();
            }
        }
        c_limit_travel=temp[0];
        c_limit_mobile=temp[1];
        s_limit_travel=temp[2];
        s_limit_mobile=temp[3];
        
        System.out.printf("%-15s %s = %,6d     %s = %,6d\n","Commision","travel limit",getCommision_Travel_Limit(),"mobile limit",getCommision_Mobile_Limit());
        System.out.printf("%-15s %s = %,6d     %s = %,6d\n","Salary+","travel limit",getSalary_Travel_Limit(),"mobile limit",getSalary_Mobile_Limit());  
        System.out.println();
    }
    
}

