package project.emergency.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.cart.dto.CartDTO;
import project.emergency.cart.entity.Cart;
import project.emergency.cart.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository repository;

    @Override
    public int register(CartDTO dto) {
        Cart cart = dtoToEntity(dto);
        repository.save(cart);

        return cart.getCartNo();
    }
}
