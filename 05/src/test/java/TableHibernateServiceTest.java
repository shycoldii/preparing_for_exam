import config.TestHibernateConfig;
import hibernate.model.Table;
import hibernate.service.TableHibernateService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.10.29
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestHibernateConfig.class)
public class TableHibernateServiceTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    TableHibernateService hibernateService;

    @Test
    public void testGetAllFromTable() {
        Table table = new Table("1");

        hibernateService.saveTable(table);
        Session session = sessionFactory.openSession();
        //HQL
        List<Table> tables = session.createQuery("FROM Table", Table.class).list();
        assertEquals(1, tables.size());

        //Criteria Builder
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Table> cr = cb.createQuery(Table.class);
        Root<Table> root = cr.from(Table.class);
        cr.select(root);

        Query<Table> query = session.createQuery(cr);
        List<Table> results = query.getResultList();
        assertEquals(1, results.size());

        session.close();

    }

    @Test
    public void testPersistAndSaveDifference() {
        //без ИД - transient state
        Table table = new Table("2");

        hibernateService.saveTable(table);
        //persistence state (появляется в session-persContext-entitiesByKey)

        //тут закрывается сессия и объект становится detached (так как завершилась транзакция)

        //тут нет информации об объекте table
        Session session = sessionFactory.openSession();

        List<Table> tables = session.createQuery("FROM Table", Table.class).list();
        session.close();

        //а тут уже есть
        assertEquals(1, tables.size());

        //упало бы с PersistentObjectException, если бы делали generated id(нельзя в персист передавать detached (т.е. с ид))
        //hibernateService.persistTable(table);

    }
}
