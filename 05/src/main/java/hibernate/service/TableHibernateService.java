package hibernate.service;

import hibernate.model.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pure_jpa.model.User;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@Service
public class TableHibernateService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveTable(Table table) {
        Session session = sessionFactory.getCurrentSession();
        session.save(table);
    }


    @Transactional //создает сессию до currentSession
    public void persistTable(Table table){
        Session session = sessionFactory.getCurrentSession();
        session.persist(table);
    }

}
