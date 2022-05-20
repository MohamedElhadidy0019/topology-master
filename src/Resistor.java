import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Resistor extends Component{
    HashMap<String,Double> resistance;
    HashMap <String,String> netlist;



    public Resistor(String type, String id, JsonObject jsonObject_resistance, JsonObject jsonObject_netlist) {
        super(type, id);
        //System.out.println(jsonObject_resistance.get("default").getAsInt());
        resistance = new HashMap<>();
        netlist = new HashMap<>();



        resistance.put("default", jsonObject_resistance.get("default").getAsDouble());
        resistance.put("min", jsonObject_resistance.get("min").getAsDouble());
        resistance.put("max", jsonObject_resistance.get("max").getAsDouble());

        netlist.put("t1", jsonObject_netlist.get("t1").getAsString());
        netlist.put("t2", jsonObject_netlist.get("t2").getAsString());






    }

    Boolean is_node_connected(String node_id){

        for (String name: netlist.keySet()) {
            String key = name.toString();
            String value = netlist.get(name).toString();
            if(value.equals(node_id)){
                return true;
            }
        }

        return false;
    }

    void print_info(){
        System.out.println("Resistor");
        System.out.println("Type: " + type);
        System.out.println("ID: " + id);
        System.out.println("Resistance: " + resistance.get("default"));
        System.out.println("Netlist: " + netlist.get("t1") + " " + netlist.get("t2"));

        System.out.println("*********************************************************");
    }

}
