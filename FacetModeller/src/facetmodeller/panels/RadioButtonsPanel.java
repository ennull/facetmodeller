package facetmodeller.panels;

import facetmodeller.FacetModeller;
import fileio.FileUtils;
import fileio.SessionIO;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/** A panel containing several radio buttons that control what is painted.
 * @author Peter
 */
public final class RadioButtonsPanel extends JPanel implements SessionIO {
    private static final long serialVersionUID = 1L;
    
    private final FacetModeller controller;
    private final JRadioButton showImageButton ,showRegionsButton;
    private JRadioButton showOutlinesButton, showAllButton, showVOIButton, showFacesButton, 
            showNormalsButton, showNormalTailsButton, showNormalHeadsButton, nodeColorButton;
    
    public RadioButtonsPanel(FacetModeller con, int ndim) {
        
        // Set the controller:
        controller = con;
        
        // Create the show image radio buttion:
        MyActionListener actionListener = new MyActionListener();
        showImageButton = new JRadioButton("");
        showImageButton.setVerticalTextPosition(AbstractButton.CENTER);
        showImageButton.setHorizontalTextPosition(AbstractButton.LEFT);
        showImageButton.setText("Show image in 2D");
        showImageButton.setToolTipText("Show image for the current section in the 2D viewer?");
        showImageButton.addActionListener(actionListener);
        showImageButton.setSelected(true);
        
        // Create the show regions button:
        showRegionsButton = new JRadioButton("");
        showRegionsButton.setVerticalTextPosition(AbstractButton.CENTER);
        showRegionsButton.setHorizontalTextPosition(AbstractButton.LEFT);
        showRegionsButton.setText("Show regions");
        showRegionsButton.setToolTipText("Show the regions?");
        showRegionsButton.addActionListener(actionListener);
        showRegionsButton.setSelected(true);
        
        // Create the buttons required by the 3D window:
        if (ndim==3) {
    //        showOtherButton = new JRadioButton("");
    //        showOtherButton.setVerticalTextPosition(AbstractButton.CENTER);
    //        showOtherButton.setHorizontalTextPosition(AbstractButton.LEFT);
    //        showOtherButton.setText("Show overlays");
    //        showOtherButton.setToolTipText("Show overlays for other sections?");
    //        showOtherButton.addActionListener(actionListener);
    //        showOtherButton.setSelected(true);
            showOutlinesButton = new JRadioButton("");
            showOutlinesButton.setVerticalTextPosition(AbstractButton.CENTER);
            showOutlinesButton.setHorizontalTextPosition(AbstractButton.LEFT);
            showOutlinesButton.setText("Show sections in 3D");
            showOutlinesButton.setToolTipText("Show outlines of the selected other sections in the 3D viewer?");
            showOutlinesButton.addActionListener(actionListener);
            showOutlinesButton.setSelected(true);
            showAllButton = new JRadioButton("");
            showAllButton.setVerticalTextPosition(AbstractButton.CENTER);
            showAllButton.setHorizontalTextPosition(AbstractButton.LEFT);
            showAllButton.setText("Show all objects in 3D");
            showAllButton.setToolTipText("Show all sections in the 3D viewer or only those for the selected sections?");
            showAllButton.addActionListener(actionListener);
            showAllButton.setSelected(true);
            showVOIButton = new JRadioButton("");
            showVOIButton.setVerticalTextPosition(AbstractButton.CENTER);
            showVOIButton.setHorizontalTextPosition(AbstractButton.LEFT);
            showVOIButton.setText("Show VOI");
            showVOIButton.setToolTipText("Show the VOI in the 3D viewer?");
            showVOIButton.addActionListener(actionListener);
            showVOIButton.setSelected(true);
            showNormalsButton = new JRadioButton("");
            showNormalsButton.setVerticalTextPosition(AbstractButton.CENTER);
            showNormalsButton.setHorizontalTextPosition(AbstractButton.LEFT);
            showNormalsButton.setText("Show facet normals in 3D");
            showNormalsButton.setToolTipText("Draw lines representing facet normals in the 3D viewer?");
            showNormalsButton.addActionListener(actionListener);
            showNormalsButton.setSelected(false);
            showNormalTailsButton = new JRadioButton("");
            showNormalTailsButton.setVerticalTextPosition(AbstractButton.CENTER);
            showNormalTailsButton.setHorizontalTextPosition(AbstractButton.LEFT);
            showNormalTailsButton.setText("Tails");
            showNormalTailsButton.setToolTipText("Draw circles representing facet normal tails in the 3D viewer?");
            showNormalTailsButton.addActionListener(actionListener);
            showNormalTailsButton.setSelected(false);
            showNormalTailsButton.setEnabled(false);
            showNormalHeadsButton = new JRadioButton("");
            showNormalHeadsButton.setVerticalTextPosition(AbstractButton.CENTER);
            showNormalHeadsButton.setHorizontalTextPosition(AbstractButton.LEFT);
            showNormalHeadsButton.setText("Heads");
            showNormalHeadsButton.setToolTipText("Draw circles representing facet normal heads in the 3D viewer?");
            showNormalHeadsButton.addActionListener(actionListener);
            showNormalHeadsButton.setSelected(false);
            showNormalHeadsButton.setEnabled(false);
            showFacesButton = new JRadioButton("");
            showFacesButton.setVerticalTextPosition(AbstractButton.CENTER);
            showFacesButton.setHorizontalTextPosition(AbstractButton.LEFT);
            showFacesButton.setText("Show faces in 3D");
            showFacesButton.setToolTipText("Draw facet faces and edges or just the edges in the 3D viewer?");
            showFacesButton.addActionListener(actionListener);
            showFacesButton.setSelected(true);
            nodeColorButton = new JRadioButton("");
            nodeColorButton.setVerticalTextPosition(AbstractButton.CENTER);
            nodeColorButton.setHorizontalTextPosition(AbstractButton.LEFT);
            nodeColorButton.setText("Colour nodes by section");
            nodeColorButton.setToolTipText("Colour the nodes by section instead of group in the 2D viewer?");
            nodeColorButton.addActionListener(actionListener);
            nodeColorButton.setSelected(false);
        }
        
        // Add the radio buttons to this panel:
        if (ndim==3) {
            setLayout(new GridLayout(9,1));
            add(showImageButton);
    //        add(showOtherButton);
            add(showOutlinesButton);
            add(showAllButton);
            add(showVOIButton);
            add(showFacesButton);
            add(showNormalsButton);
            JPanel showNormalArrows = new JPanel();
            showNormalArrows.setLayout(new GridLayout(1,3));
            showNormalArrows.add(new JLabel());
            showNormalArrows.add(showNormalTailsButton);
            showNormalArrows.add(showNormalHeadsButton);
            add(showNormalArrows);
            add(showRegionsButton);
            add(nodeColorButton);
        } else {
            setLayout(new GridLayout(2,1));
            add(showImageButton);
            add(showRegionsButton);
        }
        
    }

