package uk.gov.scotland.afrc.applications.utils;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * Utility class for exporting dbunit dataset from real database.
 *
 * Created by d608947 on 25/11/2014.
 */
public class DBUnitExportUtil {

    public static void main(String[] args) throws Exception {
        String arg1 = args[0];
        String arg2 = args[1];

        // database connection
        //Class driverClass = Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@//172.30.1.6:1521/appdev.scotland.gov.uk", arg1, arg2);
        org.dbunit.database.IDatabaseConnection connection = new org.dbunit.database.DatabaseConnection(jdbcConnection);

        // partial database export - use the selected tables for the partial database dump.
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("SCHEME_OPTION");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial_dbunit.xml"));

        // full database export - use the following for the full database dump.
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full_dbunit.xml"));

        // dependent tables database export: export table X and all tables that have a PK which is a FK on X, in the right order for insertion
        String[] depTableNames =
                TablesDependencyHelper.getAllDependentTables(connection, "SCHEME");
        IDataSet depDataSet = connection.createDataSet( depTableNames );
        FlatXmlDataSet.write(depDataSet, new FileOutputStream("dependents_dbunit.xml"));
    }
}
