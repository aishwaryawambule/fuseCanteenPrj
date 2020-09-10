package com.example.fuseCanteen.dao.foodRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fuseCanteen.Model.food.employeeFoodRequest;
import org.springframework.stereotype.Repository;

/**
 * Created by aishwarya on 9/7/20.
 */
@Repository
public interface employeeFoodRequestRepository extends JpaRepository<employeeFoodRequest,Integer> {

    employeeFoodRequest getById(Integer id);
}
