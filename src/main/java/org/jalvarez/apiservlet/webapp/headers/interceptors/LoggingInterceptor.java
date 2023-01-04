package org.jalvarez.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {
    @Inject
    private Logger log;
    @AroundInvoke
    public Object logging(InvocationContext invocation) throws Exception {
        // esto ejecuta el metodo que se intercepta
        log.info("Entrando en el metodo " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
        Object result = invocation.proceed();
        log.info("Saliendo del metodo " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
        return result;
    }
}
