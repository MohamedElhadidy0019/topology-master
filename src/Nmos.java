import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Nmos extends Component{
    HashMap<String,Double> m;
    HashMap<String,String> netlist;


    public Nmos(String type,String id, JsonObject jsonObject_m, JsonObject jsonObject_netlist ){

        super(type,id);
        m=new HashMap<>();
        netlist=new HashMap<>();


        m.put("default",jsonObject_m.get("default").getAsDouble());
        m.put("min",jsonObject_m.get("min").getAsDouble());
        m.put("max",jsonObject_m.get("max").getAsDouble());

        netlist.put("drain",jsonObject_netlist.get("drain").getAsString());
        netlist.put("gate",jsonObject_netlist.get("gate").getAsString());
        netlist.put("source",jsonObject_netlist.get("source").getAsString());




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
        System.out.println("Nmos");
        System.out.println("type: "+type);
        System.out.println("id: "+id);
        System.out.println("m: "+m);
        System.out.println("netlist: "+netlist);

        System.out.println("*********************************************************");
    }
}
