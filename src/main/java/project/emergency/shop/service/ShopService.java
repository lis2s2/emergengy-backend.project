package project.emergency.shop.service;

import project.emergency.shop.dto.ShopDTO;
import project.emergency.shop.entity.Shop;

import java.util.List;

public interface ShopService {
    List<ShopDTO> getList(); // 아이템 목록 조회

    ShopDTO read(int no); // 아이템 상세 조회

    default Shop dtoToEntity(ShopDTO dto) {
        Shop entity = Shop.builder().prodNo(dto.getNo())
                .prodPoint(dto.getPrice())
                .prodName(dto.getTitle())
                .imgPath(dto.getImgPath())
                .build();
        return entity;
    }

    default ShopDTO entityTODto(Shop entity) {
        ShopDTO dto = ShopDTO.builder()
                .no(entity.getProdNo())
                .title(entity.getProdName())
                .price(entity.getProdPoint())
                .imgPath(entity.getImgPath())
                .build();
        return dto;
    }
}
