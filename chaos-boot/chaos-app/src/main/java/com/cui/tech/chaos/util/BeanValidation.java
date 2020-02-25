package com.cui.tech.chaos.util;

import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * bean校验工具类，此工具类目的是省略BindingResult对象和@Valid注解，可根据实际情况考虑是否使用
 *
 * @author xiaoshiyilang
 * @version 1.0
 * @date 2018/10/13
 */
public class BeanValidation<T> {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    private Set<ConstraintViolation<T>> errors;

    private T t;

    /**
     * 判断校验是否通过
     *
     * @return
     */
    public boolean hasError() {
        this.errors = validator.validate(t);
        if (errors != null && errors.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 带参构造，用来传递bean类型
     *
     * @param t
     */
    public BeanValidation(T t) {
        this.t = t;
    }

    /**
     * 获取当前错误信息
     *
     * @return
     */
    public String getError() {
        Iterator<ConstraintViolation<T>> it = errors.iterator();
        ConstraintViolation<T> oldObject = (ConstraintViolation<T>) it.next();
        return oldObject.getMessage();
    }

    /**
     * 获取所有错误信息
     *
     * @return
     */
    public List<ObjectError> getAllErrors() {
        List<ObjectError> list = new ArrayList<ObjectError>();
        Iterator<ConstraintViolation<T>> it = errors.iterator();
        while (it.hasNext()) {
            ConstraintViolation<T> oldObject = (ConstraintViolation<T>) it.next();
            ObjectError objectError = new ObjectError(oldObject.getPropertyPath().toString(), oldObject.getMessage());
            list.add(objectError);
        }
        return list;
    }

}
