package pizzapizza;

/**
 * Project : A10
 * File    : Size.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : This is an Enum for the pizza sizes.
 *               Each one has a price associated with it.
 */
public enum Size
{
    SMALL(5),
    MEDIUM(7),
    LARGE(9);

    float price;

    /**
     * Creates the object.
     * @param price The price associated with this size.
     */
    Size(float price)
    {
        this.price = price;
    }

    /**
     * Getter for the price
     * @return price The price associated with this size.
     */
    public float getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
