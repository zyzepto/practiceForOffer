package BasicKnowledgeDemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    /**
     * @author zepto
     * @Description 多个classLoader下的单例变多例
     * 自定义的类加载器没有指定父加载器，在JVM规范中不指定父类加载器的情况下，
     * 默认采用系统类加载器即AppClassLoader作为其父加载器，所以在使用该自定义类加载器时，
     * 需要加载的类不能在类路径中，否则的话根据双亲委派模型的原则，待加载的类会由系统类加载器加载。
     * 如果一定想要把自定义加载器需要加载的类放在类路径中, 就要把自定义类加载器的父加载器设置为null。
     * @date 2018/12/28
     * @return void
     **/
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String folder = "urlClass";
        String url = "file:/" + System.getProperty("user.dir") + File.separator + folder + File.separator;
        System.out.println(url);
        URL[] arrUrl = new URL[]{new URL(url)};
        ClassLoader loader1 = new URLClassLoader(arrUrl);
        ClassLoader loader2 = new URLClassLoader(arrUrl);
        loader1.loadClass("BasicKnowledgeDemo.ClassLoaderTest").newInstance();
        loader2.loadClass("BasicKnowledgeDemo.ClassLoaderTest").newInstance();
    }
}
