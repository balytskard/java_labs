package org.fpm.di;

import org.fpm.di.annotations.ComponentScan;
import org.fpm.di.annotations.Configuration;

@Configuration
@ComponentScan("org.fpm.di")
public class AppConfiguration implements org.fpm.di.Configuration {

    @Override
    public void configure(Binder binder) {
    }
}
