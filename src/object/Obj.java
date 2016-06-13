package object;

import java.util.Hashtable;
import java.util.Objects;

public interface Obj{
    Object getValue(String name);

    String getType();

    void setValue(Object val);
}
