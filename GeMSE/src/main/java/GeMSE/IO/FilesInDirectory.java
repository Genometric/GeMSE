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

import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Vahid Jalili
 */
public class FilesInDirectory
{
    public static File[] Get(File folder, FileFilter fileFilter)
    {
        ArrayList<File> files = ReadFiles(folder, fileFilter, 3);
        File[] rtv = new File[files.size()];
        files.toArray(rtv);
        return rtv;
    }

    private static ArrayList<File> ReadFiles(File folder, FileFilter fileFilter, int maximumSearchDepth)
    {
        ArrayList<File> rtv = new ArrayList<>();

        File[] files = folder.listFiles();
        if (files == null) return rtv;

        for (File f : files)
        {
            if (f.isFile() && fileFilter.accept(f))
                rtv.add(f);
            else if (f.isDirectory() && --maximumSearchDepth >= 0)
                rtv.addAll(ReadFiles(f, fileFilter, maximumSearchDepth));
        }

        return rtv;
    }
}
