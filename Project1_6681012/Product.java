/*

Gael Daengsangwan 6580591
Kasidech Thongking 6681012
Patrapee Maleevech 6580074
Thanapoom Tanalakwong 6481205
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
    private int unit_price, flat_comm, q1, q2, q3, q4, sales=0;
    
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
  
    public void Summarize(ArrayList<SalesPerson> sales)
    {   
        int maxSales = -1;
        ArrayList<String> topSalespersons = new ArrayList<>();
        
        for (SalesPerson salesPerson : sales) 
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
        if(this.sales > other.sales)return -1;
        else if(this.sales < other.sales)return 1;
        return 0;
    }
}