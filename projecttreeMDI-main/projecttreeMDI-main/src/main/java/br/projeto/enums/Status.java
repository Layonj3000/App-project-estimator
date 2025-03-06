package br.projeto.enums;

public enum Status {
    ESTIMADO(0),
    EM_ANDAMENTO(1);

    private final int valor;

    Status(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static Status fromValue(int valor){
        for(Status status:Status.values()){
            if(status.getValor() == valor){
                return status;
            }
        }
        throw new IllegalArgumentException("Valor inválido: "+ valor);
    }
}
