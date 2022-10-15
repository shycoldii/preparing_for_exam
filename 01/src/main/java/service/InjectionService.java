package service;

import repo.InjectionRepo;
import repo.TestRepo;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.07.31
 */
public interface InjectionService {

    InjectionRepo getRepo();

    TestRepo getTestRepo();
}
