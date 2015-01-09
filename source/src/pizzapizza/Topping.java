package pizzapizza;

/**
 * Project : A10
 * File    : Topping.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : This is an Enum for the pizza Toppings.
 */
public enum Topping
{
    ANCHOVIES,
    PEPPERONI,
    SAUSAGE,
    ITALIAN_SAUSAGE,
    BLACK_OLIVES,
    MUSHROOMS,
    ONIONS,
    ROASTED_PEPPERS;

    @Override
    public String toString()
    {
        return name().toLowerCase().replace("_", " ");
    }
}
