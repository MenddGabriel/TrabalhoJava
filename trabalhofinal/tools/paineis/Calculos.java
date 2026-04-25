package tools.paineis;

/**
 * Classe responsável por todos os cálculos da aplicação Tools.
 */
public class Calculos {

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

    // ── Diferença entre datas ─────────────────────────────────────────────────
    /** Retorna a diferença em dias entre duas datas (data2 - data1) */
    public static long diferencaEntreDatas(java.time.LocalDate data1, java.time.LocalDate data2) {
        return java.time.temporal.ChronoUnit.DAYS.between(data1, data2);
    }

    // ── Somar dias a uma data ─────────────────────────────────────────────────
    /** Retorna a data resultante após somar (ou subtrair) dias */
    public static java.time.LocalDate somarDias(java.time.LocalDate dataInicio, int dias) {
        return dataInicio.plusDays(dias);
    }
}
