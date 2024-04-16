import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class AwtUnitConversion extends Frame implements KeyListener {
    private Frame frame;
    private TextField valueTextField;
    private Choice unitComboBox;
    private TextArea resultTextArea;
    private Canvas imageCanvas;
    private Label headerLabel;
    private Map<String, Image> unitImages;

    public AwtUnitConversion() {
        frame = new Frame("Unit Conversion");
        frame.setSize(900, 900);
        frame.setLayout(null);

        unitImages = new HashMap<>();
        loadImages();

        headerLabel = new Label(" BHARSHID MEASURE MAGICIANS");
        headerLabel.setBounds(550, 50, 1200, 100);
        headerLabel.setFont(new Font( "Arial",Font.BOLD, 30));
        frame.add(headerLabel);

        imageCanvas = new Canvas();
        imageCanvas.setBounds(1100, 250, 950, 280);
        frame.add(imageCanvas);

        Label valueLabel = new Label("Enter a value:");
        valueLabel.setBounds(550, 150, 100, 30);
        frame.add(valueLabel);

        valueTextField = new TextField();
        valueTextField.setBounds(650, 150, 200, 50);
        frame.add(valueTextField);

        Label unitLabel = new Label("Choose the unit:");
        unitLabel.setBounds(550, 160, 100, 150);
        frame.add(unitLabel);

        unitComboBox = new Choice();
        unitComboBox.setBounds(650, 230, 200, 50);
        String[] units = {"Centimeter", "Kilometer", "Meter", "Millimeter", "Gram", "Kilogram", "Liter"};
        for (String unit : units) {
            unitComboBox.add(unit);
        }
        frame.add(unitComboBox);

        unitComboBox.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                String selectedUnit = unitComboBox.getSelectedItem();
                displayUnitInformation(selectedUnit);
            }
        });

        Button convertButton = new Button("Convert");
        convertButton.setBounds(600, 320, 150, 50);
        frame.add(convertButton);

        resultTextArea = new TextArea();
        resultTextArea.setBounds(550, 400, 450, 170);
        resultTextArea.setBackground(Color.WHITE);
        resultTextArea.setFont(new Font("Times New Roman", Font.BOLD, 24));
        frame.add(resultTextArea);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        frame.addKeyListener(this);
        frame.setFocusable(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void loadImages() {
        try {
            unitImages.put("Centimeter", ImageIO.read(new File("C:/Users/Lenovo/cm.png")));
            unitImages.put("Kilometer", ImageIO.read(new File("C:/Users/Lenovo/meter.jpg")));
            unitImages.put("Meter", ImageIO.read(new File("C:/Users/Lenovo/met.png")));
            unitImages.put("Millimeter", ImageIO.read(new File("C:/Users/Lenovo/mm.png")));
            unitImages.put("Gram", ImageIO.read(new File("C:/Users/Lenovo/gra.png")));
            unitImages.put("Kilogram", ImageIO.read(new File("C:/Users/Lenovo/kilog.png")));
            unitImages.put("Liter", ImageIO.read(new File("C:/Users/Lenovo/l.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void performConversion() {
        try {
            double inputValue = Double.parseDouble(valueTextField.getText());
            int choice = unitComboBox.getSelectedIndex() + 1;

            String selectedUnit = unitComboBox.getSelectedItem();
            Image selectedImage = unitImages.get(selectedUnit);
            if (selectedImage != null) {
                Graphics g = imageCanvas.getGraphics();
                g.clearRect(0, 0, imageCanvas.getWidth(), imageCanvas.getHeight());
                g.drawImage(selectedImage, 0, 0, imageCanvas);
            }


            switch (selectedUnit) {
                case "Meter":
                    resultTextArea.setBackground(Color.PINK);
                    break;
                case "Gram":
                    resultTextArea.setBackground(Color.GREEN);
                    break;
                case "Liter":
                    resultTextArea.setBackground(Color.CYAN);
                    break;
                case "Centimeter":
                    resultTextArea.setBackground(Color.YELLOW);
                    break;
                case "Millimeter":
                    resultTextArea.setBackground(Color.CYAN);
                    break;
                case "Kilometer":
                    resultTextArea.setBackground(Color.ORANGE);
                    break;
                case "Kilogram":
                    resultTextArea.setBackground(Color.BLUE);
                    break;
                default:
                    resultTextArea.setBackground(Color.MAGENTA);
            }

            switch (choice) {
                case 1:
                    convertCentimeter(inputValue);
                    break;
                case 2:
                    convertKilometer(inputValue);
                    break;
                case 3:
                    convertMeter(inputValue);
                    break;
                case 4:
                    convertMillimeter(inputValue);
                    break;
                case 5:
                    convertGram(inputValue);
                    break;
                case 6:
                    convertKilogram(inputValue);
                    break;
                case 7:
                    convertLiter(inputValue);
                    break;
                default:
                    resultTextArea.setText("Invalid choice");
            }

        } catch (NumberFormatException ex) {
            resultTextArea.setText("Invalid input. Please enter a numeric value.");
        }
    }

    private void convertCentimeter(double value) {
        resultTextArea.setText(value + " centimeters is equal to:\n"
                + value / 100 + " meters\n"
                + value / 1000 + " kilometers\n"
                + value * 10 + " millimeters");
    }

    private void convertKilometer(double value) {
        resultTextArea.setText(value + " kilometers is equal to:\n"
                + value * 1000 + " meters\n"
                + value * 100000 + " centimeters\n"
                + value * 1e+6 + " millimeters");
    }

    private void convertMeter(double value) {
        resultTextArea.setText(value + " meters is equal to:\n"
                + value * 100 + " centimeters\n"
                + value / 1000 + " kilometers\n"
                + value * 1000 + " millimeters");
    }

    private void convertMillimeter(double value) {
        resultTextArea.setText(value + " millimeters is equal to:\n"
                + value / 10 + " centimeters\n"
                + value / 1000 + " meters\n"
                + value / 1e+6 + " kilometers");
    }

    private void convertGram(double value) {
        resultTextArea.setText(value + " grams is equal to:\n"
                + value / 1000 + " kilograms");
    }

    private void convertKilogram(double value) {
        resultTextArea.setText(value + " kilograms is equal to:\n"
                + value * 1000 + " grams");
    }

    private void convertLiter(double value) {
        resultTextArea.setText(value + " liter");
    }

    private void displayUnitInformation(String unit) {
        
        headerLabel.setText("Value Entered is in " + unit);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        headerLabel.setText("Siddhi : 21 ,\n" + "Harshada : 37 ,\n" + "Bhoomi : 19");
    }

    public void keyReleased(KeyEvent e) {
        headerLabel.setText("BHARSHID MEASURE MAGICIANS");
    }

    public static void main(String[] args) {
        AwtUnitConversion awtUnitConversion = new AwtUnitConversion();
        awtUnitConversion.frame.setVisible(true);
    }
}