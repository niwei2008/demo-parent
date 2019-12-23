package com.mixin.demo.ssm.dao.generated;

import com.mixin.demo.ssm.entity.UserDomain;
import com.mixin.demo.ssm.entity.UserDomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface UserDomainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    long countByExample(UserDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    int deleteByExample(UserDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    @Delete({
        "delete from t_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    @Insert({
        "insert into t_user (user_name, password, ",
        "phone)",
        "values (#{userName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insert(UserDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    int insertSelective(UserDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    List<UserDomain> selectByExample(UserDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    @Select({
        "select",
        "user_id, user_name, password, phone",
        "from t_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("com.mixin.demo.ssm.dao.generated.UserDomainMapper.BaseResultMap")
    UserDomain selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserDomain record, @Param("example") UserDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    int updateByExample(@Param("record") UserDomain record, @Param("example") UserDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    int updateByPrimaryKeySelective(UserDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 23 17:05:52 CST 2019
     */
    @Update({
        "update t_user",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserDomain record);
}