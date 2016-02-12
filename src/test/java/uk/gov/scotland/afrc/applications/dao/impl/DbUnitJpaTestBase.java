package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Created by d608947 on 14/11/2014.
 */
public abstract class DbUnitJpaTestBase {

    protected static EntityManagerFactory entityManagerFactory;
    protected static EntityManager entityManager;
    protected static IDatabaseConnection connection;
    protected static FlatXmlDataSetBuilder flatXmlDataSetBuilder;

    static {
        flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
        flatXmlDataSetBuilder.setColumnSensing(true);
        flatXmlDataSetBuilder.setCaseSensitiveTableNames(true);
    }

    protected abstract IDataSet getDataSet() throws DataSetException;

    @BeforeClass
    public static void initEntityManager() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("testPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        connection = new DatabaseConnection(entityManager.unwrap(java.sql.Connection.class));
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
    }

    @AfterClass
    public static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Before
    public void cleanDB() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
    }
}
