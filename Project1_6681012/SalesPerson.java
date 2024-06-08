/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project1_6681012;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
class InvalidFormatException extends Exception
{			
    public InvalidFormatException(String message)	{ super(message); }
};

public class SalesPerson 
{
    private String type, name, product;
    private int q1, q2, q3, q4, salary, sales, travel=0, mobile=0;
    
    public SalesPerson(String type, String name)                    {this.type=type;this.name=name;}
    
    public void setType(String type)                                {this.type = type;}
    public void setName(String name)                                {this.name = name;}
    public void setProduct(String product)                          {this.product = product;}
    public void setQ1(int q1)                                       {this.q1 = q1;}
    public void setQ2(int q2)                                       {this.q2 = q2;}
    public void setQ3(int q3)                                       {this.q3 = q3;}
    public void setQ4(int q4)                                       {this.q4 = q4;}
    public void setSales(int sales)                                 {this.sales = sales;}
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
    public int getSales()                                           {return sales;}
    public int getSalary()                                          {return salary;}
    public int getTravel()                                          {return travel;}
    public int getMobile()                                          {return mobile;}
    
    public int getCommission(Product product)
    {   
        int Comm=0;
        if(getType().equals("s") && product.getCode().equals(getProduct())) Comm = (getSales())*product.getFlat_comm()*product.getUnit_price()/100;
        else if(getType().equals("c") && product.getCode().equals(getProduct())) Comm = (getQ1()*product.getQ1() + getQ2()*product.getQ2() + getQ3()*product.getQ3() + getQ4()*product.getQ4())*product.getUnit_price()/100;
        
        return Comm;
    }
    
    public int getTravelDeduced(Reimbursement re)
    {
        if(getType().equals("s")) return Math.max(getTravel() - re.getSalary_Travel_Limit(), 0);
        else if(getType().equals("c")) return Math.max(getTravel() - re.getCommision_Travel_Limit(), 0);
        return 0;
    }
    
    public int getMobileDeduced(Reimbursement re)
    {
        if(getType().equals("s")) return Math.max(getMobile() - re.getSalary_Mobile_Limit(), 0);
        else if(getType().equals("c")) return Math.max(getMobile() - re.getCommision_Mobile_Limit(), 0);
        return 0;
    }
    
    public int getPayout(Product product, Reimbursement re)
    {   
        int payout = getSalary() + getCommission(product) - getMobileDeduced(re) - getTravelDeduced(re);
        return payout;
    }

    public void Payment(Product product, Reimbursement re)
    {   
        
        if(getType().equals("s"))
        {
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht\n",getName(),"Salary+","total  salary",getSalary());
            System.out.printf("%-10s %-10s >> %-38s   Q1(%3d)   Q2(%3d)   Q3(%3d)   Q4(%3d)\n","","",product.getName(),getQ1(),getQ2(),getQ3(),getQ4());
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht %5s total  = %,6d units\n", " ", " ","total  commision",getCommission(product), " ", getSales());
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht %5s excess = %,6d baht\n", " ", " ","travel  expense",getTravel()," ",getTravelDeduced(re));
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht %5s excess = %,6d baht\n", " ", " ","mobile  expense",getMobile()," ",getMobileDeduced(re));
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht\n\n", " ", " ", "total  salary", getPayout(product, re));
        }
        else if(getType().equals("c"))
        {
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht\n",getName(),"Commision","total  salary",getSalary());
            System.out.printf("%-10s %-10s >> %-38s   Q1(%3d)   Q2(%3d)   Q3(%3d)   Q4(%3d)\n","","",product.getName(),getQ1(),getQ2(),getQ3(),getQ4());
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht %5s total  = %,6d units\n", " ", " ","total  commision",getCommission(product), " ", getSales());
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht %5s excess = %,6d baht\n", " ", " ","travel  expense",getTravel()," ",getTravelDeduced(re));
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht %5s excess = %,6d baht\n", " ", " ","mobile  expense",getMobile()," ",getMobileDeduced(re));
            System.out.printf("%-10s %-10s >> %-17s = %,9d baht\n\n", " ", " ", "total  salary", getPayout(product, re));
        }
        
    }
}

class SalesPersonList
{
    private ArrayList<SalesPerson> SalesPersonList = new ArrayList<>();
    
    public ArrayList<SalesPerson> getSalesList()    {return SalesPersonList;}
    
    private int check(ProductList products, String code) //Should be implimented with Trie for a large dataset
    {   
        ArrayList<Product> product = products.getProductList();
        for(int i=0;i<product.size();i++)
        {
            if(product.get(i).getCode().equals(code)) return i;
        }
        return -1;
    }
    
    private int sales_exist(String name) //Should be implimented with Trie for a large dataset
    {   
        for(int i=0;i<SalesPersonList.size();i++)
        {
            if(SalesPersonList.get(i).getName().equals(name)) return i;
        }
        return -1;
    }
    
