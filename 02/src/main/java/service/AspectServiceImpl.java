package service;

import org.springframework.stereotype.Service;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.17
 */
@Service
public class AspectServiceImpl implements AspectService {

    @Override
    public void buyPotatoes() {
        System.out.println("Картошка куплена!");
    }

    @Override
    public void buyPotatoes2() {
        buyPotatoes();
        System.out.println("Картошка куплена 2!");
    }

    public void buyPotatoesWithError()  {
        System.out.println("Картошка  не куплена!");
        throw new Error();
    }


    protected void buyPotatoes3() {
        System.out.println("Картошка куплена 3!");
    }

    public static void buyStaticPotatoes(){
        System.out.println("static potatoes");
    }


}
