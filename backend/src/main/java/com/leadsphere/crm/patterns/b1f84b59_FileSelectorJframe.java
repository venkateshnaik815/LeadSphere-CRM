package com.leadsphere.crm.patterns;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileSelectorJframe extends JFrame implements FileSelectorView, ActionListener {

  @Serial private static final long serialVersionUID = 1L;

  private final JButton ok;

  private final JButton cancel;

  private final JTextField input;

  private final JTextArea area;

  private FileSelectorPresenter presenter;

  private String fileName;

  public FileSelectorJframe() {
    super("File Loader");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setBounds(100, 100, 500, 200);

    var panel = new JPanel();
    panel.setLayout(null);
    this.add(panel);
    panel.setBounds(0, 0, 500, 200);
    panel.setBackground(Color.LIGHT_GRAY);

    var info = new JLabel("File Name :");
    panel.add(info);
    info.setBounds(30, 10, 100, 30);

    var contents = new JLabel("File contents :");
    panel.add(contents);
    contents.setBounds(30, 100, 120, 30);

    this.input = new JTextField(100);
    panel.add(input);
    this.input.setBounds(150, 15, 200, 20);

    this.area = new JTextArea(100, 100);
    var pane = new JScrollPane(area);
    pane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
    panel.add(pane);
    this.area.setEditable(false);
    pane.setBounds(150, 100, 250, 80);

    this.ok = new JButton("OK");
    panel.add(ok);
    this.ok.setBounds(250, 50, 100, 25);
    this.ok.addActionListener(this);

    this.cancel = new JButton("Cancel");
    panel.add(this.cancel);
    this.cancel.setBounds(380, 50, 100, 25);
    this.cancel.addActionListener(this);

    this.presenter = null;
    this.fileName = null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.ok.equals(e.getSource())) {
      this.fileName = this.input.getText();
      presenter.fileNameChanged();
      presenter.confirmed();
    } else if (this.cancel.equals(e.getSource())) {
      presenter.cancelled();
    }
  }

  @Override
  public void open() {
    this.setVisible(true);
  }

  @Override
  public void close() {
    this.dispose();
  }

  @Override
  public boolean isOpened() {
    return this.isVisible();
  }

  @Override
  public void setPresenter(FileSelectorPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public FileSelectorPresenter getPresenter() {
    return this.presenter;
  }

  @Override
  public void setFileName(String name) {
    this.fileName = name;
  }

  @Override
  public String getFileName() {
    return this.fileName;
  }

  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  @Override
  public void displayData(String data) {
    this.area.setText(data);
  }
}
