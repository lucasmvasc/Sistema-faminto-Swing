package base;

public class Cartao {
    private String numero;
    private String cvv;

    public Cartao() {
    }

    public Cartao(String num, String cv) {
        super();
        this.numero = num;
        this.cvv = cv;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Cartão - [Número: " + numero + ", CVV: " + cvv + "]";
    }

}
