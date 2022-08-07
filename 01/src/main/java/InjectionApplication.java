import config.InjectionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import service.InjectionService;
import service.InjectionServiceImpl;


/**
 * Идея такая: работа аннотации autowired с required=false
 * Создала фиктивный класс Testtt и поместила в отдаленный пакет,
 * в конфиге его нет. Зависимость не подтянется, если конструктор не единственный (в нашем случае основной будет с InjectionRepo, до которого есть путь)
 * <p>
 * Если же везде сделать false и убрать из конфига пути, то будет падать NoSuchbeanDefinition
 * Т.е. при единственном конструкторе required=false без дефинишенов в конфиге не сработает
 * <p>
 * Есть вариант: ставить @Nullable (тогда Спринг сможет поднять контекст)
 * или Optional (для java8)
 * или свой дефолт конструктор пустой (но без аннотации или required=false!)
 * https://stackoverflow.com/questions/24723403/spring-3-2-annotation-autowiring-with-multiple-constructors
 * <p>
 * fun fact: спринг умный и будет всегда стараться выбирать наиболее полезный конструктор (где больше аргументов)
 * , но в случае пустого конструктора выберет его
 *
 * @author Darya Alexandrova
 * @since 2022.07.30
 */
public class InjectionApplication {
    private Logger logger = LoggerFactory.getLogger(InjectionApplication.
            class);

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(InjectionConfig.class);
        context.registerShutdownHook();
        System.out.println(context.getBean(InjectionService.class).getTestRepo());
        //не очень правильный каст, но чисто показать суть, что зависимости не подтянутся (по-хорошему, методы стоило вынести в интерфейс, но я поленилась)
        System.out.println(((InjectionServiceImpl) context.getBean(InjectionService.class)).getRepo2());
        System.out.println(((InjectionServiceImpl) context.getBean(InjectionService.class)).getTestRepoField());
    }
}
