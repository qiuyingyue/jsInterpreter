import java.io.File;

public class FileProcess {
    private File file;
    private Environment env;
    private int current;

    public FileProcess (File file, Environment env){
        this.file = file;
        this.env = env;
        this.current = 1;
        // 加标记  放在env.go
    }

    public int lineProcess(int line){
        int next = 0;
        //处理第line行 返回下一个处理第几行(next)
        //完毕CONST.FINAL 出错CONST.ERROR
        return next;
    }

}
