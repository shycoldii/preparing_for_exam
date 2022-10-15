package service;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.17
 */
public interface AspectService {

    void buyPotatoes();
    void buyPotatoes2();
    //законмментировала, чтобы увидеть, что cglib справится
    //void buyPotatoesWithError() throws Exception;
}
