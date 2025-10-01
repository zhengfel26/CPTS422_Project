package main.java.com.example.shop.model;
import jakarta.persistence.Entity;
import main.java.com.example.shop.Product;

@Entity
public class Transaction{



    public double tax() {
        if(state.equals("WA") || state.equals("OR") || state.equals("CA")){
            return 0.2;
        }else if(state.equals("TX") || state.equals("FL")){
            return 0;
        } else{
            return 0.1;
        }
    }
}