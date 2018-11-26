package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    /**
     * 根据Id查询Member
     * @return
     */
    @Select("select * from member where id = #{id}")
    Member memberById(String id);

}
