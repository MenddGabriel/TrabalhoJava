package tools.test;

import tools.utils.Calculos;

public class TestaCalculos {

    private static int testesPassaram = 0;
    private static int testesFalharam = 0;

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("              TESTANDO CLASSE UTILITÁRIA Calculos           ");
        System.out.println("═══════════════════════════════════════════════════════════\n");

        // ========== TESTES DE DESCONTO ==========
        testarDesconto();

        // ========== TESTES DE INCREMENTO ==========
        testarIncremento();

        // ========== TESTES DE AMOSTRAGEM ==========
        testarAmostragem();

        // ========== TESTES DE AMOSTRAGEM 2 ==========
        testarAmostragem2();

        // ========== TESTES DE DESCONTO PAGO ==========
        testarDescontoPago();

        // ========== TESTES DE VARIAÇÃO DELTA ==========
        testarVariacaoDelta();

        // ========== TESTES DE VALOR ORIGINAL ==========
        testarValorOriginal();

        // ========== TESTES DE REGRA DE TRÊS ==========
        testarRegraDeTres();

        // ========== TESTES DE SOBRECARGA ==========
        testarSobrecarga();

        // ========== TESTES COM VALORES LIMITE ==========
        testarValoresLimite();

        // ========== RESUMO FINAL ==========
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.printf("  ✅ TESTES QUE PASSARAM: %d%n", testesPassaram);
        System.out.printf("  ❌ TESTES QUE FALHARAM: %d%n", testesFalharam);
        System.out.println("═══════════════════════════════════════════════════════════");

