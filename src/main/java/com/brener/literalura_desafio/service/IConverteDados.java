package com.brener.literalura_desafio.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
