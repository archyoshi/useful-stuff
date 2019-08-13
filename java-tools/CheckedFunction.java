package com.fnac.search.tools;

import java.util.function.Function;

@FunctionalInterface
public interface CheckedFunction<T, R, E extends Exception> {
    static <T, R, E extends Exception> Function<T, R> uncheck(CheckedFunction<T, R, E> functionWithException) {
        return t -> {
            try {
                return functionWithException.apply(t);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

    R apply(T t) throws E;
}
