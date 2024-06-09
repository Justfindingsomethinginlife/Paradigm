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


public class ProductList //DEPRECATED not up to requirement
{   
    private String path="src/main/java/Project1_6681012/", filename=null; //Project1 should be change to Project1_XXX
    
    private ArrayList<Product> products = new ArrayList<>();
    
    public ArrayList<Product> getProductList()          {return products;}
    public String             getFileName()             {return filename;}
    public String             getPath()                 {return path;}
    
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
