package lazy;

import lazy.config.LazyConfiguration;
import lazy.service.LazyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Демонстрация работы lazy-инициализации.
 *
 * Если стереотип будет lazy, то он не попадет изначально в контекст.
 * После обращения появляется.
 *
 * Есть несколько способов (lazy+configuration дает действие только на @Bean, объявленные внутри)
 * Т.е. если используем не @Bean, а @ComponentScan, то используем lazyInit=true
 *
 * 2) lazy+component
 * 3) lazy+bean
 * 4) lazy+autowired
 *
 * Если использую autowired на конструкторе + lazy на инжекте, то упадет
 * NoSuchBeanDefinitionException: No qualifying bean of type 'lazy.repo.LazyRepo'
 * или если autowired(required=false) на конструктор
 *
 *
 *
 * @author Darya Alexandrova
 * @since 2022.08.09
 */
public class LazyApplication {
    public static void main(String[] args) {
      var context = new AnnotationConfigApplicationContext(LazyConfiguration.class);
        System.out.println(context.getBean(LazyService.class));
    }
}
