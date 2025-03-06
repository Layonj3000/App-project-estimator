package br.projeto.model;

public class PerfilProjetoIntermediariaModel {
    private Integer idProjetoDeEstimativaModel;
    private Integer idPerfilProjetoDeEstimativaModel;
    public PerfilProjetoIntermediariaModel(Integer idProjetoDeEstimativaModel,Integer idPerfilProjetoDeEstimativaModel) {
        this.idProjetoDeEstimativaModel = idProjetoDeEstimativaModel;
        this.idPerfilProjetoDeEstimativaModel = idPerfilProjetoDeEstimativaModel;
    }

    public Integer getIdProjetoDeEstimativaModel() {
        return idProjetoDeEstimativaModel;
    }

    public void setIdProjetoDeEstimativaModel(Integer idProjetoDeEstimativaModel) {
        this.idProjetoDeEstimativaModel = idProjetoDeEstimativaModel;
    }

    public Integer getIdPerfilProjetoDeEstimativaModel() {
        return idPerfilProjetoDeEstimativaModel;
    }

    public void setIdPerfilProjetoDeEstimativaModel(Integer idPerfilProjetoDeEstimativaModel) {
        this.idPerfilProjetoDeEstimativaModel = idPerfilProjetoDeEstimativaModel;
    }

    @Override
    public String toString() {
        return "PerfilProjetoIntermediariaModel{" +
                "idProjetoDeEstimativaModel=" + idProjetoDeEstimativaModel +
                ", idPerfilProjetoDeEstimativaModel=" + idPerfilProjetoDeEstimativaModel +
                '}'+"\n";
    }
}
