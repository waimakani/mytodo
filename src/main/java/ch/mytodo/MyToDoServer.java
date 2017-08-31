package ch.mytodo;

import ch.mytodo.api.DebugService;
import ch.mytodo.impl.PersistenceServiceImpl;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import spark.*;

import java.io.InputStream;
import java.io.InputStreamReader;

import static spark.Spark.get;
import static spark.Spark.modelAndView;

public class MyToDoServer {

    private DebugService debugService;

    public MyToDoServer(){
        debugService = new DebugService();
        debugService.setPersistenceService(new PersistenceServiceImpl());
    }

    public void startServer(){
        get("/ping", (req, res) -> debugService.ping());
        get("/persistence", (request, response) -> {
            response.type("application/json");
            return debugService.schemaVersionInfo();
        });
        TemplateViewRoute route = (request, response) ->
                modelAndView(Maps.newHashMap(), "index.html");
        get("/mytodo-app", route, new HtmlEngine());
    }

    private class HtmlEngine extends TemplateEngine {
        @Override
        public String render(ModelAndView modelAndView) {
            InputStream stream = HtmlEngine.class.getResourceAsStream("/" + modelAndView.getViewName());
            String html = "";
            try {
                html = CharStreams.toString(new InputStreamReader(stream, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return html;
        }
    }
}
