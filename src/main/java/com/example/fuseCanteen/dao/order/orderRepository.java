package com.example.fuseCanteen.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fuseCanteen.Model.order.orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by aishwarya on 9/5/20.
 */

@Repository
public interface orderRepository extends JpaRepository<orders,Integer>{


    @Query(value = "SELECT MAX(ID) FROM orders",nativeQuery = true)
    Integer getMaxId();


    @Query(value = "SELECT COUNT(*) FROM orders",nativeQuery = true)
    Integer getCount();

    orders getById(Integer id);

}
