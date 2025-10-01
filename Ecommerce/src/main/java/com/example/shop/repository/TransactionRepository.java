package main.java.com.example.shop.repository;

import main.java.com.example.shop.model.Transaction;

public class TransactionRepository{

    private static final List<Transaction> TRANSACTIONS_DB = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        TRANSACTIONS_DB.add(transaction);
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return TRANSACTIONS_DB;
    }

    public void clearAllTransactions() {
        TRANSACTIONS_DB.clear();
    }
}
