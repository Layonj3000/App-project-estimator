/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.enums;

/**
 *
 * @author David Potentini
 */
public enum FormatoLOG {
    CSV(0),
    JSON(1);

    private final int valor;

    FormatoLOG(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static FormatoLOG fromValue(int valor){
        for(FormatoLOG formatoLOG: FormatoLOG.values()){
            if(formatoLOG.getValor() == valor){
                return formatoLOG;
            }
        }
        throw new IllegalArgumentException("Valor inválido: "+ valor);
    }
}
