package project.emergency.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.cart.dto.CartDTO;
import project.emergency.cart.entity.Cart;
import project.emergency.cart.repository.CartRepository;
import project.emergency.shop.entity.Shop;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository repository;

    @Override
    public int addCart(CartDTO dto) {
        Optional<Cart> cartList = repository.findByShop_ProdNoAndMember_MemId(dto.getProdNo(), dto.getMemberId());

        if (cartList.isPresent()) {
            Cart cart = cartList.get();
            updateCart(cart, dto.getProdCount());
            repository.save(cart);
            return cart.getCartNo();
        } else {
            Cart cart = dtoToEntity(dto);
            repository.save(cart);
            return cart.getCartNo();
        }
    }

    @Override
    public void updateCart(Cart cart, int count) {
        cart.setProdCount(cart.getProdCount() + count);
    }

    @Override
    public List<CartDTO> getList() {
        List<Cart> entityList = repository.findAll();

        return entityList.stream()
                .map(this::entityToDto).toList();
    }
}
