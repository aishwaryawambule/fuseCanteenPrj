package com.example.fuseCanteen.Controller;

import com.example.fuseCanteen.constant.constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.fuseCanteen.Model.order.*;
import com.example.fuseCanteen.Model.food.*;

import java.util.List;
import org.slf4j.Logger;

/**
 * Created by aishwarya on 9/5/20.
 */

@RestController
@RequestMapping("/api")
public class orderRest {

    @Autowired
    com.example.fuseCanteen.Service.modelService.orderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(orderRest.class);

    @GetMapping("secure/order-list/{date}")
    public ResponseEntity getOrderList( @PathVariable String date) {
        try {
                List<orderStatsDto> orderStatsDtos = orderService.getOrderList(date);
                return ResponseEntity.status(200).body(orderStatsDtos);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to fetch order data ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @GetMapping("secure/order-by-popularity/{date}")
    public ResponseEntity getItemsByPopularity( @PathVariable String date) {
        try {
                List<orderByPopularityDto> orderByPopularityDtoList = orderService.getItemsByPopularity(date);
                return ResponseEntity.status(200).body(orderByPopularityDtoList);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to fetch order data by popularity ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }


    @GetMapping("user/historic-order-by-employee")
    public ResponseEntity getAllPreviousDayOrderByEmployee(@RequestParam String employeeNumber) {
        try {
                orderTotalAndDesc orderTotalAndDesc = orderService.getAllPreviousDayOrderByEmployee(employeeNumber);
                return ResponseEntity.status(200).body(orderTotalAndDesc);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to get historic order data by employee ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }


    @GetMapping("user/get-food-order-req")
    public ResponseEntity getFoodReqByEmp(@RequestParam String employeeNumber) {
        try {
                List<employeeFoodRequestDto> employeeFoodRequestDtoList = orderService.getFoodReqByEmp(employeeNumber);
                return ResponseEntity.status(200).body(employeeFoodRequestDtoList);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to get food order request ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PostMapping("user/order-by-employee")
    public ResponseEntity getTotalByEmployee(@RequestParam String date, @RequestParam String employeeNumber, @RequestParam String paymentFlag) {
        try {
                orderTotalAndDesc totalByEmployee = orderService.getTotalByEmployee(date, employeeNumber, paymentFlag);
                return ResponseEntity.status(200).body(totalByEmployee);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to fetch order data by popularity ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PostMapping("user/post-order-by-employee")
    public ResponseEntity insertOrderByemployee(@RequestBody List<orderDescEmployeeDto> orderDescEmployeeDtoList,@RequestParam String employeeNumber, @RequestParam String paymentFlag) {
        try {
                orderTotalAndDesc orderDescEmployeeDtos = orderService.insertOrderByemployee(orderDescEmployeeDtoList, employeeNumber, paymentFlag);
                return ResponseEntity.status(200).body(orderDescEmployeeDtos);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to insert order for employee ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }


    @PostMapping("user/post-food-request")
    public ResponseEntity insertFoodRequest(@RequestBody List<foodRequest> foodRequest, @RequestParam String employeeNumber) {
        try {
                List<employeeFoodRequestDto> employeeFoodRequestDtos = orderService.insertFoodRequest(foodRequest, employeeNumber);
                return ResponseEntity.status(200).body(employeeFoodRequestDtos);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to insert food order request ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PutMapping("user/update-order-by-employee")
    public ResponseEntity updateOrderByEmp(@RequestBody List<orderStatsDto> orderStatsDto, @RequestParam String employeeNumber) {
        try {
            List<orderStatsDto> orderStatsDtos = orderService.updateOrderByEmp(orderStatsDto, employeeNumber);
            return ResponseEntity.status(200).body(orderStatsDtos);

        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to update order for employee ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }


    @PutMapping("user/update-food-req")
    public ResponseEntity updateFoodReq(@RequestBody List<foodRequest> foodRequests, @RequestParam String employeeNumber) {
        try {
            List<employeeFoodRequestDto> orderStatsDtos = orderService.updateFoodReq(foodRequests, employeeNumber);
            return ResponseEntity.status(200).body(orderStatsDtos);

        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to update food req for employee ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }


    @DeleteMapping("user/delete-order-by-employee")
    public ResponseEntity deleteOrderByEmployee(@RequestParam String employeeNumber, @RequestBody Integer[] orderList) {
        try {
            List<orderStatsDto> orderStatsDtos = orderService.deleteOrderByEmployee(orderList, employeeNumber);
            return ResponseEntity.status(200).body(orderStatsDtos);

        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to delete order for employee ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @DeleteMapping("user/delete-food-request")
    public ResponseEntity deleteFoodReq(@RequestBody Integer[] foodReqId, @RequestParam String employeeNumber) {
        try {
            List<employeeFoodRequestDto> employeeFoodRequestDtoList = orderService.deleteFoodReq(foodReqId, employeeNumber);
            return ResponseEntity.status(200).body(employeeFoodRequestDtoList);

        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::  Unable to delete food request for employee ::::::::::::::::::::: " + e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PostMapping("user/post-schedueld-order-by-employee")
        public ResponseEntity insertScheduledOrder(@RequestBody List <orderScheduledEmployeeDto> orderScheduledEmployeeDto, @RequestParam String employeeNumber,@RequestParam String isScheduled){
            try {
                List<orderScheduledEmployeeDto> orderScheduledEmployeeDtoList = orderService.insertScheduledOrder(orderScheduledEmployeeDto, employeeNumber,isScheduled);
                return ResponseEntity.status(200).body(orderScheduledEmployeeDtoList);

            } catch (Exception e) {
                LOGGER.error(":::::::::::::::::::::::  Unable to post scheduled order for employee ::::::::::::::::::::: " + e.toString());
                return ResponseEntity.status(500).body(e.toString());
            }
        }

}