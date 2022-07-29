package service;

import org.springframework.beans.factory.annotation.Autowired;
import repo.TestRepo;

/**
 * Реализация сервиса гибридно. Используется и XML, и аннотация.
 *
 * Из плюсов: не нужно описывать аргументы инжектов в файле.
 * Из минусов: неудобно волноваться и о файле, и о аннотациях
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
public class TestServiceGibridImpl implements TestService {

    //@Autowired
    private TestRepo testRepo;

    public TestServiceGibridImpl(@Autowired TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @Override
    //@Bean
    public TestRepo getRepo() {
        return testRepo;
    }
}
