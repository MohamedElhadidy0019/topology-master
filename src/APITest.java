import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APITest {


    @org.junit.jupiter.api.Test
    void test_ReadJson_1(){
        API api=new API();
        //Boolean result = api.ReadJson("src/topology.json");
        Boolean result = api.ReadJson("src/topology.json");
        assertEquals(true, result);
    }

    @org.junit.jupiter.api.Test
    void test_ReadJson_2(){
        API api=new API();
        Boolean result = api.ReadJson("src/topology_1.json");
        assertEquals(false, result);
    }

    @Test
    void test_writeJson_1() {
        API api=new API();
        api.ReadJson("src/topology.json");
        Boolean result = api.writeJson("top1","src/topology_new.json");
        assertEquals(true, result);
    }

    @Test
    void test_writeJson_2() {
        API api=new API();
        api.ReadJson("src/topology.json");
        Boolean result = api.writeJson("top12","src/topology_new.json");
        assertEquals(false, result);
    }

    @Test
    void test_deleteTopology_1(){
        API api=new API();
        api.ReadJson("src/topology.json");
        Boolean result = api.deleteTopology("top1");
        assertEquals(true, result);
    }

    @Test
    void test_deleteTopology_2(){
        API api=new API();
        api.ReadJson("src/topology.json");
        Boolean result = api.deleteTopology("top12");
        assertEquals(false, result);
    }






}