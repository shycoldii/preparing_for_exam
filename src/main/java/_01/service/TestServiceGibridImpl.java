package _01.service;

import _01.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
public class TestServiceGibridImpl implements TestService {

    //@Autowired
    private TestRepo testRepo;

    public TestServiceGibridImpl(@Autowired TestRepo testRepo){
        this.testRepo = testRepo;
    }

    @Override
    //@Bean
    public TestRepo getRepo() {
        return testRepo;
    }
}
