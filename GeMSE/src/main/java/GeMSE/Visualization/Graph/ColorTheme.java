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
package GeMSE.Visualization.Graph;

import java.awt.Color;
import prefuse.util.ColorLib;

/**
 *
 * @author Vahid Jalili
 */
public class ColorTheme
{
    public static class BrightTheme
    {
        public static Color background = Color.white;
        public static Color foreground = Color.black;
        public static int edgeColor = ColorLib.rgb(90, 90, 90);
        public static int textColor = ColorLib.rgb(0, 10, 10);
        public static int nodeColor = ColorLib.rgb(215, 215, 215);
        public static int textColorHover = ColorLib.rgb(200, 230, 230);
        public static int nodeColorHover = ColorLib.rgb(0, 10, 10);
        public static int nodeColorSelected = ColorLib.rgb(233, 255, 0);
        public static int nodeColorSearchResult = ColorLib.rgb(0, 255, 255);
        public static int nodeAggHoverStroke = ColorLib.rgb(255, 0, 0);
        public static int nodeAggDefaultStroke = ColorLib.rgb(0, 255, 0);
        public static int aggHoverStroke = ColorLib.rgb(255, 0, 120);
        public static int aggDefaultStroke = ColorLib.rgb(0, 10, 10);
    }

    public static class DarkTheme
    {
        public static Color background = Color.black;
        public static Color foreground = Color.yellow;
        public static int edgeColor  = ColorLib.rgb(40, 117, 254);
        public static int textColor  = ColorLib.rgb(200, 255, 255);
        public static int nodeColor  = ColorLib.rgb(0, 0, 20);
        public static int textColorHover  = ColorLib.rgb(255, 165, 255);
        public static int nodeColorHover  = ColorLib.rgb(30, 0, 60);
        public static int nodeColorSelected  = ColorLib.rgb(7, 2, 30);
        public static int nodeColorSearchResult  = ColorLib.rgb(80, 80, 0);
        public static int nodeAggHoverStroke  = ColorLib.rgb(255, 0, 0);
        public static int nodeAggDefaultStroke  = ColorLib.rgb(0, 255, 0);
        public static int aggHoverStroke  = ColorLib.rgb(255, 244, 0);
        public static int aggDefaultStroke  = ColorLib.rgb(60, 70, 150);
    }
}
