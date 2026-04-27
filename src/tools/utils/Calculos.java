package tools.utils;

/**
 * Classe responsável por todos os cálculos da aplicação Tools.
 */
public final class Calculos {

    private Calculos() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada");
    }

    // ── Aplicar desconto % num valor ──────────────────────────────────────────
    /** v = a - (a * (b / 100)) */
    public static double aplicarDesconto(double a, double b) {
        return a - (a * (b / 100.0));
    }

    // ── Incrementar % a um valor ──────────────────────────────────────────────
    /** v = a + (a * (b / 100)) */
    public static double incrementarPorcentagem(double a, double b) {
        return a + (a * (b / 100.0));
    }

    // ── Amostragem: quanto X% representa de Y ────────────────────────────────
    /** v = (a * b) / 100 */
    public static double amostragem(double total, double porcentagem) {
        return (total * porcentagem) / 100.0;
    }

    // ── Amostragem 2: quanto X representa de Y ───────────────────────────────
    /** v = (b / a) * 100 */
    public static double amostragem2(double total, double parte) {
        if (total == 0) return 0;
        return (parte / total) * 100.0;
    }

    // ── Valor era A e paguei B, qual foi o desconto%? ────────────────────────
    /** v = ((a - b) / a) * 100 */
    public static double descontoPago(double valorOriginal, double valorComDesconto) {
        if (valorOriginal == 0) return 0;
        return ((valorOriginal - valorComDesconto) / valorOriginal) * 100.0;
    }

    // ── Variação Delta (%) – diferença % entre valores ───────────────────────
    /** v = ((b - a) / a) * 100 */
    public static double variacaoDelta(double valorInicial, double valorFinal) {
        if (valorInicial == 0) return 0;
        return ((valorFinal - valorInicial) / valorInicial) * 100.0;
    }

    // ── Qual era o valor original? ────────────────────────────────────────────
    /** v = (a * 100) / (100 - b) */
    public static double valorOriginal(double valorFinal, double desconto) {
        if (desconto >= 100) return 0;
        return (valorFinal * 100.0) / (100.0 - desconto);
    }

    // ── Regra de três ─────────────────────────────────────────────────────────
    /** r2 = (r1 * b) / a */
    public static double regraDeTres(double a, double r1, double b) {
        if (a == 0) return 0;
        return (r1 * b) / a;
    }

    public static int soma(int x, int y) {
        return x + y;
    }

    public static double soma(double x, double y) {
        return x + y;
    }

    public static String soma(String x, String y) {
        return x + y;
    }



}