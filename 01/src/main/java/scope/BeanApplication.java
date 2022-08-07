package scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Практика по работе внедрения прототипов в синглтоны.
 * Проблема, так как контейнер инициализирует синглотон один раз, то ему нет необходимости
 * создавать новые экземпляры и для прототипов, которые входят в состав синглтона.
 *
 * 1. Можно внедрить ApplicationContext в синглтон и дергать из него прототип - ужасно, так как нарушается принцип IoC
 * 2. Lookup A method annotated with @Lookup tells Spring to return an instance of the method's return type when we invoke it.
 * Essentially, Spring will override our annotated method and use our method's return type and parameters as arguments to BeanFactory#getBean.
 * It will use CGLIB to generate the bytecode responsible for fetching the PrototypeBean from the application context.
 *
 * 3. ObjectFactory (тоже так себе вариант)
 * 4. Прокси
 * По умолчанию Spring содержит ссылку на реальный объект для выполнения инъекции. Здесь мы создаем прокси-объект для подключения реального объекта к зависимому.
 * Каждый раз, когда вызывается метод на прокси-объекте, прокси решает, создавать ли новый экземпляр реального объекта или повторно использовать существующий.
 *
 *
 * Есть и другие методы, через inject api (провайдер) и через java.util.Function
 *
 * @author Darya Alexandrova
 * @since 2022.08.03
 */
public class BeanApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);
        System.out.println(context.getBean(SingletonBean.class).getPrototypeBean());
        System.out.println(context.getBean(SingletonBean.class).getPrototypeBean());

        //System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeBean());
        //System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeBean());

        System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeInstance());
        System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeInstance());

        System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeProxyBean());
        System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeProxyBean());
        System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeProxyBean().getClass());
        System.out.println(context.getBean(SingletonLookUpBean.class).getPrototypeProxyBean().getClass());
    }
}
