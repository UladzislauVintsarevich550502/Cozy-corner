package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class ChangePasswordFunction created to change password
 */
public class ChangePasswordFunction implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INDEX;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Change password start");
        IAdminService adminService = serviceFactory.getAdminService();
        IClientService clientService = serviceFactory.getClientService();
        IStaffService staffService = serviceFactory.getStaffService();

        User user = (User) request.getSession().getAttribute("user");

        String oldPassword = request.getParameter(AttributeParameterName.OLD_PASSWORD.getValue());
        String newPassword = request.getParameter(AttributeParameterName.NEW_PASSWORD.getValue());
        String newPasswordRepeat = request.getParameter(AttributeParameterName.NEW_PASSWORD_REPEAT.getValue());
        try {
            if (user.getRole().equals("admin")) {
                if (adminService.checkPassword(oldPassword, user.getId()) && newPassword.equals(newPasswordRepeat)) {
                    adminService.changePassword(newPassword, user.getId());
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                    return jspPageName.getPath();
                }
            }
            if (user.getRole().equals("client")) {
                if (clientService.checkPassword(oldPassword, user.getId()) && newPassword.equals(newPasswordRepeat)) {
                    clientService.changePassword(newPassword, user.getId());
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                    return jspPageName.getPath();
                }
            }
            if (user.getRole().equals("staff")) {
                if (staffService.checkPassword(oldPassword, user.getId()) && newPassword.equals(newPasswordRepeat)) {
                    staffService.changePassword(newPassword, user.getId());
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                    return jspPageName.getPath();
                }
            }
            diagnoseError(request);
            jspPageName = JspPageName.CHANGE_PASSWORD;
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Change password finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.CHANGE_PASSWORD_ERROR.getValue(), "Старый пароль введен не верно!");
        } else {
            request.getSession().setAttribute(AttributeParameterName.CHANGE_PASSWORD_ERROR.getValue(), "Old password is incorrect!");
        }
    }

}