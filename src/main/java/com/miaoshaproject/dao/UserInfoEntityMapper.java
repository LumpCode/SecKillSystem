package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.UserInfoEntity;

public interface UserInfoEntityMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_info
   *
   * @mbg.generated Mon Jan 14 22:46:22 CST 2019
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_info
   *
   * @mbg.generated Mon Jan 14 22:46:22 CST 2019
   */
  int insert(UserInfoEntity record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_info
   *
   * @mbg.generated Mon Jan 14 22:46:22 CST 2019
   */
  int insertSelective(UserInfoEntity record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_info
   *
   * @mbg.generated Mon Jan 14 22:46:22 CST 2019
   */
  UserInfoEntity selectByPrimaryKey(Integer id);

  UserInfoEntity selectByTelephone(String telephone);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_info
   *
   * @mbg.generated Mon Jan 14 22:46:22 CST 2019
   */
  int updateByPrimaryKeySelective(UserInfoEntity record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_info
   *
   * @mbg.generated Mon Jan 14 22:46:22 CST 2019
   */
  int updateByPrimaryKey(UserInfoEntity record);
}