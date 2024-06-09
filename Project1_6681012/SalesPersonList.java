/*
Gael Daengsangwan 6580591
Karndanai Udomsiriphocksai 6481324
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


public class SalesPersonList //DEPRECATED not up to requirement
{
    private ArrayList<SalesPerson> SalesPersonList = new ArrayList<>();
    private String sales_file=null, expenses_file=null, path="src/main/java/Project1_6681012/";
    
    public ArrayList<SalesPerson> getSalesList()                {return SalesPersonList;}
    public String                 getSalesFile()                {return sales_file;}
    public String                 getExpensesFile()             {return expenses_file;}
    public String                 getPath()                     {return path;}
    
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
        for(SalesPerson salesperson : SalesPersonList)
        {   
            int prodcut_index = check(products,salesperson.getProduct());
            Product product = products.getProductList().get(prodcut_index);
            salesperson.Payment(product, re);
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
    
    public SalesPersonList(String path,String sales_file, String expenses_file, ProductList products)
    {
        Boolean file_failure=false;
        
        this.path = path;
        this.sales_file = sales_file;
        this.expenses_file = expenses_file;
        
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
        System.out.println();
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
        
        this.sales_file = sales_file;
        this.expenses_file = expenses_file;
        
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
}
