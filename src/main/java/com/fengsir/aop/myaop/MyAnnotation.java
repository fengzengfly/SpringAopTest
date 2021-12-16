package com.fengsir.aop.myaop;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
}
