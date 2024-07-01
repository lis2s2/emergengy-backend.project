package project.emergency.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.shop.dto.ShopDTO;
import project.emergency.shop.entity.Shop;
import project.emergency.shop.repositorty.ShopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    ShopRepository repository;

    @Override
    public List<ShopDTO> getList() {
        List<Shop> entityList = repository.findAll();

        return entityList.stream()
                .map(this::entityToDto).toList();
    }

    @Override
    public ShopDTO read(int no) {
        Optional<Shop> result = repository.findById(no);
        if (result.isPresent()) {
            Shop shop = result.get();
            return entityToDto(shop);
        }
        return null;
//        Shop shop = repository.findById(no)
//                .orElse(null);
//        return shop == null ? null : entityTODto(shop);
    }

    @Override
    public List<ShopDTO> getByCategory(String category) {
        List<Shop> entityList = repository.findByProdCategory(category);
        return entityList.stream()
                .map(this::entityToDto).toList();
    }


}
