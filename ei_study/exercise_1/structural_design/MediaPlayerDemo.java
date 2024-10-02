// *** STRUCTURAL DESIGN PATTERN USE CASE 1: Adapter Pattern ***
// *** Use Case: Media Player Compatibility ***
//
// This implementation demonstrates the **Adapter Pattern**, where the media player supports both MP3 and MP4 file formats through an adapter.
//
// 1. The **AudioPlayer** class is the client that supports MP3 by default. When an MP4 file is played, the **MediaAdapter** bridges the gap.
// 2. The **Mp4Player** acts as the adaptee, which provides the functionality to play MP4 files.
// 3. **Defensive programming** ensures that only supported media types are played, and throws exceptions for unsupported types.
// 4. **Logging** is used to track the file being played.
//
// The Adapter pattern is useful in cases where two incompatible interfaces need to work together, like supporting multiple file formats in a media player.

import java.util.logging.Logger;

interface MediaPlayer {
    void play(String audioType, String fileName);
}

class Mp4Player {
    private static final Logger logger = Logger.getLogger(Mp4Player.class.getName());

    public void playMp4(String fileName) {
        logger.info("Playing MP4 file. Name: " + fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    private Mp4Player mp4Player;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("MP4")) {
            mp4Player = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("MP4")) {
            mp4Player.playMp4(fileName);
        } else {
            throw new IllegalArgumentException("Invalid media type: " + audioType);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    private static final Logger logger = Logger.getLogger(AudioPlayer.class.getName());
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("MP3")) {
            logger.info("Playing MP3 file. Name: " + fileName);
        } else if (audioType.equalsIgnoreCase("MP4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            throw new IllegalArgumentException("Invalid media type");
        }
    }
}

public class MediaPlayerDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("MP3", "song.mp3");
        audioPlayer.play("MP4", "video.mp4");
    }
}
