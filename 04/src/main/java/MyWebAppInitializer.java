import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Настройка диспатчер сервлета (их может быть много и у каждого есть свой контекст, servletConfig для каждого)
 * Большой вариант: implements WebApplicationInitializer (более кастомно если хотим настроить веб приложение)
 *
 * @author Darya Alexandrova
 * @since 2022.11.20
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //указываем класс конфигурации
        return new Class[] {MyConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        //все запросы пересылаем на диспатчер сервлет
        return new String[]{"/"};
    }
}
