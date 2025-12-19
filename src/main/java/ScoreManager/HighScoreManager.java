package ScoreManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreManager {

    private ArrayList<PlayerScore> scores = new ArrayList<>();
    private final String SAVE_FILE = "scores.dat";

    public HighScoreManager() {
        try {
            // First load default scores from resources
            loadInitialScoresFromResources();

            // Then load and merge with saved scores (if they exist)
            loadSavedScores();

        } catch (ScoreFileException e) {
            System.out.println("High score loading issue: " + e.getMessage());
        }
    }

    public void addScore(PlayerScore ps) {
        scores.add(ps);
        scores.sort((a, b) -> b.getScore() - a.getScore());

        if (scores.size() > 10) {
            scores.subList(10, scores.size()).clear();
        }

        try {
            saveScores();
        } catch (ScoreFileException e) {
            System.out.println("Unable to save scores: " + e.getMessage());
        }
    }

    private void saveScores() throws ScoreFileException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(scores);
        } catch (IOException e) {
            throw new ScoreFileException("Saving failed", e);
        }
    }

    private void loadSavedScores() throws ScoreFileException {
        File f = new File(SAVE_FILE);
        if (!f.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            scores = (ArrayList<PlayerScore>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ScoreFileException("Loading saved scores failed", e);
        }
    }

    private void loadInitialScoresFromResources() throws ScoreFileException {
        try (InputStream is = getClass().getResourceAsStream("scores.dat")) {
            if (is == null) return;

            // Properly close ObjectInputStream
            try (ObjectInputStream ois = new ObjectInputStream(is)) {
                scores = (ArrayList<PlayerScore>) ois.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new ScoreFileException("Loading resource file failed", e);
        }
    }

    public List<PlayerScore> getScores() {
        return new ArrayList<>(scores); // Return a copy to prevent external modification
    }
}