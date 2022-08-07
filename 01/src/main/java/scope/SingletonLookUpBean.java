package scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.03
 */
@Component
public class SingletonLookUpBean {

    private PrototypeBean prototypeBean;
    private PrototypeProxyBean prototypeProxyBean;

    @Autowired
    private ObjectFactory<PrototypeBean> prototypeBeanObjectFactory;

    public SingletonLookUpBean(PrototypeBean prototypeBean, PrototypeProxyBean prototypeProxyBean) {
        System.out.println("Singleton instance created");
        this.prototypeBean = prototypeBean;
        this.prototypeProxyBean = prototypeProxyBean;
    }

    @Lookup
    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }

    public PrototypeBean getPrototypeInstance() {
        return prototypeBeanObjectFactory.getObject();
    }

    public PrototypeProxyBean getPrototypeProxyBean(){
        return prototypeProxyBean;
    }
}