    /** Action listener for the buttons. */
    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object src = event.getSource();
            if (src==null) { return; }
//            if (src == showOtherButton) {
//                drawCurrentSection();
            if (src==showOutlinesButton) {
                controller.redraw3D();
            } else if (src == showAllButton) {
                controller.redraw3D();
            } else if (src == showImageButton) {
                controller.redraw2D();
            } else if (src == showVOIButton) {
                controller.redraw3D();
            } else if (src == showFacesButton) {
                controller.redraw3D();
            } else if (src == showNormalsButton) {
                boolean ok = showNormalsButton.isSelected();
                showNormalTailsButton.setEnabled(ok);
                showNormalHeadsButton.setEnabled(ok);
                controller.redraw3D();
            } else if (src == showNormalTailsButton) {
                if (showNormalTailsButton.isSelected()) {
                    showNormalHeadsButton.setSelected(false);
                }
                controller.redraw3D();
            } else if (src == showNormalHeadsButton) {
                if (showNormalHeadsButton.isSelected()) {
                    showNormalTailsButton.setSelected(false);
                }
                controller.redraw3D();
            } else if (src == nodeColorButton) {
                controller.redraw();
            } else if (src == showRegionsButton) {
                //if (showView3DPanel) { view3DPanel.repaint(); }
                controller.redraw();
            } else {
               // do nothing
            }
        }
    }
    
    public boolean getShowImage() {
        return showImageButton.isSelected();
    }
