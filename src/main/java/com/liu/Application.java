package com.liu;


import com.liu.util.MySplashScreen;
import com.liu.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@SpringBootApplication
@MapperScan("com.liu.mapper")
public class Application extends AbstractJavaFxApplicationSupport implements ApplicationRunner {

    public static void main(String[] args) {
        launch(Application.class, MainView.class, new MySplashScreen(), args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner.run...");
        Thread.sleep(1000);
    }

    @Override
    public void init() throws Exception {
        log.info("init");
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        log.info("start");
        super.start(stage);
    }

    @Override
    public void beforeShowingSplash(Stage splashStage) {
        super.beforeShowingSplash(splashStage);
        log.info("beforeShowingSplash");
    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        log.info("beforeInitialView");
        super.beforeInitialView(stage, ctx);
    }

    @Override
    public void stop() throws Exception {
        log.info("stop");
        super.stop();
    }

    // 虽然在application.yml中可以设置应用图标，但是首屏启动时的应用图标未改变，建议在此处覆盖默认图标
    @Override
    public Collection<Image> loadDefaultIcons() {
        return Collections.singletonList(new Image(getClass().getResource("/icon/icon.png").toExternalForm()));
    }

}
