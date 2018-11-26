package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    /**
     * 多对多关系
     * 通过orderId查询traveller
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{id})")
    List<Traveller> travellerByOrderid();

}
