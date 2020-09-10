package com.example.fuseCanteen.Service.modelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fuseCanteen.dao.order.*;
import com.example.fuseCanteen.Model.order.*;
import com.example.fuseCanteen.Model.food.*;

import java.util.List;

/**
 * Created by aishwarya on 9/5/20.
 */

@Service
public class orderService {

    @Autowired
    orderDao orderDao;

    public List<orderStatsDto> getOrderList(String  date){
        return orderDao.getOrderList(date);
    }

    public List<orderByPopularityDto> getItemsByPopularity(String  date){
        return orderDao.getItemsByPopularity(date);
    }


    public orderTotalAndDesc getTotalByEmployee( String date,  String employeeNumber,String paymentFlag){
        return orderDao.getTotalByEmployee(date,employeeNumber,paymentFlag);
    }

    public orderTotalAndDesc insertOrderByemployee(List<orderDescEmployeeDto> orderDescEmployeeDtoList, String employeeNumber,String paymentFlag ){
        return orderDao.insertOrderByemployee(orderDescEmployeeDtoList,employeeNumber,paymentFlag);
    }

    public List<orderStatsDto> updateOrderByEmp(List<orderStatsDto> orderStatsDtos, String employeeNumber ){
        return orderDao.updateOrderByEmp(orderStatsDtos,employeeNumber);
    }

    public List<orderStatsDto> deleteOrderByEmployee(Integer[] id, String employeeNumber ){
        return orderDao.deleteOrderByEmployee(id,  employeeNumber );
    }

    public List<orderScheduledEmployeeDto> insertScheduledOrder(List<orderScheduledEmployeeDto> orderDescEmployeeDtos, String employeeNumber,String isScheduled){
        return orderDao.insertScheduledOrder(orderDescEmployeeDtos,  employeeNumber,isScheduled );
    }

    public orderTotalAndDesc getAllPreviousDayOrderByEmployee(String employeeNumber){
        return orderDao.getAllPreviousDayOrderByEmployee(employeeNumber);
    }

    public List<employeeFoodRequestDto> insertFoodRequest(List<foodRequest> foodRequests, String employeeNumber ){
        return orderDao.insertFoodRequest(foodRequests,employeeNumber);
    }

    public  List<employeeFoodRequestDto> getFoodReqByEmp(String employeeNumber){
        return orderDao.getFoodReqByEmp(employeeNumber);
    }

    public  List<employeeFoodRequestDto> updateFoodReq(List<foodRequest> foodRequests,String employeeNumber){
        return orderDao.updateFoodReq(foodRequests,employeeNumber);
    }

    public List<employeeFoodRequestDto> deleteFoodReq(Integer[] id, String employeeNumber ){
        return orderDao.deleteFoodReq(id,  employeeNumber );
    }


}
