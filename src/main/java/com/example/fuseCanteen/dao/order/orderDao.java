package com.example.fuseCanteen.dao.order;


import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.fuseCanteen.Model.order.*;
import com.example.fuseCanteen.Model.food.*;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.fuseCanteen.Model.user.*;
import com.example.fuseCanteen.dao.user.*;
import com.example.fuseCanteen.dao.food.*;
import com.example.fuseCanteen.dao.days.*;
import com.example.fuseCanteen.dao.foodRequest.*;
import javax.persistence.*;
import com.example.fuseCanteen.constant.*;
import com.example.fuseCanteen.constant.Paymentflag;


/**
 * Created by aishwarya on 9/5/20.
 */
@Repository
public class orderDao{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private foodsRepository foodsRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private scheduledOrderRepository scheduledOrderRepository;

    @Autowired
    private dayRepository dayRepository;

    @Autowired
    private employeeFoodRequestRepository employeeFoodRequestRepository;


    List<totalOrderEmployeeDto> totalOrderEmployeeDtos;
    List<orderDescEmployeeDto> orderDescEmployeeDtos;

    public static final Logger LOGGER = LoggerFactory.getLogger(orderDao.class);

    public  List<orderStatsDto> getOrderList(String date) {
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_orderList", "orderStats");
        storedProcedureQuery.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("dateWhen",date);
        return storedProcedureQuery.getResultList();
    }

