package com.example.commonlibrary.dagger.scope;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}

