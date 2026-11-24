package com.unoeste.fallpreventionbe.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Validacoes
{
    private Validacoes() {}

    public static boolean cpfValido(String cpf)
    {
        if (cpf == null)
            return false;
        String digitos = cpf.replaceAll("\\D", "");
        if (digitos.length() != 11)
            return false;
        if (digitos.matches("(\\d)\\1{10}"))
            return false;

        return calcularDigito(digitos.substring(0, 9), 10) == digitos.charAt(9) - '0'
                && calcularDigito(digitos.substring(0, 10), 11) == digitos.charAt(10) - '0';
    }

    private static int calcularDigito(String base, int pesoInicial)
    {
        int soma = 0;
        for (int i = 0; i < base.length(); i++)
            soma += (base.charAt(i) - '0') * (pesoInicial - i);
        int resto = (soma * 10) % 11;
        return resto == 10 ? 0 : resto;
    }

    public static boolean dataNascimentoValida(LocalDate data)
    {
        return data != null && !data.isAfter(LocalDate.now());
    }

    public static boolean dataHoraSessaoValida(LocalDateTime dataHora)
    {
        return dataHora != null && !dataHora.isBefore(LocalDateTime.now().minusMinutes(1));
    }
}
