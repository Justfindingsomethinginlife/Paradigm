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