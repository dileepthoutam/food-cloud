package foods.data.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import foods.data.OrderRepository;

@Service
public class OrderAdminService {

  private OrderRepository orderRepository;

  public OrderAdminService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void deleteAllOrders() {
    orderRepository.deleteAll();
  }

}