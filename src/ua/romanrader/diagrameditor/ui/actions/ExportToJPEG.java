package ua.romanrader.diagrameditor.ui.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ua.romanrader.diagrameditor.model.DataModel;
import ua.romanrader.diagrameditor.model.csv.DataSet;
import ua.romanrader.diagrameditor.ui.DiagramEditor;

/**
 * Действие экспорта в JPG-картинку
 * @author romanrader
 *
 */
public class ExportToJPEG implements ActionListener {
    /**
     * Главное окно
     */
    private DiagramEditor de;

    /**
     * Конструктор действия
     * @param tde главное окно
     */
    public ExportToJPEG(final DiagramEditor tde) {
        this.de = tde;
    }

    /**
     * Выполнение действия
     * @param e действие
     */
    public final void actionPerformed(final ActionEvent e) {
        DataSet ds = DataModel.getInstance().getCurrentDataSet();
        if (ds != null) {
            de.getStatusBar().setText("Saving dataset...");
            JFileChooser fc = new JFileChooser();

            int returnVal = fc.showSaveDialog(de);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Image img = de.getDiagramView().toImage();
                String path = fc.getSelectedFile().getPath();
                try {
                    if (!path.toLowerCase().endsWith(".jpg")) {
                        path += ".jpg";
                    }
                    ImageIO.write((RenderedImage) img, "jpg", new File(path));
                    de.getStatusBar().setText("Saved to JPG");
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(de, "Saving failed", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    de.getStatusBar().setText("I/O error");
                }
            }
        }
    }
}
