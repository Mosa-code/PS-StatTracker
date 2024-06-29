import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TrackerWindow extends JFrame {

    private JTextArea textArea;

    public TrackerWindow() {
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tracker Window");

        // Create a panel for the components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a button for file selection
        JButton button = new JButton("Select Replay File");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    parseFile(selectedFile.getAbsolutePath());
                }
            }
        });

        // Create a text area for displaying the parsed kills
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Add the components to the panel
        panel.add(button, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Add the panel to the frame
        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    private void parseFile(String fileName) {
        // Parse the replay file
        ParseReplay parseReplay = new ParseReplay(fileName);

        // Display the parsed kills in the text area
        textArea.setText("");
        for (String pokemon : parseReplay.getKills().keySet()) {
            textArea.append(pokemon + ": " + parseReplay.getKills().get(pokemon) + " kills\n");
        }
    }
}
