package main.java.com.example.shop.model;
import jakarta.persistence.Entity;
@Entity
public class Product{
    private string name;

    public double getPrice() {
        return price;
    }

    public string getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    private double price;
    private int id;

    private static int ID=1;

    public Product(string name, double price, int id){
        this.name=name;
        this.price=price;
        this.id = ID++;
    }


}