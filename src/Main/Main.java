package Main;

import DAOs.DAOSensor;
import DAOs.DAOTipoSensor;
import Entidades.Sensor;
import Entidades.TipoSensor;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TipoSensor tipoSensor;
        DAOTipoSensor daoTipoSensor = new DAOTipoSensor();

        tipoSensor = new TipoSensor();
        tipoSensor.setIdTipoSensor(1);
        if (daoTipoSensor.obter(tipoSensor.getIdTipoSensor()) == null) {//se não está cadastrado            
            tipoSensor.setNomeTipoSensor("Temperatura");
            daoTipoSensor.inserir(tipoSensor);
        }

        tipoSensor = new TipoSensor();
        tipoSensor.setIdTipoSensor(2);
        if (daoTipoSensor.obter(tipoSensor.getIdTipoSensor()) == null) {//se não está cadastrado            
            tipoSensor.setNomeTipoSensor("Luminosidade");
            daoTipoSensor.inserir(tipoSensor);
        }

        System.out.println("\n\n\nListagem com os tipos de sensores já cadastrados");
        List<TipoSensor> listaDeTiposDeSensores = daoTipoSensor.list();
        for (TipoSensor tipo : listaDeTiposDeSensores) {
            System.out.println(tipo.getIdTipoSensor() + " - " + tipo.getNomeTipoSensor());
        }

        Sensor sensor;
        DAOSensor daoSensor = new DAOSensor();

        sensor = new Sensor();
        sensor.setIdSensor(1);
        if (daoSensor.obter(sensor.getIdSensor()) == null) {//se não está casdastrado o sensor com esse Id
            sensor.setNomeSensor("Temp 01");
            sensor.setLocalDoSensor("sobre o armário 1");
            sensor.setTipoSensorIdTipoSensor(daoTipoSensor.obter(1));
            daoSensor.inserir(sensor);
        }

        sensor = new Sensor();
        sensor.setIdSensor(2);
        if (daoSensor.obter(sensor.getIdSensor()) == null) {//se não está casdastrado o sensor com esse Id
            sensor.setNomeSensor("Temp 02");
            sensor.setLocalDoSensor("sobre o armário 2");
            sensor.setTipoSensorIdTipoSensor(daoTipoSensor.obter(1));
            daoSensor.inserir(sensor);
        }

        sensor = new Sensor();
        sensor.setIdSensor(4);
        if (daoSensor.obter(sensor.getIdSensor()) == null) {//se não está casdastrado o sensor com esse Id
            sensor.setNomeSensor("Sensor 04");
            sensor.setLocalDoSensor("444");
            sensor.setTipoSensorIdTipoSensor(daoTipoSensor.obter(2));
            daoSensor.inserir(sensor);
        }
        System.out.println("\nListagem com os sensores já cadastrados");
        List<Sensor> listaDeSensores = daoSensor.list();
        for (Sensor s : listaDeSensores) {
            System.out.println(s.getIdSensor() + " - " + s.getNomeSensor() + " - " + s.getLocalDoSensor());
        }

        //Procurar e mostrar os dados de um sensor, inclusive seu tipo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome de um Sensor =>");
        String nomeSensor = scanner.next().trim();
        sensor = daoSensor.listByNome(nomeSensor);
        if (sensor != null) {//se achou no bd
            System.out.println("Id=" + sensor.getIdSensor()
                    + " nome=" + sensor.getNomeSensor() + " [tipo: "
                    + sensor.getTipoSensorIdTipoSensor().getNomeTipoSensor()
                    + "]");
        } else {
            System.out.println("não achou");
        }

        //atualizar dados de um sensor
        //1 - localizar
        sensor = daoSensor.obter(2);// buscar o sensor com id=2
        if (sensor != null) {//se achou
            sensor.setNomeSensor("xxxxxxxxxx");
            daoSensor.atualizar(sensor);
        }

        //excluir um sensor da lista
        sensor = daoSensor.obter(1);
        if (sensor != null) {
            daoSensor.remover(sensor);
        }

        //buscar novamente e imprimir a lista de sensores
        listaDeSensores = daoSensor.list();
        for (Sensor s : listaDeSensores) {
            System.out.println(s.getIdSensor() + " - " + s.getNomeSensor() + " - " + s.getLocalDoSensor());
        }
    }

}
