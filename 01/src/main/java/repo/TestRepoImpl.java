package repo;

/**
 * Реализация репозитория через XML. Старый подход.
 *
 * @author Darya Alexandrova
 * @since 2022.07.28
 */
public class TestRepoImpl implements TestRepo {

    @Override
    public String getTestId() {
        return "1";
    }
}
