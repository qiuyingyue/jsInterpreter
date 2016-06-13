import java.util.Hashtable;
import object.*;

public class Environment {
    Hashtable<String, Obj> arugement = null;
    Hashtable<String, Integer> go = null;
    Hashtable<String, Obj> val = null;
    Environment parent = null;


    public Environment(Environment parent){
        arugement = new Hashtable<>();
        go = new Hashtable<>();
        val = new Hashtable<>();
        this.parent = parent;
    }

    public Hashtable<String, Obj> findVal(String name){
        Object val = null;
        Environment env = this;
        while (env.val != null){
            if (env.val.containsKey(name)){
                return env.val;
            }else{
                env = env.parent;
            }
        }
        return null;
    }
}