//    public boolean getShowOther() {
//        if (showOtherButton==null) {
//            return false;
//        } else {
//            return showOtherButton.isSelected();
//        }
//    }
    public boolean getShowOutlines() {
        if (showOutlinesButton==null) {
            return false;
        } else {
            return showOutlinesButton.isSelected();
        }
    }
    public boolean getShowAll() {
        if (showAllButton==null) {
            return false;
        } else {
            return showAllButton.isSelected();
        }
    }
    public boolean getShowVOI() {
        if (showVOIButton==null) {
            return false;
        } else {
            return showVOIButton.isSelected();
        }
    }
    public boolean getShowFaces() {
        if (showFacesButton==null) {
            return false;
        } else {
            return showFacesButton.isSelected();
        }
    }
    public boolean getShowNormals() {
        if (showNormalsButton==null) {
            return false;
        } else {
            return showNormalsButton.isSelected();
        }
    }
    public boolean getShowNormalTails() {
        if (getShowNormals()==false) { return false; }
        if (showNormalTailsButton==null) {
            return false;
        } else {
            return showNormalTailsButton.isSelected();
        }
    }
    public boolean getShowNormalHeads() {
        if (getShowNormals()==false) { return false; }
        if (showNormalHeadsButton==null) {
            return false;
        } else {
            return showNormalHeadsButton.isSelected();
        }
    }
    public boolean getShowRegions() {
        if (showRegionsButton==null) {
            return false;
        } else {
            return showRegionsButton.isSelected();
        }
    }
    public boolean getNodeColorBySection() {
        if (nodeColorButton==null) {
            return false;
        } else {
            return nodeColorButton.isSelected();
        }
    }
    
    // -------------------- SectionIO Methods --------------------
    
    @Override
    public boolean writeSessionInformation(BufferedWriter writer) {
        // Write booleans all on a single line:
        String textLine;
        if ( showOutlinesButton != null ) {
            textLine =
                    showImageButton.isSelected() + " " + 
                    showOutlinesButton.isSelected() + " " + 
                    showAllButton.isSelected() + " " + 
                    showVOIButton.isSelected() + " " + 
                    showFacesButton.isSelected() + " " + 
                    showRegionsButton.isSelected() + " " + 
                    nodeColorButton.isSelected() + " " + 
                    showNormalsButton.isSelected() + " " + //  8th boolean is a later addition, hence the if statement below in readSessionInformat
                    showNormalTailsButton.isSelected() + " " + //  9th boolean is a later addition, hence the if statement below in readSessionInformat
                    showNormalHeadsButton.isSelected() + " " ; // 10th boolean is a later addition, hence the if statement below in readSessionInformat
        } else {
            textLine =
                    showImageButton.isSelected() + " " +
                    true + " " +
                    true + " " +
                    true + " " +
                    true + " " + 
                    showRegionsButton.isSelected() + " " +
                    true + " " +
                    false + false + false;
        }
        return FileUtils.writeLine(writer,textLine);
    }
    
    @Override
    public String readSessionInformation(BufferedReader reader, boolean merge) {
        // Write booleans all from a single line:
        String textLine = FileUtils.readLine(reader);
        if (textLine==null) { return "Radio button booleans line."; }
        textLine = textLine.trim();
        String[] s = textLine.split("[ ]+");
        if (s.length<7) { return "Not enough radio button booleans."; }
        try {
            boolean is;
            // Read the booleans and set the radio button selections:
            is = Boolean.parseBoolean(s[0]); showImageButton.setSelected(is);
            if (showOutlinesButton!=null) {
                is = Boolean.parseBoolean(s[1]); showOutlinesButton.setSelected(is);
            }
            if (showAllButton!=null) {
                is = Boolean.parseBoolean(s[2]); showAllButton.setSelected(is);
            }
            if (showVOIButton!=null) {
                is = Boolean.parseBoolean(s[3]); showVOIButton.setSelected(is);
            }
            if (showFacesButton!=null) {
                is = Boolean.parseBoolean(s[4]); showFacesButton.setSelected(is);
            }
            if (showRegionsButton!=null) {
                is = Boolean.parseBoolean(s[5]); showRegionsButton.setSelected(is);
            }
            if (nodeColorButton!=null) {
                is = Boolean.parseBoolean(s[6]); nodeColorButton.setSelected(is);
            }
            boolean ok = false;
            if (showNormalsButton!=null) {
                if (s.length>7) {
                    is = Boolean.parseBoolean(s[7]); showNormalsButton.setSelected(is);
                } else {
                    showNormalsButton.setSelected(false);
                }
                ok = showNormalsButton.isSelected();
            }
            if (showNormalTailsButton!=null) {
                if (s.length>8) {
                    is = Boolean.parseBoolean(s[8]); showNormalTailsButton.setSelected(is);
                } else {
                    showNormalTailsButton.setSelected(false);
                }
                showNormalTailsButton.setEnabled(ok);
            }
            if (showNormalHeadsButton!=null) {
                if (s.length>9) {
                    is = Boolean.parseBoolean(s[9]); showNormalHeadsButton.setSelected(is);
                } else {
                    showNormalHeadsButton.setSelected(false);
                }
                showNormalHeadsButton.setEnabled(ok);
            }
        } catch (NumberFormatException e) { return "Parsing radio button booleans."; }
        // Return successfully:
        return null;
    }
    
}
