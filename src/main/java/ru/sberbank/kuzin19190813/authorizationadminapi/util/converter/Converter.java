package ru.sberbank.kuzin19190813.authorizationadminapi.util.converter;

public interface Converter<T, F> {
    T to(F from);

    F from(T to);
}
