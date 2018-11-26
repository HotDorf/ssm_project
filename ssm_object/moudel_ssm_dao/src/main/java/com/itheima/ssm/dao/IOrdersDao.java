package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查找所有订单
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(column = "productId", property = "product", one = @One(select = "com.itheima.ssm.dao.IProductDao.ProductById"))
    })
    List<Orders> finAll();

    /**
     * 根据order的Id查询详情
     * 包括订单、商品、会员、旅客
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "productId", property = "product", one = @One(select = "com.itheima.ssm.dao.IProductDao.ProductById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(select = "com.itheima.ssm.dao.IMemberDao.memberById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.travellerByOrderid"))
    })
    Orders OrderById(String id);

}
