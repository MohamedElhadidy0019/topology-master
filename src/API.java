import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Api.
 */
public class API {


    /**
     * The Topologies.
     */
    List<Topology> topologies;


    /**
     * Instantiates a new Api.
     */
    public API(){
        topologies=new ArrayList<>();
    }


    /**
     * Read json boolean.
     * This function tkaes the path of the json file, read it and fill the topoplogy list with the topologies.
     *
     * @param filePath the file path.
     * @return the boolean true if success, false if exception happened.
     */
    Boolean ReadJson(String filePath){
        File input=new File(filePath);
        String id="";
        try {

            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            id=fileObject.get("id").getAsString();
            Topology topology=new Topology(id,fileObject);

            for (JsonElement component : fileObject.get("components").getAsJsonArray()) {

                JsonObject componentObject = component.getAsJsonObject();
                String component_type=componentObject.get("type").getAsString();
                String component_id=componentObject.get("id").getAsString();


                switch (component_type) {
                    case "resistor":
                        Resistor resistor=new Resistor(component_type,component_id,componentObject.get("resistance").getAsJsonObject(),componentObject.get("netlist").getAsJsonObject());
                        //System.out.println(componentObject.get("resistance").getAsJsonObject().get("default").getAsString());
                        topology.addComponent(resistor);
                        break;
                    case "nmos":
                        Nmos nmos=new Nmos(component_type,component_id,componentObject.get("m(l)").getAsJsonObject(),componentObject.get("netlist").getAsJsonObject());
                        topology.addComponent(nmos);
                        break;
                }
            }
            topologies.add(topology);
            return true;

        } catch (FileNotFoundException e) {
            return false;
        }


    }

    /**
     * Write json boolean.
     *This function save the topoplogy as a json file on the hard drive.
     * @param topology_id the topology id
     * @param json_name   the json name
     * @return the  boolean true if success, false if exception happened or the topology id does not exist.
     */
    Boolean writeJson(String topology_id,String json_name){
        //itterate over topologies

        for (Topology topology:topologies) {
            if(topology.id.equals(topology_id)){
                FileWriter myWriter = null;
                try {
                    myWriter = new FileWriter(json_name+".json");
                    myWriter.write(topology.json_topology.toString());
                    myWriter.close();
                    return true;
                } catch (IOException e) {
                    return false;
                }

            }
        }
        return false;
    }

    /**
     * Query topologies list.
     *
     * @return the list of all topologies.
     */
    public List<Topology> queryTopologies() {
        return topologies;
    }

    /**
     * Query devices list.
     * This function return a list of all devices in the topology given the topology id.
     * @param topology_id the topology id
     * @return the list
     */
    List<Component> queryDevices(String topology_id) {
        List<Component> components = null;
        for (Topology topology:topologies) {
            if(topology.id.equals(topology_id)){
                components=topology.components;
            }
        }
        return components;
    }

    /**
     * Delete topology boolean.
     *
     * @param topology_id the topology id
     * @return the boolean true if deleted, false if id does not exist.
     */
    Boolean deleteTopology(String topology_id){
        for (Topology topology:topologies) {
            if(topology.id.equals(topology_id)){
                topologies.remove(topology);
                return true;
            }
        }
        return false;
    }

    /**
     * Query devices with netlist node list.
     * This function return a list of all devices connected to a node in the topology given the topology id and the name of the wanted node.
     * @param topology_id the topology id
     * @param node_id     the node id
     * @return the list
     */
    List<Component> queryDevicesWithNetlistNode(String topology_id,String node_id){
        List<Component> components = new ArrayList<>();
        for (Topology topology:topologies) {
            if(topology.id.equals(topology_id)){
                List<Component> temp=topology.devices_connected_netlist(node_id);

                if(temp!=null){
                    components.addAll(temp);

                }
            }
        }
        return components;
    }








}
