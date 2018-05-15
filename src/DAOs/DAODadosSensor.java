package DAOs;

import Entidades.DadosSensor;
import Entidades.DadosSensorPK;
import java.util.ArrayList;
import java.util.List;

public class DAODadosSensor extends DAOGenerico<DadosSensor> {

    private List<DadosSensor> lista = new ArrayList<>();

    public DAODadosSensor() {
        super(DadosSensor.class);
    }

    public DadosSensor obter(DadosSensorPK dsPK) {
        return em.find(DadosSensor.class, dsPK);
    }

    public int autoIdDadosSensor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idDadosSensor) FROM DadosSensor) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<DadosSensor> listByNome(String nome) {
        return em.createQuery("SELECT e FROM DadosSensor e WHERE e.idDadosSensor) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<DadosSensor> listById(int id) {
        return em.createQuery("SELECT e FROM DadosSensor + e WHERE e.dataHoraColeta= :id").setParameter("id", id).getResultList();
    }

    public List<DadosSensor> listInOrderNome() {
        return em.createQuery("SELECT e FROM DadosSensor e ORDER BY e.dataHoraColeta").getResultList();
    }

    public List<DadosSensor> listInOrderId() {
        return em.createQuery("SELECT e FROM DadosSensor e ORDER BY e.idDadosSensor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<DadosSensor> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getDado() + "-" + lf.get(i).getDadosSensorPK().getDataHoraColeta());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAODadosSensor daoDadosSensor = new DAODadosSensor();
        List<DadosSensor> listaDadosSensor = daoDadosSensor.list();
        for (DadosSensor dadosSensor : listaDadosSensor) {
            System.out.println(dadosSensor.getDadosSensorPK().getIdDadosSensor()
                    + "-" + dadosSensor.getDadosSensorPK().getDataHoraColeta());
        }
    }
}
