package br.projeto.enums;

public enum SimNao {
    NAO(0),
    SIM(1);

    private final int valor;

    SimNao(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static SimNao fromValue(int valor){
        for(SimNao simNao: SimNao.values()){
            if(simNao.getValor() == valor){
                return simNao;
            }
        }
        throw new IllegalArgumentException("Valor inválido: "+ valor);
    }
}
