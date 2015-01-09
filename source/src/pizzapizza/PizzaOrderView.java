package pizzapizza;

import java.text.ParseException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;

/**
 * Project : A10
 * File    : PizzaOrderView.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : Arguably the heart of the program, this
 *               class contains the main method. A GUI
 *               is created and lambda expressions are
 *               used for actionListeners. The object
 *               created by main() has an instance of
 *               PizzaOrderModel to "manage the
 *               functionality of the program".
 */
public class PizzaOrderView  extends Thread
{
    private final PizzaOrderModel model = new PizzaOrderModel();
	private final JFrame frame = new JFrame();
	private final JButton
            phoneButton   = new JButton("Search Phone #"),
            updateInfo    = new JButton("Update Info"),
            addToOrder    = new JButton("Add pizza to this Order"),
            totalTheOrder = new JButton("Total this Order"),
            submitButton = new JButton("Submit"),
            clearButton = new JButton("Clear");
    private final JTextField
            nameField = new JTextField(),
            addressField = new JTextField(),
            instructionsField = new JTextField();
    private final JTextArea employeeField = new JTextArea(5,30),
            orderSummaryField = new JTextArea(5,30);
    private final JFormattedTextField phoneNumber;

    /**
     * The MaskFormatter keeps input restrained to a certain input
     */
    {
        MaskFormatter mask = null;
        try
        {
        mask = new MaskFormatter("(###) ###-####");
        }
        catch(ParseException ignored)
        {
        }
        phoneNumber = new JFormattedTextField(mask);
    }
    
    /**
     * Launch the application.
     * @param args Stuff. Secretly unused.
     */
    public static void main(String[] args) 
    {
        new PizzaOrderView().start();
        
    }
    
