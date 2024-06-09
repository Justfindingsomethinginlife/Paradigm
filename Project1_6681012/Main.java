/*
Gael Daengsangwan 6580591
Karndanai Udomsiriphocksai 6481324
Kasidech Thongking 6681012
Patrapee Maleevech 6580074
Thanapoom Tanalakwong 6481205
*/
package Project1_6681012;

import Project1_6681012.ProductList;
import Project1_6681012.Reimbursement;
import Project1_6681012.SalesPersonList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {     
        ArrayList<Product> products = parseProduct("src/main/java/Project1_6681012/","product.txt"); //read products data from products.txt 
        
        Reimbursement re = new Reimbursement(); //read reimbursements data from reimbursements.txt 
        
        ArrayList<SalesPerson> sales = parseSales("src/main/java/Project1_6681012/","salespersons_error.txt","expense.txt",products); //read salespersons and expenses data from salespersons_errors.txt and expenses.txt 
        
        //Sales Payment Info
        System.out.println("=".repeat(10) + " Process Payments " + "=".repeat(10) + "\n");
        for(SalesPerson salesperson : sales)
        {   
            int prodcut_index = check(products,salesperson.getProduct());
            Product product = products.get(prodcut_index);
            salesperson.Payment(product, re);
        }
        
        //Summarizing Product
        Collections.sort(products);
        System.out.println("=".repeat(10) + " Summary " + "=".repeat(10) + "\n");
        for(Product product : products)
        {
            product.Summarize(sales);
        }
        System.out.println();   
    }
    
    public void demo_with_external_classes() //Demo using ProductList and SalesPersonList which are just like implementation in main() but already wrapped
    {
        ProductList products = new ProductList("src/main/java/Project1_6681012/","products.txt");
         
        Reimbursement re = new Reimbursement(); //read reimbursements data from reimbursements.txt 
        
        SalesPersonList sales = new SalesPersonList("src/main/java/Project1_6681012/","salespersons_errors.txt","expenses.txt",products);
        
        sales.DisplayPaymentInfo(products, re); //Sales Payment Info
        
        products.SummarizeList(sales); //Summarizing Product
    }
    
    private static ArrayList<Product> parseProduct(String path, String filename)                         
    {
    
        Boolean file_failure=false;
        
        Scanner fscan = null;
        Scanner keyboard = new Scanner(System.in);
        
        ArrayList<Product> products = new ArrayList<>();
        
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
        
        for(int i=0;i<products.size();i++)
        {
            System.out.printf("%-20s (%-2s) unit price = %,7d %5s Commisions  >>  flat = %,3d%%   Q1 = %,3d%%   Q2 = %,3d%%   Q3 = %,3d%%   Q4 = %,3d%%\n", products.get(i).getName(), products.get(i).getCode(), products.get(i).getUnit_price(), " ", products.get(i).getFlat_comm(), products.get(i).getQ1(), products.get(i).getQ2(), products.get(i).getQ3(), products.get(i).getQ4(),products.get(i).getSales());
        }
        
        System.out.println();
        fscan.close();
        
        return products;
    }
    
    private static ArrayList<SalesPerson> parseSales(String path,String sales_file, String expenses_file, ArrayList<Product> products)
    {
        Boolean file_failure=false;
        
        ArrayList<SalesPerson> sales = new ArrayList<>();
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
                ProcessLine(l,products,sales);   
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
                int person_index = sales_exist(sales,col[0].trim());
                if( person_index == -1)
                {   
                    System.out.printf("%s   >>   not exist\n",l);
                }
                else
                {
                    System.out.printf("%s   >>   total = %7s %7s\n",l,String.format("%,d", Integer.valueOf(col[1].trim())) +",",String.format("%,d", Integer.valueOf(col[2].trim())));
                    sales.get(person_index).setMobile(Integer.parseInt(col[2].trim()));
                    sales.get(person_index).setTravel(Integer.parseInt(col[1].trim()));
                }                
        }
        System.out.println();
        fscan.close();
        
        return sales;
    }
    
    private static void ProcessLine(String l, ArrayList<Product> products,  ArrayList<SalesPerson> sales) throws InvalidFormatException
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
            
                products.get(product_index).updateSales(totalSales);
                Collections.sort(products);
                
                sales.add(temp_sales);
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
            
                products.get(product_index).updateSales(totalSales);
                Collections.sort(products);
                
                sales.add(temp_sales);
            }    
    }
    
    private static int check(ArrayList<Product> products, String code) //Should be implimented with Trie for a large dataset
    {   
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).getCode().equals(code)) return i;
        }
        return -1;
    }
    
    private static int sales_exist(ArrayList<SalesPerson> sales, String name) //Should be implimented with Trie for a large dataset
    {   
        for(int i=0;i<sales.size();i++)
        {
            if(sales.get(i).getName().equals(name)) return i;
        }
        return -1;
    }
}
    
