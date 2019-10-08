package com.jswl.portal.dao;

import com.jswl.portal.entity.Role;
import com.jswl.portal.entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role
     *
     * @mbggenerated Thu Sep 19 15:31:21 CST 2019
     */
    int updateByPrimaryKey(Role record);
}