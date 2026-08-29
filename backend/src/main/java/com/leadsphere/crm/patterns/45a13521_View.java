package com.leadsphere.crm.patterns;

import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class View {
  private final PresentationModel model;

  private TextField txtTitle;

  private TextField txtArtist;

  private JCheckBox chkClassical;

  private TextField txtComposer;

  private JList<String> albumList;

  private JButton apply;

  private JButton cancel;

  static final int WIDTH_TXT = 200;

  static final int HEIGHT_TXT = 50;

  static final int LOCATION_X = 200;

  static final int LOCATION_Y = 200;
  static final int WIDTH = 500;
  static final int HEIGHT = 300;

  public View() {
    model = new PresentationModel(PresentationModel.albumDataSet());
  }

  public void saveToMod() {
    LOGGER.info("Save data to PresentationModel");
    model.setArtist(txtArtist.getText());
    model.setTitle(txtTitle.getText());
    model.setIsClassical(chkClassical.isSelected());
    model.setComposer(txtComposer.getText());
  }

  public void loadFromMod() {
    LOGGER.info("Load data from PresentationModel");
    txtArtist.setText(model.getArtist());
    txtTitle.setText(model.getTitle());
    chkClassical.setSelected(model.getIsClassical());
    txtComposer.setEditable(model.getIsClassical());
    txtComposer.setText(model.getComposer());
  }

  public void createView() {
    var frame = new JFrame("Album");
    var b1 = Box.createHorizontalBox();

    frame.add(b1);
    albumList = new JList<>(model.getAlbumList());
    albumList.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(final MouseEvent e) {
            model.setSelectedAlbumNumber(albumList.getSelectedIndex() + 1);
            loadFromMod();
          }
        });
    b1.add(albumList);

    var b2 = Box.createVerticalBox();
    b1.add(b2);

    txtArtist = new TextField();
    txtTitle = new TextField();

    txtArtist.setSize(WIDTH_TXT, HEIGHT_TXT);
    txtTitle.setSize(WIDTH_TXT, HEIGHT_TXT);

    chkClassical = new JCheckBox();
    txtComposer = new TextField();
    chkClassical.addActionListener(
        itemEvent -> {
          txtComposer.setEditable(chkClassical.isSelected());
          if (!chkClassical.isSelected()) {
            txtComposer.setText("");
          }
        });
    txtComposer.setSize(WIDTH_TXT, HEIGHT_TXT);
    txtComposer.setEditable(model.getIsClassical());

    apply = new JButton("Apply");
    apply.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(final MouseEvent e) {
            saveToMod();
            loadFromMod();
          }
        });
    cancel = new JButton("Cancel");
    cancel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(final MouseEvent e) {
            loadFromMod();
          }
        });

    b2.add(txtArtist);
    b2.add(txtTitle);

    b2.add(chkClassical);
    b2.add(txtComposer);

    b2.add(apply);
    b2.add(cancel);

    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setBounds(LOCATION_X, LOCATION_Y, WIDTH, HEIGHT);
    frame.setVisible(true);
  }
}
