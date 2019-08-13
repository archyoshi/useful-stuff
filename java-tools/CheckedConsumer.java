package com.fnac.search.tools;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T, E extends Exception> {
    static <T, E extends Exception> Consumer<T> uncheck(CheckedConsumer<T, E> consumerWithException) {
        return t -> {
            try {
                consumerWithException.accept(t);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

    void accept(T t) throws E;
}
