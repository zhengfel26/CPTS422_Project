import com.example.shop.reppository.CartRepository;
import com.example.shop.reppository.InventoryRepository;
import com.example.shop.reppository.ProductRepository;
import com.example.shop.reppository.RefundRepository;
import com.example.shop.reppository.TransactionRepository;

import org.springframework.stereotype.Component;

@Component
public class InitialSetup{
    private InventoryRepository inventoryRepo;
    private ProductRepository productRepo;
    private RefundRepository refundRepo;
    private CartRepository cartRepo;
    private TransactionRepo transactionRepo;

    public InitialSetup(RefundRepository refundRepo, InventoryRepository inventoryRepo,ProductRepository productRepo, CartRepository cartRepo, TransactionRepo transactionRepo){
        this.inventoryRepo = inventoryRepo;
        this.productRepo = productRepo;
        this.refundRepo = refundRepo;
        this.cartRepo = cartRepo;
        this.transactionRepo = transactionRepo;
    }

}