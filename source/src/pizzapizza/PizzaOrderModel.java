package pizzapizza;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Project : A10
 * File    : PizzaOrderModel.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : This class manages the functionality
 *               of the program. Although it provides
 *               no interface for the user, it keeps
 *               track of customers and orders as well
 *               as managing serialization.
 */
public class PizzaOrderModel
{
    private LinkedList<Order> queue;
    public HashMap<Long, Customer> customerHash;
    public Order order;
    public Customer customer;

    /**
     * When this object is created objects are read from
     * a ser file so that some degree of this program's
     * state can be the same as when the program was last
     * closed.
     */
    @SuppressWarnings("unchecked")
    PizzaOrderModel()
    {
        customerHash = (HashMap<Long, Customer>)readFromFile("Customers.ser");
        if (customerHash == null) customerHash = new HashMap<>();
        queue = (LinkedList<Order>)readFromFile("Queue.ser");
        if (queue == null) queue = new LinkedList<>();
    }

    /**
     * Updates the state of this object to reflect the
     * current customer being served.
     * @param customer The customer that is being served.
     * @return Returns false the last order was not cleared.
     */
    public boolean setCustomer(Customer customer)
    {
        if (order != null) return false;
        order = new Order(customer);
        this.customer = customer;
        return true;
    }

    /**
     * Clears the current order.
     */
    public void clear()
    {
        order = null;
        customer = null;
    }

    /**
     * Updates a customer with new info. Also used to
     * record a completely new customer.
     * @param customer A customer object with the desired info.
     */
    public void update(Customer customer)
    {
        this.customer = customer;
        customerHash.put(customer.getPhone(), customer);
        writeToFile("Customers.ser", customerHash);
    }

    /**
     * Called when the current order is finished.
     */
    public void submitOrder()
    {
        queue.push(order);
        //writeToFile("Queue.ser", queue);//Not really part of the specification
    }

    /**
     * Creates and adds a pizza to the order.
     */
    public void addPizzaToOrder()
    {
        Topping[] tempTopping = new Topping[CheckBoxWrapper.all.length];
        int i = 0;//Moved to the outside of for loop. Brian Blake
        for(CheckBoxWrapper a :CheckBoxWrapper.all)
        {
            if (a.checkBox.isSelected())
            {
                tempTopping[i++] = a.topping;
            }
        }
        
        for(RadioButtonWrapper a : RadioButtonWrapper.allRadioButtons)
        {
            if (a.radio.isSelected())
            {
                order.addPizza(new Pizza (a.size, tempTopping));
            }
        } 
    }

    /**
     * Searches the HashMap for a Customer via phone number.
     * @param number The phone number to use in searching.
     * @return The customer or null.
     */
    public Customer searchCustomerViaPhone(long number)
    {
        return customerHash.get(number);
    }

    /**
     * Attempts to write an object to a file.
     * @param file The file's name.
     * @param object The object to be serialized.
     * @return Did serialization succeed?
     */
    public boolean writeToFile(String file, Serializable object)
    {
        boolean success = false;
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file)))
        //Automatic Resource Management block. https://docs.google.com/document/d/130ZhGw7zaK7P8XZQlW8mNMtOAC9Ve_pmGR9ayXFW_Ds/preview?pli=1
        {
            stream.writeObject(object);
            success = true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Attempts to read an object from file.
     * @param file The file's name.
     * @return The resulting object or null.
     */
    public Object readFromFile(String file)
    {
        Object toReturn = null;
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file)))
        {
            toReturn = stream.readObject();
        }
        catch(FileNotFoundException e)
        {//Silence
        }
        catch(IOException|ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return toReturn;
    }
}
