import com.google.gson.JsonObject;

abstract public class Component {
    String type;
    String id;


    //constructor
    public Component(String type, String id  ) {

        this.type = type;
        this.id = id;
    }
    abstract void print_info();

    abstract Boolean is_node_connected(String node_id);


}
