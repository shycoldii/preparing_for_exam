package service;

import config.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * After/afterReturning даже при void-функциях
 * Around до Before (могут существовать вместе) (т.е around будет внутри себя включать before при запуске функции)
 * Любое исключение можно (даже Error)
 *
 * @author Darya Alexandrova
 * @since 2022.08.17
 */
public class AspectApplication extends AspectServiceImpl {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectConfiguration.class);
        //proxy nature - если показать на две функции, подходящие под поинкат, то выполнится только первая
        //в данном случае, хоть и видит, что есть 2 кандидата, но адвайсы выполняет только при вызове первого

        context.getBean(AspectService.class).buyPotatoes();
        context.getBean(AspectService.class).buyPotatoes2();

        //выполняет и default, и protected
        context.getBean(AspectServiceImpl.class).buyPotatoes3();

        //не работает адвайс
        context.getBean(AspectServiceImpl.class).buyStaticPotatoes();

        //вот так не сработает, если не объявлять метод в интерфейсе при динамикПрокси
        context.getBean(AspectServiceImpl.class).buyPotatoesWithError();
        System.out.println("Завершаем");
    }
}
