package scope;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.03
 */
public class SingletonBean {
    private PrototypeBean prototypeBean;

    public SingletonBean(PrototypeBean prototypeBean) {
        System.out.println("Singleton instance created");
        this.prototypeBean = prototypeBean;
    }

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }
}
