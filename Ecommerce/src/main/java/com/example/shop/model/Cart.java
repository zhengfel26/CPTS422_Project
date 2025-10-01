package com.example.shop.model

import com.example.shop.model.Product;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cart{
    private List<Product> products;

    @Id
    @GeneratedValue
    private int id;

    public Cart(){
        products= new List<>*();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(int id){
        for (Product product: products){
            if (product.getID()==id){
                products.remove(id);
            }
        }
    }

    public double getTotal(){
        double total=0;
        for (Product product: products){
            total+=product.getPrice();
        }
        return total;
    }
}