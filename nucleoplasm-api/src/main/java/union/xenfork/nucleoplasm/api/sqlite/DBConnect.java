package union.xenfork.nucleoplasm.api.sqlite;

import org.sqlite.JDBC;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static union.xenfork.nucleoplasm.api.NucleoplasmApi.logger;

public class DBConnect {
    private Connection connection = null;

    public DBConnect() {
        try {
            Class.forName(JDBC.class.getName());
            connection = DriverManager.getConnection(JDBC.PREFIX + "sample.db");
            connection.setAutoCommit(true);
            logger.info("DB Connected!");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public DBConnect(File file) {
        if (!file.isDirectory() && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + file.getAbsolutePath());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    public DBConnect(Path path) {
        if (!path.toFile().isDirectory() && !path.toFile().getParentFile().exists()) {
            path.toFile().getParentFile().mkdirs();
        }
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + path);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }



    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
