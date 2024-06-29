package openschool.java.aop.executiontime.aspect.annotation;

import openschool.java.aop.executiontime.aspect.TrackingAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, указывающая, что время выполнения аннотированного метода должно быть отслежено.
 * Эта аннотация используется для пометки методов, время выполнения которых должно быть отслежено и записано.
 * Аннотация применяется только к методам, а собранные данные будут включать имя класса,
 * имя метода и продолжительность выполнения метода.
 *
 * @see TrackAsyncTime
 * @see TrackingAspect
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackTime {
}
