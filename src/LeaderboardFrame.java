import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class LeaderboardFrame extends JFrame {

    public LeaderboardFrame(Map<String, Integer> scores) {
        setTitle("Leaderboard");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Leaderboard");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Sort scores by value descending
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(scores.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : sorted) {
            JLabel label = new JLabel(entry.getKey() + ": " + entry.getValue());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        JScrollPane scroll = new JScrollPane(panel);
        add(scroll);
    }
}
