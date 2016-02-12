package uk.gov.scotland.afrc.applications.entity.base.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class EntityManagerBaseImplTest {

    private TestDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<TestDao> query;
    @Mock
    private TransactionManager txManager;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new TestDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void createTest() {

        TestDao obj = new TestDao();

        dao.create(obj);

        Mockito.verify(entityManager).persist(obj);
        Mockito.verify(entityManager).flush();
    }

    @Test
    public void deleteTest() throws ConcurrentAccessException {

        TestDao obj = new TestDao();

        dao.delete(obj);

        Mockito.verify(entityManager).remove(obj);
    }

    @Test
    public void updateTest() throws ConcurrentAccessException {

        TestDao obj = new TestDao();

        dao.update(obj);

        Mockito.verify(entityManager).merge(obj);
        Mockito.verify(entityManager).flush();
    }

    @Test
    public void createListTest() {

        TestDao obj1 = new TestDao();
        TestDao obj2 = new TestDao();

        List<TestDao> entities = new ArrayList<TestDao>();
        entities.add(obj1);
        entities.add(obj2);

        dao.createList(entities);

        Mockito.verify(entityManager).persist(obj1);
        Mockito.verify(entityManager).persist(obj2);
        Mockito.verify(entityManager, times(2)).flush();
    }

    @Test
    public void findByIdTest() {

        dao.findById(1L);

        Mockito.verify(entityManager).find(TestDao.class, 1L);
    }

    @Test
    public void queryAllTest() {

        Mockito.when(entityManager.createNamedQuery("testQuery", TestDao.class)).thenReturn(query);

        dao.queryAll();

        Mockito.verify(entityManager).createNamedQuery("testQuery", TestDao.class);
    }

    @Test
    public void queryByKeyTest() {

        Mockito.when(entityManager.createNamedQuery("testQuery", TestDao.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.queryByKey(1L);

        Mockito.verify(entityManager).createNamedQuery("testQuery", TestDao.class);

        Mockito.verify(query).setParameter("id", 1L);

    }

    @Test
    public void queryByStringKeyTest() {

        Mockito.when(entityManager.createNamedQuery("testQuery", TestDao.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.queryByKey("key");

        Mockito.verify(entityManager).createNamedQuery("testQuery", TestDao.class);

        Mockito.verify(query).setParameter("id", "key");

    }

    public class TestDaoImpl extends EntityManagerBaseImpl<TestDao, Long> {

        public TestDaoImpl() {
            super(TestDao.class);
            super.setTxManager(txManager);
        }

        public TestDao create(TestDao obj) {
            return super.create(obj);
        }

        public void delete(TestDao obj) throws ConcurrentAccessException {
            super.delete(obj);
        }

        public TestDao update(TestDao obj) throws ConcurrentAccessException {
            return super.update(obj);
        }

        public void createList(List<TestDao> entities) {
            super.createList(entities);
        }

        public TestDao findById(Long id) {
            return super.findById(id);
        }

        public List<TestDao> queryAll() {
            return super.queryAll("testQuery");
        }

        public List<TestDao> queryByKey(Long id) {
            return super.queryByKey("testQuery", "id", id);
        }

        public TestDao queryByKey(String id) {
            return super.queryByKey("testQuery", "id", id);
        }

    }

    public class TestDao {

    }
}
