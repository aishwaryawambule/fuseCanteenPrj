package com.example.fuseCanteen.dao.days;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.fuseCanteen.Model.days.*;
/**
 * Created by aishwarya on 9/7/20.
 */

@Repository
public interface dayRepository extends JpaRepository<days,Integer> {

    Integer getById(Integer id);

    @Query(value = "SELECT * FROM days WHERE DAY =:dayName",nativeQuery = true)
    days getDayByDayName(@Param("dayName") String dayName);

}
