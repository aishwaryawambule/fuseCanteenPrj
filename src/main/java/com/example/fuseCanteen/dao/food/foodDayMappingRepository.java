package com.example.fuseCanteen.dao.food;

import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import com.example.fuseCanteen.Model.food.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aishwarya on 9/10/20.
 */

@Repository
public interface foodDayMappingRepository extends JpaRepository<foodDayMapping,Integer> {

    @Query(value = "SELECT * FROM fooddaymapping where FOOD_ID =:foodId",nativeQuery = true)
    foodDayMapping getById(@Param("foodId") Integer foodId);


    @Query(value = "SELECT * FROM fooddaymapping where FOOD_ID =:foodId",nativeQuery = true)
    List<foodDayMapping> getAllById(@Param("foodId") Integer foodId);



}
