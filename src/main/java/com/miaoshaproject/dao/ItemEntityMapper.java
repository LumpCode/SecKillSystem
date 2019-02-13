package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemEntity;

import java.util.List;

public interface ItemEntityMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table item
   *
   * @mbg.generated Tue Feb 12 20:29:46 CST 2019
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table item
   *
   * @mbg.generated Tue Feb 12 20:29:46 CST 2019
   */
  int insert(ItemEntity record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table item
   *
   * @mbg.generated Tue Feb 12 20:29:46 CST 2019
   */
  int insertSelective(ItemEntity record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table item
   *
   * @mbg.generated Tue Feb 12 20:29:46 CST 2019
   */
  ItemEntity selectByPrimaryKey(Integer id);

  List<ItemEntity> listItem();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table item
   *
   * @mbg.generated Tue Feb 12 20:29:46 CST 2019
   */
  int updateByPrimaryKeySelective(ItemEntity record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table item
   *
   * @mbg.generated Tue Feb 12 20:29:46 CST 2019
   */
  int updateByPrimaryKey(ItemEntity record);
}