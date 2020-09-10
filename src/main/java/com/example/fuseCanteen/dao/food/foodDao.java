package com.example.fuseCanteen.dao.food;

import com.example.fuseCanteen.Model.food.foodItemsMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import javax.persistence.Query;
import com.example.fuseCanteen.Model.food.*;
import com.example.fuseCanteen.Helper.objectToDtoParser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

/**
 * Created by aishwarya on 9/4/20.
 */


@Service
public class foodDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(foodDao.class);

    private Connection connection;

    @Autowired
    foodRepository foodRepository;

    @Autowired
    foodItemsRepositoty foodItemsRepositoty;

    @Autowired
    foodsRepository foodsRepository;

    @Autowired
    foodDayMappingRepository foodDayMappingRepository;

    @PersistenceContext
    private EntityManager em;


    public List<foodItemsDto> getAllFoodItems() {

        Query query = this.em.createNativeQuery("SELECT FOOD_CODE,FOOD_NAME,FOOD_TYPE,FOOD_PRICE FROM foodtypemapping fm JOIN fooditems fi on fi.FOOD_ID = fm.FOOD_ID\n");
        return objectToDtoParser.parseFoodObjectToList(query.getResultList());
    }

    public List<foodDto> getAllFood() {
        Query query = this.em.createNativeQuery("SELECT * FROM foods\n");
        return objectToDtoParser.parseFoodToObject(query.getResultList());
    }


    public void insertIntoFoodItems(List<foodItemsDto> foodItemsDtos) {
//        List<foodItems> foodItemsList = objectToDtoParser.parseFoodsItemsToTables(foodItemsDtos);
//        foodItemsRepositoty.saveAll(foodItemsList);
        List<foodItemsMapping> foodItemsMappings = objectToDtoParser.parseFoodsItemsToTables(foodItemsDtos);
        foodRepository.saveAll(foodItemsMappings);
    }

    public void insertFood(food food) {
        Integer count = foodsRepository.getCount();
        food.setId(count += 1);
        food.setAddedDate(LocalDateTime.now());
        foodsRepository.save(food);
    }

    public void insertFoodList(List<food> food) {

        Query query = this.em.createNativeQuery("SELECT COUNT(*) FROM foods");
        Integer count = Integer.parseInt(query.getSingleResult().toString());
        for (food obj : food) {
            obj.setId(count += 1);
            obj.setAddedDate(LocalDateTime.now());
            foodsRepository.save(obj);
        }
    }

    public List<foodDto> updateFood(food foodObj) {
        food food = foodsRepository.getById(foodObj.getId());
        food.setFoodName(foodObj.getFoodName());
        food.setFoodCode(foodObj.getFoodCode());
        food.setFoodPrice(foodObj.getFoodPrice());
        food.setModifiedDate(LocalDateTime.now());
        foodsRepository.save(food);
        return getAllFood();
    }

    public List<foodDto> updateFoodList(List<food> foodObj) {
        List<food> foodArrayList = new ArrayList<>();
        for (food foodList : foodObj) {
            food food = foodsRepository.getById(foodList.getId());
            food.setFoodName(foodList.getFoodName());
            food.setFoodCode(foodList.getFoodCode());
            food.setFoodPrice(foodList.getFoodPrice());
            food.setModifiedDate(LocalDateTime.now());
            foodArrayList.add(food);
        }
        foodsRepository.saveAll(foodArrayList);

        return getAllFood();
    }


    public  List<foodDto> deleteFood(String foodName) {
        Integer foodId = foodsRepository.getIdByName(foodName);
        food food = foodsRepository.getById(foodId);
        List<foodDayMapping> foodDayMappings = foodDayMappingRepository.getAllById(foodId);

        if(foodDayMappings != null) {
            for(foodDayMapping foodDayMapping: foodDayMappings){
                foodDayMappingRepository.delete(foodDayMappingRepository.getById(foodId));
            }
        }

        foodsRepository.delete(food);
        return getAllFood();
    }

    public List<foodDto> deleteFoodList(List<String> foodName) {

        Query query = this.em.createNativeQuery("select id from foods where FOOD_NAME in (:foodName)");
        query.setParameter("foodName", foodName);
        List<Integer> foodId = query.getResultList();

        Query query1 = this.em.createNativeQuery("SELECT * FROM fooddaymapping where FOOD_ID in (:foodId)");
        query1.setParameter("foodId", foodId);
        List<foodDayMapping> foodDayMappings = query1.getResultList();

        if(foodDayMappings != null) {
            for(foodDayMapping foodDayMapping: foodDayMappings){
                foodDayMappingRepository.deleteById(foodDayMapping.getId());
            }
        }

        for(Integer id : foodId){
            foodsRepository.deleteById(id);
        }

        return getAllFood();
    }


    public void deleteAll() {

        foodDayMappingRepository.deleteAll();
    }

    public List<foodNameDto> getTodaysMenu(String day){

        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_scheduledFoodMenu");
        storedProcedureQuery.registerStoredProcedureParameter("Day", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("Day",day);
        List<Object[]> queryResults = null;
        try {
            queryResults = storedProcedureQuery.getResultList();
        } catch (Exception ex) {
            LOGGER.error("::::::::::::::::::::: Error fetching food menu data :::::::::::::::::::::::::" + ex.toString());
        }
        List<foodNameDto> foodMenuDtos = objectToDtoParser.parseObjectToFoodMenuDto(queryResults);
        return foodMenuDtos;
    }
}


//    public void insertIntoFoodItems(List<foodItemsDto> foodItems){
//        List<foodItemsMapping> foodItemsMappings = objectToDtoParser.parseFoodsItemsToTables(foodItems);
//        foodRepository.saveAll(foodItemsMappings);
//    }




