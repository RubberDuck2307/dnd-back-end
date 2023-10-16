package dnd.RestApi.api.repositories.monster;

import dnd.RestApi.game.creature.monster.Monster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

    Long countAllByCrIs(Double cr);
    Page<Monster> getAllByCrIs(Double cr, Pageable pageable);
    HashMap<Double, List<Monster>> getMonstersByCrAndAmount(HashMap<Double, Integer> crs);

}