    public  List<orderByPopularityDto> getItemsByPopularity(String date) {

        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_orderByPopularity", "orderByPopularity");
        storedProcedureQuery.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("dateWhen",date);
        storedProcedureQuery.registerStoredProcedureParameter("paymentFlag",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("paymentFlag",Paymentflag.PAID_FLAG);
        return storedProcedureQuery.getResultList();
    }

    public  orderTotalAndDesc getTotalByEmployee(String date, String empNum,String paymentFlag) {

        if(paymentFlag.equalsIgnoreCase("paid")){
            paymentFlag = Paymentflag.PAID_FLAG;
        }
        else if(paymentFlag.equalsIgnoreCase("unpaid")) {
            paymentFlag = Paymentflag.UNPAID_FLAG;
        }
        else{
            paymentFlag ="";
        }

        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_orderTotalByEmployee", "orderTotalByEmployee");
        storedProcedureQuery.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("dateWhen",date);
        storedProcedureQuery.registerStoredProcedureParameter("paymentFlag",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("paymentFlag",paymentFlag);
        storedProcedureQuery.registerStoredProcedureParameter("employeeNumber",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("employeeNumber",empNum);
        StoredProcedureQuery storedProcedureQuery1 = em.createStoredProcedureQuery("db_orderDescriptionByEmployee", "orderDescForEmployee");
        storedProcedureQuery1.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery1.setParameter("dateWhen",date);
        storedProcedureQuery1.registerStoredProcedureParameter("paymentFlag",String.class, ParameterMode.IN);
        storedProcedureQuery1.setParameter("paymentFlag",paymentFlag);
        storedProcedureQuery1.registerStoredProcedureParameter("employeeNumber",String.class, ParameterMode.IN);
        storedProcedureQuery1.setParameter("employeeNumber",empNum);
        orderDescEmployeeDtos =  storedProcedureQuery1.getResultList();
        totalOrderEmployeeDtos = storedProcedureQuery.getResultList();
        return new orderTotalAndDesc(totalOrderEmployeeDtos,orderDescEmployeeDtos);
    }

    public orderTotalAndDesc insertOrderByemployee(List<orderDescEmployeeDto> orderDescEmployeeDtoLists,String empNumber, String paymentFlag) {
        String Date = dateStr();
        insertOrderLists(Date,orderDescEmployeeDtoLists,empNumber);
        return  getTotalByEmployee(Date,empNumber,paymentFlag);
    }

    public void insertOrderLists(String Date,List<orderDescEmployeeDto> orderDescEmployeeDtoLists,String empNumber){
        users usersData ;
        usersData = userRepository.getUserByempNum(empNumber);
        List<orders> ordersList = new ArrayList<>();
        List<scheduledorders> scheduledOrderDtoList = new ArrayList<>();
        for (orderDescEmployeeDto orderDescEmployeeDto : orderDescEmployeeDtoLists) {
            orders orders = new orders();
            food food = new food();
//            Integer maxFood = foodsRepository.getMaxId();
            Integer orderId = orderRepository.getCount();
//            Integer countFoodData = foodsRepository.getCount();
//            Integer foodId = maxFood += 1;
            BigDecimal totalPrice = multiply(orderDescEmployeeDto.getFoodPrice(), orderDescEmployeeDto.getOrderQty());
            //            Integer userId = userRepository.getIdByEmployeeNumber(employeeNumber);
//            food.setId(countFoodData+=1);
//            food.setFoodName(orderDescEmployeeDto.getFoodName());
//            food.setFoodPrice(orderDescEmployeeDto.getFoodPrice());
//            food.setFoodCode("F0" + foodId);
//            food.setAddedDate(LocalDateTime.now());
//            foodsRepository.save(food);

            Integer foodId = foodsRepository.getIdByName(orderDescEmployeeDto.getFoodName());
            orders.setId(orderId+=1);
            orders.setOrderPrice(orderDescEmployeeDto.getFoodPrice());
            orders.setOrderQty(orderDescEmployeeDto.getOrderQty());
            orders.setFood(foodsRepository.getById(foodId));
            orders.setUsers(usersData);
            orders.setOrderQty(orderDescEmployeeDto.getOrderQty());
            orders.setOrderStatus(constants.ORDER_PENDING);
            orders.setOrderTime(LocalDateTime.now());
            orders.setTotalPrice(totalPrice);
            orders.setPaidStatus(constants.ORDER_PAID);
            orders.setEmployeeNumber(empNumber);
            ordersList.add(orders);
        }
        orderRepository.saveAll(ordersList);

    }


    public void insertScheduledOrderLists(List<orderScheduledEmployeeDto> orderScheduledEmployeeDtoList,String empNumber,String isScheduled){
        users usersData ;
        usersData = userRepository.getUserByempNum(empNumber);
        List<scheduledorders> scheduledOrderDtoList = new ArrayList<>();
        for (orderScheduledEmployeeDto orderScheduledEmployeeDto : orderScheduledEmployeeDtoList) {
            orders orders = new orders();
            Integer orderId = orderRepository.getCount();
            BigDecimal totalPrice = multiply(orderScheduledEmployeeDto.getFoodPrice(), orderScheduledEmployeeDto.getOrderQty());
            Integer foodId = foodsRepository.getIdByName(orderScheduledEmployeeDto.getFoodName());
            orders.setId(orderId+=1);
            orders.setOrderPrice(orderScheduledEmployeeDto.getFoodPrice());
            orders.setOrderQty(orderScheduledEmployeeDto.getOrderQty());
            orders.setFood(foodsRepository.getById(foodId));
            orders.setUsers(usersData);
            orders.setOrderQty(orderScheduledEmployeeDto.getOrderQty());
            orders.setOrderStatus(constants.ORDER_PENDING);
            orders.setOrderTime(LocalDateTime.now());
            orders.setTotalPrice(totalPrice);
            orders.setPaidStatus(constants.ORDER_PAID);
            orders.setEmployeeNumber(empNumber);
            orderRepository.save(orders);

            if(isScheduled.equalsIgnoreCase(constants.IS_SCHEDULED))
            {
                LocalDateTime localDateTime = localDateTime(orderScheduledEmployeeDto.getScheduledTime().toString());
                scheduledorders scheduledOrderDto = new scheduledorders();
                scheduledOrderDto.setOrders(orderRepository.getById(orderId+=1));
                scheduledOrderDto.setScheduledTime(localDateTime);
                scheduledOrderDtoList.add(scheduledOrderDto);
            }
        }
        if(isScheduled.equalsIgnoreCase(constants.IS_SCHEDULED)){
            scheduledOrderRepository.saveAll(scheduledOrderDtoList);
        }
    }

    public List<orderScheduledEmployeeDto> insertScheduledOrder(List<orderScheduledEmployeeDto> orderScheduledEmployeeDtos,String empNumber,String isScheduled){
        String Date = dateStr();
        insertScheduledOrderLists(orderScheduledEmployeeDtos,empNumber,isScheduled);
        return getAllScheduledOrderByEmployee(Date,empNumber);
    }

    public List<orderScheduledEmployeeDto> getAllScheduledOrderByEmployee(String Date,String empNum){
        List<orderScheduledEmployeeDto> orderScheduledEmployeeDtoList ;
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_orderScheduledByEmployee", "orderScheduledEmployee");
        storedProcedureQuery.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("dateWhen",Date);
        storedProcedureQuery.registerStoredProcedureParameter("employeeNumber",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("employeeNumber",empNum);
        orderScheduledEmployeeDtoList = storedProcedureQuery.getResultList();
        return orderScheduledEmployeeDtoList;
    }


    public List<orderStatsDto> updateOrderByEmp(List<orderStatsDto> orderStatsDtos,String empNum ) {
        String Date = dateStr();
        List<orders> ordersArrayList = new ArrayList<>();
        for (orderStatsDto orderStatsDto : orderStatsDtos) {
            orders orders = orderRepository.getById(orderStatsDto.getId());
            orders.setFood(foodsRepository.getByFoodName(orderStatsDto.getFoodName()));
            orders.setOrderQty(orderStatsDto.getOrderQty());
            orders.setOrderPrice(foodsRepository.getByFoodName(orderStatsDto.getFoodName()).getFoodPrice());
            orders.setTotalPrice( multiply(foodsRepository.getByFoodName(orderStatsDto.getFoodName()).getFoodPrice(), orderStatsDto.getOrderQty()));
            orders.setOrderTime(LocalDateTime.now());
            orders.setOrderStatus(orderStatsDto.getOrderStatus());
            orders.setPaidStatus(orderStatsDto.getPaidStatus());
            ordersArrayList.add(orders);
        }
        orderRepository.saveAll(ordersArrayList);
        return getAllOrderByEmployee(Date,empNum);
    }

    public List<orderStatsDto> deleteOrderByEmployee(Integer[] orderId,String empNum) {
        String Date = dateStr();
        List<Integer> orderList = Arrays.asList(orderId);
        for(Integer id : orderList){
            orderRepository.deleteById(id);
        }
        return getAllOrderByEmployee(Date,empNum);
    }

    public List<orderStatsDto> getAllOrderByEmployee(String date,String empNum){
        List<orderStatsDto> orderStatsDtoList;
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_orderListByEmployee", "orderStats");
        storedProcedureQuery.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("dateWhen",date);
        storedProcedureQuery.registerStoredProcedureParameter("employeeNumber",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("employeeNumber",empNum);
        orderStatsDtoList = storedProcedureQuery.getResultList();
        return orderStatsDtoList;
    }

    public orderTotalAndDesc getAllPreviousDayOrderByEmployee(String empNum){
        String date =dateStr();
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_previousDayOrdersByEmployee", "orderDescForEmployee");
        storedProcedureQuery.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("dateWhen",date);
        storedProcedureQuery.registerStoredProcedureParameter("employeeNumber",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("employeeNumber",empNum);
        StoredProcedureQuery storedProcedureQuery1 = em.createStoredProcedureQuery("db_previousTotalOrdersByEmployee", "orderTotalByEmployee");
        storedProcedureQuery1.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery1.setParameter("dateWhen",date);
        storedProcedureQuery1.registerStoredProcedureParameter("employeeNumber",String.class, ParameterMode.IN);
        storedProcedureQuery1.setParameter("employeeNumber",empNum);
        orderDescEmployeeDtos =  storedProcedureQuery.getResultList();
        totalOrderEmployeeDtos = storedProcedureQuery1.getResultList();
        return new orderTotalAndDesc(totalOrderEmployeeDtos,orderDescEmployeeDtos);
    }



    public List<employeeFoodRequestDto> insertFoodRequest(List<foodRequest> foodRequests,String empNum){
        String dayName = getDayName();
        List<employeeFoodRequest> employeeFoodRequestList = new ArrayList<>();
        for (foodRequest  foodRequest : foodRequests) {
            employeeFoodRequest employeeFoodRequestData = new employeeFoodRequest();
            employeeFoodRequestData.setFood(foodsRepository.getByFoodName(foodRequest.getFoodName()));
            employeeFoodRequestData.setUsers(userRepository.getUserByempNum(empNum));
            employeeFoodRequestData.setDays(dayRepository.getDayByDayName(dayName));
            employeeFoodRequestData.setDateRequested(LocalDateTime.now());
            employeeFoodRequestData.setRequestAddedBy(userRepository.getUserByempNum(empNum).getUserName());
            employeeFoodRequestData.setRequestStatus(constants.FOOD_REQUEST);
            employeeFoodRequestData.setRemarks(foodRequest.getRemarks());
            employeeFoodRequestList.add(employeeFoodRequestData);
        }
        employeeFoodRequestRepository.saveAll(employeeFoodRequestList);
        return getFoodReqByEmp(empNum);
    }


    public List<employeeFoodRequestDto> getFoodReqByEmp(String empNumber){
        String date = dateStr();
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("db_getFoodRequestByEmployee", "emplooyeeFoodRequest");
        storedProcedureQuery.registerStoredProcedureParameter("dateWhen",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("dateWhen",date);
        storedProcedureQuery.registerStoredProcedureParameter("empNum",String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("empNum",empNumber);
        return storedProcedureQuery.getResultList();
    }

    public List<employeeFoodRequestDto> updateFoodReq(List<foodRequest> foodRequests,String empNum ) {
        List<employeeFoodRequest> foodRequestArrayList = new ArrayList<>();
        for (foodRequest foodRequests1 : foodRequests) {
            employeeFoodRequest employeeFoodRequest = employeeFoodRequestRepository.getById(foodRequests1.getId());
            employeeFoodRequest.setFood(foodsRepository.getByFoodName(foodRequests1.getFoodName()));
            employeeFoodRequest.setDateModified(LocalDateTime.now());
            employeeFoodRequest.setRequestModifiedBy(userRepository.getUserByempNum(empNum).getUserName());
            employeeFoodRequest.setRequestStatus(constants.FOOD_REQUEST);
            employeeFoodRequest.setRemarks(foodRequests1.getRemarks());
            foodRequestArrayList.add(employeeFoodRequest);
        }
        employeeFoodRequestRepository.saveAll(foodRequestArrayList);
        return getFoodReqByEmp(empNum);
    }

    public List<employeeFoodRequestDto> deleteFoodReq(Integer[] foodReqId,String empNum) {
        List<Integer> foodReqIdList = Arrays.asList(foodReqId);
        for(Integer id : foodReqIdList){
            employeeFoodRequestRepository.deleteById(id);
        }
        return getFoodReqByEmp(empNum);
    }



    public BigDecimal multiply(BigDecimal orderPrice, Integer  Qty){
        MathContext mc = new MathContext(4); // 4 precision
        BigDecimal bg1 = new BigDecimal(Qty);
        BigDecimal totalPrice = bg1.multiply(orderPrice,mc);
        return totalPrice;
    }

    public String dateStr(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return  strDate;
    }

    public LocalDateTime localDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return  dateTime;
    }

    public String getDayName() {
        Calendar calendar = Calendar.getInstance();
        String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        String day = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        return day;
    }

}







