/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Project1_6681012;

import Project1_6681012.ProductList;
import Project1_6681012.Reimbursement;
import Project1_6681012.SalesPersonList;

/**
 *
 * @author ACER
 */
public class Functional {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ProductList products = new ProductList("src/main/java/Project1_6681012/","product.txt");
        
        Reimbursement re = new Reimbursement();
        
        SalesPersonList sales = new SalesPersonList("src/main/java/Project1_6681012/","salespersons_error.txt","expense.txt",products);
        sales.DisplayPaymentInfo(products, re);
        products.SummarizeList(sales);

        
        
        
    
    }
    
}
