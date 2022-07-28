package _01;

import _01.config.TestConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import _01.service.TestService;

/**
 * Базовое приложение для демонстрации работы IoC Spring.
 *
 * Различные способы создания контекста приложения.
 * 1) Из main_config.xml с заданием всех бинов и их конструкторов (самый старый метод)
 *
 * 2) 2.5 версия (гибридная) - из gibrid_config.xml + Autowired на конструктор/поле или же @Bean на метод
 * Суть в том, что сокращается описание метаданных в XML файле - не нужно описывать конструкторы и прочее,
 * необходимо лишь подсказывать в коде аннотациями спрингу, где и какой бин.
 * <context:annotation-config> используется для активации аннотаций в beans,
 * уже зарегистрированных в контексте приложения
 * независимо от того, были ли они определены с помощью XML или сканирования пакетов).
 *
 * <context:component-scan> также может выполнять то,
 * что <context:annotation-config> делает, но <context:component-scan> также
 * сканирует пакеты, чтобы найти и зарегистрировать beans в контексте приложения.
 *
 * 3) 3 версия (полностью java-код) - TestConfig. Специально используется отдельная реализация, чтобы показать,
 * что нужно помечать сами классы аннотациями бинов, чтобы Spring распознал их.
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
public class Application {


    public static void main(String[] args) {

        var xmlContext = new ClassPathXmlApplicationContext("/_01/main_config.xml");
        TestService testServiceBean = xmlContext.getBean(TestService.class);
        System.out.println(testServiceBean.getRepo().getTestId());

        var gibridXmlContext = new ClassPathXmlApplicationContext("/_01/gibrid_config.xml");
        testServiceBean = gibridXmlContext.getBean(TestService.class);
        System.out.println(testServiceBean.getRepo().getTestId());

        var javaContext = new AnnotationConfigApplicationContext(TestConfig.class);
        testServiceBean = javaContext.getBean(TestService.class);
        System.out.println(testServiceBean.getRepo().getTestId());
    }
}
