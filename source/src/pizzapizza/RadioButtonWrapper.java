package pizzapizza;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 * Project : A10
 * File    : RadioButtonWrapper.java
 * Authors : Brian Blake
 *           Ken Bonar
 *           Kyle Eastman
 *           Jacob Packer
 * Date    : (12/11/14)
 *
 * Description : This class wraps RadioButtons that are
 *               used by the GUI for pizza size and Sizes
 *               from the Enum used by the rest of the
 *               program.
 */
public class RadioButtonWrapper
{
    public static final RadioButtonWrapper small  = new RadioButtonWrapper(Size.SMALL , "Small");
    public static final RadioButtonWrapper medium = new RadioButtonWrapper(Size.MEDIUM, "Medium");
    public static final RadioButtonWrapper large  = new RadioButtonWrapper(Size.LARGE , "Large");

    public static final RadioButtonWrapper[] allRadioButtons = {small, medium, large};

    public final Size size;
    public final JRadioButton radio;

    /**
     * Wraps a RadioButton with a Size.
     * @param size The enum.
     * @param string The name of the RadioButton.
     */
    private RadioButtonWrapper(Size size, String string)
    {
		    this.size = size;
		    radio = new JRadioButton(string);
    }

    /**
     * The group makes the buttons mutually exclusive.
     * If one is selected the other two can't be.
     */
    static
    {
        ButtonGroup group = new ButtonGroup();
        group.add(small.radio);
        group.add(medium.radio);
        group.add(large.radio);
    }
}
