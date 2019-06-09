package SmartPhone;

import java.io.IOException;

/**
 * 
 * <b>Launcher is the class that launch the Smartphone programm .</b>
 * <p>
 * Launcher is caracterised by this information :
 * <ul>
 * <li>A Smartphone to launch.</li>
 * </ul>
 * </p>
 * 
 * 
 * @author Ludovic Sahraoui, Alexandre Piguet
 * @version 2.0
 */
public class Launcher
{
    public static void main(String[] args) throws IOException
    {
        new Smartphone();
    }
}