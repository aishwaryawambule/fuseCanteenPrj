package com.example.fuseCanteen.Service.modelService;

import com.example.fuseCanteen.Model.food.foodItemsDto;
import com.example.fuseCanteen.dao.food.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fuseCanteen.Model.food.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aishwarya on 9/4/20.
 */

@Service
public class foodService {

    @Autowired
    foodDao foodDao;

    public List<foodItemsDto> getAllFoodItems(){
        return (List<foodItemsDto>) foodDao.getAllFoodItems();
    }

    public void insertIntoFoodItems(List<foodItemsDto> foodItems){
        foodDao.insertIntoFoodItems(foodItems);
    }

    public List<foodDto> getAllFood(){
        return (List<foodDto>) foodDao.getAllFood();
    }

    public void insertFood(food food) {
        foodDao.insertFood(food);
    }


    public void insertFoodList(List<food> food) {
        foodDao.insertFoodList(food);
    }

    public  List<foodDto>  updateFood(food food) {
       return foodDao.updateFood(food);
    }

    public  List<foodDto>  updateFoodList(List<food> food) {
        return foodDao.updateFoodList(food);
    }

    public List<foodDto>  deleteFood(String foodName) {
        return foodDao.deleteFood(foodName);
    }

    public List<foodDto>  deleteFoodList(String[] foodName) {
        List<String> nameList = Arrays.asList(foodName);
        return foodDao.deleteFoodList(nameList);
    }

    public void  deleteAll() {
        foodDao.deleteAll();
    }

    public List<foodNameDto> getTodaysMenu(String day){
        return foodDao.getTodaysMenu(day);
    }


}
