package object;

public class Num implements Obj{
    double value;

    @Override
    public Object getValue(String name) {
        return value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Num";
    }

    @Override
    public void setValue(Object val) {
        value = (double) val;
    }

}
