package dnd.RestApi.game.creature.monster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {


    ArrayList<Monster> getMonstersByCrIs(Double cr);
    Long countAllByCrIs(Double cr);
    Page<Monster> getAllByCrIs(Double cr, Pageable pageable);

}
