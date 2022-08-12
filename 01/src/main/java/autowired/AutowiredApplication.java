package autowired;

import autowired.config.AutowiredConfiguration;
import autowired.service.AutowiredService;
import autowired.service.PortService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Демонстрация работы autowired аннотации (noUniqueBeanDefinitionException)
 * 1. Смотрит по типу в указанных дефинишенах (у нас 2 типа AutowiredService => идет дальше по пунктам)
 * 2. Смотрит на Primary (Специально сделала у обоих) (можно использовать с @Bean)
 * 3. Смотрит на Qualifier (указываем на параметр в конструкторе имя бина с маленькой буквы)
 * Еще можно так: указываем рандомное имя mm и такой же квалифавер вешаем на сам класс (либо в самом компоненте имя)
 * Если указано и primary, и qualifier, то qualifier будет иметь приоритет (primary используют чаще для дефолтов)
 * 4. По имени бина (anotherAutowiredService) (дефолт, если нет других подсказок)
 * 5. Эксепшен
 *
 * @author Darya Alexandrova
 * @since 2022.08.07
 */
public class AutowiredApplication {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AutowiredConfiguration.class);
        context.registerShutdownHook();
        System.out.println(context.getBean(PortService.class).getMyAutowiredService());
    }
}
