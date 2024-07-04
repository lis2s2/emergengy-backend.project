package project.emergency.cart.service;

import project.emergency.cart.dto.CartDTO;
import project.emergency.cart.entity.Cart;
import project.emergency.member.entitiy.Member;
import project.emergency.shop.entity.Shop;

import java.util.List;


public interface CartService {

    int addCart(CartDTO dto);

    void updateCart(Cart cart, int count);

    List<CartDTO> getList(String memberId);

    void updateCartCount(int cartNo, int prodCount);

    void deleteCart(int cartNo);

    default Cart dtoToEntity(CartDTO dto) {
        Member member = Member.builder().memId(dto.getMemberId()).build(); // Member 객체 생성
        Shop shop = Shop.builder().prodNo(dto.getProdNo()).build(); // Shop 객체 생성

        return Cart.builder()
                .cartNo(dto.getNo())
                .shop(shop)
                .member(member)
                .prodCount(dto.getProdCount())
                .build();
    }

    default CartDTO entityToDto(Cart entity) {
        CartDTO dto = CartDTO.builder()
                .no(entity.getCartNo())
                .prodNo(entity.getShop().getProdNo()) // Shop 객체의 ID 사용
                .memberId(entity.getMember().getMemId()) // Member 객체의 ID 사용
                .prodCount(entity.getProdCount())
                .build();

        if (entity.getShop() != null) {
            dto.setProdPrice(entity.getShop().getProdPrice());
            dto.setProdImgpath(entity.getShop().getProdImgpath());
            dto.setProdTitle(entity.getShop().getProdName());
        }

        return dto;
    }
}
