package pizzapizza;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Project : A10
 * File    : Pizza.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : Objects of type Pizza each have a
 *               Size and a list of toppings. There
 *               is a toString method as well as
 *               pricing methods that are used by
 *               the Order class.
 */
public class Pizza implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Topping> toppings;
    private Size size;

    /**
     * Creates a Pizza object with the specified
     * size and toppings.
     * @param size The size of the pizza.
     * @param tArray An array of the toppings this pizza will have.
     */
    public Pizza(Size size, Topping[] tArray)
    {
        toppings = new LinkedList<Topping>();
        this.size = size;
        
        for (Topping t : tArray)
        {
            if (t == null) break;
            else toppings.add(t);
        }
    }

    /**
     * This methods is responsible for pricing the pizza.
     * @return The price of this pizza
     */
    public double price()
    {
        return size.getPrice() +  toppings.size()*0.20;
    }

    /**
     * Formats the price of this pizza as a String.
     * @return A String representing the price.
     */
    public String priceAsString()
    {
        return "$" + String.format("%.2f", price());
    }

    @Override
    public String toString()
    {
        Iterator<Topping> iter = toppings.iterator();
        String toReturn = size.toString() + " pizza";
        if (toppings.size() == 0) return toReturn + " with no toppings. " + priceAsString();
        if (toppings.size() == 8) return toReturn + " with all the toppings! " + priceAsString();
        Topping t = iter.next();
        toReturn += " with " + t.toString();
        if (toppings.size() == 1) return toReturn + t.toString() + ". " + priceAsString();
        t = iter.next();
        while(iter.hasNext())
        {
          toReturn += ", " + t.toString();
          t = iter.next();
        }
        return toReturn + " and " + t.toString() + ". " + priceAsString();
    }
}
