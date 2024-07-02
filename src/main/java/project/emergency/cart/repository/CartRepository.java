package project.emergency.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.emergency.cart.entity.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer> {
}
