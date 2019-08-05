package com.test.impl;


import com.test.entity.User2;
import com.test.entity.User3;
import com.test.mapper.User2Mapper;
import com.test.mapper.User3Mapper;
import com.test.service.TestBService;
import com.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuyachao on 2018/9/3.
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private User2Mapper user2Mapper;

    @Autowired
    private User3Mapper user3Mapper;

    @Autowired
    private TestBService testBService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    List<TransactionStatus> transactionStatuses = Collections.synchronizedList(new ArrayList<TransactionStatus>());

    int count = 112;

    static int countTest = 0;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int saveUser2(User2 user2) {
        Integer result = 0;
        try{
            result = user2Mapper.insertSelective(user2);
            //int i = 1/0;
            if(user2.getId() == 114){
                int i = 1/0;
            }
        }catch (Exception e){
            log.error("插入异常",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        return result;
    }

    @Override
    public User3 getUser3List(User3 user3) {
        User3 result =user3Mapper.selectByPrimaryKey(user3.getId());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void threadMethod(){
        User2 user1 = new User2();
        user1.setId(111);
        user1.setPassword("1");
        user1.setUsername("1");
        try{
            // 使用这种方式将事务状态都放在同一个事务里面
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            transactionStatuses.add(status);
            testBService.saveUser2(user1);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        System.out.println("main insert is over");
        try{
            for(int a=0 ;a<3;a++){
                ThreadOperation threadOperation= new ThreadOperation();
                Thread innerThread = new Thread(threadOperation);
                /*innerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e){
                        *//*throw new RuntimeException();
                        log.error("###内部线程发生异常");
                        e.printStackTrace();*//*
                        // 这边回滚不好使，需要用逻辑删除处理增加的数据
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                });*/
                innerThread.start();
            }
        }catch (Exception e){
            log.error("###线程异常");
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    public class ThreadOperation implements Runnable {
        @Override
        public void run() {
            try{
                // 使用这种方式将事务状态都放在同一个事务里面
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                transactionStatuses.add(status);
                User2 user2 = new User2();
                user2.setId(count++);
                user2.setPassword("10");
                user2.setUsername("10");
                /**
                 * 1.这里如果用其他类的saveUser2方法，在这个线程内事务生效，其他线程不受影响
                 * 2.如果是用本类的方法，这个线程内的事务不生效，其他线程也不受影响
                 */
                testBService.saveUser2(user2); // testBService.
                System.out.println("thread insert is over");
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                //throw new RuntimeException();
                // 事务回滚不管用
                /*TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new RuntimeException();*/
                for (TransactionStatus transactionStatus:transactionStatuses) {
                    transactionStatus.setRollbackOnly();
                }
            }
        }
    }

    /**
     * 多线程争夺全局资源
     * @param args
     */
    public static void main(String[] args){
        for(int a=0 ;a<100;a++){
            ThreadOperation2 threadOperation2 = new ThreadOperation2();
            Thread innerThread = new Thread(threadOperation2);
            innerThread.start();
        }
        System.out.println(countTest);
    }

    public static class ThreadOperation2 implements Runnable {
        @Override
        public void run() {
            countTest++;
        }
    }

}
