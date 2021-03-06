package com.guigarage.dynamicvalidation;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface BeanValidator<T> {

    <V> BeanValidator<T> withProperty(String propertyName, Supplier<V> valueSupplier);

    <V> BeanValidator<T> withProperty(String propertyName, Supplier<V> valueSupplier, Consumer<Set<ConstraintViolation<V>>> propertyViolationConsumer);

    <U> BeanValidator<T> withBeanValidator(String propertyName, BeanValidator<U> propertyValidator);

    Set<ConstraintViolation<T>> validate(Class<?>... groups);

    Set<ConstraintViolation<T>> validate(T baseBean, Class<?>... groups);

    static <U> BeanValidator<U> build(Class<U> beanClass) {
        return null;
    }
}
