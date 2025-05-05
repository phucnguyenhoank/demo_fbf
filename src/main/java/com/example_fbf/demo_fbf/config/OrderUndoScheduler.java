package com.example_fbf.demo_fbf.config;

import com.example_fbf.demo_fbf.entity.FbfOrderStatus;
import com.example_fbf.demo_fbf.repository.FbfOrderRepository;
import com.example_fbf.demo_fbf.service.FbfOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class OrderUndoScheduler {

    private final FbfOrderService fbfOrderService;
    private final TaskScheduler taskScheduler;
    private final FbfOrderRepository fbfOrderRepository;

    public void scheduleUndoOrder(Long orderId, Instant triggerTime) {
        taskScheduler.schedule(() -> {
            fbfOrderRepository.findById(orderId).ifPresent(o -> {
                long minutes = ChronoUnit.MINUTES.between(o.getCreatedAt(), LocalDateTime.now());
                if (o.getStatus() == FbfOrderStatus.PENDING && minutes >= 2) {
                    fbfOrderService.undoOrder(o.getFbfUser().getId(), o.getId()); // Gọi qua proxy
                }
            });
        }, triggerTime);
    }
}
