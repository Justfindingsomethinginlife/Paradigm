/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Product
{
    private String code, name, highest_sales;
    private int unit_price, flat_comm, q1, q2, q3, q4, sales;
    
    public Product(String code, String name, int unit_price)             {this.code=code;this.name=name;this.unit_price = unit_price;}

    
    public String getCode()                         {return code;}
    public String getName()                         {return name;}
    public int getUnit_price()                      {return unit_price;}
    public int getFlat_comm()                       {return flat_comm;}
    public int getQ1()                              {return q1;}
    public int getQ2()                              {return q2;}
    public int getQ3()                              {return q3;}
    public int getQ4()                              {return q4;}
    public int getSales()                           {return sales;}
    
    public void setUnit_price(int unit_price)       {this.unit_price=unit_price;}
    public void setFlat_comm(int flat_comm)         {this.flat_comm=flat_comm;}
    public void setQ1(int q1)                       {this.q1=q1;}
    public void setQ2(int q2)                       {this.q2=q2;}
    public void setQ3(int q3)                       {this.q3=q3;}
    public void setQ4(int q4)                       {this.q4=q4;}
    public void setSales(int sales)                 {this.sales=sales;}        
}

class ProductList
{   
    private String path="src/main/java/Project1/", filename=null; //Project1 should be change to Project1_XXX
    
    private ArrayList<Product> products = new ArrayList<>();
    
    public ArrayList<Product> getProductList() {return products;}
        
    public ProductList(String path, String filename)                         
    {
        this.path=path;
        this.filename=filename;
        Boolean file_failure=false;
        
        Scanner fscan = null;
        Scanner keyboard = new Scanner(System.in);
                
        while(!file_failure)
        {
            try
            {
                File fp = new File(path + filename);
                fscan = new Scanner(fp);
                fscan.nextLine();
                file_failure = true;
            }
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("New file name = ");
                filename = keyboard.nextLine();
            }
        }
        
        while(fscan.hasNext())
        {
            try
            {
                file_failure = true;
                String line = fscan.nextLine();
                String [] col = line.split(",");
                Product temp = new Product(col[0].trim(),col[1].trim(),Integer.parseInt(col[2].trim()));
                        temp.setFlat_comm( Integer.parseInt(col[3].trim()) );
                        temp.setQ1( Integer.parseInt(col[4].trim()) );
                        temp.setQ2( Integer.parseInt(col[5].trim()) );
                        temp.setQ3( Integer.parseInt(col[6].trim()) );
                        temp.setQ4( Integer.parseInt(col[7].trim()) );
                
                products.add(temp);
            }
            catch(Exception e)
            {   
                e.printStackTrace();
            }
        }
        fscan.close();
    }
    
    public ProductList(String filename)                         
    {
        this.filename=filename;
        
        Boolean file_failure=false;
        String path ="src/main/java/Project1/";
        
        Scanner fscan = null;
        Scanner keyboard = new Scanner(System.in);
                
        while(!file_failure)
        {
            try
            {
                File fp = new File("src/main/java/Project1/" + filename);
                fscan = new Scanner(fp);
                fscan.nextLine();
                file_failure = true;
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("New file name = ");
                filename = keyboard.nextLine();
            }
        }
        
        while(fscan.hasNext())
        {
            try
            {
                file_failure = true;
                String line = fscan.nextLine();
                String [] col = line.split(",");
                Product temp = new Product(col[0].trim(),col[1].trim(),Integer.parseInt(col[2].trim()));
                        temp.setFlat_comm( Integer.parseInt(col[3].trim()) );
                        temp.setQ1( Integer.parseInt(col[4].trim()) );
                        temp.setQ2( Integer.parseInt(col[5].trim()) );
                        temp.setQ3( Integer.parseInt(col[6].trim()) );
                        temp.setQ4( Integer.parseInt(col[7].trim()) );
                
                products.add(temp);
            }
            catch(Exception e)
            {   
                e.printStackTrace();
            }
        }
        fscan.close();
    }
    
    public void DisplayProduct()
    {   
        System.out.printf("%-5s %-25s %10s %10s %10s %10s %10s %10s \n","Code,","name,","unit price,","flat comm,","q1 comm,","q2 comm,","q3 comm,","q4 comm,");
        for(int i=0;i<products.size();i++)
        {
            System.out.printf("%-5s %-25s %6s %6s %6s %6s %6s %6s \n", products.get(i).getCode(), products.get(i).getName(), products.get(i).getUnit_price(), products.get(i).getFlat_comm(), products.get(i).getQ1(), products.get(i).getQ2(), products.get(i).getQ3(), products.get(i).getQ4());
        }
    }

}