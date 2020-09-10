package com.example.fuseCanteen.Controller;

import com.example.fuseCanteen.Model.user.users;
import com.example.fuseCanteen.Service.modelService.foodService;
import com.example.fuseCanteen.Model.food.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.fuseCanteen.constant.constants;

import java.util.List;

/**
 * Created by aishwarya on 9/4/20.
 */

@RestController
@RequestMapping("/api")
public class foodRest {

    @Autowired
    public foodService foodService;

    private static final Logger LOGGER = LoggerFactory.getLogger(users.class);

    /** Under Consideraiton not fully completed (W.I.P)
     * Uses table foodItems and foodTypeMapping
     * From here -,-
     * **/

    @GetMapping("public/all-food-names")
    public ResponseEntity getAllFoodItems() {
        try {
            List<foodItemsDto> usersList = foodService.getAllFoodItems();
            return ResponseEntity.status(200).body(usersList);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to fetch foodItems data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }


    @GetMapping("secure/all-food-names/{userId}")
    public ResponseEntity getAllFoodItemsByUserId(@PathVariable Integer userId) {
        try {
            if (userId == constants.TRUE_VAL) {
                List<foodItemsDto> usersList = foodService.getAllFoodItems();
                return ResponseEntity.status(200).body(usersList);
            }
            else {
                return ResponseEntity.status(405).body("Not Authorized to access");
            }
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::: Unable to fetch foodItems by userId ::::::::::::::::::::: "  + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }

    }


    @PostMapping("secure/insert-food-by-type/{userId}")
    public ResponseEntity insertIntoFoodItems(@PathVariable Integer userId,@RequestBody List<foodItemsDto> foodItems) {
        try {
            if (userId == constants.TRUE_VAL) {

                foodService.insertIntoFoodItems(foodItems);
                return ResponseEntity.status(200).body("inserted successfully");
            }
            else {
                return ResponseEntity.status(405).body("Not Authorized to access");
            }
        } catch (NullPointerException e) {
            LOGGER.error(":::::::::::::::::::::::: Unable to insert into foodItems for userId ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }

    }

    /** Till here W.I.P **/


    @GetMapping("public/all-foods")
    public ResponseEntity getAllFood() {
        try {
            List<foodDto> food = foodService.getAllFood();
            return ResponseEntity.status(200).body(food);
        }
        catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to fetch foods data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PostMapping("secure/insert-food")
    public ResponseEntity insertFood(@RequestBody food food) {
        try {
                foodService.insertFood(food);
                return ResponseEntity.status(200).body("inserted successfully");

        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to insert food data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PostMapping("secure/insert-food-list")
    public ResponseEntity insertFoodList(@RequestBody List<food> food) {
        try {
                foodService.insertFoodList(food);
                return ResponseEntity.status(200).body("inserted successfully");
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to insert foods data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PutMapping("secure/update-food")
    public ResponseEntity updateFood(@RequestBody food food) {
        try {
                List<foodDto>  foodItemsDtos = foodService.updateFood(food);
                return ResponseEntity.status(200).body(foodItemsDtos);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to update food data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PutMapping("secure/update-food-list")
    public ResponseEntity updateFood(@RequestBody List<food> food) {
        try {
                List<foodDto>  foodItemsDtos = foodService.updateFoodList(food);
                return ResponseEntity.status(200).body(foodItemsDtos);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to update foods data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @DeleteMapping("secure/delete-food/{foodName}")
    public ResponseEntity deleteFood(@PathVariable String foodName ) {
        try {
                List<foodDto>  foodItemsDtos = foodService.deleteFood(foodName);
                return ResponseEntity.status(200).body(foodItemsDtos);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to delete food data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @DeleteMapping("secure/delete-food-list/{foodName}")
    public ResponseEntity deleteFoodList(@PathVariable String[] foodName ) {
        try {
                List<foodDto>  foodItemsDtos = foodService.deleteFoodList(foodName);
                return ResponseEntity.status(200).body(foodItemsDtos);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to delete given food data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @DeleteMapping("secure/delete-food-all")
    public ResponseEntity deleteFoodList(@PathVariable Integer userId) {
        try {
                foodService.deleteAll();
                return ResponseEntity.status(200).body("Deleted Successfully");
            } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to delete all food data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @GetMapping("public/todays-menu/{day}")
    public ResponseEntity getTodaysMenu(@PathVariable String day) {
        try {
                List<foodNameDto>  foodMenuDtoList = foodService.getTodaysMenu(day);
                return ResponseEntity.status(200).body(foodMenuDtoList);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to fetch today's Food Menu ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

}


