import java.io.File;

public class N2S {
    public static void main(String args[]) {
        File file = new File("test.ns");
        Environment current = new Environment(null);
        FileProcess fp = new FileProcess(file, current);
        int line = 1;

        while(true){
            line = fp.lineProcess(line);
        }

    }
}