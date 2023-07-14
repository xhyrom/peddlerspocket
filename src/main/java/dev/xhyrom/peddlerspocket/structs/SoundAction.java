package dev.xhyrom.peddlerspocket.structs;

import org.bukkit.entity.Player;

public class SoundAction implements Action {
    private final String sound;
    private final float volume;
    private final float pitch;

    public SoundAction(String sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void execute(Player player, double result) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
