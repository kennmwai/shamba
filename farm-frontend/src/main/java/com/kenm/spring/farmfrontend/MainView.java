package com.kenm.spring.farmfrontend;

import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("main-view")
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public MainView() {

		add(new H1("Hello, World"));

		// Td list
		VerticalLayout todosList = new VerticalLayout();

		TextField taskField = new TextField();
		Button addButton = new Button("Add");

		addButton.addClickListener(click -> {
			Checkbox checkbox = new Checkbox(taskField.getValue());
			todosList.add(checkbox);
		});

		addButton.addClickShortcut(Key.ENTER);

		add(new H1("Vaadin Todo"), todosList, new HorizontalLayout(taskField, addButton));

		// Email
		EmailField validEmailField = new EmailField();
		validEmailField.setLabel("Email address");
		validEmailField.getElement().setAttribute("name", "email");
		validEmailField.setValue("julia.scheider@email.com");
		validEmailField.setErrorMessage("Enter a valid email address");
		validEmailField.setClearButtonVisible(true);

		EmailField invalidEmailField = new EmailField();
		invalidEmailField.setLabel("Email address");
		invalidEmailField.getElement().setAttribute("name", "email");
		invalidEmailField.setValue("This is not an email");
		invalidEmailField.setErrorMessage("Enter a valid email address");
		invalidEmailField.setClearButtonVisible(true);
		invalidEmailField.setInvalid(true);

		add(new H1("Email Validator"), new HorizontalLayout(validEmailField, invalidEmailField));

		// BadgeBasics
		Span pending  = new Span("Pending");
		pending.getElement().getThemeList().add("badge");
		
		Span confirmed = new Span("Confirmed");
		confirmed.getElement().getThemeList().add("badge success");

		Span denied = new Span("Denied");
		denied.getElement().getThemeList().add("badge error");

		Span onHold = new Span("On hold");
		onHold.getElement().getThemeList().add("badge contrast");

		add(new H1("Badges"), new HorizontalLayout(pending, confirmed, denied, onHold));
		
		// Grid
		List<Person> people = Arrays.asList(
					new Person("Nicolaus Copernicus", 1543),
					new Person("Galileo Galilei", 1564), 
					new Person("Johannes Kepler", 1571)
				);

		// Create a grid bound to the list
		Grid<Person> grid = new Grid<>();
		grid.setItems(people);
		grid.addColumn(Person::name).setHeader("Name");
		grid.addColumn(Person::yearOfBirth).setHeader("Year of birth");

		add(new H1("Grid"), grid);
	}
}
