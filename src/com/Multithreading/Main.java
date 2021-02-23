package com.Multithreading;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

//динамический прокси - имплементирует интерфейсы выбранных классов
public class Main {

    public static void main(String[] args) {
        // Наш класс наследует InvocationHandler и передаёт туда Int=5
	    InvocationHandler handler = new MyProxy(new Integer(5));

	    //запихиваем интерфейсы которые хотим
	    Class[] classes = new Class[] {Comparable.class, Callable.class};

	    //Объект = (класс лоадер, список интерфейсов, объект который создаем)
	    Object proxy = Proxy.newProxyInstance(null, classes, handler);
	    
        System.out.println(proxy.getClass());
	    proxy.toString();
    }

    static class MyProxy implements InvocationHandler{
        Object target;

        //получаем Int=5
        public MyProxy(Object target){
            this.target = target;
        }

        // вызвать методы оригинального класса
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(args);
            return method.invoke(target, args);
        }
    }
}
