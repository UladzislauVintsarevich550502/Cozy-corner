package bsuir.vintsarevich.command;


import bsuir.vintsarevich.command.impl.forwarding.*;
import bsuir.vintsarevich.command.impl.redirecting.*;
import bsuir.vintsarevich.enumeration.CommandName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * class CommandProvider created to choose command by command name
 */
public final class CommandProvider {
    private final static CommandProvider instance = new CommandProvider();
    private static final Logger LOGGER = Logger.getLogger(CommandProvider.class);
    private Map<CommandName, ICommand> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.INDEX, new Index());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        repository.put(CommandName.ADD_PRODUCT, new AddProduct());
        repository.put(CommandName.BASKET, new Basket());
        repository.put(CommandName.ADD_PRODUCT_TO_BASKET, new AddBasketProduct());
        repository.put(CommandName.REMOVE_PRODUCT_FROM_BASKET, new RemoveBasketProduct());
        repository.put(CommandName.SET_CURRENT_PAGE, new SetCurrentPage());
        repository.put(CommandName.SEARCH_PRODUCT, new SearchProduct());
        repository.put(CommandName.FIND_BY_TYPE, new FindByType());
        repository.put(CommandName.ADD_ADMIN, new AddAdmin());
        repository.put(CommandName.EDIT_PRODUCT, new EditProduct());
        repository.put(CommandName.DELETE_PRODUCT, new DeleteProduct());
        repository.put(CommandName.RESET_FORM, new ResetForm());
    }

    /**
     * @return CommandProvider
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * @param request
     * @return ICommand
     */
    public ICommand getCommand(HttpServletRequest request) {
        ICommand iCommand = repository.get(CommandName.WRONG_REQUEST);
        String command = request.getRequestURI();
        command = command.replace("/cafe.by/", "");
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase().replace('-', '_'));
            LOGGER.log(Level.DEBUG, "Command name:" + commandName.toString());
            iCommand = repository.get(commandName);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.DEBUG, "No such support command name");
            request.setAttribute("wrongAction", e.getMessage());
        }
        return iCommand;
    }
}
