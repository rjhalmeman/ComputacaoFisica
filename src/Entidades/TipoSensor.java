/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author radames
 */
@Entity
@Table(name = "tipo_sensor")
@NamedQueries({
    @NamedQuery(name = "TipoSensor.findAll", query = "SELECT t FROM TipoSensor t")})
public class TipoSensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_sensor")
    private Integer idTipoSensor;
    @Column(name = "nome_tipo_sensor")
    private String nomeTipoSensor;
    @OneToMany(mappedBy = "tipoSensorIdTipoSensor")
    private List<Sensor> sensorList;

    public TipoSensor() {
    }

    public TipoSensor(Integer idTipoSensor) {
        this.idTipoSensor = idTipoSensor;
    }

    public Integer getIdTipoSensor() {
        return idTipoSensor;
    }

    public void setIdTipoSensor(Integer idTipoSensor) {
        this.idTipoSensor = idTipoSensor;
    }

    public String getNomeTipoSensor() {
        return nomeTipoSensor;
    }

    public void setNomeTipoSensor(String nomeTipoSensor) {
        this.nomeTipoSensor = nomeTipoSensor;
    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSensor != null ? idTipoSensor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSensor)) {
            return false;
        }
        TipoSensor other = (TipoSensor) object;
        if ((this.idTipoSensor == null && other.idTipoSensor != null) || (this.idTipoSensor != null && !this.idTipoSensor.equals(other.idTipoSensor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoSensor[ idTipoSensor=" + idTipoSensor + " ]";
    }
    
}
