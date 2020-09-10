package com.example.fuseCanteen.dao.order;

import com.example.fuseCanteen.Model.order.scheduledorders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aishwarya on 9/7/20.
 */

@Repository
public interface scheduledOrderRepository extends JpaRepository<scheduledorders,Integer> {
}
