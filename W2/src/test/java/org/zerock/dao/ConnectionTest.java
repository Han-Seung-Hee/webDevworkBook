package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

    @Test
    public void testConnection() throws Exception{
        Connection _conn = null;
        try{
            Class.forName("org.postgresql.Driver");

            _conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "xorhd1222",
                    "tmdgml12"
            );

            Assertions.assertNotNull(_conn);
        }catch (Exception e){
            System.out.println("11111");
        }finally {
            assert _conn != null;
            _conn.close();
        }
    }

    @Test
    public void testHikariCp() throws Exception{

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("xorhd1222");
        config.setPassword("tmdgml12");
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection _conn = ds.getConnection();

        System.out.println(_conn);

        _conn.close();


    }
}
