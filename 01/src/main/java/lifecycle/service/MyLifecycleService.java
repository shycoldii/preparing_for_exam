package lifecycle.service;

import lifecycle.repo.MyLifecycleRepo;
import lifecycle.repo.TheirLifecycleRepo;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.06
 */
@Service
public class MyLifecycleService implements LifecycleService, InitializingBean, DisposableBean {

    private MyLifecycleRepo myLifecycleRepo;

    @Autowired
    private TheirLifecycleRepo theirLifecycleRepo;


    public MyLifecycleService(MyLifecycleRepo myLifecycleRepo){
        System.out.println("MyLifecycleService");
        this.myLifecycleRepo = myLifecycleRepo;
        System.out.println(theirLifecycleRepo);
    }

    @PostConstruct
    public void myInit2(){
        System.out.println("post construct 2");
        System.out.println(theirLifecycleRepo);
    }

    @PostConstruct
    public void myInit1(){
        System.out.println("post construct 1");
        System.out.println(theirLifecycleRepo);
    }


    public void initMethod(){
        System.out.println("initMethod");
        System.out.println(theirLifecycleRepo);
    }

    public void destroyMethod(){
        System.out.println("destroy method");
        System.out.println(theirLifecycleRepo);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("after properties set");
        System.out.println(theirLifecycleRepo);
    }


    @Override
    public void destroy()  {
        System.out.println("destroy");
    }

    @PreDestroy
    public void myDestroy1(){
        System.out.println("Pre destroy 1");
    }

    @PreDestroy
    public void myDestroy2(){
        System.out.println("Pre destroy 2");
    }

}
