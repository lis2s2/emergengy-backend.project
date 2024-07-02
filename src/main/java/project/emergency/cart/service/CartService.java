package project.emergency.cart.service;

import project.emergency.cart.dto.CartDTO;
import project.emergency.cart.entity.Cart;

public interface CartService {

    int register(CartDTO dto);

    default Cart dtoToEntity(CartDTO dto) {
        Cart entity = Cart.builder()
                .cartNo(dto.getNo())
                .Shop(dto.getProdNo())
                .Member(dto.getMemberId())
                .prodCount(dto.getProdCount())
                .build();
        return entity;
    }

    default CartDTO EntityToDto(Cart entity) {
        CartDTO dto = CartDTO.builder()
                .no(entity.getCartNo())
                .prodNo(entity.getShop())
                .memberId(entity.getMember())
                .prodCount(entity.getProdCount())
                .build();
        return dto;
    }
}
