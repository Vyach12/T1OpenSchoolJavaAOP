package openschool.java.aop.executiontime.aspect.annotation;

import openschool.java.aop.executiontime.aspect.TrackingAsyncAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, указывающая, что время выполнения аннотированного метода должно быть отслежено асинхронно.
 * Эта аннотация используется для пометки методов, время выполнения которых должно быть отслежено и записано асинхронно.
 * Аннотация применяется только к методам, а собранные данные будут включать имя класса,
 * имя метода и продолжительность выполнения метода.
 *
 * @see TrackTime
 * @see TrackingAsyncAspect
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackAsyncTime {
}
