package com.example.fuseCanteen.dao.food;

import com.example.fuseCanteen.Model.food.food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by aishwarya on 9/5/20.
 */

@Repository
public interface foodsRepository extends JpaRepository<food,Integer> {

    food getById(Integer id);

    food getByFoodName(String foodName);


    @Query(value = "SELECT id FROM foods WHERE FOOD_NAME =:foodName",nativeQuery = true)
    Integer getIdByName(@Param("foodName") String foodName);

    @Query(value = "SELECT MAX(ID) FROM foods",nativeQuery = true)
    Integer getMaxId();

    @Query(value = "SELECT COUNT(*) FROM foods",nativeQuery = true)
    Integer getCount();

}


