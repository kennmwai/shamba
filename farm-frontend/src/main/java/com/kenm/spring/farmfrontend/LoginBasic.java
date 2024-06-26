package com.kenm.spring.farmfrontend;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("login-basic")
public class LoginBasic extends Div {

    public LoginBasic() {
        // Demo purposes only
        getStyle().set("background-color", "var(--lumo-contrast-5pct)")
                .set("display", "flex").set("justify-content", "center")
                .set("padding", "var(--lumo-space-l)");

        LoginForm loginForm = new LoginForm();
        add(loginForm);
        // Prevent the example from stealing focus when browsing the
        // documentation
        loginForm.getElement().setAttribute("no-autofocus", "");
    }

}
