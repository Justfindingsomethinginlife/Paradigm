/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Project1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Project1.Product;
import Project1.Reimbursement;

/**
 *
 * @author ACER
 */

class InvalidFormatException extends Exception
{			
    public InvalidFormatException(String message)	{ super(message); }
};

class SalesPersonList
{
    private ArrayList<SalesPerson> SalesPersonList = new ArrayList<>();
    
    private Boolean check(ProductList products, String code) //Should be implimented with Trie for a large dataset
    {   
        ArrayList<Product> product = products.getProductList();
        for(Product product_temp : product)
        {
            if(product_temp.getCode().equals(code)) return true;
        }
        return false;
    }
    
    private int sales_exist(String name) //Should be implimented with Trie for a large dataset
    {   
        for(int i=0;i<SalesPersonList.size();i++)
        {
            if(SalesPersonList.get(i).getName().equals(name)) return i;
        }
        return -1;
    }
    
    private void ProcessLine(String l, ProductList products) throws InvalidFormatException
    {
        String [] col = l.split(",");
                
            if(!(col[0].trim().equals("s") || col[0].trim().equals("c")))throw new InvalidFormatException("For input: \"" + col[0].trim() + "\"");
            else if(!check(products, col[2].trim()))throw new InvalidFormatException("For input: \"" + col[2].trim() + "\"");
            else if(Integer.parseInt(col[3].trim()) < 0)throw new InvalidFormatException("For input: \"" + col[3].trim() + "\"");
            else if(Integer.parseInt(col[4].trim()) < 0)throw new InvalidFormatException("For input: \"" + col[4].trim() + "\"");
            else if(Integer.parseInt(col[5].trim()) < 0)throw new InvalidFormatException("For input: \"" + col[5].trim() + "\"");
            else if(Integer.parseInt(col[6].trim()) < 0)throw new InvalidFormatException("For input: \"" + col[6].trim() + "\"");
            else if(col[0].trim().equals("s"))if(Integer.parseInt(col[7].trim()) < 0)throw new InvalidFormatException("For input: \"" + col[7].trim() + "\"");
            
            if(col[0].trim().equals("s"))
            {
                SalesPerson temp_sales = new SalesPerson(col[0].trim(),col[1].trim());
                temp_sales.setProduct(col[2].trim());
                temp_sales.setQ1(Integer.parseInt(col[3].trim()));
                temp_sales.setQ2(Integer.parseInt(col[4].trim()));
                temp_sales.setQ3(Integer.parseInt(col[5].trim()));
                temp_sales.setQ4(Integer.parseInt(col[6].trim()));
                temp_sales.setSalary(Integer.parseInt(col[7].trim()));

                SalesPersonList.add(temp_sales);
            }
            else if(col[0].trim().equals("c"))
            {
                SalesPerson temp_sales = new SalesPerson(col[0].trim(),col[1].trim());
                temp_sales.setProduct(col[2].trim());
                temp_sales.setQ1(Integer.parseInt(col[3].trim()));
                temp_sales.setQ2(Integer.parseInt(col[4].trim()));
                temp_sales.setQ3(Integer.parseInt(col[5].trim()));
                temp_sales.setQ4(Integer.parseInt(col[6].trim()));

                SalesPersonList.add(temp_sales);
            }
            
    }
    public void DisplaySales()
    {
        for(int i=0;i<SalesPersonList.size();i++)
        {
            System.out.printf("%-10s %-10s %4d %4d %4d %4d %8d %8d %8d\n",SalesPersonList.get(i).getName() , SalesPersonList.get(i).getType() , SalesPersonList.get(i).getQ1() , SalesPersonList.get(i).getQ2() , SalesPersonList.get(i).getQ3() , SalesPersonList.get(i).getQ4(),SalesPersonList.get(i).getSalary(),SalesPersonList.get(i).getTravel(),SalesPersonList.get(i).getMobile());
        }
    }
    
    public void parseSales(String path, SalesPersonList Sales, ProductList products)    //more efficient with Trie for large number of sales persons or Product reduce to O(k) k be length of name needed
    {
        Boolean file_failure=false;
        
        Scanner fscan = null;
        Scanner keyboard = new Scanner(System.in);
        
        String expense_file = "expenses.txt";
        String filename = "salespersons_errors.txt";
        
        while(!file_failure)
        {
            try
            {
                File fp = new File("src/main/java/Project1/" + filename);
                System.out.println("Read from " + fp.getPath());
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
            catch(Exception e2)
            {
                System.out.println(e2 + " is an EXCEPTION NOT INCLUDED IN THIS HANDLING\n");
            }
        }
        String uni=null;
        while(fscan.hasNext())
        {
            try
            {
                String l = fscan.nextLine();uni=l;
                ProcessLine(l,products);   
            }
            catch(Exception e)
            {   
                System.err.println(e);
                System.err.println("[" + uni +  "] " + " --> " + "skip this line " + "\n");
            }
        }
        fscan.close();
        file_failure=false;
        
        while(!file_failure)
        {
            try
            {
                File fp = new File("src/main/java/Project1/" + expense_file);
                System.out.println("Read from " + fp.getPath());
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
            catch(Exception e2)
            {
                System.out.println(e2 + " is an EXCEPTION NOT INCLUDED IN THIS HANDLING\n");
            }
        }
        while(fscan.hasNext())
        {
                String l = fscan.nextLine();uni=l;
                String [] col = l.split(",");
                int person_index = sales_exist(col[0].trim());
                if( person_index == -1)
                {   
                    System.out.printf("%s   >>   not exist\n",l);
                }
                else
                {
                    System.out.printf("%s   >>   total = %7s %7s\n",l,String.format("%,d", Integer.valueOf(col[1].trim())) +",",String.format("%,d", Integer.valueOf(col[2].trim())));
                    SalesPersonList.get(person_index).setMobile(Integer.parseInt(col[2].trim()));
                    SalesPersonList.get(person_index).setTravel(Integer.parseInt(col[1].trim()));
                }                
        }
        fscan.close();
        
    };
}

class SalesPerson 
{
    private String type, name, product;
    private int q1, q2, q3, q4, salary, travel, mobile;
    
    public SalesPerson(String type, String name)                    {this.type=type;this.name=name;}
    
    public void setType(String type)                                {this.type = type;}
    public void setName(String name)                                {this.name = name;}
    public void setProduct(String product)                          {this.product = product;}
    public void setQ1(int q1)                                       {this.q1 = q1;}
    public void setQ2(int q2)                                       {this.q2 = q2;}
    public void setQ3(int q3)                                       {this.q3 = q3;}
    public void setQ4(int q4)                                       {this.q4 = q4;}
    public void setSalary(int salary)                               {this.salary = salary;}
    public void setTravel(int travel)                               {this.travel = travel;}
    public void setMobile(int mobile)                               {this.mobile = mobile;}
    
    public String getType()                                         {return type;}
    public String getName()                                         {return name;}
    public String getProduct()                                      {return product;}
    public int getQ1()                                              {return q1;}
    public int getQ2()                                              {return q2;}
    public int getQ3()                                              {return q3;}
    public int getQ4()                                              {return q4;}
    public int getSalary()                                          {return salary;}
    public int getTravel()                                          {return travel;}
    public int getMobile()                                          {return mobile;}

    
}

public class Functional {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reimbursement.testvalue();
        ProductList products = new ProductList("products.txt");
        
        SalesPersonList sales = new SalesPersonList();
        sales.parseSales(" ", sales, products);
        sales.DisplaySales();
        //products.DisplayProduct();

        
        
        
    
    }
    
}
