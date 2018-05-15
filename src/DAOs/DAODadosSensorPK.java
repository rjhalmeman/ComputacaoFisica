package DAOs;

import Entidades.DadosSensorPK;
import java.util.ArrayList;
import java.util.List;

public class DAODadosSensorPK extends DAOGenerico<DadosSensorPK> {

    private List<DadosSensorPK> lista = new ArrayList<>();

    public DAODadosSensorPK() {
        super(DadosSensorPK.class);
    }

    public int autoIdDadosSensorPK() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idDadosSensor) FROM DadosSensorPK) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<DadosSensorPK> listByNome(String nome) {
        return em.createQuery("SELECT e FROM DadosSensorPK e WHERE e.idDadosSensor) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<DadosSensorPK> listById(int id) {
        return em.createQuery("SELECT e FROM DadosSensorPK + e WHERE e.dataHoraColeta= :id").setParameter("id", id).getResultList();
    }

    public List<DadosSensorPK> listInOrderNome() {
        return em.createQuery("SELECT e FROM DadosSensorPK e ORDER BY e.dataHoraColeta").getResultList();
    }

    public List<DadosSensorPK> listInOrderId() {
        return em.createQuery("SELECT e FROM DadosSensorPK e ORDER BY e.idDadosSensor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<DadosSensorPK> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdDadosSensor() + "-" + lf.get(i).getDataHoraColeta());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAODadosSensorPK daoDadosSensorPK = new DAODadosSensorPK();
        List<DadosSensorPK> listaDadosSensorPK = daoDadosSensorPK.list();
        for (DadosSensorPK dadosSensorPK : listaDadosSensorPK) {
            System.out.println(dadosSensorPK.getIdDadosSensor() + "-" + dadosSensorPK.getDataHoraColeta());
        }
    }
}
