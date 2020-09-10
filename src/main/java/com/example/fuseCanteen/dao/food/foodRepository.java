package com.example.fuseCanteen.dao.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.fuseCanteen.Model.food.foodItemsMapping;


/**
 * Created by aishwarya on 9/4/20.
 */

@Repository
public  interface  foodRepository extends JpaRepository<foodItemsMapping,Integer>  {

}
