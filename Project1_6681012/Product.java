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
public class Product implements Comparable<Product>
{
    private String code, name;
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
    public void updateSales(int d_sales)            {this.sales += d_sales;} 
    
    public void Summarize(SalesPersonList sales)
    {   
        int maxSales = -1;
        ArrayList<String> topSalespersons = new ArrayList<>();
        
        for (SalesPerson salesPerson : sales.getSalesList()) 
        {
            if (salesPerson.getProduct().equals(getCode())) {
                int totalSales = salesPerson.getQ1() + salesPerson.getQ2() + salesPerson.getQ3() + salesPerson.getQ4();
                if (totalSales > maxSales) {
                    maxSales = totalSales;
                    topSalespersons.clear();
                    topSalespersons.add(salesPerson.getName());
                } else if (totalSales == maxSales) {
                    topSalespersons.add(salesPerson.getName());
                }
            }
        }
        System.out.printf("%-20s total sales = %,4d units = %,10d baht %3s highest sales by %s\n",getName(),getSales(),getSales()*getUnit_price()," ",String.join(", ", topSalespersons));
    }

        
    @Override
    public int compareTo(Product other) 
    {
        if(this.sales*this.unit_price > other.sales*other.unit_price)return -1;
        else if(this.sales*this.unit_price < other.sales*other.unit_price)return 1;
        return 0;
    }
}

class ProductList
{   
    private String path="src/main/java/Project1_6681012/", filename=null; //Project1 should be change to Project1_XXX
    
    private ArrayList<Product> products = new ArrayList<>();
    
    public ArrayList<Product> getProductList() {return products;}
    
    public final void DisplayProduct()
    {   
        for(int i=0;i<products.size();i++)
        {
            System.out.printf("%-20s (%-2s) unit price = %,7d %5s Commisions  >>  flat = %,3d%%   Q1 = %,3d%%   Q2 = %,3d%%   Q3 = %,3d%%   Q4 = %,3d%%\n", products.get(i).getName(), products.get(i).getCode(), products.get(i).getUnit_price(), " ", products.get(i).getFlat_comm(), products.get(i).getQ1(), products.get(i).getQ2(), products.get(i).getQ3(), products.get(i).getQ4(),products.get(i).getSales());
        }
        System.out.println();
    }
    
    public void SummarizeList(SalesPersonList sales)
    {   
        Collections.sort(products);
        System.out.println("=".repeat(10) + " Summary " + "=".repeat(10) + "\n");
        for(Product product : products)
        {
            product.Summarize(sales);
        }
        System.out.println();
    }
    
        
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
                System.out.println();
            }
        }
        
        while(fscan.hasNext())
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
        DisplayProduct();
        fscan.close();
    }
    
    public ProductList(String filename)                         
    {
        this.filename=filename;
        
        Boolean file_failure=false;
        
        Scanner fscan = null;
        Scanner keyboard = new Scanner(System.in);
                
        while(!file_failure)
        {
            try
            {
                File fp = new File("src/main/java/Project1_6681012/" + filename);
                System.out.println("Read from " + fp.getPath());
                fscan = new Scanner(fp);
                fscan.nextLine();
                file_failure = true;
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("New file name = ");
                filename = keyboard.nextLine();
                System.out.println();
            }
        }
        
        while(fscan.hasNext())
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
        DisplayProduct();
        fscan.close();
    }
}