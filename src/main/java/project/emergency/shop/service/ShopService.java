package project.emergency.shop.service;

import project.emergency.shop.dto.ShopDTO;
import project.emergency.shop.entity.Shop;

import java.util.List;

public interface ShopService {


    List<ShopDTO> getList(); // 아이템 목록 조회

    ShopDTO read(int no); // 아이템 상세 조회

    List<ShopDTO> getByCategory(String category); // 카테고리별 아이템 목록 조회



    default Shop dtoToEntity(ShopDTO dto) {
        Shop entity = Shop.builder().prodNo(dto.getNo())
                .prodPrice(dto.getPrice())
                .prodName(dto.getTitle())
                .prodImgpath(dto.getImgpath())
                .prodDetailimgpath(dto.getDetailImgpath())
                .prodCategory(dto.getCategory())
                .build();
        return entity;
    }

    default ShopDTO entityToDto(Shop entity) {
        ShopDTO dto = ShopDTO.builder()
                .no(entity.getProdNo())
                .title(entity.getProdName())
                .price(entity.getProdPrice())
                .imgpath(entity.getProdImgpath())
                .detailImgpath(entity.getProdDetailimgpath())
                .category(entity.getProdCategory())
                .build();
        return dto;
    }
}
