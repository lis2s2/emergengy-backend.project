package project.emergency.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.emergency.cart.entity.Cart;

import java.util.List;
import java.util.Optional;


@Repository
//@EnableJpaAuditing
public interface CartRepository  extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByShop_ProdNoAndMember_MemId(int prodNo, String memberId);

    @Query("SELECT c FROM Cart c WHERE c.member.memId = :memberId")
    List<Cart> findByMemberId(@Param("memberId") String memberId);

    @Query("SELECT COUNT(c.cartNo) FROM Cart c WHERE c.member.memId = :memberMemId AND c.isDeleted = false")
    long countByMemIdAndIsDeletedFalse(@Param("memberMemId") String memberMemId);

}
