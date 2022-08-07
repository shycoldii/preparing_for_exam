package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import repo.InjectionRepo;
import repo.TestRepo;
import test.Testtt;

import java.util.Optional;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.07.30
 */
@Service
public class InjectionServiceImpl implements InjectionService {

    private InjectionRepo repo;
    private Testtt repo2;
    private TestRepo testRepo;

    /**
     * DI через поле.
     */
    @Autowired(required = false)
    private Testtt testRepoField;

    /**
     * DI через конструктор.
     * Важно! @Autowired указывать обязательно, если более 1 конструктора в классе
     * , иначе упадет NoSuchMethodException для instantiate бина
     *
     * @param repo - репозиторий
     */

    @Autowired(required = false)
    public InjectionServiceImpl(Testtt repo) {
        this.repo2 = repo;
        System.out.println("I am the 1");
    }

    @Autowired(required = false)
    public InjectionServiceImpl(InjectionRepo repo1, @Value("5") String someValue) {
        //if(repo1.isPresent())
        System.out.println("I am the biggest");
        this.repo = repo1;
    }

    @Autowired(required = false)
    public InjectionServiceImpl(){
        System.out.println("I am here");
    }

    public InjectionRepo getRepo() {
        return repo;
    }

    public Testtt getRepo2() {
        return repo2;
    }

    /**
     * DI через сеттер.
     *
     * @param repo - репозиторий
     */
    @Autowired(required = false)
    public void setTestRepo(TestRepo repo) {
        this.testRepo = repo;
    }

    public TestRepo getTestRepo() {
        return testRepo;
    }

    public Testtt getTestRepoField() {
        return testRepoField;
    }
}
