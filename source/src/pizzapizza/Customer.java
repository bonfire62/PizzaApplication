package pizzapizza;

import java.io.Serializable;

/**
 * Project : A10
 * File    : Customer.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : Objects of type Customer have a phone
 *               number that is assumed to never change.
 *               Each object also has a name, an address,
 *               and instructions associated with it.
 */
public class Customer implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final long phone;
    private String name;
    private String address;
    private String instructions;

    /**
     * When the object is created it must have a
     * phone number associated with it.
     * @param phone The phone number.
     */
    public Customer(long phone)
    {
        this.phone = phone;
    }

    /**
     * Convenience constructor that initializes all fields.
     * @param phone The phone number.
     * @param name The customer's name.
     * @param address The customer's address.
     * @param instructions Instructions associated with this customer.
     */
    public Customer(long phone, String name, String address, String instructions)
    {
        this(phone);
        this.name = name;
        this.address = address;
        this.instructions = instructions;
    }
    
    public long getPhone()
    {
        return phone;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getInstructions()
    {
        return instructions;
    }
    
    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }
}
