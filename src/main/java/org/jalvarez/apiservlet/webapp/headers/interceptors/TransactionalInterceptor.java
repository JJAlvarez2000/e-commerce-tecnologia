package org.jalvarez.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.jalvarez.apiservlet.webapp.headers.annotations.MySqlConn;
import org.jalvarez.apiservlet.webapp.headers.exceptions.ServiceJDBCException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {
    @Inject
    @MySqlConn
    private Connection conn;
    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        log.info("Starting transaction");
        if(conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }
        try {
            log.info("Executing method: " + invocation.getMethod().getName());
            Object result = invocation.proceed();
            conn.commit();
            log.info("Transaction committed: " + invocation.getMethod().getName());
            return result;
        } catch (ServiceJDBCException e) {
            conn.rollback();
            log.info("Transaction rolled back");
            throw e;
        }
    }
}
