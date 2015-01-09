package pizzapizza;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Project : A10
 * File    : Order.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : Objects of type Order eash have a customer
 *               and any number of pizzas associated with them.
 *               Each Order is timestamped upon creation. This
 *               class also has methods for totaling the price
 *               of an order.
 */
public class Order implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final Customer customer;
    private final List<Pizza> pizzas;
    public final Date timeStamp;

    /**
     * Creates a new Order wih this customer.
     * @param customer
     */
    Order(Customer customer)
    {
        this.customer = customer;
        pizzas = new ArrayList<Pizza>();
        timeStamp = new Date();
    }

    /**
     * Adds a pizza to the Order.
     * @param pizza The pizza to be added.
     */
    public void addPizza(Pizza pizza)
    {
        pizzas.add(pizza);
    }

    /**
     * Getter for customer field.
     * @return The Customer this Order is assosiated with.
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * Totals the price for all pizzas in this order.
     * @return The price for this order.
     */
    public double getPrice()
    {
        double priceX = 0;
        for(Pizza pizza : this.pizzas)
        {
            priceX = priceX + pizza.price();
        }
        return priceX;
    }

    /**
     * Formats the price for this order to a String.
     * @return A String representing the price.
     */
    public String getPriceAsString()
    {
        return "$" + String.format("%.2f", getPrice());
    }
    
    @Override
    public String toString()
    {
        String toReturn = "";
        for (Pizza p : this.pizzas) toReturn += p.toString() + "\n";
        return toReturn;
    }
}
