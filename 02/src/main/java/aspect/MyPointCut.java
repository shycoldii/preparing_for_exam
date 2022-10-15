package aspect;

import org.aspectj.lang.annotation.Pointcut;
import service.AspectService;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.17
 */

public class MyPointCut {
    @Pointcut(value = "execution(* service.*Service.buyPotatoes(..))")
    public void beforeBuyPointCut(){}

    @Pointcut(value = "execution(* service.*Service.buyPotatoes(..)) && target(service)")
    public void beforeBuyPointCutDireclty(AspectService service){}
}
