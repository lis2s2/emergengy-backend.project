package project.emergency.cart.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.cart.dto.CartDTO;
import project.emergency.cart.entity.Cart;
import project.emergency.cart.repository.CartRepository;

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
    public List<CartDTO> getList(String memberId) {
        List<Cart> entityList = repository.findByMemberId(memberId);

        return entityList.stream()
                .map(this::entityToDto).toList();
    }

    @Override
    public void updateCartCount(int cartNo, int prodCount) {
        Cart cart = repository.findById(cartNo)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 장바구니 번호입니다"));

        cart.setProdCount(prodCount);
        repository.save(cart);
    }

    @Transactional
    @Override
    public void deleteCart(int cartNo) {
        Cart cart = repository.findById(cartNo)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 장바구니 번호입니다"));

        cart.setDeleted(true); // isDeleted 값을 true로 변경
        repository.save(cart);
    }
}
