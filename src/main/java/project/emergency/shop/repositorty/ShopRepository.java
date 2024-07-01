package project.emergency.shop.repositorty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.emergency.shop.entity.Shop;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findByProdCategory(String category);
}
