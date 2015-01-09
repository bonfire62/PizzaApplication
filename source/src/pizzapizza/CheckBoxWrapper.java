package pizzapizza;

import javax.swing.JCheckBox;

/**
 * Project : A10
 * File    : CheckBoxWrapper.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : This class wraps CheckBoxes that are
 *               used by the GUI for pizza toppings and
 *               Toppings from the Enum used by the rest
 *               of the program.
 */
public final class CheckBoxWrapper
{
    public static final CheckBoxWrapper anchovies      = new CheckBoxWrapper(Topping.ANCHOVIES      , "Anchovies");
    public static final CheckBoxWrapper pepperoni      = new CheckBoxWrapper(Topping.PEPPERONI      , "Pepperoni");
    public static final CheckBoxWrapper sausage        = new CheckBoxWrapper(Topping.SAUSAGE        , "Sausage");
    public static final CheckBoxWrapper italianSausage = new CheckBoxWrapper(Topping.ITALIAN_SAUSAGE, "Italian Sausage");

    public static final CheckBoxWrapper blackOlives    = new CheckBoxWrapper(Topping.BLACK_OLIVES  , "Black Olives");
    public static final CheckBoxWrapper mushrooms      = new CheckBoxWrapper(Topping.MUSHROOMS     , "Mushrooms");
    public static final CheckBoxWrapper onions         = new CheckBoxWrapper(Topping.ONIONS        , "Onions");
    public static final CheckBoxWrapper roastedPeppers = new CheckBoxWrapper(Topping.ROASTED_PEPPERS, "Roasted Peppers");

    public static final CheckBoxWrapper[] all = {anchovies, pepperoni, sausage, italianSausage,
                                                 blackOlives, mushrooms, onions, roastedPeppers};
    
    public final JCheckBox checkBox;
    public final Topping topping;

    /**
     * Wraps a CheckBox with a Topping.
     * @param topping The enum.
     * @param name The name of the CheckBox.
     */
    private CheckBoxWrapper(Topping topping, String name)
    {
        this.topping = topping;
        checkBox = new JCheckBox(name);
    }
}
