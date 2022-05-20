import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Topology {
    List<Component> components;
    String id;
    JsonObject json_topology;


    public Topology(String id, JsonObject json_topology) {
        this.components = new ArrayList<>();
        this.id = id;
        this.json_topology = json_topology;
    }
    public Topology(List<Component> components, String id, JsonObject json_topology) {
        this.components = components;
        this.id = id;
        this.json_topology = json_topology;
    }


    public void print_info_components(){
        System.out.println("======================================================================");
        System.out.println("TOPOLOGY ID: " + this.id);
        System.out.println("___________");
        for(Component component : this.components){
            component.print_info();
        }
        System.out.println("=====================================================================");
    }

    List<Component> devices_connected_netlist(String node_name){
        //loop over components
        //if component is a device
        //add to list
        List<Component> devices = new ArrayList<>();
        for(Component component : this.components){
            if(component.is_node_connected(node_name)){
                devices.add(component);
            }
        }
        return devices;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }


}
