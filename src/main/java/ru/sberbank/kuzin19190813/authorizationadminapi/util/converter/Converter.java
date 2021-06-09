package ru.sberbank.kuzin19190813.authorizationadminapi.util.converter;

public interface Converter<M, D> {
    M to(D dto);

    D from(M entity);
}
