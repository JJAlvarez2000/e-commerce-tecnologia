package org.jalvarez.apiservlet.webapp.headers.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import org.jalvarez.apiservlet.webapp.headers.annotations.MySqlConn;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {
    @Inject
    private Logger log;
    @Resource(name="jdbc/mysqlDB")
    private DataSource ds;
    // produces produce un objeto de tipo Connection en el contexto de la aplicacion
    @Produces
    @RequestScoped
    @MySqlConn
    private Connection beanConnection() throws NamingException, SQLException {
//        Context initContext = new InitialContext();
//        Context envContext = (Context) initContext.lookup("java:/comp/env");
//        DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }

    @Produces
    private Logger beanLogger(InjectionPoint ip)   {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }

    public void close(@Disposes @MySqlConn Connection conn) throws SQLException {
        conn.close();
        log.info("Conexión de MySQL cerrada");
    }
}

