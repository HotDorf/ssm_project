package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> finAll() throws Exception;

    /**
     * 添加产品
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void addProduct(Product product);

    /**
     * 根据id找product
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product ProductById(String id) throws Exception;


}
