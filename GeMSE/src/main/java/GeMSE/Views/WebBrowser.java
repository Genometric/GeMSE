/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeMSE.Views;

import java.net.URL;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Vahid Jalili
 */
public class WebBrowser extends JFXPanel
{
    public WebBrowser(URL url)
    {
        Platform.runLater(() ->
        {
            WebView view = new WebView();
            engine = view.getEngine();
            setScene(new Scene(view));
            engine.load(url.toExternalForm().replace("file:", "file:///"));
        });
    }

    private WebEngine engine;
}
