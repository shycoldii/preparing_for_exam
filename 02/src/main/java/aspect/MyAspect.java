package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import service.AspectService;

import java.util.Arrays;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.17
 */
@Component
@Aspect
public class MyAspect {

    @Before("aspect.MyPointCut.beforeBuyPointCut()")
    public void beforeBuyPotatoesAdvice(JoinPoint joinPoint) {
        System.out.println(joinPoint.getTarget().toString());
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getStaticPart());
        System.out.println("BEFORE");
    }

    //пример с target и args directlry
    //если хотим и joinPont, то он должен быть первым параметром!
    @Before("aspect.MyPointCut.beforeBuyPointCutDireclty(service2)")
    public void beforeBuyPotatoesAdviceDirectly(JoinPoint jointPoint, AspectService service2) {
        System.out.println(service2.toString());
        System.out.println("BEFORE DIRECTLY");
    }

    @After("execution(* service.*Service.buyPotatoes(..))")
    public void afterBuyPotatoesAdvice(JoinPoint joinPoint) {
        System.out.println("AFTER");
    }

    @AfterReturning("execution(* service.*Service.buyPotatoes(..))")
    public void afterReturningBuyPotatoesAdvice(JoinPoint joinPoint) {
        System.out.println("AFTER RETURNING");
    }

    @AfterThrowing(value = "execution(* service.*ServiceImpl.buyPotatoesWithError(..))", throwing = "myError")
    public void afterThrowingBuyPotatoesAdvice(JoinPoint joinPoint, Throwable myError) {
        System.out.println("AFTER THROWING");
        throw new RuntimeException();
    }

    @Around("execution(* service.*Service.buyPotatoes(..))")
    public Object aroundBuyPotatoesAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("AROUND");
        Object result = joinPoint.proceed();
        System.out.println("END AROUND");
        //result null при void
        return result;
    }

    @Before("execution(* service.*ServiceImpl.buyPotatoes3(..))")
    public void beforeBuyPotatoesAdvice3(JoinPoint joinPoint) {
        System.out.println(joinPoint.getTarget().toString());
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getStaticPart());
        System.out.println("BEFORE 3");
    }

    @Before("execution(* service.*ServiceImpl.buyStatic*(..))")
    public void beforeBuyStatic(JoinPoint joinPoint) {
        System.out.println("BEFORE STATIC");
    }
}