    public void DisplayPaymentInfo(ProductList products, Reimbursement re)
    {   
        System.out.println("=".repeat(10) + " Process Payments " + "=".repeat(10) + "\n");
        for(SalesPerson sales : SalesPersonList)
        {   
            int prodcut_index = check(products,sales.getProduct());
            Product product = products.getProductList().get(prodcut_index);
            sales.Payment(product, re);
        }
    }
    
    private void ProcessLine(String l, ProductList products) throws InvalidFormatException
    {
        String [] col = l.split(",");
        int product_index = check(products, col[2].trim());        
            if(!(col[0].trim().equals("s") || col[0].trim().equals("c")))               throw new InvalidFormatException("For input: \"" + col[0].trim() + "\"");
            else if(product_index == -1)                                                throw new InvalidFormatException("For input: \"" + col[2].trim() + "\"");
            else if(Integer.parseInt(col[3].trim()) < 0)                                throw new InvalidFormatException("For input: \"" + col[3].trim() + "\"");
            else if(Integer.parseInt(col[4].trim()) < 0)                                throw new InvalidFormatException("For input: \"" + col[4].trim() + "\"");
            else if(Integer.parseInt(col[5].trim()) < 0)                                throw new InvalidFormatException("For input: \"" + col[5].trim() + "\"");
            else if(Integer.parseInt(col[6].trim()) < 0)                                throw new InvalidFormatException("For input: \"" + col[6].trim() + "\"");
            else if(col[0].trim().equals("s"))if(Integer.parseInt(col[7].trim()) < 0)   throw new InvalidFormatException("For input: \"" + col[7].trim() + "\"");
            
            if(col[0].trim().equals("s"))
            {
                SalesPerson temp_sales = new SalesPerson(col[0].trim(),col[1].trim());
                temp_sales.setProduct(col[2].trim());
                temp_sales.setQ1(Integer.parseInt(col[3].trim()));
                temp_sales.setQ2(Integer.parseInt(col[4].trim()));
                temp_sales.setQ3(Integer.parseInt(col[5].trim()));
                temp_sales.setQ4(Integer.parseInt(col[6].trim()));
                temp_sales.setSalary(Integer.parseInt(col[7].trim()));
                temp_sales.setSales(temp_sales.getQ1() + temp_sales.getQ2() + temp_sales.getQ3() + temp_sales.getQ4());
                
                int totalSales = temp_sales.getQ1() + temp_sales.getQ2() + temp_sales.getQ3() + temp_sales.getQ4();            
            
                products.getProductList().get(product_index).updateSales(totalSales);
                Collections.sort(products.getProductList());
                
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
                temp_sales.setSales(temp_sales.getQ1() + temp_sales.getQ2() + temp_sales.getQ3() + temp_sales.getQ4());
               
                int totalSales = temp_sales.getQ1() + temp_sales.getQ2() + temp_sales.getQ3() + temp_sales.getQ4();            
            
                products.getProductList().get(product_index).updateSales(totalSales);
                Collections.sort(products.getProductList());
                
                SalesPersonList.add(temp_sales);
            }    
    }
    
    public SalesPersonList(String path,String sales_file,String expenses_file, ProductList products)
    {
        Boolean file_failure=false;
        
        Scanner fscan = null;
        Scanner keyboard = new Scanner(System.in);
                
        while(!file_failure)
        {
            try
            {
                File fp = new File(path + sales_file);
                System.out.println("Read from " + fp.getPath());
                fscan = new Scanner(fp);
                fscan.nextLine();
                file_failure = true;
            }
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("New file name = ");
                sales_file = keyboard.nextLine();
                System.out.println();
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
                File fp = new File(path + expenses_file);
                System.out.println("Read from " + fp.getPath());
                fscan = new Scanner(fp);
                fscan.nextLine();
                file_failure = true;
            }
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("New file name = ");
                expenses_file = keyboard.nextLine();
                System.out.println();
            }
            catch(Exception e2)
            {
                System.out.println(e2 + " is an EXCEPTION NOT INCLUDED IN THIS HANDLING\n");
            }
        }
        
        while(fscan.hasNext())
        {
                String l = fscan.nextLine();
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
        System.out.println();
        fscan.close();
    }
    
    public SalesPersonList(String sales_file,String expenses_file, ProductList products)
    {
        Boolean file_failure=false;
        
        Scanner fscan = null;
        Scanner keyboard = new Scanner(System.in);
                
        while(!file_failure)
        {
            try
            {
                File fp = new File("src/main/java/Project1_6681012/" + sales_file);
                System.out.println("Read from " + fp.getPath());
                fscan = new Scanner(fp);
                fscan.nextLine();
                file_failure = true;
            }
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("New file name = ");
                sales_file = keyboard.nextLine();
                System.out.println();
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
                File fp = new File("src/main/java/Project1_6681012/" + expenses_file);
                System.out.println("Read from " + fp.getPath());
                fscan = new Scanner(fp);
                fscan.nextLine();
                file_failure = true;
            }
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("New file name = ");
                expenses_file = keyboard.nextLine();
                System.out.println();
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
        System.out.println();
        fscan.close();
    }
}
