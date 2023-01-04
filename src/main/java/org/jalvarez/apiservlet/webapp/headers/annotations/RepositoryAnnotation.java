package org.jalvarez.apiservlet.webapp.headers.annotations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ApplicationScoped
@Named
@Stereotype
@Retention(RUNTIME)
@Target({TYPE})
public @interface RepositoryAnnotation {
}
