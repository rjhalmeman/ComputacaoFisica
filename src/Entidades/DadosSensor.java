/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "dados_sensor")
@NamedQueries({
    @NamedQuery(name = "DadosSensor.findAll", query = "SELECT d FROM DadosSensor d")})
public class DadosSensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DadosSensorPK dadosSensorPK;
    @Column(name = "dado")
    private String dado;
    @JoinColumn(name = "sensor_id_sensor", referencedColumnName = "id_sensor")
    @ManyToOne(optional = false)
    private Sensor sensorIdSensor;

    public DadosSensor() {
    }

    public DadosSensor(DadosSensorPK dadosSensorPK) {
        this.dadosSensorPK = dadosSensorPK;
    }

    public DadosSensor(int idDadosSensor, Date dataHoraColeta) {
        this.dadosSensorPK = new DadosSensorPK(idDadosSensor, dataHoraColeta);
    }

    public DadosSensorPK getDadosSensorPK() {
        return dadosSensorPK;
    }

    public void setDadosSensorPK(DadosSensorPK dadosSensorPK) {
        this.dadosSensorPK = dadosSensorPK;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public Sensor getSensorIdSensor() {
        return sensorIdSensor;
    }

    public void setSensorIdSensor(Sensor sensorIdSensor) {
        this.sensorIdSensor = sensorIdSensor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dadosSensorPK != null ? dadosSensorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DadosSensor)) {
            return false;
        }
        DadosSensor other = (DadosSensor) object;
        if ((this.dadosSensorPK == null && other.dadosSensorPK != null) || (this.dadosSensorPK != null && !this.dadosSensorPK.equals(other.dadosSensorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DadosSensor[ dadosSensorPK=" + dadosSensorPK + " ]";
    }
    
}
