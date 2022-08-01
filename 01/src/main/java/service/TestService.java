package service;

import repo.TestRepo;

/**
 * Базовый сервис для демонстрации работы контекста.
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
public interface TestService {
    TestRepo getRepo();
}
