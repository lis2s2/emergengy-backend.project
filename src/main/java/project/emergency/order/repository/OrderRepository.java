package project.emergency.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.emergency.order.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
