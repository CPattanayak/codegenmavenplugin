package codegen.plugin;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Mojo( name = "codeGen")
public class CodegenMojo extends AbstractMojo {
    @Parameter( defaultValue = "codegen" )
    private String codegen;
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info( "getting data from "+ codegen +" folder");
        getLog().info("---------------------------------------");
        Configuration cfg = new Configuration();
        try {
            Template template=cfg.getTemplate(codegen);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            FileWriter file = new FileWriter (new File("src/main/java/MainClass.java"));
            template.process(dataMap, file);
            file.flush();
            getLog().info("success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
