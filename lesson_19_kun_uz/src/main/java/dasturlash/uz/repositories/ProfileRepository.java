package dasturlash.uz.repositories;

import dasturlash.uz.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findById(Integer id);

//    @Transactional
//    @Modifying
//    @Query("update ProfileEntity set visible = false where id = ?1")
//    int updateVisibleById(Integer id);


    Optional<ProfileEntity> findByUsername(String username);
}
