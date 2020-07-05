package base;

public class Cheque {
    private String numero;
    private String conta;
    private String agencia;
    private String banco;

    public Cheque() {
    }

    public Cheque(String numero, String conta, String agencia, String banco) {
        super();
        this.numero = numero;
        this.conta = conta;
        this.agencia = agencia;
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Cheque - [Número: " + numero + ", Conta: " + conta + ", Agência: " + agencia + ", Banco: " + banco
                + "]";
    }

}
