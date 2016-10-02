package io.sponges.bot.dashboard;

import io.sponges.bot.dashboard.controllers.auth.LoginController;
import io.sponges.bot.dashboard.controllers.auth.RegisterController;
import io.sponges.bot.dashboard.controllers.client.PickClientController;
import io.sponges.bot.dashboard.controllers.network.NetworkController;
import io.sponges.bot.dashboard.controllers.network.NetworksController;
import io.sponges.bot.dashboard.controllers.store.StoreController;
import io.sponges.bot.dashboard.controllers.IndexController;
import spark.*;

import java.lang.reflect.InvocationTargetException;

public class MasterController {

    private final Server server;

    MasterController(Server server) {
        this.server = server;
        register(
                new IndexController(),
                new NetworksController(),
                new NetworkController(),
                new StoreController(),
                new PickClientController(),
                new LoginController(),
                new RegisterController()
        );
    }

    private boolean isAuthenticated(Request request) {
        // TODO authentication shit
        return true;
    }

    private void register(Controller controller, boolean template, Method method, java.lang.reflect.Method execute)
            throws InvocationTargetException, IllegalAccessException {
        Service service = server.getService();
        Class<? extends Service> clazz = service.getClass();
        String methodName = method.name().toLowerCase();
        if (template) {
            java.lang.reflect.Method m;
            try {
                m = clazz.getMethod(methodName, String.class, TemplateViewRoute.class, TemplateEngine.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return;
            }
            m.invoke(service, controller.getRoute(), (TemplateViewRoute) (request, response) -> {
                Context context = new ContextImpl(request, response, new Model());
                if (controller.isAuth() && !isAuthenticated(request)) {
                    context.alert("Not authenticated!");
                    return new ModelAndView(context.getModel().build(), "error.ftl");
                }
                return (ModelAndView) execute.invoke(controller, context);
            }, server.getEngine());
        } else {
            java.lang.reflect.Method m;
            try {
                m = clazz.getMethod(methodName, String.class, spark.Route.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return;
            }
            m.invoke(service, controller.getRoute(), (spark.Route) (request, response) -> {
                if (controller.isAuth() && !isAuthenticated(request)) {
                    return "Not authenticated.";
                }
                return execute.invoke(controller, new ContextImpl(request, response, new Model()));
            });
        }
    }

    private void register(Controller controller) {
        Class<? extends Controller> clazz = controller.getClass();
        for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
            Route route = method.getAnnotation(Route.class);
            if (route == null) continue;
            Class<?> returnType = method.getReturnType();
            boolean template = returnType == ModelAndView.class;
            try {
                register(controller, template, route.method(), method);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void register(Controller... controllers) {
        for (Controller controller : controllers) {
            register(controller);
        }
    }

}
