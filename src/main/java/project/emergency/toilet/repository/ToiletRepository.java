package project.emergency.toilet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.emergency.toilet.entity.Toilet;

import java.util.List;

public interface ToiletRepository extends JpaRepository<Toilet, String> {

    Toilet findByToiletNo(String toiletNo);
    List<Toilet> findByMemRegister(Boolean memRegister);
    List<Toilet> findByDisabled(Boolean disabled);
    List<Toilet> findByDiaper(Boolean diaper);
    List<Toilet> findBySeparated(Boolean separated);
    List<Toilet> findByPaper(Boolean separated);

}
