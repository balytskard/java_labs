package org.fpm.di.example;

import org.fpm.di.AppConfiguration;
import org.fpm.di.AppContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private AppContext appContext;

    @Before
    public void setUp() {
        appContext = new AppContext(AppConfiguration.class);
    }

    @Test
    public void shouldInjectSingleton() throws Exception {
        assertSame(appContext.getComponent(MySingleton.class), appContext.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() throws Exception {
        assertNotSame(appContext.getComponent(MyPrototype.class), appContext.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() throws Exception {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = appContext.getComponent(B.class);
        assertSame(appContext.getComponent(A.class), bAsSingleton);
        assertSame(appContext.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() throws Exception {
        final UseA hasADependency = appContext.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), appContext.getComponent(B.class));
    }
}
