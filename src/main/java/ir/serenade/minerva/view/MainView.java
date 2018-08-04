package ir.serenade.minerva.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.repository.ActivityRepository;

@Route
public class MainView extends VerticalLayout {

    private final ActivityRepository repo;

    final Grid<Activity> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(ActivityRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(Activity.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid);

        grid.setHeight("300px");
        grid.setColumns("id", "action", "name", "album", "publisher", "aggregator", "dateCreated");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Initialize listing
        listCustomers(null);
    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        grid.setItems(repo.findAll());
    }
    // end::listCustomers[]

}