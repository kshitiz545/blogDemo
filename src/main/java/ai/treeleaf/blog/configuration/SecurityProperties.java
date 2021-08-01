package ai.treeleaf.blog.configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class SecurityProperties {

    private static String classPath;
    private static Resource resource ;
    private static Properties props;

    public static Properties getProps() {
        return props;
    }

    private static void setClassPath(String classPath) {
        SecurityProperties.classPath = classPath;
    }

    private static void setResource(Resource resource) {
        SecurityProperties.resource = resource;
    }

    private static void setProps(Properties props) {
        SecurityProperties.props = props;
    }

    private static String getClassPath() {
        return classPath;
    }

    private static Resource getResource() {
        return resource;
    }

    public SecurityProperties() {
        setClassPath("security.properties");
        setResource(new ClassPathResource(getClassPath()));
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(getResource());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setProps(properties);
    }

}
