package profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Empty (without @profile) = everywhere (даже при указании профайла)
 * Просто создать контекст как обычно не получится, так как нужен рефреш, а его
 * можно делать только 1 раз (поэтому сперва создаем пустой контекст)
 * <p>
 * в бин дефинишины попадают только профайленные файлы
 * <p>
 * Если указать несуществующий активный профайл, то не упадет
 * <p>
 * внутренние бины конфига создаются в зависимости от профилирования
 *
 * @author Darya Alexandrova
 * @since 2022.08.12
 */
public class ProfileApplication {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfiguration.class);
        context.refresh();
    }
}
