package az.idrak.appv1.controller;

import az.idrak.appv1.service.order.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/add")
    public void addProductToOrder(@RequestParam Long userId,
                                  @RequestParam List<Long> cartItemId,
                                  @RequestParam Integer statusId,
                                  @RequestParam String address) throws Exception {
        orderService.addOrderItem(userId, cartItemId, statusId, address);
    }

//    @PostMapping
//    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
//        try {
//            String response = orderItemService.placeOrder(orderRequest);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}
