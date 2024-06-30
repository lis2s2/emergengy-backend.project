package project.emergency.shop.repositorty;

import org.springframework.data.jpa.repository.JpaRepository;
import project.emergency.shop.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
