/** GenoMetric Space Explorer (GeMSE) Copyright (C) 2017 Vahid Jalili
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package GeMSE.IO;

import GeMSE.GlobalVariables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Vahid Jalili
 *
 * Serializes and de-serializes GeMSE session.
 */
public class SessionIO
{
    private static FileNameExtensionFilter _sessionExtension = new FileNameExtensionFilter("GeMSE Session", "gs");

    public static void Serialize(JFrame parent, Boolean exitUponSuccess)
    {
        JFileChooser chooser = new JFileChooser(GlobalVariables.GetLastBrowsedDirectory());
        chooser.setDialogTitle("Specify a session file");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Save session");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(_sessionExtension);
        if (JFileChooser.APPROVE_OPTION != chooser.showOpenDialog(parent))
            return;

        GlobalVariables.SetLastBrowsedDirectoryFromFile(chooser.getSelectedFile().getAbsolutePath());

        InProgress inProgress = new InProgress(parent, "Saving the session, please wait ...");
        inProgress.setLocationRelativeTo(parent);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground()
            {
                try (FileOutputStream fileOut = new FileOutputStream(
                        chooser.getSelectedFile()
                        + "." + _sessionExtension.getExtensions()[0], false) ;
                     ObjectOutputStream out = new ObjectOutputStream(fileOut))
                {
                    GlobalVariables gv = new GlobalVariables();
                    out.writeObject(gv);
                    inProgress.dispose();

                    if (exitUponSuccess)
                        System.exit(0);
                    else
                        JOptionPane.showMessageDialog(parent, "GeMSE session is successfully saved.");
                }
                catch (IOException e)
                {
                    inProgress.dispose();
                    JOptionPane.showMessageDialog(
                            parent,
                            "A problem occured when saving the session.     "
                            + "\nError message: \n" + e,
                            "Session save error.",
                            JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done()
            {

            }
        };
        worker.execute();
        inProgress.setVisible(true);
    }
    public static void Deserialize(JFrame parent) throws Exception
    {
        JFileChooser chooser = new JFileChooser(GlobalVariables.GetLastBrowsedDirectory());
        chooser.setDialogTitle("Choose a session file");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Load session");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(_sessionExtension);
        if (JFileChooser.APPROVE_OPTION != chooser.showOpenDialog(parent))
            return;

        GlobalVariables.SetLastBrowsedDirectoryFromFile(chooser.getSelectedFile().getAbsolutePath());

        InProgress inProgress = new InProgress(parent, "Loading the session, please wait ...");
        inProgress.setLocationRelativeTo(parent);

        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground()
            {
                GlobalVariables gv = null;
                try (FileInputStream fileIn = new FileInputStream(chooser.getSelectedFile()) ;
                     ObjectInputStream in = new ObjectInputStream(fileIn))
                {
                    gv = (GlobalVariables) in.readObject();
                    inProgress.dispose();
                }
                catch (InvalidClassException e)
                {
                    inProgress.dispose();
                    JOptionPane.showMessageDialog(
                            parent,
                            "An error occured when loading the session.\n"
                            + "\nThe specified file contains a session which is created by a different version of GeMSE.     \n"
                            + "Therefore, the session in this file can not be loaded into this version of GeMSE.",
                            "Session load error",
                            JOptionPane.ERROR_MESSAGE);
                }
                catch (FileNotFoundException e)
                {
                    inProgress.dispose();
                    JOptionPane.showMessageDialog(
                            parent,
                            "An error occured when loading the session.\n"
                            + "\nGeMSE can not find/open the specified file.     \n",
                            "Session load error",
                            JOptionPane.ERROR_MESSAGE);
                }
                catch (IOException e)
                {
                    inProgress.dispose();
                    JOptionPane.showMessageDialog(
                            parent,
                            "An error occured when loading the session.\n"
                            + "\nGeMSE can not find/open the specified file.     \n",
                            "Session load error",
                            JOptionPane.ERROR_MESSAGE);
                }
                catch (ClassNotFoundException e)
                {
                    inProgress.dispose();
                    JOptionPane.showMessageDialog(
                            parent,
                            "An unknown error occured when loading the session.\n"
                            + "\nThe error message is:     \n" + e,
                            "Session load error",
                            JOptionPane.ERROR_MESSAGE);
                }

                return null;
            }

            @Override
            protected void done()
            {

            }
        };
        worker.execute();
        inProgress.setVisible(true);
    }
}