    /**
     * Sets up the GUI.
     */
    @Override
    public void run()
    {
        /*Phone Panel*/
            phoneButton.setToolTipText("Click to Search for Phone #");
            phoneButton.addActionListener(
                    event ->
                    {
                        Customer customer = model.searchCustomerViaPhone(Long.parseLong((phoneNumber.getText()).replaceAll("[^0-9]", "")));
                        if (customer == null) changeToCustNotFoundState();
                        else changeToCustFoundState(customer);
                    }
            );
          
            phoneNumber.setColumns(10);

            final JPanel phonePanel = new JPanel();
            phonePanel.setLayout(new MigLayout("", "0[150px]0[150px]0", "0[30px]0"));
            phonePanel.setBorder(BorderFactory.createTitledBorder("Customer Phone Number"));
            phonePanel.add(phoneButton, "flowx,cell 0 0,grow");
            phonePanel.add(phoneNumber, "cell 1 0,alignx center,growy");
        //Phone Panel*/

        /*Customer Info Panel*/
            /*Name Panel*/
                JLabel nameLabel = new JLabel("Name:");
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
          
                nameField.setColumns(17);

                final JPanel namePanel = new JPanel();
                namePanel.setLayout(new MigLayout("", "[75px][225px]", "[20px]"));
                namePanel.add(nameLabel, "cell 0 0,alignx left,aligny center");
                namePanel.add(nameField,"cell 1 0,alignx right,aligny center");
            //Name Panel*/

            /*Address Panel*/
                JLabel addressLabel = new JLabel("Address:");
                addressField.setColumns(17);

                final JPanel addressPanel = new JPanel();
                addressPanel.setLayout(new MigLayout("", "[75px][225px]", "[20px]"));
                addressPanel.add(addressLabel, "cell 0 0,alignx left,aligny center");
                addressPanel.add(addressField,"cell 1 0,alignx right,aligny center");
            //Address Panel*/

            /*Instructions Panel*/
                JLabel instructionsLabel = new JLabel("Instructions:");

                instructionsField.setColumns(17);

                final JPanel instructionsPanel = new JPanel();
                instructionsPanel.setLayout(new MigLayout("", "[75px][225]", "[20px]"));
                instructionsPanel.add(instructionsLabel, "cell 0 0,alignx left,aligny center");
                instructionsPanel.add(instructionsField,"cell 1 0,alignx right,aligny center");
            //Instructions Panel*/

            updateInfo.addActionListener(
                    event ->
                    {
                        Customer customer = new Customer(
                                Long.parseLong((phoneNumber.getText()).replaceAll("[^0-9]", "")),
                                nameField.getText(),
                                addressField.getText(),
                                instructionsField.getText());
                        model.update(customer);
                        changeToCustFoundState(customer);
                    }
            );
            updateInfo.setToolTipText("Click to Update Info");

            final JPanel customerInfoPanel = new JPanel();
            customerInfoPanel.setLayout(new MigLayout("", "0[300px]0", "0[40px]0[]0[]0[20px]0"));
            customerInfoPanel.setBorder(BorderFactory.createTitledBorder("Customer Info"));
            customerInfoPanel.add(namePanel, "flowx,cell 0 0,alignx center");
            customerInfoPanel.add(addressPanel, "cell 0 1,alignx left");
            customerInfoPanel.add(instructionsPanel, "flowx,cell 0 2,alignx left");
            customerInfoPanel.add(updateInfo, "cell 0 3,alignx center,aligny center");
        //Customer Info Panel*/

        /*Order Panel*/
            /*Toppings Panel*/
                /*Meat Toppings Panel*/
                    final JPanel meatToppingsPanel = new JPanel();
                    meatToppingsPanel.setLayout(new MigLayout("", "0[]0", "0[]0[]0[]0[]0"));
                    meatToppingsPanel.setBorder(BorderFactory.createTitledBorder("Meat Toppings:"));

                    meatToppingsPanel.add(CheckBoxWrapper.anchovies.checkBox, "cell 0 0,alignx left, aligny center");
                    meatToppingsPanel.add(CheckBoxWrapper.pepperoni.checkBox, "cell 0 1,alignx left, aligny center");
                    meatToppingsPanel.add(CheckBoxWrapper.sausage.checkBox, "cell 0 2,alignx left, aligny center");
                    meatToppingsPanel.add(CheckBoxWrapper.italianSausage.checkBox, "cell 0 3,alignx left, aligny center");
                //Meat Toppings Panel*/

                /*Veggie Toppings Panel*/
                    final JPanel veggieToppingsPanel = new JPanel();
                    veggieToppingsPanel.setLayout(new MigLayout("","0[]0","0[]0[]0[]0[]0"));
                    veggieToppingsPanel.setBorder(BorderFactory.createTitledBorder("Veggie Toppings"));

                    veggieToppingsPanel.add(CheckBoxWrapper.blackOlives.checkBox, "cell 0 0,alignx left, aligny center");
                    veggieToppingsPanel.add(CheckBoxWrapper.mushrooms.checkBox, "cell 0 1,alignx left, aligny center");
                    veggieToppingsPanel.add(CheckBoxWrapper.onions.checkBox, "cell 0 2,alignx left, aligny center");
                    veggieToppingsPanel.add(CheckBoxWrapper.roastedPeppers.checkBox, "cell 0 3,alignx left, aligny center");
                //Veggie Toppings Panel*/

                final JPanel toppingsPanel = new JPanel();
                toppingsPanel.setLayout(new MigLayout("", "[]", "[]"));
                toppingsPanel.setBorder(BorderFactory.createTitledBorder("Pizza Order"));
                toppingsPanel.add(meatToppingsPanel);
                toppingsPanel.add(veggieToppingsPanel);
            //Toppings Panel*/

            /*Pizza Size Panel*/
                RadioButtonWrapper.small.radio.setToolTipText("Select Small Pizza");
                RadioButtonWrapper.medium.radio.setToolTipText("Select Medium Pizza");
                RadioButtonWrapper.large.radio.setToolTipText("Select for Large Pizza");

                final JPanel pizzaSizesPanel = new JPanel();
                pizzaSizesPanel.setToolTipText("Select Size of Pizza");
                pizzaSizesPanel.add(RadioButtonWrapper.small.radio);
                pizzaSizesPanel.add(RadioButtonWrapper.medium.radio);
                pizzaSizesPanel.add(RadioButtonWrapper.large.radio);
                pizzaSizesPanel.setBorder(BorderFactory.createTitledBorder("Pizza Size"));
                pizzaSizesPanel.setLayout(new MigLayout("", "0[]0[]0[]0", "0[30px]0"));
            //Pizza Size Panel*/

            /*Order Buttons Panel*/
                addToOrder.addActionListener(event -> changeToOrderStartedState());
                addToOrder.setToolTipText("Click to Add Pizza");
              
                totalTheOrder.addActionListener(event -> changeToOrderFinishedState());
                totalTheOrder.setToolTipText("Click to Total Order");

                final JPanel orderButtonsPanel = new JPanel();
                orderButtonsPanel.setLayout(new MigLayout("", "0[140px][140px]0", "0[23px]0"));
                orderButtonsPanel.add(addToOrder, "cell 0 0,growx,aligny top");
                orderButtonsPanel.add(totalTheOrder, "cell 1 0,growx,aligny top");
            //Order Buttons Panel*/

            final JPanel orderPanel = new JPanel();
            orderPanel.setLayout(new MigLayout("", "0[300px]0", "0[140px]0[30px]0"));
            orderPanel.add(toppingsPanel, "cell 0 0,alignx center,aligny top");
            orderPanel.add(pizzaSizesPanel, "cell 0 1,alignx center,aligny center");
        //Order Panel*/

        /*Order Summary Panel*/
            orderSummaryField.setToolTipText("Order Summary Details\r\n");
            JScrollPane orderSummaryPane = new JScrollPane(orderSummaryField);

            final JPanel orderSummaryPanel = new JPanel();
            orderSummaryPanel.setLayout(new MigLayout("", "[300px]", "[100px]"));
            orderSummaryPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Order Summary", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
            orderSummaryPanel.setToolTipText("Order Summary Details");
            orderSummaryPanel.add(orderSummaryPane, "cell 0 0,alignx center,aligny center");
        //Order Summary Panel*/

        /*Finish Up Panel*/
            submitButton.addActionListener(event -> changeToOrderSubmittedState());
            clearButton.addActionListener(event -> changeToSearchState());

            final JPanel finishUpPanel = new JPanel();
            finishUpPanel.setBorder(new TitledBorder(null, "Finish the Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            finishUpPanel.setLayout(new MigLayout("", "150px", "[23px]"));
            finishUpPanel.add(submitButton, "cell 0 0,growx,aligny top");
            finishUpPanel.add(clearButton, "cell 1 0,growx,aligny top");
        //Finish Up Panel*/

        /*Employee Panel*/
            final JPanel employeePanel = new JPanel();
            employeePanel.setBorder(new TitledBorder(null, "Employee Instructions:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            employeePanel.add(employeeField);
        //Employee Panel*/

        frame.setResizable(false);
        frame.setBounds(100, 100, 375, 830);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new WrapLayout());
          
        frame.getContentPane().add(phonePanel);
        frame.getContentPane().add(customerInfoPanel);
        frame.getContentPane().add(orderPanel);
        frame.getContentPane().add(orderButtonsPanel);
        frame.getContentPane().add(orderSummaryPanel);
        frame.getContentPane().add(finishUpPanel);
        frame.getContentPane().add(employeePanel);
        frame.setVisible(true);
        changeToSearchState();
    }

    /**
     * Changes the GUI's state to Search State.
     * Can be called from any other state.
     */
    private void changeToSearchState()
    {
        phoneButton.setEnabled(true);
        updateInfo.setEnabled(false);
        addToOrder.setEnabled(false);
        totalTheOrder.setEnabled(false);
        submitButton.setEnabled(false);
        clearButton.setEnabled(true);
        
        phoneNumber.setEnabled(true);
        nameField.setEnabled(false);
        addressField.setEnabled(false);
        instructionsField.setEnabled(false);
        
        nameField.setText(null);
        addressField.setText(null);
        instructionsField.setText(null);
        employeeField.setText(null);
        orderSummaryField.setText(null);
        phoneNumber.setText(null);
        
        for (CheckBoxWrapper c : CheckBoxWrapper.all)
            c.checkBox.setSelected(false);
        RadioButtonWrapper.large.radio.setSelected(true);

        employeeField.setText("Enter a phone number.");

        model.clear();
    }

    /**
     * Changes the GUI's state to Customer Not Found State.
     * Can be called from the Search State.
     */
    private void changeToCustNotFoundState()
    {
        phoneButton.setEnabled(false);
        updateInfo.setEnabled(true);
        
        phoneNumber.setEnabled(false);
        nameField.setEnabled(true);
        addressField.setEnabled(true);
        instructionsField.setEnabled(true);

        employeeField.setText("Customer Not Found. Please update info.");
    }

    /**
     * Changes the GUI's state to Customer Found State.
     * Can be called from the Search State or the Customer Not Found State.
     */
    private void changeToCustFoundState(Customer customer)
    {
        model.setCustomer(customer);

        phoneButton.setEnabled(false);
        updateInfo.setEnabled(true);
        addToOrder.setEnabled(true);

        phoneNumber.setEnabled(false);
        nameField.setEnabled(true);
        addressField.setEnabled(true);
        instructionsField.setEnabled(true);

        nameField.setText(customer.getName());
        addressField.setText(customer.getAddress());
        instructionsField.setText(customer.getInstructions());

        employeeField.setText("Customer is in database. Please update info or add a pizza.");
    }

    /**
     * Changes the GUI's state to Order Started State.
     * Can be called from the Customer Found State and the Order Started State.
     */
    private void changeToOrderStartedState()
    {
        model.addPizzaToOrder();
        orderSummaryField.setText(model.order.toString());
        employeeField.setText("Pizza Added");

        updateInfo.setEnabled(false);
        totalTheOrder.setEnabled(true);

        nameField.setEnabled(false);
        addressField.setEnabled(false);
        instructionsField.setEnabled(false);

        for (CheckBoxWrapper c : CheckBoxWrapper.all)
            c.checkBox.setSelected(false);
        RadioButtonWrapper.large.radio.setSelected(true);
    }

    /**
     * Changes the GUI's state to Order Finished State.
     * Can be called from the Order Started State.
     */
    private void changeToOrderFinishedState()
    {
        updateInfo.setEnabled(false);
        addToOrder.setEnabled(false);
        totalTheOrder.setEnabled(false);
        submitButton.setEnabled(true);

        for (CheckBoxWrapper c : CheckBoxWrapper.all)
            c.checkBox.setSelected(false);
        RadioButtonWrapper.large.radio.setSelected(true);
        orderSummaryField.append("Total: " + model.order.getPriceAsString());

        employeeField.setText("The total is displayed above in the Order Summary. \nPlease verify, and submit the order.");
    }

    /**
     * Changes the GUI's state to Order Submitted State.
     * Can be called from the Order Finished State.
     */
    private void changeToOrderSubmittedState()
    {
        submitButton.setEnabled(false);
        orderSummaryField.append("\nOrder Complete!");

        employeeField.setText("Order Complete! Please Start Another Order.");

        model.submitOrder();
    }
}
