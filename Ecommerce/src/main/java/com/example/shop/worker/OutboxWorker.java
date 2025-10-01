package main.java.com.example.shop.worker;

import com.example.shop.model.*;
import com.example.shop.repository.*;
import main.java.com.example.shop.service.InventoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OutboxWorker {
    private final OutboxRepository outboxRepo;
    private final OrderRepository orderRepo;
    private final PaymentRepository paymentRepo;
    private final InventoryService inventoryService;

    public OutboxWorker(OutboxRepository outboxRepo, OrderRepository orderRepo, PaymentRepository paymentRepo, InventoryService inventoryService) {
        this.outboxRepo = outboxRepo;
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
        this.inventoryService = inventoryService;
    }

    // Run every 5 seconds: simulate payment authorization success
    @Scheduled(fixedDelay = 5000L, initialDelay = 2000L)
    @Transactional
    public void poll() {
        List<OutboxEvent> events = outboxRepo.findTop10ByProcessedFalseOrderByCreatedAtAsc();
        for (OutboxEvent evt : events) {
            if (!"PAYMENT_AUTHORIZE_REQUESTED".equals(evt.getType())) {
                evt.setProcessed(true);
                outboxRepo.save(evt);
                continue;
            }
            // Simulate PSP authorize success
            // In real life, call PSP here, handle decline, etc.
            // Mark payment authorized and order paid; consume reservations
            // (We assume one payment per order for simplicity)
            Long orderId = extractOrderId(evt.getPayload());
            CustomerOrder order = orderRepo.findById(orderId).orElseThrow();
            order.setStatus(OrderStatus.PAID);
            orderRepo.save(order);
            inventoryService.consumeReservations(orderId);
            evt.setProcessed(true);
            outboxRepo.save(evt);
        }
    }

    private Long extractOrderId(String payload) {
        // extremely naive parser: {"orderId":123,...}
        try {
            String key = ""orderId":";
            int i = payload.indexOf(key);
            int j = payload.indexOf(",", i+key.length());
            if (j == -1) j = payload.indexOf("}", i+key.length());
            return Long.parseLong(payload.substring(i+key.length(), j).trim());
        } catch (Exception e) {
            throw new RuntimeException("Bad payload: " + payload);
        }
    }
}
