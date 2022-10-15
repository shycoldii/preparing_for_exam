package service;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;
import repo.TestRepo;

/**
 * Реализация сервиса, используя аннотации и конфигурацию
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
//@Service //todo поставила комментарий, чтобы не мешало (в дальнейшем нужно будет отдельные кейсы в разные пакеты разносить)
public class TestServiceWithBeanImpl implements TestService {

    private TestRepo testRepo;

    public TestServiceWithBeanImpl(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @Override
    public TestRepo getRepo() {
        return testRepo;
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Destroying");
    }
}
