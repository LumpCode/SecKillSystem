package com.miaoshaproject.service.impl;


import com.miaoshaproject.dao.UserInfoEntityMapper;
import com.miaoshaproject.dao.UserPasswordEntityMapper;
import com.miaoshaproject.dataobject.UserInfoEntity;
import com.miaoshaproject.dataobject.UserPasswordEntity;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.UserInfoService;
import com.miaoshaproject.service.model.UserModel;
import com.miaoshaproject.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author liugan83@gmail.com
 * @version V1.0
 * @date 2019/1/14 21:55
 * @Description TODO
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Autowired
  private UserInfoEntityMapper userInfoEntityMapper;

  @Autowired
  private UserPasswordEntityMapper userPasswordEntityMapper;

  @Autowired
  private ObjectUtils objectUtils;

  /**
   * 根据用户ID获取用户信息
   * @param id 用户Id
   * @return 用户实体
   */
  @Override
  public UserModel getUserById(Integer id) {
    UserInfoEntity userInfoEntity = userInfoEntityMapper.selectByPrimaryKey(id);
    if (null == userInfoEntity) {
      return null;
    }
    UserPasswordEntity userPasswordEntity = userPasswordEntityMapper.selectByUserId(userInfoEntity.getId());
    return convertFromDataObject(userInfoEntity, userPasswordEntity);
  }

  /**
   * 用户注册
   *
   * @param userModel
   * @return
   */
  @Override
  @Transactional
  public Boolean userRegister(UserModel userModel) throws BusinessException {
    if (null == userModel) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }
    try {
      if (!objectUtils.checkObjFieldIsNull(userModel)) {
        throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    // 实现model -> dataObject方法
    UserInfoEntity userInfoEntity = convertFromModel(userModel);

    try {
      userInfoEntityMapper.insertSelective(userInfoEntity);
    } catch (DuplicateKeyException e) {
      e.printStackTrace();
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已重复注册");
    }

    userModel.setId(userInfoEntity.getId());

    UserPasswordEntity userPasswordEntity = convertPasswordFromModel(userModel);

    userPasswordEntityMapper.insertSelective(userPasswordEntity);

    return true;
  }

  /**
   * 用户登录
   *
   * @param telephone 电话
   * @param password  密码
   * @return
   */
  @Override
  public UserModel login(String telephone, String password) throws BusinessException {
    UserInfoEntity userInfoEntity = null;
    UserPasswordEntity userPasswordEntity = null;
    try {
      userInfoEntity = userInfoEntityMapper.selectByTelephone(telephone);
      userPasswordEntity = userPasswordEntityMapper.selectByUserId(userInfoEntity.getId());
    } catch (Exception e) {
      throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
    }

    if (null == userInfoEntity && null == userPasswordEntity &&
        password.equals(userPasswordEntity.getEncrptPassword())) {
      throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
    }

    UserModel userModel = convertFromDataObject(userInfoEntity, userPasswordEntity);
    return userModel;
  }


  private UserPasswordEntity convertPasswordFromModel(UserModel userModel) {
    if (null == userModel) {
      return null;
    }
    UserPasswordEntity userPasswordEntity = new UserPasswordEntity();
    userPasswordEntity.setEncrptPassword(userModel.getEncrptPassword());
    userPasswordEntity.setUserId(userModel.getId());
    return userPasswordEntity;
  }

  private UserInfoEntity convertFromModel(UserModel userModel) {
    if (null == userModel) {
      return null;
    }
    UserInfoEntity userInfoEntity = new UserInfoEntity();

    BeanUtils.copyProperties(userModel, userInfoEntity);
    userInfoEntity.setRegisterModel(userModel.getRegisterMode());
    userInfoEntity.setGender(userModel.getGender());

    return userInfoEntity;
  }

  /**
   * 实体model转换
   * @param userInfoEntity 用户信息实体
   * @param userPasswordEntity 用户密码实体
   * @return userModel
   */
  private UserModel convertFromDataObject(UserInfoEntity userInfoEntity, UserPasswordEntity userPasswordEntity) {
    if (null == userInfoEntity) {
      return null;
    }
    UserModel userModel = new UserModel();
    BeanUtils.copyProperties(userInfoEntity, userModel);
    if (null != userPasswordEntity) {
      userModel.setEncrptPassword(userPasswordEntity.getEncrptPassword());
    }
    return userModel;
  }
}