        if (testesFalharam == 0) {
            System.out.println("\nTodas as fórmulas estão CORRETAS!");
        } else {
            System.out.println("\n⚠ATENÇÃO! Algumas fórmulas precisam ser corrigidas!");
        }
    }

    // ========== METODO AUXILIAR PARA TESTAR ==========
    private static void testar(String nomeTeste, double obtido, double esperado) {
        double margemErro = 0.01; // margem de 0.01 para comparação de doubles
        boolean passou = Math.abs(obtido - esperado) <= margemErro;

        if (passou) {
            System.out.printf("  ✅ %s: %.2f = %.2f%n", nomeTeste, obtido, esperado);
            testesPassaram++;
        } else {
            System.out.printf("  ❌ %s: %.2f (obtido) != %.2f (esperado)%n", nomeTeste, obtido, esperado);
            testesFalharam++;
        }
    }

    private static void testar(String nomeTeste, int obtido, int esperado) {
        boolean passou = obtido == esperado;

        if (passou) {
            System.out.printf("  ✅ %s: %d = %d%n", nomeTeste, obtido, esperado);
            testesPassaram++;
        } else {
            System.out.printf("  ❌ %s: %d (obtido) != %d (esperado)%n", nomeTeste, obtido, esperado);
            testesFalharam++;
        }
    }

    private static void testar(String nomeTeste, String obtido, String esperado) {
        boolean passou = obtido.equals(esperado);

        if (passou) {
            System.out.printf("  ✅ %s: \"%s\" = \"%s\"%n", nomeTeste, obtido, esperado);
            testesPassaram++;
        } else {
            System.out.printf("  ❌ %s: \"%s\" (obtido) != \"%s\" (esperado)%n", nomeTeste, obtido, esperado);
            testesFalharam++;
        }
    }

    // ========== TESTES ESPECÍFICOS ==========

    private static void testarDesconto() {
        System.out.println("TESTES DE DESCONTO:");
        testar("Desconto 25% de 100", Calculos.aplicarDesconto(100, 25), 75.00);
        testar("Desconto 10% de 50", Calculos.aplicarDesconto(50, 10), 45.00);
        testar("Desconto 50% de 200", Calculos.aplicarDesconto(200, 50), 100.00);
        testar("Desconto 0% de 100", Calculos.aplicarDesconto(100, 0), 100.00);
        testar("Desconto 100% de 100", Calculos.aplicarDesconto(100, 100), 0.00);
        System.out.println();
    }

    private static void testarIncremento() {
        System.out.println("TESTES DE INCREMENTO:");
        testar("Incremento 36% de 100", Calculos.incrementarPorcentagem(100, 36), 136.00);
        testar("Incremento 10% de 50", Calculos.incrementarPorcentagem(50, 10), 55.00);
        testar("Incremento 50% de 200", Calculos.incrementarPorcentagem(200, 50), 300.00);
        testar("Incremento 0% de 100", Calculos.incrementarPorcentagem(100, 0), 100.00);
        testar("Incremento 100% de 100", Calculos.incrementarPorcentagem(100, 100), 200.00);
        System.out.println();
    }

    private static void testarAmostragem() {
        System.out.println("TESTES DE AMOSTRAGEM (quanto X% representa de Y):");
        testar("15% de 250", Calculos.amostragem(250, 15), 37.50);
        testar("10% de 200", Calculos.amostragem(200, 10), 20.00);
        testar("50% de 80", Calculos.amostragem(80, 50), 40.00);
        testar("0% de 100", Calculos.amostragem(100, 0), 0.00);
        testar("100% de 50", Calculos.amostragem(50, 100), 50.00);
        System.out.println();
    }

    private static void testarAmostragem2() {
        System.out.println("TESTES DE AMOSTRAGEM 2 (quanto X representa de Y em %):");
        testar("37.50 de 250", Calculos.amostragem2(250, 37.50), 15.00);
        testar("20 de 200", Calculos.amostragem2(200, 20), 10.00);
        testar("40 de 80", Calculos.amostragem2(80, 40), 50.00);
        testar("0 de 100", Calculos.amostragem2(100, 0), 0.00);
        testar("50 de 50", Calculos.amostragem2(50, 50), 100.00);
        System.out.println();
    }

    private static void testarDescontoPago() {
        System.out.println("TESTES DE DESCONTO PAGO (valor era A, paguei B):");
        testar("650 → 123", Calculos.descontoPago(650, 123), 81.08);
        testar("100 → 75", Calculos.descontoPago(100, 75), 25.00);
        testar("200 → 100", Calculos.descontoPago(200, 100), 50.00);
        testar("100 → 0", Calculos.descontoPago(100, 0), 100.00);
        testar("100 → 100", Calculos.descontoPago(100, 100), 0.00);
        System.out.println();
    }

    private static void testarVariacaoDelta() {
        System.out.println("TESTES DE VARIAÇÃO DELTA (diferença % entre valores):");
        testar("700 → 40", Calculos.variacaoDelta(700, 40), -94.29);
        testar("100 → 150", Calculos.variacaoDelta(100, 150), 50.00);
        testar("200 → 100", Calculos.variacaoDelta(200, 100), -50.00);
        testar("100 → 100", Calculos.variacaoDelta(100, 100), 0.00);
        testar("50 → 75", Calculos.variacaoDelta(50, 75), 50.00);
        System.out.println();
    }

    private static void testarValorOriginal() {
        System.out.println("TESTES DE VALOR ORIGINAL (qual era o valor antes do desconto):");
        testar("41 com 2% desconto", Calculos.valorOriginal(41, 2), 41.84);
        testar("75 com 25% desconto", Calculos.valorOriginal(75, 25), 100.00);
        testar("100 com 50% desconto", Calculos.valorOriginal(100, 50), 200.00);
        testar("50 com 0% desconto", Calculos.valorOriginal(50, 0), 50.00);
        System.out.println();
    }

    private static void testarRegraDeTres() {
        System.out.println("TESTES DE REGRA DE TRÊS:");
        testar("3 → 2, 1 → ?", Calculos.regraDeTres(3, 2, 1), 0.67);
        testar("2 → 4, 3 → ?", Calculos.regraDeTres(2, 4, 3), 6.00);
        testar("5 → 10, 2 → ?", Calculos.regraDeTres(5, 10, 2), 4.00);
        testar("10 → 100, 1 → ?", Calculos.regraDeTres(10, 100, 1), 10.00);
        testar("4 → 8, 6 → ?", Calculos.regraDeTres(4, 8, 6), 12.00);
        System.out.println();
    }

    private static void testarSobrecarga() {
        System.out.println("TESTES DE SOBRECARGA (métodos soma):");
        testar("soma(int, int): 5 + 3", Calculos.soma(5, 3), 8);
        testar("soma(int, int): 10 + 20", Calculos.soma(10, 20), 30);
        testar("soma(double, double): 5.5 + 3.2", Calculos.soma(5.5, 3.2), 8.7);
        testar("soma(double, double): 10.1 + 20.2", Calculos.soma(10.1, 20.2), 30.3);
        testar("soma(String, String): ABC + 123", Calculos.soma("ABC", "123"), "ABC123");
        testar("soma(String, String): Hello + World", Calculos.soma("Hello", "World"), "HelloWorld");
        System.out.println();
    }

    private static void testarValoresLimite() {
        System.out.println("⚠️ TESTES COM VALORES LIMITE:");

        // Teste com zero
        testar("Desconto com valor zero", Calculos.aplicarDesconto(0, 50), 0.00);
        testar("Incremento com valor zero", Calculos.incrementarPorcentagem(0, 50), 0.00);
        testar("Amostragem com total zero", Calculos.amostragem(0, 50), 0.00);
        testar("Amostragem2 com total zero (deve retornar 0)", Calculos.amostragem2(0, 50), 0.00);
        testar("Desconto pago com original zero", Calculos.descontoPago(0, 50), 0.00);
        testar("Variação delta com inicial zero", Calculos.variacaoDelta(0, 50), 0.00);
        testar("Regra de três com a zero", Calculos.regraDeTres(0, 10, 5), 0.00);

        // Teste com valores negativos (se sua aplicação permitir)
        testar("Desconto com valor negativo", Calculos.aplicarDesconto(-100, 10), -90.00);
        testar("Variação delta negativa", Calculos.variacaoDelta(100, 50), -50.00);

        // Teste com números grandes
        testar("Desconto com número grande", Calculos.aplicarDesconto(1000000, 25), 750000.00);
        testar("Regra de três com números grandes", Calculos.regraDeTres(1000000, 500000, 2000000), 1000000.00);

        System.out.println();
    }
}