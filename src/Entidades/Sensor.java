/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "sensor")
@NamedQueries({
    @NamedQuery(name = "Sensor.findAll", query = "SELECT s FROM Sensor s")})
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sensor")
    private Integer idSensor;
    @Column(name = "nome_sensor")
    private String nomeSensor;
    @JoinColumn(name = "tipo_sensor_id_tipo_sensor", referencedColumnName = "id_tipo_sensor")
    @ManyToOne
    private TipoSensor tipoSensorIdTipoSensor;
    @JoinColumn(name = "local_id_local", referencedColumnName = "id_local")
    @ManyToOne(optional = false)
    private Local localIdLocal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensorIdSensor")
    private List<DadosSensor> dadosSensorList;

    public Sensor() {
    }

    public Sensor(Integer idSensor) {
        this.idSensor = idSensor;
    }

    public Integer getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(Integer idSensor) {
        this.idSensor = idSensor;
    }

    public String getNomeSensor() {
        return nomeSensor;
    }

    public void setNomeSensor(String nomeSensor) {
        this.nomeSensor = nomeSensor;
    }

    public TipoSensor getTipoSensorIdTipoSensor() {
        return tipoSensorIdTipoSensor;
    }

    public void setTipoSensorIdTipoSensor(TipoSensor tipoSensorIdTipoSensor) {
        this.tipoSensorIdTipoSensor = tipoSensorIdTipoSensor;
    }

    public Local getLocalIdLocal() {
        return localIdLocal;
    }

    public void setLocalIdLocal(Local localIdLocal) {
        this.localIdLocal = localIdLocal;
    }

    public List<DadosSensor> getDadosSensorList() {
        return dadosSensorList;
    }

    public void setDadosSensorList(List<DadosSensor> dadosSensorList) {
        this.dadosSensorList = dadosSensorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSensor != null ? idSensor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) object;
        if ((this.idSensor == null && other.idSensor != null) || (this.idSensor != null && !this.idSensor.equals(other.idSensor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Sensor[ idSensor=" + idSensor + " ]";
    }
    
}
