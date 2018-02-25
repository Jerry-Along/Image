package com.along.image.test;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by mip on 2018/2/25.
 */

public class Invocation {

    Animal dog=new Dog();
    Animal cat=new Cat();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface urlString{

    }

    public void dynamicpProxy(){
        Animal animal = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //调用代理的方法
                System.out.println("调用的方法："+method.getName());
                //获取注解
//                method.getAnnotation()
                //获取返回值的类型
                ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
                Type type = returnType.getActualTypeArguments()[0];
                //Call<type>  (class)type
                Object invoke = method.invoke(dog, args);
                return invoke;
            }
        });

        animal.setEat("1");
        animal.setJump("2");

    }

}
