/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author radames
 */
@Embeddable
public class DadosSensorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_dados_sensor")
    private int idDadosSensor;
    @Basic(optional = false)
    @Column(name = "data_hora_coleta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraColeta;

    public DadosSensorPK() {
    }

    public DadosSensorPK(int idDadosSensor, Date dataHoraColeta) {
        this.idDadosSensor = idDadosSensor;
        this.dataHoraColeta = dataHoraColeta;
    }

    public int getIdDadosSensor() {
        return idDadosSensor;
    }

    public void setIdDadosSensor(int idDadosSensor) {
        this.idDadosSensor = idDadosSensor;
    }

    public Date getDataHoraColeta() {
        return dataHoraColeta;
    }

    public void setDataHoraColeta(Date dataHoraColeta) {
        this.dataHoraColeta = dataHoraColeta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDadosSensor;
        hash += (dataHoraColeta != null ? dataHoraColeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DadosSensorPK)) {
            return false;
        }
        DadosSensorPK other = (DadosSensorPK) object;
        if (this.idDadosSensor != other.idDadosSensor) {
            return false;
        }
        if ((this.dataHoraColeta == null && other.dataHoraColeta != null) || (this.dataHoraColeta != null && !this.dataHoraColeta.equals(other.dataHoraColeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DadosSensorPK[ idDadosSensor=" + idDadosSensor + ", dataHoraColeta=" + dataHoraColeta + " ]";
    }
    
}
