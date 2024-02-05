package UI;
import javax.swing.*;
class ResponseStreamer implements Runnable {
    private String response;
    private JTextArea chatArea;

    public ResponseStreamer(String response, JTextArea chatArea) {
        this.response = response;
        this.chatArea = chatArea;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> chatArea.append("Mario:\n\n"));

        for (int i = 0; i < response.length(); i++) {
            char c = response.charAt(i);
            // Use SwingUtilities.invokeLater to update the GUI
            SwingUtilities.invokeLater(() -> chatArea.append(Character.toString(c)));
            try {
                Thread.sleep(10); // 200 ms delay between characters
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Append guards after response is fully streamed
        SwingUtilities.invokeLater(() -> chatArea.append("\n_____________________________________________________________\n"));
    }
}