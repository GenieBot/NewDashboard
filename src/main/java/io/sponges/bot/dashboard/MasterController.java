package io.sponges.bot.dashboard;

import io.sponges.bot.dashboard.controllers.account.AccountController;
import io.sponges.bot.dashboard.controllers.account.ChangeEmailController;
import io.sponges.bot.dashboard.controllers.account.ChangePasswordController;
import io.sponges.bot.dashboard.controllers.auth.LoginController;
import io.sponges.bot.dashboard.controllers.auth.LogoutController;
import io.sponges.bot.dashboard.controllers.auth.RegisterController;
import io.sponges.bot.dashboard.controllers.client.AddClientController;
import io.sponges.bot.dashboard.controllers.client.PickClientController;
import io.sponges.bot.dashboard.controllers.network.NetworkController;
import io.sponges.bot.dashboard.controllers.network.NetworksController;
import io.sponges.bot.dashboard.controllers.oauth.OAuthController;
import io.sponges.bot.dashboard.controllers.store.StoreController;
import io.sponges.bot.dashboard.controllers.IndexController;
import io.sponges.bot.dashboard.database.Database;
import io.sponges.bot.dashboard.entities.ClientManager;
import io.sponges.bot.dashboard.entities.ClientUserManager;
import io.sponges.bot.dashboard.entities.UserManager;
import spark.*;

import java.lang.reflect.InvocationTargetException;

public class MasterController {

    private final Server server;
    private final UserManager userManager;

    MasterController(Server server, Configuration configuration, Database database, UserManager userManager,
                     ClientManager clientManager, ClientUserManager clientUserManager) {
        this.server = server;
        this.userManager = userManager;
        register(
                new IndexController(),
                new NetworksController(),
                new NetworkController(),
                new StoreController(),
                new PickClientController(clientManager),
                new AddClientController(configuration, clientManager),
                new LoginController(userManager),
                new RegisterController(userManager),
                new LogoutController(userManager),
                new AccountController(),
                new ChangePasswordController(),
                new ChangeEmailController(),
                new OAuthController(database, configuration, clientManager, userManager, clientUserManager)
        );
    }

    private boolean isAuthenticated(Request request) {
        Session session = request.session();
        return userManager.isLoggedIn(session);
    }

    // Could do caching here, doesn't really matter as it is only called on initialization
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
                ContextImpl context = new ContextImpl(request, response, new Model());
                if (isAuthenticated(request)) {
                    context.setUser(userManager.getUser(request.session()));
                } else {
                    if (controller.isAuth()) {
                        context.alert("You must login to access that!");
                        context.redirect("/login");
                        return null;
                    }
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
                ContextImpl context = new ContextImpl(request, response, new Model());
                if (isAuthenticated(request)) {
                    context.setUser(userManager.getUser(request.session()));
                } else {
                    if (controller.isAuth()) {
                        context.alert("You must login to access that!");
                        context.redirect("/login");
                        return "Not authenticated";
                    }
                }
                return execute.invoke(controller, context);
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
