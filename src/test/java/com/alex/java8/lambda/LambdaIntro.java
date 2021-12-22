package com.alex.java8.lambda;

import com.alex.java8.lambda.entity.Employee;
import org.testng.annotations.Test;

import java.util.*;

public class LambdaIntro {
    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    /*原来的匿名内部类写法*/
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    /*Lambda 表达式写法*/
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }


    /*需求：获取当前公司中员工年龄大于35的员工信息*/
    /*常规写法*/
    private List<Employee> filterEmployee(List<Employee> list) {
        ArrayList<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() >= 35) {
                emps.add(employee);
            }
        }
        return emps;
    }

    @Test
    public void test3() {
        List<Employee> emps = filterEmployee(this.employees);
        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    /*需求：获取当前公司中员工工资大于5000的员工信息*/
    private List<Employee> filterEmployee2(List<Employee> list) {
        ArrayList<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getSalary() >= 5000) {
                emps.add(employee);
            }
        }
        return emps;
    }
    /*如果再有这些需求，主要修改的就一句，所以其他都是冗余代码*/


    /*设计模式优化*/



}
