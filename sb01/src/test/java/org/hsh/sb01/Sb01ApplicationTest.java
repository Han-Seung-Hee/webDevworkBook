package org.hsh.sb01;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class Sb01ApplicationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {

        @Cleanup Connection conn = dataSource.getConnection();

        log.info("conn :::::::::::::::::::::::::::::::::");
        log.info(conn);
        log.info("conn end :::::::::::::::::::::::::::::");

        Assertions.assertNotNull(conn);
    }
}