package com.example.fuseCanteen.Helper;

import com.example.fuseCanteen.Model.food.foodItemsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.fuseCanteen.Model.food.*;
import com.example.fuseCanteen.dao.food.foodsRepository;

/**
 * Created by aishwarya on 9/4/20.
 */

public class objectToDtoParser {

    @Autowired
    foodsRepository foodsRepository;

    public static List<foodItemsDto> parseFoodObjectToList(List<Object[]> objects) {
        List<foodItemsDto> foodItemsDtoList = new ArrayList<foodItemsDto>();
        for (Object[] obj : objects) {
            foodItemsDto foodItemsDto = new foodItemsDto();
            foodItemsDto.setFoodCode((String) obj[0]);
            foodItemsDto.setFoodName((String) obj[1]);
            foodItemsDto.setFoodType((String) obj[2]);
            foodItemsDto.setFoodPrice((BigDecimal) obj[3]);
            foodItemsDtoList.add(foodItemsDto);
        }
        return foodItemsDtoList;
    }

    public static List<foodDto> parseFoodToObject(List<Object[]> objects) {
        List<foodDto> foodDtos = new ArrayList<foodDto>();
        for (Object[] obj : objects) {
            foodDto foodDto1 = new foodDto();
            foodDto1.setId((Integer) obj[0]);
            foodDto1.setFoodName((String) obj[1]);
            foodDto1.setFoodCode((String) obj[2]);
            foodDto1.setFoodPrice((BigDecimal) obj[3]);
            foodDto1.setAddedDate((Date) obj[4]);
            foodDto1.setModifiedDate((Date) obj[5]);
            foodDtos.add(foodDto1);
        }
        return foodDtos;
    }

    public static List<foodItemsMapping> parseFoodsItemsToTables(List<foodItemsDto> foodItemsDtos) {
        Date date = new Date();

        List<foodItemsMapping> foodItemsMappings = new ArrayList<foodItemsMapping>();
        for (foodItemsDto obj : foodItemsDtos) {
            foodItemsMapping foodItemsMappings1 = new foodItemsMapping();
            foodItems foodItems1 = new foodItems();
            foodItems1.setId(4);
            foodItems1.setFoodCode(obj.getFoodCode());
            foodItems1.setFoodName(obj.getFoodName());
            foodItems1.setAddedDate(date);
            foodItemsMappings1.setFoodItems(foodItems1);
            foodItemsMappings1.setFoodType(obj.getFoodType());
            foodItemsMappings1.setFoodPrice(obj.getFoodPrice());
            foodItemsMappings1.setAddedDate(date);
            foodItemsMappings1.setId(4);
            foodItemsMappings.add(foodItemsMappings1);
        }
        return foodItemsMappings;
    }

    public static List<foodNameDto> parseObjectToFoodMenuDto(List<Object[]> objects) {
        List<foodNameDto> foodMenuDtos = new ArrayList<foodNameDto>();
        for (Object[] obj : objects) {
            foodNameDto foodMenuDto = new foodNameDto();
            foodMenuDto.setFoodName((String ) obj[0]);
            foodMenuDto.setFoodPrice((BigDecimal) obj[1]);
            foodMenuDto.setDay((String ) obj[2]);
            foodMenuDtos.add(foodMenuDto);
        }
        return foodMenuDtos;
    }




}

//
//    public static List<foodItemsMapping> parseFoodsItemsMappingToTables(List<foodItemsDto> foodItemsDtos) {
//        Date date = new Date();
//        List<foodItemsMapping> foodItemsMappings = new ArrayList<foodItemsMapping>();
//
//        for (foodItemsDto obj : foodItemsDtos) {
//
//            foodItemsMapping foodItemsMappings1 = new foodItemsMapping();
//
//            foodItemsMappings1.setId(4);
//            foodItemsMappings1.setFoodType(obj.getFoodType());
//            foodItemsMappings1.setFoodPrice(obj.getFoodPrice());
//            foodItemsMappings1.setAddedDate(date);
//
//            foodItemsMappings.add(foodItemsMappings1);
//
//        }
//
//        return foodItemsMappings;
//    }
//    public static foodItems  persistFoodItems(foodItems foodItems) {
//        foodItemsMapping foodItemsMappings1 = new foodItemsMapping();
//        foodItemsMappings1.setFoodItems(foodItems);
//
//
//    }




//
//    public static List<foodItems> parseFoodsItemsToTables(List<foodItemsDto> foodItemsDtos) {
//        Date date = new Date();
//        List<foodItems> foodItems = new ArrayList<foodItems>();
//        for (foodItemsDto obj : foodItemsDtos) {
//
//            foodItems foodItems1 = new foodItems();
//            foodItems1.setFoodCode(obj.getFoodCode());
//            foodItems1.setFoodName(obj.getFoodName());
//            foodItems1.setAddedDate(date);
//
//            foodItems.add(foodItems1);
//
//        }
//
//        return foodItems;
//    }
//}