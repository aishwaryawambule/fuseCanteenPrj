package com.example.fuseCanteen.dao.food;

import com.example.fuseCanteen.Model.food.foodItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aishwarya on 9/5/20.
 */

@Repository
public interface foodItemsRepositoty  extends JpaRepository<foodItems,Integer> {


}