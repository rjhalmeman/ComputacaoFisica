package DAOs;

import Entidades.TipoSensor;
import java.util.ArrayList;
import java.util.List;

public class DAOTipoSensor extends DAOGenerico<TipoSensor> {

    public DAOTipoSensor() {
        super(TipoSensor.class);
    }

    public int autoIdTipoSensor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoSensor) FROM TipoSensor e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoSensor> listByNome(String nome) {
        return em.createQuery("SELECT e FROM TipoSensor e WHERE e.nomeTipoSensor LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TipoSensor> listById(int id) {
        return em.createQuery("SELECT e FROM TipoSensor e WHERE e.idTipoSensor = :id").setParameter("id", id).getResultList();
    }

    public List<TipoSensor> listInOrderNome() {
        return em.createQuery("SELECT e FROM TipoSensor e ORDER BY e.nomeTipoSensor").getResultList();
    }

    public List<TipoSensor> listInOrderId() {
        return em.createQuery("SELECT e FROM TipoSensor e ORDER BY e.idTipoSensor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoSensor> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipoSensor() + "-" + lf.get(i).getNomeTipoSensor());
        }
        return ls;
    }
}
