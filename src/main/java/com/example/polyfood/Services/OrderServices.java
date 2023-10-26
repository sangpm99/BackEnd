package com.example.polyfood.Services;

import com.example.polyfood.Models.OrderDetail;
import com.example.polyfood.Models.User;
import com.example.polyfood.Repositories.OrderDetailRepository;
import com.example.polyfood.Repositories.OrdersRepository;
import com.example.polyfood.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.polyfood.Models.Orders;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServices {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;

    public int addNewOrder(Orders orders) {
        // 1. Lấy tất cả user
        List<User> users = userRepository.findAll();
        boolean isExistUser = false;
        int userId = -1;

        // 2. Kiểm tra có user nào có email trùng với email nhập vào không
        // Nếu có thì lấy Id của user đó
        for(User user: users) {
            if(user.getEmail() != null) {
                if((user.getEmail()).equals(orders.getEmail())) {
                    isExistUser = true;
                    userId = user.getUserId();
                    break;
                }
            }
        }

        // 3. Tạo mới user nếu chưa tồn tại email
        if(!isExistUser) {
            User user = new User();
            user.setFullName(orders.getFullName());
            user.setEmail(orders.getEmail());
            user.setPhone(orders.getPhone());
            user.setAddress(orders.getAddress());
            user.setAccountId(1);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdateAt(LocalDateTime.now());
            userRepository.save(user);

            userId = user.getUserId(); // Lấy Id của user mới tạo
        }

        // Tạo mới order
        Orders order = new Orders();
        order.setPaymentId(orders.getPaymentId());
        order.setUserId(userId);
        order.setOriginalPrice(orders.getOriginalPrice());
        order.setActualPrice(orders.getActualPrice());
        order.setFullName(orders.getFullName());
        order.setEmail(orders.getEmail());
        order.setPhone(orders.getPhone());
        order.setAddress(orders.getAddress());
        order.setOrderStatusId(1);
        order.setNote(orders.getNote());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());
        ordersRepository.save(order);

        return order.getOrderId();
    }

    public void addNewOrderDetail(OrderDetail orderDetail, int orderId) {
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setOrderid(orderId);
        orderDetail1.setProductId(orderDetail.getProductId());
        orderDetail1.setPriceTotal(orderDetail.getPriceTotal());
        orderDetail1.setQuantity(orderDetail.getQuantity());
        orderDetail1.setCreatedAt(LocalDateTime.now());
        orderDetail1.setUpdateAt(LocalDateTime.now());
        orderDetailRepository.save(orderDetail1);
    }
}