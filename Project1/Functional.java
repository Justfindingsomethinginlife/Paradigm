/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Project1;

import Project1.ProductList;
import Project1.Reimbursement;
import Project1.SalesPersonList;

/**
 *
 * @author ACER
 */
public class Functional {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductList products = new ProductList("products.txt");
        
        SalesPersonList sales = new SalesPersonList("salespersons.txt","expenses.txt",products);

        sales.DisplaySales();
        //products.DisplayProduct();

        
        
        
    
    }
    
}
